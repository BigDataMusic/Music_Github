<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<style> 
    #nchart {position:relative;} 

    #popup {
    	display:none; position:absolute; top:10px; left:10px; 
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
    
</head>
<body>
	<div id="sidebar">
		<div class="logo_block">
			<a href="#"><img src="resources/images/nav_newtracks.png" alt="setalpm" width="190" height="156" /></a><br />
			 <span class="slogan">음악의	모든건<br>음악인으로 통한다!</span>
			<p class="text1">음악이 멈추었다...<br>
			 그러자 세상이 멈추었다...<br>
			 <br>
			나를 움직이게 하는 힘 !<br>
			Music makes me high !</p>
		</div>
		<p id="title_ex">날짜별 앨범발매수</p>
		<br />
		<div id="nchart">
			<input type="image" src="resources/images/chart_new.jpg" alt="" width="180" height="200" onmouseover="show()"/>
			<div id="popup">
				<img src="resources/images/chart_new.jpg" alt="" onmouseout="closepop()"/>
			</div>
		</div>
		<br />
		<br />
		<br />
		<p id="title_ex">신곡출시가수</p>
		<br/>
		<ul id="navigation" style="padding-top: 0px">
			<c:forEach var="vo" items="${nlist }" varStatus="i">
				<c:if test="${i.index<20}">
					<c:if test="${i.index%2==0 }">
						<li class="color">
					</c:if>
					<c:if test="${i.index%2!=0 }">
						<li>
					</c:if>
					<a href="#">${vo.artist.trim() }</a></li>
				</c:if>
			</c:forEach>
		</ul>	
	</div>	
</body>
</html>