<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/top100_table.css" />
<script type="text/javascript" src="resources/smarteditor2/workspace/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
var oEditors = [];

var sLang = "ko_KR";	// 언어 (ko_KR/ en_US/ ja_JP/ zh_CN/ zh_TW), default = ko_KR

// 추가 글꼴 목록
//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];

nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ir1",
	sSkinURI: "SmartEditor2Skin.html",	
	htParams : {
		bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		//bSkipXssFilter : true,		// client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
		//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
		fOnBeforeUnload : function(){
			//alert("완료!");
		},
		I18N_LOCALE : sLang
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});

function pasteHTML() {
	var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
	oEditors.getById["ir1"].exec("PASTE_HTML", [sHTML]);
}

function showHTML() {
	var sHTML = oEditors.getById["ir1"].getIR();
	alert(sHTML);
}
	
function submitContents(elClickedObj) {
	oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
	
	// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("ir1").value를 이용해서 처리하면 됩니다.
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}

function setDefaultFont() {
	var sDefaultFont = '궁서';
	var nFontSize = 24;
	oEditors.getById["ir1"].setDefaultFont(sDefaultFont, nFontSize);
}
</script>
<title>Insert title here</title>
</head>
<body>
<center><h1 style="margin-bottom: 10px">Free Board 글쓰기</h1></center>
<div id="content_top100" style="margin-top: 10px">
	<table width="735" id="table2">
		<tr>
			<th width="20%" style="border-radius : 15px;">제목</th>
			<td width="80%" colspan="3" style="text-align: left; padding-left: 10px"><input type="text" size="64" align="left"></td>
		</tr>
		<tr>
			<th width="20%" style="border-radius : 15px;">작성자</th>
			<td width="42%" style="text-align: left; padding-left: 10px"><input type="text" size="25" align="left"></td>
			<th width="23%" style="border-radius : 15px;"></th>
			<td width="15%" style="text-align: left; padding-left: 10px"></td>
		</tr>
		<tr>
			<th colspan="4" style="border-radius : 15px;">본문내용</th>
		</tr>
		<tr>
			<td colspan="4" style="padding: 0px">
				<iframe src="resources/smarteditor2/workspace/SmartEditor_content.html" width="735" height="600" frameborder="0" scrolling="no"></iframe>
			</td>
		</tr>
	</table>
	<table width="735" style="margin-top: 5px">
		<tr>
			<td align="center" valign="middle" style="padding: 10px 0 0 0">
				<input type="button" value="수정" style="padding: 5px"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="삭제" style="padding: 5px"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="댓글" style="padding: 5px"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="목록" style="padding: 5px" onclick="javascript:history.back()"/>
			</td>
		</tr>
	</table>
</div>
</body>
</html>