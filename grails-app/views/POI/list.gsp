
<%@ page import="dbadmin.POI" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'POI.label', default: 'POI')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-POI" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-POI" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

                        <g:sortableColumn property="id" title="${message(code: 'POI.id.label', default: 'ID')}" />

                        <g:sortableColumn property="address" title="${message(code: 'POI.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="x_coordinate" title="${message(code: 'POI.x_coordinate.label', default: 'Xcoordinate')}" />
					
						<g:sortableColumn property="y_coordinate" title="${message(code: 'POI.y_coordinate.label', default: 'Ycoordinate')}" />
					

					</tr>
				</thead>
				<tbody>
				<g:each in="${POIInstanceList}" status="i" var="POIInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td><g:link action="show" id="${POIInstance.id}">${fieldValue(bean: POIInstance, field: "id")}</g:link></td>

                        <td>${fieldValue(bean: POIInstance, field: "address")}</td>
					
						<td>${fieldValue(bean: POIInstance, field: "x_coordinate")}</td>
					
						<td>${fieldValue(bean: POIInstance, field: "y_coordinate")}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${POIInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
