<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>丝苗米数据展示页</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/public.css" />
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>丝苗米系统</h1>
        <div class="publicHeaderR">
            <p><span>下午好！</span><span style="color: #fff21b"> ${userSession.username }</span> , 欢迎你！</p>
            <a href="${pageContext.request.contextPath }/jsp/logout.do">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
 <!--主体内容-->
 <section class="publicMian ">
     <div class="left">
         <h2 class="leftH2"><span class="span1"></span>数据展示页<span></span></h2>
         <nav>
             <ul class="list">
              <li><a href="${pageContext.request.contextPath }/jsp/provider.do?method=query">可视化平台</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/search.jsp">查病虫措施</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/insert.jsp">增加病虫害</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/query.jsp">删除病虫害</a></li>
              <li><a href="${pageContext.request.contextPath }/jsp/logout.do">退出该系统</a></li>
             </ul>
         </nav>
     </div>
     <input type="hidden" id="path" name="path" value="${pageContext.request.contextPath }"/>
     <input type="hidden" id="referer" name="referer" value="<%=request.getHeader("Referer")%>"/>