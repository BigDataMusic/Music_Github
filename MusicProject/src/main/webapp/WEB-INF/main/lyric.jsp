<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<table border="1" width="380" height="530">
			<tr height=15% > 
				<td rowspan="2" width=30%> 
				&nbsp;<img src="${vo.poster }" width="100" height="100">
				</td>
				<td>
					${vo.title }
				</td>
			</tr>
			<tr>
				<td height=9% >
				${vo.artist }
				</td>
			</tr>
			<tr> 
				<td colspan="2"> 
					${vo.lyrics } 
				</td>
			</tr>
		</table>
	
	</center>
</body>
</html>