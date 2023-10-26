<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%@ include file = "../temp/header.jsp"%>
<article>
	<header style="color:white">FreeBoard</header>
	<ul class="list-unstyled">
	<li class="border-top my-3">ICT No1 Detail 페이지 입니다.</li>
	</ul>
	<div class="container">
		<div class="row">
			<!-- UpDemoController에서 v로 설정.... v.title -->
			<h2>FreeBoard Detail</h2>
			<div class="row mb-3">
				<label for="subject" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" name="title" class="form-control" id="title"
						readonly="readonly" value="${v.title}">
				</div>
			</div>
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-10">
					<input type="text" name="writer" class="form-control" id="writer"
						readonly="readonly" value="${v.writer}">
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<textarea name="content" rows="10" cols="50" id="content"
						readonly="readonly"> ${v.content}</textarea>
				</div>
			</div>
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">아이피</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" readonly="readonly"
						value="${v.reip}">
				</div>
			</div>
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">날짜</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" readonly="readonly"
						value="${v.bdate}">
				</div>
			</div>
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">이미지</label>
				<div class="col-sm-10">
					<img src="${rPath}/imgfile/${v.imgn}"
						style="width: 60px; height: 40px; border: dotted 1px; curcor: pointer" />
				</div>
			</div>
			<h2>댓글 영역</h2>
			<%-- 댓글 영역 시작 --%>
			<form action="bcominsert" method="post">
				<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>"> 
					<input type="hidden" name="ucode" value="${v.num}">
				<div class="row mb-3">
					<label for="uwriter" class="col-sm-2 col-form-label">작성자</label>
					<div class="col-sm-10">
						<input type="text" name="uwriter" class="form-control" id="uwriter">
					</div>
				</div>
				<div class="row">
					<label for="ucontent" class="col-sm-2 col-form-label">내용</label>
					<div class="col-sm-10">
						<textarea name="ucontent" rows="10" cols="50" id="content"> </textarea>
					</div>
				</div>
				<button class="btn btn-primary" type="submit">등록</button>
				<div class="col-sm-10">
					<table class="table">
						<thead>
							<tr>
								<td>no</td>
								<td>writer</td>
								<td>content</td>
								<td>reip</td>
								<td>date</td>
							</tr>
						</thead>
						 <tbody id="comm">
							 <c:forEach var="e" items="${listComm}">
							 <tr>
							 <td>${e.num }</td>
							 <td><a href="" >${e.uwriter }</a></td>
							 <td>${e.ucontent }</td>
							 <td>${e.reip }</td>
							 <td>${e.uregdate}</td>
							 </tr>
							 </c:forEach> 
						 </tbody>
					</table>
				</div>
			</form>
		</div>
		<%@ include file = "../temp/pageProcess.jsp"%>
		<div class="container text-center" role="group">
			<form action="upboardmodify" method="post">
			<%-- chkpwdForm을 화면출력하고, sysout으로 2파라미터값을 출력 해보기 --%>
				<button class="btn btn-primary" type="submit">수정</button>
				<input type="hidden" name="num" value="${v.num }">
			</form>
			
			<form action="upboardDelete" method="post">
				<button class="btn btn-primary" type="submit">삭제</button>
				<input type="hidden" name="num" value="${v.num }">
			</form>
            	<button class="btn btn-danger" type="button" onclick="location='upList'">리스트</button>
			</div>
		</div>
</article>
<%@ include file = "../temp/footer.jsp"%>