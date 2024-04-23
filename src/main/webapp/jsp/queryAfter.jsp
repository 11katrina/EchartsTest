<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>病害与虫害删除</title>
  <style>
    * {
      margin: 0;
      padding: 0;
    }
    html,
    body{
      height: 100%;
    }
    body{
      background:url("./image/background.png") no-repeat top center;
      background-size: 100% 100%;
    }
    .a{
      margin-bottom: 20px;
    }
    .b{
      margin-top:45px;
      margin-bottom: 20px;
    }
    .checkbox{
      width:600px;
      height:300px;
      background:rgb(195,195,195,0.4);
      position:absolute;
      top:50%;
      left:40%;
      margin: -150px -120px;
      transform:translaet(-50%,-50%);
    }
    .d{
      font-size:5px;
      color:black;
      font-family:"宋体";
    }
    .button{
      width:200px;
      height:30px;
      margin-left:50px;
      background-color:rgba(35, 171, 198, 0.371);
      border: none;
    }
    .f{
      margin-left:10%;
      margin-top:45px;
      margin-bottom: 25px;
      font-size: 40px;
      font-family:"宋体";
    }
    .g{
      margin-left:40px;
      margin-bottom: 20px;
    }
    .n{
      font-size: 25px;
      font-family:"宋体";
    }
    input{
      font-size: 25px;
      font-family:"宋体";

    }
    form{
      text-align: right;
      float: left;
      width: 50%;
      margin:15% 0 0 15%;
    }
  </style>
</head>
<body>
<c:if test="${not empty diseases}">
  <hr>
  <h3>查询结果：</h3>

  <form action="${pageContext.request.contextPath}/Delete?method=delete" method="post">
    <c:forEach items="${diseases}" var="disease">
      <label>
        <input type="hidden" name="number" value="${disease.soilNumber}">
        <c:if test="${not empty disease.disease}">
        <input type="checkbox" name="isDelete" value="disease">
        ${disease.disease}
        </c:if>
        <c:if test="${not empty disease.pest}">
        <input type="checkbox" name="isDelete" value="Pest">
        ${disease.pest}
        </c:if>
        <c:if test="${not empty disease.other}">
        <input type="checkbox" name="isDelete" value="other">
        ${disease.other}
        </c:if>
      </label><br>
    </c:forEach>
    <button type="submit">删除</button>
  </form>

</c:if>
</body>
</html>

