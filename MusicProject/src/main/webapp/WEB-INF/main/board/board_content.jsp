<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"	href="resources/css/top100_table.css" />
<title>Insert title here</title>
<!-- <script type="text/javascript" src="resources/smarteditor2/dist/js/service/HuskyEZCreator.js" charset="utf-8"></script> -->
</head>
<body>
	<center>
		<h1 style="margin-bottom: 10px">Free Board Content</h1>
	</center>
	<div id="content_top100" style="margin-top: 10px">
		<table width="735" id="table2">
			<tr>
				<th width="20%" style="border-radius: 15px;">제목</th>
				<td width="40%" style="text-align: left; padding-left: 10px">
					<label>제목이 들어갑니다.</label>
				</td>
				<th width="20%" style="border-radius: 15px;">작성일</th>
				<td width="20%" style="text-align: center; padding-left: 10px">
					<label>2017-05-09</label>
				</td>
			</tr>
			<tr>
				<th width="20%" style="border-radius: 15px;">작성자</th>
				<td width="30%" style="text-align: left; padding-left: 10px">
					<label>작성자 이름이 들어갑니다.</label>
				</td>
				<th width="20%" style="border-radius: 15px;">조회수</th>
				<td width="30%" style="text-align: right; padding-right: 30px">
					<label>1000</label>
				</td>
			</tr>
			<tr>
				<th colspan="4" style="border-radius: 15px;">본문내용</th>
			</tr>
			<tr>
				<td colspan="4" style="padding: 10px 0">
					<textarea name="content" id="content" style="width: 728px; height: 580px"></textarea>
				</td>
			</tr>
		</table>
		<table width="735" style="margin-top: 5px">
			<tr>
				<td align="center" valign="middle" style="padding: 10px 0 0 0">
					<input type="button" value="수정" style="padding: 5px" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="삭제" style="padding: 5px" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="댓글" style="padding: 5px" />&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="목록" style="padding: 5px"
					onclick="javascript:history.back()" />
				</td>
			</tr>
		</table>
	</div>
	<!-- <script type="text/javascript">
		var oEditors = [];
		nhn.husky.EZCreator
				.createInIFrame({
					oAppRef : oEditors,
					elPlaceHolder : "content", //textarea에서 지정한 id와 일치해야 합니다.
					sSkinURI : "resources/smarteditor2/workspace/SmartEditor2Skin.html",
					fCreator : "createSEditor2"
				});
	</script> -->
</body>
</html>