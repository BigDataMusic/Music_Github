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

<script type="text/javascript" src="resources/smarteditor2/dist/js/service/HuskyEZCreator.js" charset="utf-8"></script>

</head>
<body ng-app="">
	<center>
		<h1 style="margin-bottom: 10px">Free Board Content</h1>
	</center>
	<div ng-init="data=${json }" id="content_top100" style="margin-top: 10px">
	<table id="table2" width="735">
      <tr>
       <th width="20%" style="border-radius: 15px;">번호</th>
       <td width="30%" align="center">{{data[0]}}</td>
       <th width="20%" style="border-radius: 15px;">작성일</th>
       <td width="30%" align="center">{{data[3]}}</td>
      </tr>
      <tr>
       <th width="20%" style="border-radius: 15px;">이름</th>
       <td width="30%" align="center">{{data[1]}}</td>
       <th width="20%" style="border-radius: 15px;">조회수</th>
       <td width="30%" align="center">{{data[4]}}</td>
      </tr>
      <tr>
       <th width="20%" style="border-radius: 15px;">제목</th>
       <td colspan="3" class="song">{{data[2]}}</td>
      </tr>
      <tr>
       <td colspan="4" style="border-radius: 15px;" align="left" valign="top" height="200">
       <textarea id="content" style="width: 728px;height: 580px;" >{{data[5]}}</textarea>
       </td>
      </tr>
     </table>
     <table width="735" style="margin-top: 5px">
			<tr>
				<td align="center" valign="middle" style="padding: 10px 0 0 0">
					<a href="board_update.do?no={{data[0]}}" style="text-decoration: none"><input type="button" value="수정" style="padding: 5px" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="board_delete.do?no={{data[0]}}" style="text-decoration: none"><input type="button" value="삭제" style="padding: 5px" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="board_reply.do?no={{data[0]}}" style="text-decoration: none"><input type="button" value="댓글" style="padding: 5px" /></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="목록" style="padding: 5px"
					onclick="javascript:location.href='board.do'" />
				</td>
			</tr>
		</table>
     </div>
     
     <script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator
				.createInIFrame({
					oAppRef : oEditors,
					elPlaceHolder : "content", //textarea에서 지정한 id와 일치해야 합니다.
					sSkinURI : "resources/smarteditor2/workspace/SmartEditor2Skin.html",
					fCreator : "createSEditor2"
				});
	</script>
</body>
</html>