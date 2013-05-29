<%@ page import="dbadmin.POI" %>



<div class="fieldcontain ${hasErrors(bean: POIInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="POI.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="address" required="" value="${POIInstance?.address}"/>
</div>
