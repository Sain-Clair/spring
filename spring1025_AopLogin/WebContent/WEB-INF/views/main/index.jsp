<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<c:set var="rPath" value="${pageContext.request.contextPath}/resources"/>
<c:set var="cPath" value="${pageContext.request.contextPath}"/>
<article>
	<header style="color:white">Main Page</header>
	<ul class="list-unstyled">
		<li class="boarder-top my-3">ICT No! 메인페이지 입니다.</li>
	</ul>
	<div class="container">
		<p>여기는 메인의 내용이 들어 갈 자리<p> 
		
		<li><a href="${cPath}/upBoard/upList">UpList</a></li>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>
a


