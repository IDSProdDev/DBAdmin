package dbadmin

import groovy.json.JsonSlurper
import groovyx.net.http.*
import org.springframework.dao.DataIntegrityViolationException

class POIController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [POIInstanceList: POI.list(params), POIInstanceTotal: POI.count()]
    }

    def create() {
        [POIInstance: new POI(params)]
    }

    def save() {
        def POIInstance = new POI(params)
        //what i added

        def url = "http://geocode.arcgis.com"
        def path = "/arcgis/rest/services/World/GeocodeServer/find"
        def query = [ text: POIInstance.address, f:"pjson" ]

        def response = postText(url, path, query)
        def slurper = new JsonSlurper()
        def result = slurper.parseText(response)
        POIInstance.x_coordinate = result.locations[0].feature.geometry.x
        POIInstance.y_coordinate = result.locations[0].feature.geometry.y
        POIInstance.address = result.locations[0].name
        POIInstance.JSON = response


        //what i didn't add
        if (!POIInstance.save(flush: true)) {
            render(view: "create", model: [POIInstance: POIInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'POI.label', default: 'POI'), POIInstance.id])
        redirect(action: "show", id: POIInstance.id)
    }

    def show(Long id) {
        def POIInstance = POI.get(id)
        if (!POIInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'POI.label', default: 'POI'), id])
            redirect(action: "list")
            return
        }

        [POIInstance: POIInstance]
    }

    def edit(Long id) {
        def POIInstance = POI.get(id)
        if (!POIInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'POI.label', default: 'POI'), id])
            redirect(action: "list")
            return
        }

        [POIInstance: POIInstance]
    }

    def update(Long id, Long version) {
        def POIInstance = POI.get(id)
        if (!POIInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'POI.label', default: 'POI'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (POIInstance.version > version) {
                POIInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'POI.label', default: 'POI')] as Object[],
                        "Another user has updated this POI while you were editing")
                render(view: "edit", model: [POIInstance: POIInstance])
                return
            }
        }

        POIInstance.properties = params

        if (!POIInstance.save(flush: true)) {
            render(view: "edit", model: [POIInstance: POIInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'POI.label', default: 'POI'), POIInstance.id])
        redirect(action: "show", id: POIInstance.id)
    }

    def delete(Long id) {
        def POIInstance = POI.get(id)
        if (!POIInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'POI.label', default: 'POI'), id])
            redirect(action: "list")
            return
        }

        try {
            POIInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'POI.label', default: 'POI'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'POI.label', default: 'POI'), id])
            redirect(action: "show", id: id)
        }
    }

    static def postText(String baseUrl, String path, query, method = Method.POST) {
        try {
            def ret = null
            def http = new HTTPBuilder(baseUrl)

            // perform a POST request, expecting TEXT response
            http.request(method, ContentType.TEXT) {
                uri.path = path
                uri.query = query
                headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

                // response handler for a success response code
                response.success = { resp, reader ->
                    ret = reader.getText()
                }
            }
            return ret

        } catch (groovyx.net.http.HttpResponseException ex) {
            ex.printStackTrace()
            return null
        } catch (java.net.ConnectException ex) {
            ex.printStackTrace()
            return null
        }
    }

    static def getText(String baseUrl, String path, query) {
        return postText(baseUrl, path, query, Method.GET)
    }
}


