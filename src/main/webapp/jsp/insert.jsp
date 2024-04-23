<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>增加病虫害</title>
      <link rel="stylesheet" href="../css/common.css">
      <link rel="stylesheet" href="../css/index.css">
      <link rel="stylesheet" href="../css/index.less">
      <link rel="stylesheet" href="../font/iconfont.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/jquery.code.js"></script>
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
      background:url("../image/background.png") no-repeat top center;
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
      padding-bottom:5px;
      margin: 5px;
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
 <header>
    <div class="iconfont icon-menu" id="iconmenu">
      <div class="menu">
        <ul class="menu_super">
            <li><a href="../index.html">首页</a></li>
            <li><a href="search.jsp">查病虫措施</a></li>
            <li><a href="insert.jsp">增加病虫害</a></li>
            <li><a href="query.jsp">删除病虫害</a></li>
          <li><a href="logout.do">退出该系统</a></li>
        </ul>
    </div>
    </div>
    <h1>丝苗米生长情况</h1>
    <div class="showTime"></div>
  </header>
<form action="${pageContext.request.contextPath }/Insert?method=insert" method="POST">
  <span>如果有病害，请输入病害名称:(若无则填0)</span>
  <input type="text" name="Date" required><br>
  <span>如果有虫害，请输入虫害名称:(若无则填0)</span>
  <input type="text" name="Date" required><br>
  <span>请输入所遭受该病虫害的土壤编号:(必填)</span>
  <input type="number" name="number" required><br>
  <input type="submit" value="添加"><br>
</form>

</body>
</html>


