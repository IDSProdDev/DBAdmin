
<%@ page import="dbadmin.POI" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'POI.label', default: 'POI')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-POI" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-POI" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list POI">
			
				<g:if test="${POIInstance?.address}">
                <li class="fieldcontain">
                    <span id="address-label" class="property-label"><g:message code="POI.address.label" default="Address" /></span>

                    <span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${POIInstance}" field="address"/></span>

                </li>
            </g:if>

                <g:if test="${POIInstance?.type}">
                    <li class="fieldcontain">
                        <span id="type-label" class="property-label"><g:message code="POI.type.label" default="Type" /></span>

                        <span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${POIInstance}" field="type"/></span>

                    </li>
                </g:if>
			
				<g:if test="${POIInstance?.x_coordinate}">
				<li class="fieldcontain">
					<span id="x_coordinate-label" class="property-label"><g:message code="POI.x_coordinate.label" default="Xcoordinate" /></span>
					
						<span class="property-value" aria-labelledby="x_coordinate-label"><g:fieldValue bean="${POIInstance}" field="x_coordinate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${POIInstance?.y_coordinate}">
				<li class="fieldcontain">
					<span id="y_coordinate-label" class="property-label"><g:message code="POI.y_coordinate.label" default="Ycoordinate" /></span>
					
						<span class="property-value" aria-labelledby="y_coordinate-label"><g:fieldValue bean="${POIInstance}" field="y_coordinate"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${POIInstance?.JSON}">
				<li class="fieldcontain">
                        <span id="JSON-label" class="property-label"><g:message code="POI.JSON.label" default="JSON" /></span>
                    <pre>

                        <span class="property-value" aria-labelledby="JSON-label"><g:fieldValue bean="${POIInstance}" field="JSON"/></span>
					
				</li>
                </pre>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${POIInstance?.id}" />
					<g:link class="edit" action="edit" id="${POIInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
