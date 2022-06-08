<%@page import="vo.MovieVo"%>
<%@page import="dao.MovieDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>영화 정보</h1>
	<ul>
	<%
		for (MovieVo vo : MovieDao.selectAll())
		{
			%>
			<li><%=vo.getTitle() %> <a href="detail?mnum=<%= vo.getMnum()%>">상세 보기</a></li>
			<%
		}
	%>
	</ul>
</body>
</html>