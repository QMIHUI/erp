<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="${pageContext.request.contextPath }/sys/users/userList.jsp">系统管理</a></li>
    <li><a href="logList.jsp">日志管理</a></li>
    <li><a href="#">日志查看</a></li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>日志信息</span></div>
  <ul class="forminfo">
    <li>
      <label>日志编号</label>
      <cite>${journal.jId}</cite>
    </li>
    <li>
      <label>被修改者员工姓名</label>
      <cite>${journal.bname}</cite>
    </li>
    <li>
      <label>修改者员工ID</label>
      <cite>${journal.uId}</cite>
    </li>
    <li>
      <label>日志内容</label>
      <cite>${journal.jcontent}</cite>
    </li>
    <li>
      <label>记录时间</label>
      <cite>${journal.jdate}</cite>
    </li>

    <li>
      <label>&nbsp;</label>
      <input name="" type="button" class="btn" value="返回" onclick="window.history.go(-1);"/>
    </li>
  </ul>
</div>
</body>
</html>
