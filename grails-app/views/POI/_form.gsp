<%@ page import="dbadmin.POI" %>



<div class="fieldcontain ${hasErrors(bean: POIInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="POI.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="address" required="" value="${POIInstance?.address}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: POIInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="POI.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${POIInstance?.type}"/>
</div>

