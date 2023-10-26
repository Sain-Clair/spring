<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<style>
/* 스타일링 요소 추가 예제 */
/* 배경 색상 및 글꼴 설정 */
body {
    background-color: #f0f0f0;
    font-family: 'Helvetica Neue', sans-serif;
}

/* 로그인 폼 스타일링 */
.container {
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    margin-top: 20px;
}

/* 입력 필드 스타일링 */
.form-group {
    margin-bottom: 20px;
}

.form-group label {
    font-weight: bold;
    color: #333;
}

.form-control {
    width: 100%;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 5px;
}

/* 로그인 버튼 스타일링 */
.btn-primary {
    background-color: #0077b6;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 15px 30px;
    cursor: pointer;
    margin-right: 10px;
    font-weight: bold;
    text-transform: uppercase;
    transition: background-color 0.3s;
}

.btn-primary:hover {
    background-color: #005786;
}

/* ID/PWD 찾기 버튼 스타일링 */
.btn-secondary {
    background-color: #00a9b7;
    color: #fff;
    border: none;
    border-radius: 5px;
    padding: 15px 30px;
    cursor: pointer;
    font-weight: bold;
    text-transform: uppercase;
    transition: background-color 0.3s;
}

.btn-secondary:hover {
    background-color: #00869c;
}

/* 스타일링 요소 추가 예제 끝 */

</style>
<article>
	<header>
		<h1>로그인</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%-- contents --%>
		<form method="post" action="loginProcess" id="loginInfo">
			<div class="form-group">
				<label for="id">ID</label>
				<input type="text" class="for-control" id= "id" name="id" value="xman" 
				placeholder="아이디 입력" 
				maxlength="10" required="required" pattern=".{2,10}">
			</div>
			<div class="form-group">
				<label for="pwd">PWD</label>
				<input type="text" class="for-control" id= "pwd" name="pwd" value="xman"
				placeholder="******"   maxlength="10">
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary">로그인</button>
				<button type="button" class="btn btn-primary">ID/PWD 찾기</button>
			</div>
		</form>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>
