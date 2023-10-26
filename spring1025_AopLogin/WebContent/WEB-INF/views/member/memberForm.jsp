<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>

<article>
	<header>
		<h1>ȸ������</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%-- contents --%>
		<form method="post" action="memberIn">
			<table class="styled-form">
				<tr>
					<td>���̵�</td>
					<td>
						<div class="form-input">
							<input type="text" name="id" id="id" maxlength="10">
							<button type="button" id="idChkBtn">�ߺ�Ȯ��</button>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" id="target"></td>
				</tr>
				<tr>
					<td>��й�ȣ</td>
					<td>
						<div class="form-input">
							<input type="password" name="pwd" maxlength="10">
						</div>
					</td>
				</tr>
				<tr>
					<td>�̸�</td>
					<td>
						<div class="form-input">
							<input type="text" name="name">
						</div>
					</td>
				</tr>
				<tr>
					<td>�̸���</td>
					<td>
						<div class="form-input">
							<input type="email" name="email" id="email">
						</div>
					</td>
				</tr>
				<tr>
					<td>��ȭ��ȣ</td>
					<td>
						<div class="form-input">
							<input type="tel" name="tel" id="tel">
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="����"
						class="submit-button"></td>
				</tr>
			</table>
		</form>
	</div>
</article>
<script>
	window.onload = function() {
		document.querySelector("#idChkBtn").onclick = function(e) {
			//�������� ���� ��� 
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
						msg = "��밡���� ���̵� �Դϴ�.";
						document.getElementById("target").style.backgroundColor = "orange";
					} else {
						msg = "������� ���̵� �Դϴ�.";
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