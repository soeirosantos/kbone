<%@ page import="br.com.esmbusiness.domain.KUnit"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'KUnit.label', default: 'KUnit')}" />
<title>Knowledge Base</title>

<link rel="stylesheet" href="${resource(dir: 'markitup', file: 'skins/markitup/style.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'markitup', file: 'sets/default/style.css')}" type="text/css">
<script type="text/javascript" src="${resource(dir: 'markitup', file: 'jquery.markitup.js')}"></script>
<script type="text/javascript" src="${resource(dir: 'markitup', file: 'sets/default/set.js')}"></script>

<g:javascript library="jquery" />

<script type="text/javascript">
<!--
jQuery(document).ready(function(){

	$("#editor").markItUp(mySettings);

   jQuery(document).bind("keydown", function(e){
		character = String.fromCharCode(e.which).toLowerCase()
		if(e.altKey && character == "w") {
			jQuery('#action_button').trigger('click');
		}
	});

   $("#editor").focus();
	
});

//-->
</script>
</head>
<body>
	<div id="KBase" class="content" role="main">
		<h1>Studying: ${tag}</h1>
		<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
		</g:if>
		<g:form>
			<g:hiddenField name="id" value="${tag.id }"/>
			<g:hiddenField name="unit.id" value="${kUnit?.id}"/>
			<div style="width:92%;margin-left:30px;">
				<textarea id="editor" name="content">${kUnit?.content}</textarea>
			</div>
			<g:actionSubmit style="margin-top:10px;margin-left:30px" id="action_button" value="${kUnit?.id != null ? 'Change' : 'Create'} (alt+w)" action="${kUnit?.id != null ? 'update_unit_of_knowledge' : 'insert_unit_of_knowledge'}" />
		</g:form>
		
			<div style="float:right;width:110px;margin-right:10px;">
			    <g:if test="${units.size()>3}">
				    <g:if test="${!reading_mode}">
						<g:link action="study_session_of"  id="${tag.id}" params="['reading_mode': 'true']">reading mode</g:link> 
						<div style="font-size: 11px">(revert order by date)</div>
					</g:if>
					<g:else>
						<g:link action="study_session_of"  id="${tag.id}" params="['reading_mode': 'false']">back to default</g:link>
					</g:else>
				</g:if>
			</div>
		
		<ul style="padding-left:30px;margin-top:10px;">
			<g:each in="${units}" status="i" var="unit_of_knowledge">
				<li style="list-style-type: none;margin:30px">
					<div onmouseover="$('div:last',this).show()" onmouseout="$('div:last',this).hide()">
						<div style="color: #C0C0C0;font-size: 12px;">
							<g:formatDate date="${unit_of_knowledge.dateCreated}" format="dd/MM/yy HH'h'mm" /> 
						    <g:if test="${unit_of_knowledge.creator}">
								- ${unit_of_knowledge.creator.username }
						    </g:if>
						</div>
						<div>
							${unit_of_knowledge.content }
						</div>
						<div style="display:none">
							<g:link action="study_session_of"  id="${tag.id}" params="['unit.id': unit_of_knowledge.id]">change</g:link>
							<g:link action="remove_unit_of_knowledge" id="${tag.id}" params="['unit.id': unit_of_knowledge.id]">delete</g:link>
						</div>
					</div>
				</li>
			</g:each>
		</ul>
	</div>
</body>
</html>