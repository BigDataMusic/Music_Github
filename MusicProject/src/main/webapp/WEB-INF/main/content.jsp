<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Paradise</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
<style> 
    #nchart {position:relative;} 

    #popup {
    	display:none; position:absolute; top:10px; left:-400px; 
       z-index : 1000; background-color:#fff; 
       padding:20px; border:2px solid #ff0000; 
    } 
</style>
<script type="text/javascript">
function show() {
	document.getElementById("popup").style.display = "block";
}
function closepop() {
	document.getElementById("popup").style.display = "none";
}
</script> 
<script type="text/javascript">
$(function(){
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: '날씨 감성'
	    },
	    subtitle: {
	        text: '네이버 블로그 리뷰 분석'
	    },
	    xAxis: {
	        type: 'category'
	    },
	    yAxis: {
	        title: {
	            text: '감성율'
	        }

	    },
	    legend: {
	        enabled: false
	    },
	    plotOptions: {
	        series: {
	            borderWidth: 0,
	            dataLabels: {
	                enabled: true,
	                format: '{point.y:.1f}%'
	            }
	        }
	    },

	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
	    },

	    series: [{
	        name: '감성율',
	        colorByPoint: true,
	        data: ${json}
	    }]
	    
	    
	});
});
</script>
</head>

<body>
	<div id="content">
		<!-- <div class="search">
			<span>Search</span> <input type="text" />
		</div> -->
		<div class="column_dc">
			<!-- <div class="tree">
				<a href="#">Tours</a> &raquo; Maldives
			</div> -->
			<!-- <img src="resources/images/title5.gif" alt="" width="255" height="19" /><br /> -->
			<p id="title_ex" style="margin: 0px;padding: 10px 0;">${vo.song } / ${vo.singer }</p>
			<div class="gallery">
				<iframe id="mv" src="https://www.youtube.com/embed/dMn509ddAkc" frameborder="0" allowfullscreen></iframe>
				<p id="title_ex" style="margin: 0px;padding: 10px 0;">수록곡</p>
				<!-- <div>
					<a href="#" class="arrow"><img src="resources/images/arrow_l.gif" alt="" width="10" height="96" /></a>
					<div>
						
					</div>
					<a href="#" class="arrow"><img src="resources/images/arrow_r.gif" alt="" width="10" height="96" /></a>
				</div> -->
				<table id="table1" width="500">
					<tr>
						<th width="15%">번호</th>
						<th width="65%">곡명</th>
						<th width="20%">아티스트</th>
					</tr>	
					<c:forEach var="vo" items="${mList }">
					<tr class="dataTr">
						<td align=center>${vo.rank }</td>
						<td>${vo.title }</td>
						<td align=center>${vo.artist }</td>
					</tr>
					</c:forEach>
				</table>
				<p id="title_ex" style="margin-top: 0;padding: 10px 0;">감정 분석</p>
				<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div> 
			</div>			
		</div>
		<div class="column_dc2">
			<p id="title_ex" style="margin: 0px;padding: 10px 0;">곡 정보</p>
			<!-- <img src="http://sstatic.naver.net/people/portrait/201704/20170417141627678.jpg" alt="" width="200" height="270"/>	 -->
	
			<img src="${vo.poster }" width="80" height="80">
			<img src="${vo.poster }" width="80" height="80"><br>
			<img src="${vo.poster }" width="80" height="80">
			<img src="${vo.poster }" width="80" height="80"><br>
			
			<p class="info">
			<br>
			${vo.singer }<br>
			${vo.song } <br> 
			${vo.album } <br> 
			</p>
			<a href="#" class="button">more info</a>
			<p id="title_ex" style="margin: 0px; padding: 10px 0;">이럴때 추천</p>
			<div>
			<img src="resources/images/wordcloud.png" alt="" width="200" height="200" />
			</div>
			<div style="height: 10px"></div>
			<div id="nchart">
			<img src="resources/images/emotion.png" alt="" width="200" height="200" onmouseover="show()"/>
				<div id="popup">
				<img src="resources/images/emotion.png" alt="" onmouseout="closepop()" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>
