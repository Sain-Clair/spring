<%@page import="dao.FboardDao"%>
<%@page import="kr.co.ictedu.mvc.dto.FboardDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>addDemo.jsp �Դϴ�.</title>
</head>
<body>
<%
	FboardDTO vo = new FboardDTO();
	vo.setSubject("����3");
	vo.setWriter("�����2");
	vo.setContent("MyBatis2");
	vo.setReip(request.getRemoteAddr());
	FboardDao.getDao().addFboard(vo);
%>
</body>
</html>