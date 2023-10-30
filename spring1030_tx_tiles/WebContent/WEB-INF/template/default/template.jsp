<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%-- ��� ���� --%>
<%-- cPath Ʈ�� ������ ����� ����--%>
<c:set var="rPath" value="${pageContext.request.contextPath}/resources" scope="application"/>
<c:set var="cPath" value="${pageContext.request.contextPath}/web" scope="application"/>
<!DOCTYPE html>
<html>
<head>
<TITLE>New Document</TITLE>
<meta charset="euc-kr">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${rPath}/css/style.css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- ajax�� ��ũ �ɱ� -->
<!-- <script -->
<%-- 	src="<%=application.getContextPath()%>/resources/js/ajaxdemo1.js"></script> --%>
<script src="${rPath}/js/ajaxdemo1.js" /></script>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap')
	;

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

@
keyframes Gradient { 0% {
	background-position: 0% 50%;
}

50%
{
background-position


:



100%
50%;
}
100%
{
background-position
 
:



0%
50%;
}
}
.myHeaderBox {
	padding: 10px 20px;
	margin: 5px;
	border-radius: 8px;
	transition: transform 0.2s ease, box-shadow 0.2s ease;
	box-shadow: 0px 3px 10px rgba(0, 0, 0, 0.2);
	background: linear-gradient(90deg, #1e3c72, #2a5298); /* �⺻ �׶��̼� ���� */
}

.myHeaderBox:nth-child(1) {
	background: linear-gradient(90deg, #ee0979, #ff6a00);
	/* ù ��° �ڽ� �׶��̼� */
}

.myHeaderBox:nth-child(2) {
	background: linear-gradient(90deg, #2193b0, #6dd5ed);
	/* �� ��° �ڽ� �׶��̼� */
}

.myHeaderBox:nth-child(3) {
	background: linear-gradient(90deg, #834d9b, #d04ed6);
	/* �� ��° �ڽ� �׶��̼� */
}

.myHeaderBox:hover {
	box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.3);
}

.myHeaderBox a.nav-link {
	color: #fff;
	font-weight: bold;
	background-color: transparent !important; /* ���ڿ� ��ư ������ �� ���� ���� */
}
</style>
<script>
	$(function() {
		$('#searchv').focusin(function() {
			// 		$(this).val("�˻��� �Է�")
		});
		$('#searchv').focusout(function() {
			$(this).val("")
		});

		//let firstColor ="d-flex flex-row-reverse mybgColor";
		//console.log("log00 => "+$('.mybgColor').attr('class'));
		$('#item1').click(function() {
			//$(this).attr('class',firstColor)
			//console.log("log => "+$('.mybgColor').attr('class'));
			$('.mybgColor').attr('class', function() {
				$(this).attr('style', 'background-color:#0dcaf0');
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
		$('#item2').click(
				function() {
					console.log("log => " + $('.mybgColor').attr('class'));
					$('.mybgColor').attr('class', function() {
						$(this).attr('style', 'background-color:#ffc107');
					});
					//�̹��� �����ϱ�
					let imgArr = [ 'd1.jpg', 'd2.jpg', 'd3.jpg' ];
					$('.carousel-item > img').each(
							function(idx) {
								console
										.log("�ٲ� �̹��� :" + imgArr[idx]
												+ ", log Img => "
												+ $(this).attr('src'));
								$(this).attr('src',
										'${rPath}/image/' + imgArr[idx]);
							});

				});
		$('#item3').click(
				function() {
					$('.mybgColor').attr('class', function() {
						$(this).attr('style', 'background-color:#0d6efd');
					});
					//�̹��� �����ϱ�
					let imgArr = [ 'c1.jpg', 'c2.jpg', 'c3.jpg' ];
					$('.carousel-item > img').each(
							function(idx) {
								console
										.log("�ٲ� �̹��� :" + imgArr[idx]
												+ ", log Img => "
												+ $(this).attr('src'));
								$(this).attr('src',
										'${rPath}/image/' + imgArr[idx]);
							});
				});
	});
</script>
</head>
<body>
	<%-- header.jsp--%>
	<tiles:insertAttribute name="header" />
	<%--bg-dark navbar-dark --%>
	<%--menu.jsp --%>
	<tiles:insertAttribute name="menu" />
	<%-- body.jsp --%>
	<tiles:insertAttribute name="body" />

	<aside>
		<header class="bg-primary p-2 text-white text-center rounded-top">
			<h2 class="fs-4">�ٽ� ������</h2>
		</header>
		<ul class="list-unstyled mt-3">
			<li class="mb-2">
				<button class="btn btn-primary btn-toggle d-inline-flex align-items-center rounded border-0" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">
					Java</button>
				<div class="collapse show" id="home-collapse">
					<ul class="btn-toggle-nav list-unstyled mt-2">
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Ŭ����</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">�������̽�</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">NetWork</a></li>
					</ul>
				</div>
			</li>
			<li class="mb-2">
				<button class="btn btn-primary btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse"
					aria-expanded="false">������</button>
				<div class="collapse" id="dashboard-collapse">
					<ul class="btn-toggle-nav list-unstyled mt-2">
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Python</a></li>
						<li><a href="#"class "link-primaryd-inline-flextext-decoration-nonerounded">NumPy</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Pandas</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Tensorflow</a></li>
					</ul>
				</div>
			</li>
			<li class="mb-2">
				<button class="btn btn-primary btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse"
					aria-expanded="false">Orders</button>
				<div class="collapse" id="orders-collapse">
					<ul class="btn-toggle-nav list-unstyled mt-2">
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">New</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Processed</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Shipped</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Returned</a></li>
					</ul>
				</div>
			</li>
			<li class="mb-2">
				<button class="btn btn-primary btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse"
					aria-expanded="false">Account</button>
				<div class="collapse" id="account-collapse">
					<ul class="btn-toggle-nav list-unstyled mt-2">
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">New...</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Profile</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Settings</a></li>
						<li><a href="#" class="link-primary d-inline-flex text-decoration-none rounded">Sign out</a></li>
					</ul>
				</div>
			</li>
		</ul>
	</aside>
	<%-- footer.jsp --%>
	<tiles:insertAttribute name="footer" />
	<script>
		$(function() {
			$('#wbtn').click(function() {
				location = "boardForm";
			});

		});
	</script>
</body>
</html>

