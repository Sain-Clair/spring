<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }

        nav {
            background-color: #007bff;
            padding: 10px;
            color: #fff;
        }

        nav a {
            color: #fff;
            text-decoration: none;
            margin: 0 10px;
        }

        nav a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div>
    <nav>
        <a href="main">Home</a> |
        <a href="<%=application.getContextPath()%>/memForm">Member</a> |
        <a href="#">ParamTest</a> |
        <a href="#">DBTest</a>
    </nav>
</div>
</body>
</html>
