<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/top100_table.css" />
<link rel="stylesheet" type="text/css" href="resources/css/section.css" />
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="resources/js/jquery.easing.1.3"></script>
<script>
$(function(){
	$(".section01 .cont").mouseover(function(){					
		$(".section01 .cont").stop().animate({width:"120px"}, 900, 'easeOutCirc' ).removeClass("on");
		$(this).stop().animate({width:"250px"}, 900, 'easeOutCirc' ).addClass("on");							
	});
});
</script>
</head>
<body>
<center>
	<h1 style="margin-bottom: 0px;padding:0px auto">금주의 신곡</h1>
	<p id="title_ex" style="margin: 0px;padding: 10px 0;">[2017.04.30. ~ 2017.05.06.]</p>
</center>
<div id="content_top100" style="margin: 10px auto;">			
	<p id="title_ex1" style="margin: 0px;padding: 10px 0;">신규 앨범 BEST 5</p>
	<div class="section01">
		<c:forEach var="vo" items="${malist }" varStatus="s">
			<c:if test="${s.index==0 }">
				<div class="cont on">			
			</c:if>		
			<c:if test="${s.index!=0 }">
				<div class="cont">			
			</c:if>
				<span class="bg"><a href="content.do"><img src="${vo.alPoster }" alt=""/></a></span>															
				<span class="layer"></span>
			</div>
		</c:forEach>
	</div>
</div>	
<div id="content_top100"  style="margin-top: 10px;">
	<p id="title_ex1" style="margin: 0px;padding: 10px 0;">신규 싱글 BEST 50</p>
	<table id="table2" width="735">
		<tr>
			<th width="10%">No</th>
			<th colspan="2">곡명</th>
			<th width="15%">아티스트</th>
			<th width="15%">가사보기</th>
		</tr>	
		<c:forEach var="vo" items="${vList }" varStatus="i">
		<tr class="dataTr">
			<td>${vo.n }</td>	
			<td><img src="${vo.poster }"></td>
			<td class="song"><a href="${vo.alno }">${vo.title }</a></td>
			<td>${vo.artist }</td>		
			<td><img src="resources/images/ly.png"></td>
		</tr>
		</c:forEach>
	</table>
	<table width="746">
		<tr>
			<td align="center">
				<input type="button" value="1" id="btn01" onclick="javascript:location.href='newtracks.do?page=1'">
				<input type="button" value="2" id="btn01" onclick="javascript:location.href='newtracks.do?page=2'">
				<input type="button" value="3" id="btn01" onclick="javascript:location.href='newtracks.do?page=3'">
				<input type="button" value="4" id="btn01" onclick="javascript:location.href='newtracks.do?page=4'">
				<input type="button" value="5" id="btn01" onclick="javascript:location.href='newtracks.do?page=5'">
				<input type="button" value="6" id="btn01" onclick="javascript:location.href='newtracks.do?page=6'">
				<input type="button" value="7" id="btn01" onclick="javascript:location.href='newtracks.do?page=7'">
				<input type="button" value="8" id="btn01" onclick="javascript:location.href='newtracks.do?page=8'">
				<input type="button" value="9" id="btn01" onclick="javascript:location.href='newtracks.do?page=9'">
				<input type="button" value="10" id="btn01" onclick="javascript:location.href='newtracks.do?page=10'">
			</td>
		</tr>
	</table>
</div>
</body>
</html>