<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>

<article>
	<header>
		<h1>회원가입</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%-- contents --%>
		<form method="post" action="memberIn">
			<table class="styled-form">
				<tr>
					<td>아이디</td>
					<td>
						<div class="form-input">
							<input type="text" name="id" id="id" maxlength="10">
							<button type="button" id="idChkBtn">중복확인</button>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" id="target"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<div class="form-input">
							<input type="password" name="pwd" maxlength="10">
						</div>
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>
						<div class="form-input">
							<input type="text" name="name">
						</div>
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<div class="form-input">
							<input type="email" name="email" id="email">
						</div>
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>
						<div class="form-input">
							<input type="tel" name="tel" id="tel">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="가입"
						class="submit-button"></td>
				</tr>
			</table>
		</form>
	</div>
</article>
<script>
	window.onload = function() {
		document.querySelector("#idChkBtn").onclick = function(e) {
			//폼전송을 막는 기능 
			e.preventDefault();
			let param = "id=" + document.getElementById("id").value;
			sendRequest("idcheck", param, res, "get");
		};
		function res() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					let idCnt = parseInt(xhr.responseText);
					console.log(idCnt + ":" + typeof (idCnt));
					let msg = "";
					if (idCnt === 0) {
						msg = "사용가능한 아이디 입니다.";
						document.getElementById("target").style.backgroundColor = "orange";
					} else {
						msg = "사용중인 아이디 입니다.";
						document.getElementById("target").style.backgroundColor = "red";
						document.getElementById("target").style.color = 'white';
					}
					document.getElementById("target").innerHTML = msg;
				}
			}
		}
	};
</script>
</body>
<%@include file="../temp/footer.jsp"%>
</html>