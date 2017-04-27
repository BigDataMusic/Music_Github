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
	<p id="title_ex1" style="margin: 0px;padding: 10px 0;">신규 앨범</p>
	<div class="section01 section_nav">
		<div class="cont on">					
			<span class="bg"><a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/51/659/10051659_500.jpg" alt=""/></a></span>															
			<span class="layer"></span>
		</div>
		<div class="cont">					
			<span class="bg"><a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/53/651/10053651_500.jpg" alt=""/></a></span>	                    									
			<span class="layer"></span>
		</div>
		<div class="cont">					
			<span class="bg"><a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/51/659/10051659_500.jpg" alt=""/></a></span>	                    				
			<span class="layer"></span>
		</div>
		<div class="cont">										
			<span class="bg"><a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/47/890/10047890_500.jpg" alt=""/></a></span>	                    			
			<span class="layer"></span>						
		</div>
		<div class="cont">										
			<span class="bg"><a href="#"><img src="http://cdnimg.melon.co.kr/cm/album/images/100/51/659/10051659_500.jpg" alt=""/></a></span>	                    					
			<span class="layer"></span>
		</div>
	</div>
</div>	
<div id="content_top100">
	<p id="title_ex1" style="margin: 0px;padding: 10px 0;">신규 싱글</p>
	<table id="table2" width="735">
		<tr>
			<th width="20%">발매일</th>
			<th width="40%">곡명</th>
			<th width="15%">아티스트</th>
			<th width="10%">장르</th>
			<th width="15%">발매사</th>
		</tr>	
		<c:forEach var="i" begin="1" end="10">
		<tr class="dataTr">
			<td>2017.12.31.</td>
			<td class="song"><a href="#">노래제목이 들어가는 자리입니다.</a></td>
			<td>아티스트</td>			
			<td>장르</td>
			<td>발매사발매사발매</td>
		</tr>
		</c:forEach>
	</table>
</div>
<div id="content_top100" style="margin-top: 10px;">
	<table width="735">
		<tr>
			<td id="chart_rank">
			&lt;&lt;&nbsp;&nbsp;&nbsp;&lt;&nbsp;&nbsp;&nbsp;
				<c:forEach var="i" begin="1" end="10">
					<a href="#">[ ${i} ]</a>&nbsp;
				</c:forEach>
			&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&gt;&gt;
			</td>
		</tr>
	</table>
</div>
</body>
</html>