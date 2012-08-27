<%@ page import="br.com.esmbusiness.domain.Event" %>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'userId', 'error')} required">
	<label for="userId">
		<g:message code="event.userId.label" default="User Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="userId" required="" value="${eventInstance.userId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'tagId', 'error')} required">
	<label for="tagId">
		<g:message code="event.tagId.label" default="Tag Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="tagId" required="" value="${eventInstance.tagId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'kUnitId', 'error')} ">
	<label for="kUnitId">
		<g:message code="event.kUnitId.label" default="KU nit Id" />
		
	</label>
	<g:field type="number" name="kUnitId" value="${eventInstance.kUnitId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'typeCode', 'error')} required">
	<label for="typeCode">
		<g:message code="event.typeCode.label" default="Type Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="typeCode" required="" value="${eventInstance.typeCode}"/>
</div>

