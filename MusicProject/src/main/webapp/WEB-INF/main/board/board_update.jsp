<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/top100_table.css" />
<script type="text/javascript" src="resources/smarteditor2/dist/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1 style="margin-bottom: 10px">Free Board 수정하기</h1>
	</center>
	<div id="content_top100" style="margin-top: 10px">
		<table width="735" id="table2">
			<tr>
				<th width="20%" style="border-radius: 15px;">제목</th>
				<td width="80%" colspan="3"
					style="text-align: left; padding-left: 10px"><input
					type="text" size="64" align="left"></td>
			</tr>
			<tr>
				<th width="20%" style="border-radius: 15px;">작성자</th>
				<td width="42%" style="text-align: left; padding-left: 10px"><input
					type="text" size="25" align="left"></td>
				<th width="23%" style="border-radius: 15px;">비밀번호</th>
				<td width="15%" style="text-align: left; padding-left: 10px"><input
					type="text" size="11" align="left"></td>
			</tr>
			<tr>
				<th colspan="4" style="border-radius: 15px;">본문내용</th>
			</tr>
			<tr>
				<td colspan="4" style="padding: 10px 0">
					<textarea name="content" id="content" style="width: 728px; height: 540px"></textarea>
				</td>
			</tr>
		</table>
		<table width="735" style="margin-top: 5px">
			<tr>
				<td align="center" valign="middle" style="padding: 10px 0 0 0">
					<input type="submit" value="수정완료" style="padding: 5px" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="수정취소" style="padding: 5px"
					onclick="javascript:history.back()" />
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