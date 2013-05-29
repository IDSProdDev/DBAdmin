package dbadmin



import org.junit.*
import grails.test.mixin.*

@TestFor(POIController)
@Mock(POI)
class POIControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/POI/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.POIInstanceList.size() == 0
        assert model.POIInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.POIInstance != null
    }

    void testSave() {
        controller.save()

        assert model.POIInstance != null
        assert view == '/POI/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/POI/show/1'
        assert controller.flash.message != null
        assert POI.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/POI/list'

        populateValidParams(params)
        def POI = new POI(params)

        assert POI.save() != null

        params.id = POI.id

        def model = controller.show()

        assert model.POIInstance == POI
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/POI/list'

        populateValidParams(params)
        def POI = new POI(params)

        assert POI.save() != null

        params.id = POI.id

        def model = controller.edit()

        assert model.POIInstance == POI
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/POI/list'

        response.reset()

        populateValidParams(params)
        def POI = new POI(params)

        assert POI.save() != null

        // test invalid parameters in update
        params.id = POI.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/POI/edit"
        assert model.POIInstance != null

        POI.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/POI/show/$POI.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        POI.clearErrors()

        populateValidParams(params)
        params.id = POI.id
        params.version = -1
        controller.update()

        assert view == "/POI/edit"
        assert model.POIInstance != null
        assert model.POIInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/POI/list'

        response.reset()

        populateValidParams(params)
        def POI = new POI(params)

        assert POI.save() != null
        assert POI.count() == 1

        params.id = POI.id

        controller.delete()

        assert POI.count() == 0
        assert POI.get(POI.id) == null
        assert response.redirectedUrl == '/POI/list'
    }
}
