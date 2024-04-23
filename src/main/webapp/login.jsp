<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <title>登录界面</title>
</head>
<body>
<section>
    <!-- 背景颜色 -->
    <div class="color"></div>
    <div class="color"></div>
    <div class="color"></div>
    <div class="box">
        <!-- 背景圆 -->
        <div class="circle" style="--x:0"></div>
        <div class="circle" style="--x:1"></div>
        <div class="circle" style="--x:2"></div>
        <div class="circle" style="--x:3"></div>
        <div class="circle" style="--x:4"></div>
        <!-- 登录框 -->
        <div class="container">
            <div class="form">
                <h2>登录</h2>
                <form class="loginForm" action="${pageContext.request.contextPath }/login.do"  name="actionForm" id="actionForm"  method="post" >
                    <div class="inputBox">
                        <input type="text" class="input-text" id="username" name="username" placeholder="请输入用户名">
                    </div>
                    <div class="inputBox">
                        <input type="password" id="password" name="password" placeholder="请输入密码" >
                    </div>
                    <div class="inputBox">
                        <input type="submit" value="登录">
                    </div>

                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>