<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Paradise</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
</head>

<body>
<div>
	<tiles:insertAttribute name="header"/>
</div>
<div id="wrapper">
	<div>
		<tiles:insertAttribute name="nav"/>
	</div>
	<div>
		<tiles:insertAttribute name="body"/>
	</div>
</div>
<div>
	<tiles:insertAttribute name="footer"/>
</div>
</body>
</html>
