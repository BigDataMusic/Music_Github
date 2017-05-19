<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
 a:link { color: black; text-decoration: none;}
 a:visited { color: black; text-decoration: none;}
 a:hover { color: black; text-decoration: underline;}
</style>
<link rel="stylesheet" type="text/css" href="resources/css/top100_table.css" />
<link rel="stylesheet" href="resources/css/r_style.css">
<link rel="stylesheet" href="resources/css/style.css">
<!-- <link rel='stylesheet prefetch'	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'> -->
<link rel='stylesheet prefetch'	href='https://fonts.googleapis.com/icon?family=Material+Icons'>
<link rel='stylesheet'	href='resources/css/Materialize.css'>
<link rel='stylesheet'	href='resources/css/normalize.css'>
<link rel='stylesheet'	href='resources/css/picker.css'>
<link rel='stylesheet'	href='resources/css/wave.css'>
</head>
<body>
	<div id="content">
		<center><h3 style="margin-bottom: 10px">추천음악 Best</h3></center>
		<div class="row">
		<div
			class="col-xs-12 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
			<div class="card">
				<div class="card-move-up waves-effect waves-block waves-light">
					<div class="move-up material-blue darken-1">
						<div>
							<span class="chart-title white-text"></span>

							<div class="chart-revenue material-blue darken-2 white-text">
								<p class="chart-revenue-total"></p>
							</div>
							<!--
								<div class="switch chart-revenue-switch right">
									<label class="material-blue-text text-lighten-5">
									  TOP 10
									  <input type="checkbox" checked>
									  <span class="lever"></span> WORST 10
									</label>
								</div>
							-->
						</div>
						<div id="chart-ping-10" style="width: 100%; height: 350px;"
							class="trending-line-chart-wrapper"></div>
					</div>
				</div>
				<div class="card-content">
					<a
						class="btn-floating btn-move-up waves-effect waves-light darken-2 right"><i
						class="material-icons activator">add</i></i></a>
					<div
						class="col-sx-10 col-sx-offset-1 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
						<div id="chart-ping-10-legend">
							<p>Lorem</p>
							<p>Ipsum</p>
							<p>Dolorem</p>
							<p>Sit</p>
							<p>Amet</p>
						</div>
					</div>
				</div>

				<div class="card-reveal">
					<span class="card-title grey-text text-darken-4">Hosts
						Latency Info <i class="material-icons right">clear</i>
					</span>
					<table class="">
						<thead>
							<tr>
								<th data-field="position">추천순위</th>
								<th data-field="host">곡명</th>
								<th data-field="mac">가수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach  var="vo" items="${list }" varStatus="s">
							<c:if test="${s.index<10 }">
							<tr>
								<td><a href="content.do?song=${vo.song }&singer=${vo.singer}"><img src="${vo.poster }"></a></td>
								<td><a href="content.do?song=${vo.song }&singer=${vo.singer}">${vo.song }</a></td>
								<td>${vo.singer }</td>
							</tr>
							</c:if>
							</c:forEach>
							<c:forEach var="vo" items="${elist }" varStatus="s">
			 	 <c:if test="${s.index<10 }">
							<tr>
								<td><a href="content.do?song=${vo.song }&singer=${vo.singer}"><img src="${vo.poster }"></a></td>
								<td><a href="content.do?song=${vo.song }&singer=${vo.singer}">${vo.song }</a></td>
								<td>${vo.singer }</td>
							</tr>
					</c:if>
				</c:forEach>
						</tbody>
					</table>
					<p class="reveal-footer">Worst 10</p>
				</div>
			</div>
		</div>
	</div>
	<!--work collections end-->

	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src='http://www.amcharts.com/lib/3/amcharts.js'></script>
<script src='http://www.amcharts.com/lib/3/pie.js'></script>
<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/js/materialize.min.js'></script>
<script type="text/javascript">
$(function() {
	var setupAmChart = function(labelText) {
		  AmCharts.makeChart("chart-ping-10", {
		    "type": "pie",
		    "angle": 22.5,
		    "balloonText": "[[title]]<br><span style='font-size:14px'><b>[[value]]</b> pts</span>",
		    "depth3D": 15,
		    "gradientType": "linear",
		    "innerRadius": 13,
		    "labelRadius": 9,
		    "color": "#ffffff",
		    "labelText": labelText,
		    "startAngle": 0,
		    "hoverAlpha": 0.86,
		    "labelColorField": "#FFF",
		    "labelTickAlpha": 0.27,
		    "labelTickColor": "#983333",
		    "outlineThickness": 2,
		    "titleField": "category",
		    "valueField": "column-1",
		    "classNamePrefix": "chart",
		    "fontSize": 14,
		    "allLabels": [],
		    "balloon": {},
		    "legend": {
		      "enabled": true,
		      "divId": "chart-ping-10-legend"
		    },
		    "titles": [],
		    "responsive": {
		      "enabled": true
		    },
		    "dataProvider": <%=request.getAttribute("json")%>
		  });
		};
		if (window.matchMedia("(min-width:1025px)").matches) { // desktop
			setupAmChart("[[title]]");
		}

		window.addEventListener("orientationchange", function() {
				Math.abs(window.orientation) - 90 != 0 ? setupAmChart("") : setupAmChart("[[title]]");
		});
});
</script>
	</div>
</body>
</html>