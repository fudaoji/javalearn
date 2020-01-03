<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="utf-8"
%>
	
	
	
<%
	pageContext.setAttribute("pageContext", "yes");
	request.setAttribute("request", "yes");
%>

<% 
	out.println(pageContext.getAttribute("pageContext"));
	out.println(request.getAttribute("request"));
	
	response.sendRedirect("getContext.jsp");
%>


<br><a href="getContext.jsp">客户端跳转获取值</a> <br>

<!--服务端调整取值 -->
<%-- <jsp:forward page="getContext.jsp"/> --%>