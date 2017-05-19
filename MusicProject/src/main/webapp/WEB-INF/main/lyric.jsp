<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="#efcec5">
<center>
	<table border="0" width="400" style="overflow: scroll;">
		<tr>
			<td rowspan="4" width="20%">
				<img alt="" src="${vo.poster }" width="100" height="100">
			</td>
			<th align="left" valign="middle" style="padding-left: 5px">곡명</th>
		</tr>
		<tr>
			<td align="left" valign="middle" style="padding-left: 5px">${vo.title }</td>
		</tr>
		<tr>
			<th align="left" valign="middle" style="padding-left: 5px;">가수</th>
		</tr>
		<tr>			
			<td align="left" valign="middle" style="padding-left: 5px">${vo.artist }</td>
		</tr>
	</table>
	<div style="height : 460px; width:400px; overflow:auto; background-color: #fff; margin-top: 15px; vertical-align: middle;">
	${vo.lyrics }</div>
</center>
</body>
</html>