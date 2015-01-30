<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>개발센터 웹어플리케이션</title>
<head>
	<title>직원관리</title>
	<%@ include file="/WEB-INF/views/common-header.jspf"%>
	<%@ include file="/WEB-INF/views/common-taglib.jspf"%>
	<script type="text/javascript">
		$(document).ajaxStart(function(){
			$("#wrapper").ajaxloader('show','tata-ajax-loader-img2');
		}).ajaxStop(function() {
			$("#wrapper").ajaxloader('hide','tata-ajax-loader-img2');
		});;
	</script>
</head>
<body>
<div id="wrapper" class="well">
	<div id="container">
		<div id="content" class="clear">
			<!-- left menu 시작 -->
<%-- 			<tiles:insertAttribute name="left" /> --%>
			<!-- left menu 끝 -->
			<div id="primaryContent" style="min-height: 738px">
				<!-- top menu 시작 --> 
				<tiles:insertAttribute name="header" /> 
				<!-- top menu 끝 -->
				
				<!-- START OF : 컨텐츠 영역 -->
				<!-- body 시작 -->
				<tiles:insertAttribute name="body" />
				<!-- body 끝 -->
				<!-- END OF : 컨텐츠 영역 -->
			</div>
		</div>
	</div>
	
	<!-- footer 시작 -->
	<tiles:insertAttribute name="footer" />
	<!-- footer 끝 -->

</div>
</body>
</html>