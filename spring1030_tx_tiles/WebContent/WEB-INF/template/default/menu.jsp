<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<nav class="navbar navbar-expand-sm mybgColor">
	<div class="container-fluid">
		<ul class="navbar-nav">
			<li class="nav-item"><a href="${cPath}/main" class="nav-link active">Home</a></li>
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