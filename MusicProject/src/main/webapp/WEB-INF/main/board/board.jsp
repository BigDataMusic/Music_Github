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
			<td align="right" valign="middle"><a href="board_insert.do" style="text-decoration: none"><input type="button" value="write"/></a></td>
		</tr>
	</table>
	<table id="table2" width="735">
	<thead>
		<tr>
			<th width="10%">글번호</th>
			<th width="45%">제목</th>
			<th width="15%">글쓴이</th>
			<th width="20%">게시일</th>
			<th width="10%">조회수</th>
		</tr>	
	</thead>
	<tbody ng-repeat="data in json">
		<tr class="dataTr">
	       <td width="10%" align=center>{{data.no}}</td>
	       <td width="45%" align=left ng-if="data.group_tab==0">
	        <a href="board_content.do?no={{data.no}}">{{data.subject}}</a>
	          <sup ng-if="data.regdate==data.today">
	            <font color="red">new</font>
	          </sup>
	       </td>
	       <td width="45%" align=left ng-if="data.group_tab!=0">
	          <%-- for(int i:4) --%>
	        <span ng-repeat="i in data.group_tab">
	          &nbsp;&nbsp;
	        </span>
	           ☞
	        <a href="board_content.do?no={{data.no}}">{{data.subject}}</a>
	          <sup ng-if="data.regdate==data.today">
	            <font color="red">new</font>
	          </sup>
	       </td>
	       <td width="15%" align=center>{{data.name}}</td>
	       <td width="20%" align=center>{{data.regdate}}</td>
	       <td width="10%" align=center>{{data.hit}}</td>
	     </tr>
	   </tbody>
	</table>
</div>
	<div id="content_top100" align="right">
            <a href="board.do?page=${curpage>1?curpage-1:curpage}" style="text-decoration: none"><input type="button" value="이전"/></a>&nbsp;
            <a href="board.do?page=${curpage<totalpage?curpage+1:curpage}" style="text-decoration: none"><input type="button" value="다음"/></a>&nbsp;&nbsp;
            ${curpage } page / ${totalpage } pages
    </div>
</center>
</body>
</html>