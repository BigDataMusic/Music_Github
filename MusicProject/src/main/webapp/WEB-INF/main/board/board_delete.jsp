<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"	href="resources/css/top100_table.css" />
<title>Insert title here</title>
<script type="text/javascript" src="resources/smarteditor2/dist/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
</head>
<body>
	<center>
		<h1 style="margin-top: 300px;margin-bottom: 30px">Free Board Delete</h1>
	<form method=post action="board_delete_ok.do">
     <table id="table2" width=300 style="margin-bottom: 30px">
       <tr>
          <th width=30% style="border-radius: 15px;">비밀번호</th>
          <td align=left width=70%>
             <input type=password name=pwd size=15>
             <input type=hidden name=no value="${no }">
          </td>
       </tr>
       <tr>
          <td colspan="2" align=center>
             <input type=submit value="삭제">
             <input type="button" value="취소"
            onclick="javascript:history.back()">
          </td>
       </tr>
     </table>
     </form>
     <div>
     	<p><font color="red" size="4pt">주의! 삭제된 내용은 복구되지 않습니다. <br>
     	삭제전 다시 생각해보시고 삭제하시기 바랍니다.</font> </p>
     </div>
	</center>
</body>
</html>