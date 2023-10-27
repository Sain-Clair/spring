<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 경로 설정 -->
<!-- cPath 트리 구조로 만들어 보기? -->
<c:set var="rPath" value="${pageContext.request.contextPath}/resources"/>
<c:set var="cPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
 <head>
  <TITLE> New Document </TITLE>
  <meta charset="euc-kr">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${rPath}/css/style.css"/>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- ajax를 링크 걸기 -->
<!-- <script -->
<%-- 	src="<%=application.getContextPath()%>/resources/js/ajaxdemo1.js"></script> --%>
<script 
	src="${rPath}/js/ajaxdemo1.js"/></script>
<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap');

body, a, h3, p {
    font-family: 'Poppins', sans-serif;
}

a {
    text-decoration: none;
    transition: color 0.3s ease;
}

a:hover {
    color: #333;
}

select.btn-mini {
    height: auto;
    width: 200px;
    line-height: 14px;
}

.card {
    border: none;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

.dots {
    height: 4px;
    width: 4px;
    margin-bottom: 2px;
    background-color: #bbb;
    border-radius: 50%;
    display: inline-block;
}

.badge {
    padding: 7px;
    padding-right: 9px;
    padding-left: 16px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    background-color: #f8f9fa;
    color: #333;
}

.user-img {
    margin-top: 4px;
}

.check-icon {
    font-size: 17px;
    color: #aaa;
    top: 1px;
    position: relative;
    margin-left: 3px;
}

.form-check-input {
    margin-top: 6px;
    margin-left: -24px !important;
    cursor: pointer;
}

.form-check-input:focus {
    box-shadow: none;
}

.icons i {
    margin-left: 8px;
}

.reply {
    margin-left: 12px;
}

.reply small {
    color: #aaa;
}

.reply small:hover {
    color: green;
    cursor: pointer;
}

.mytable-condensed {
    font-size: 11px;
}

.navbar {
    background: linear-gradient(135deg, #71b7e6, #9b59b6);
}

.nav-link {
    color: #fff;
    background-color: rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    transition: transform 0.3s ease, box-shadow 0.3s ease; 
}

.nav-link:hover {
   color: #fff;
   background-color: rgba(0, 0, 0, 0.1);
    transform: scale(1.1);
}

.btn-primary {
    background-color: #9b59b6;
    border: none;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease;
}

.btn-primary:hover {
    background-color: #8e44ad;
}

.mybgColor {
    background: linear-gradient(135deg, #7be5d5, #e58df0, #5a84e7);
    background-size: 400% 400%;
    animation: Gradient 10s ease infinite;
}

@keyframes Gradient {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}

.myHeaderBox {
    padding: 10px 20px;
    margin: 5px;
    border-radius: 8px;
    transition: transform 0.2s ease, box-shadow 0.2s ease;
    box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.2);
    background: linear-gradient(90deg, #1e3c72, #2a5298); /* 기본 그라데이션 색상 */
}

.myHeaderBox:nth-child(1) {
    background: linear-gradient(90deg, #ee0979, #ff6a00); /* 첫 번째 박스 그라데이션 */
}

.myHeaderBox:nth-child(2) {
    background: linear-gradient(90deg, #2193b0, #6dd5ed); /* 두 번째 박스 그라데이션 */
}

.myHeaderBox:nth-child(3) {
    background: linear-gradient(90deg, #834d9b, #d04ed6); /* 세 번째 박스 그라데이션 */
}

.myHeaderBox:hover {
    box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.3);
}

.myHeaderBox a.nav-link {
    color: #fff;
    font-weight: bold;
    background-color: transparent !important; /* 글자와 버튼 사이의 색 차이 제거 */
}

</style>
<script>
$(function(){
	$('#searchv').focusin(function() {
// 		$(this).val("검색어 입력")
	});
	$('#searchv').focusout(function() {
		$(this).val("")
	});
	
	//let firstColor ="d-flex flex-row-reverse mybgColor";
	//console.log("log00 => "+$('.mybgColor').attr('class'));
	$('#item1').click(function(){
		//$(this).attr('class',firstColor)
		//console.log("log => "+$('.mybgColor').attr('class'));
		$('.mybgColor').attr('class',function(){
			$(this).attr('style','background-color:#0dcaf0');
			/*
			let changeClass = $(this).attr('class');
			let class_sttr_arr = changeClass.split(" ");
			$.each(class_sttr_arr,function(e,val){
				console.log(e+"::::::::::::::::::"+val)
				if(val.includes("bg-")){
					console.log("================>"+e+":"+val);
					$('.mybgColor').attr('class').replace(val,'bg-info');
				}else{
					changeClass +=" bg-info";
				}
				$('.mybgColor').attr('class',changeClass)
			});
			*/
		});
	});
	$('#item2').click(function(){
		console.log("log => "+$('.mybgColor').attr('class'));
		$('.mybgColor').attr('class',function(){
			$(this).attr('style','background-color:#ffc107');
		});
		//이미지 세팅하기
		let imgArr = ['d1.jpg','d2.jpg','d3.jpg'];
		$('.carousel-item > img').each(function(idx){
			console.log("바뀔 이미지 :"+imgArr[idx]+", log Img => "+$(this).attr('src'));
			$(this).attr('src','${rPath}/image/'+imgArr[idx]);
		});
		
	});
	$('#item3').click(function(){
		$('.mybgColor').attr('class',function(){
			$(this).attr('style','background-color:#0d6efd');
		});
		//이미지 세팅하기
		let imgArr = ['c1.jpg','c2.jpg','c3.jpg'];
		$('.carousel-item > img').each(function(idx){
			console.log("바뀔 이미지 :"+imgArr[idx]+", log Img => "+$(this).attr('src'));
			$(this).attr('src','${rPath}/image/'+imgArr[idx]);
		});
	});
});
</script>
 </head>
 <body>
<header class="text-white text-center" >
		<div class="d-flex flex-row-reverse mybgColor">
			<div class="p-2 bg-info">
				<a href="${cPath}/chatdemo/chat" class="nav-link text-white" id="item1">Chatdemo</a>
			</div>
			<%-- 로그인 전 --%>
			<c:choose>
				<c:when test="${sessionScope.sessionID == null}">
					<div class="p-2 bg-warning">
						<a href="${cPath}/login/loginForm" class="nav-link text-white"
							id="item2">Login</a>
					</div>
					<div class="p-2 bg-primary">
						<a href="${cPath}/member/memForm" class="nav-link text-white"
							id="item3">회원가입</a>
					</div>
				</c:when>
				<%-- 로그인 후 --%>
				<c:when test="${sessionScope.sessionID != null}">
					<div class="p-2 bg-warning">
						<a href="${cPath}/login/logout" class="nav-link text-white"
							id="item2">Logout</a>
					</div>
					<div class="p-2 bg-primary">
						<a href="${cPath}/member/mypage" class="nav-link text-white"
							id="item3">MyPage</a>
					</div>
				</c:when>
			</c:choose>
		</div>

		<!-- Carousel -->
<div id="demo" class="carousel slide" data-bs-ride="carousel">
  <!-- Indicators/dots -->
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
    <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
  </div>
  <c:choose>
  	<c:when test="${sessionScope.sessionID == null }">
  		<c:set var ="carouselMsg" value="제이쿼리 어쩌구 저쩌구..." />
  	</c:when>
  	<c:otherwise>
  		<c:set var="carouselMsg" value="${sessionScope.sessionName} 님 오늘 날씨 좋네"/>
  	</c:otherwise>
  </c:choose>
  <!-- The slideshow/carousel -->
  <%-- resources --> ${rPath} --%>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="${rPath}/image/d1.jpg" alt="Kosmo113" class="d-block" style="width:100%">
      <div class="carousel-caption">
        <h3>UI 요구사항 확인하기,UI 설계하기 </h3>
        <p>${carouselMsg }</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="${rPath}/image/d2.jpg" alt="Carousel" class="d-block" style="width:100%">
      <div class="carousel-caption">
        <h3>Carousel 기능을 CSS3로!</h3>
        <p>${carouselMsg }</p>
      </div> 
    </div>
    <div class="carousel-item">
      <img src="${rPath}/image/d3.jpg" alt="JSP" class="d-block" style="width:100%">
      <div class="carousel-caption">
        <h3>UI 요구사항 확인하기,UI 설계하기 </h3>
        <p>${carouselMsg }</p>
      </div>  
    </div>
  </div>
  
  <!-- Left and right controls/icons -->
  <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
    <span class="carousel-control-next-icon"></span>
  </button>
</div>  
</header>
<%--bg-dark navbar-dark --%>
    <nav class="navbar navbar-expand-sm mybgColor" >
     <div class="container-fluid" >
        <ul class="navbar-nav" >
            <li class="nav-item"><a href="${cPath}/main" class="nav-link active" >Home</a></li>
            <li class="nav-item"><a href="${cPath}/fboard/fboardList" class="nav-link">FBoard</a></li>
            <li class="nav-item"><a href="${cPath}/upBoard/upList" class="nav-link">Board</a></li>
            <li class="nav-item"><a href="#" class="nav-link">Profile</a></li>
            <li class="nav-item"><a href="#" class="nav-link">Contact</a></li>
        </ul>
        <form class="d-flex">
        <input class="form-control me-2" type="text" placeholder="Search" name="searchv" id="searchv">
        <button class="btn btn-primary" type="button">Search</button>
        </form>
      </div>
    </nav>