<%@ page import="br.com.esmbusiness.domain.KUnit"%>
<%@ page import="br.com.esmbusiness.domain.Tag" %>

<!doctype html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Knowledge Base</title>
	<g:javascript library="jquery" />
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'jqcloud.css')}" type="text/css">
	<script type="text/javascript" src="${resource(dir: 'js', file: 'jqcloud-1.0.0.min.js')}"></script>
	    <script type="text/javascript">
	      $(document).ready(function() {
	        $("#cloud").jQCloud(${allTagsJSON});
	      });
	    </script>

</head>
<body>
<div id="KBase" class="content" role="main">
	<h1>Knowledge Base</h1>

	<div style="padding-left: 30px;">
		<g:form action="new_study_session" >
			<g:textField name="name" required="" />
			<g:submitButton name="create" class="save" value="start a new study session" />
		</g:form>
	</div>
	<div id="cloud" style="width: 100%;height: 500px;"></div>
</div>
</body>
</html>