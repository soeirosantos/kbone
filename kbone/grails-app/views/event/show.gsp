
<%@ page import="br.com.esmbusiness.domain.Event" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-event" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-event" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list event">
			
				<g:if test="${eventInstance?.userId}">
				<li class="fieldcontain">
					<span id="userId-label" class="property-label"><g:message code="event.userId.label" default="User Id" /></span>
					
						<span class="property-value" aria-labelledby="userId-label"><g:fieldValue bean="${eventInstance}" field="userId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.tagId}">
				<li class="fieldcontain">
					<span id="tagId-label" class="property-label"><g:message code="event.tagId.label" default="Tag Id" /></span>
					
						<span class="property-value" aria-labelledby="tagId-label"><g:fieldValue bean="${eventInstance}" field="tagId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.kUnitId}">
				<li class="fieldcontain">
					<span id="kUnitId-label" class="property-label"><g:message code="event.kUnitId.label" default="KU nit Id" /></span>
					
						<span class="property-value" aria-labelledby="kUnitId-label"><g:fieldValue bean="${eventInstance}" field="kUnitId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.typeCode}">
				<li class="fieldcontain">
					<span id="typeCode-label" class="property-label"><g:message code="event.typeCode.label" default="Type Code" /></span>
					
						<span class="property-value" aria-labelledby="typeCode-label"><g:fieldValue bean="${eventInstance}" field="typeCode"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${eventInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="event.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${eventInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${eventInstance?.id}" />
					<g:link class="edit" action="edit" id="${eventInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
