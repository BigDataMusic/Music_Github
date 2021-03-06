<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/top100_table.css" />
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script type="text/javascript">
 var app=angular.module('myApp',[]);
 app.controller('ctrl1',function($scope){
	$scope.json=${json}; 
 });
</script>
</head>
<body ng-app="myApp">
	<center>
		<h1 style="margin-bottom: 10px">Free Board</h1>
		<!-- <p id="title_ex" style="margin: 0px;padding: 10px 0;">[음악인] TOP 100</p> -->
		<div id="content_top100" ng-controller="ctrl1">
			<table width="735">
				<tr>
					<td align="right"><a href="board_insert.do" style="text-decoration: none">
					<input	type="button" value="write" /></a></td>
				</tr>
			</table>
			<table id="table2" width="735">
				<thead>
					<tr>
						<th width="10%" id="th">글번호</th>
						<th width="45%">제목</th>
						<th width="15%">글쓴이</th>
						<th width="20%">게시일</th>
						<th width="10%">조회수</th>
					</tr>
				</thead>
				<tbody ng-repeat="data in json">
					<tr class="dataTr">
						<td width="10%" id="td">{{data.no}}</td>
						<td width="45%" ng-if="data.group_tab==0" class="song"><a
							href="board_content.do?no={{data.no}}">{{data.subject}}</a> <sup
							ng-if="data.regdate==data.today"> <font color="red">new</font>
						</sup></td>
						
						<td width="45%" ng-if="data.group_tab==1" class="song">
							<%-- for(int i:4) --%> 
							<span ng-repeat="i in data.group_tab">	&nbsp;&nbsp;</span>&nbsp;&nbsp;☞&nbsp;<a href="board_content.do?no={{data.no}}">{{data.subject}}</a>
							<sup ng-if="data.regdate==data.today"> <font color="red">new</font>
						</sup>
						</td>
						
						<td width="45%" ng-if="data.group_tab==2" class="song">
							<%-- for(int i:4) --%> 
							<span ng-repeat="i in data.group_tab">	&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;☞&nbsp;<a href="board_content.do?no={{data.no}}">{{data.subject}}</a>
							<sup ng-if="data.regdate==data.today"> <font color="red">new</font>
						</sup>
						</td>
						
						<td width="45%" ng-if="data.group_tab==3" class="song">
							<%-- for(int i:4) --%> 
							<span ng-repeat="i in data.group_tab">	&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;☞&nbsp;<a href="board_content.do?no={{data.no}}">{{data.subject}}</a>
							<sup ng-if="data.regdate==data.today"> <font color="red">new</font>
						</sup>
						</td>
						
						<td width="45%" ng-if="data.group_tab>4" class="song">
							<%-- for(int i:4) --%> 
							<span ng-repeat="i in data.group_tab">	&nbsp;&nbsp;</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;☞&nbsp;<a href="board_content.do?no={{data.no}}">{{data.subject}}</a>
							<sup ng-if="data.regdate==data.today"> <font color="red">new</font>
						</sup>
						</td>
						
						<td width="15%">{{data.name}}</td>
						<td width="20%">{{data.regdate}}</td>
						<td width="10%">{{data.hit}}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="content_top100" style="margin-top: 15px;padding-left:200px">
			<a href="board.do?page=${curpage>1?curpage-1:curpage}"
				style="text-decoration: none"><img alt="" src="resources/images/f-left.png"  style="vertical-align: middle;"></a>
			<span style="margin: 0 40px;vertical-align: middle;">${curpage } page / ${totalpage } pages</span>
			<a href="board.do?page=${curpage<totalpage?curpage+1:curpage}"
				style="text-decoration: none"><img alt="" src="resources/images/f-right.png" style="vertical-align: middle;"></a>
		</div>
	</center>
</body>
</html>