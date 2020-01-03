<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.util.*" isELIgnored="false"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="name" value="googie" scope="request" />

<c:out value="${name}" />
<hr />
<c:set var="age" value="12" scope="page" />
<c:set var="age" value="11" scope="request" />
<c:set var="age" value="10" scope="session" />
<c:set var="age" value="9" scope="application" />
  
  <!-- EL会按照从高到低的优先级顺序获取
pageContext>request>session>application -->
4个作用域都有name,优先获取出来的是 ： ${age}
<hr>

${param.name}