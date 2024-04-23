<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>这里是一个警告信息</title>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.code.js"></script>
<style>
    *{
      margin: 0;
      padding: 0;
    }
    html,
    body{
      height: 100%;
    }
    body{
      background:url("../image/background.png") no-repeat top center;
      background-size: 100% 100%;
    }
    .container{
      text-align: center;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }
    .alert{
      margin-left:30%;
      width: 400px;
      max-width: 90%;
      background-color: #eeeeee;
      border: 1px solid black;
      border-radius: 6px;
      padding: 20px;
      text-align: center;
      font-weight: bold;
      box-shadow: 0 10px 15px rgba(0, 0, 0, 0.3);
    }
    .btn{
      display: inline-block;
      padding: 5px 10px;
      background-color: #4286f4;
      color: #ffffff;
      border-radius: 4px;
      cursor: pointer;
    }
  </style>
   <script>
      function hideConfirm(){
        //获取弹窗元素节点
        var alertBox = document.getElementById("alertBox");
        //设置弹窗为隐藏
        alertBox.style.display = "none";
      }
    </script>
</head>
<body>
   <form action="${pageContext.request.contextPath }/jsp/search.jsp"  method="post"  onsubmit="hideConfirm()">
<div class="container">
  <div class="alert" id="alertBox">
    <p>土壤病虫措施！</p>
    <c:forEach var="data" items="${datas}">
        <c:if test="${not empty data.measure}">
       			<span>${data.measure}</span>
       	</c:if>
   	</c:forEach>

    <c:if test="${not empty message}">
         <p>${message}</p>
    </c:if>
    <button onclick="hideConfirm()" class="btn">确认</button>
  </div>
</div>
</body>
</html>
