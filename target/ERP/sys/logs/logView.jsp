<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="../users/userList.jsp">系统管理</a></li>
    <li><a href="logList.jsp">日志管理</a></li>
    <li><a href="#">日志查看</a></li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>日志信息</span></div>
  <ul class="forminfo">
    <li>
      <label>员工编号</label>
      <cite>9527</cite>
    </li>
    <li>
      <label>员工姓名</label>
      <cite>唐寅</cite>
    </li>
    <li>
      <label>操作模块</label>
      <cite>用户管理</cite>
    </li>
    <li>
      <label>日志内容</label>
      <cite>进行了添加用户xxx的操作</cite>
    </li>
    <li>
      <label>记录时间</label>
      <cite>2016-11-20 15:05:29</cite>
    </li>

    <li>
      <label>&nbsp;</label>
      <input name="" type="button" class="btn" value="返回" onclick="window.history.go(-1);"/>
    </li>
  </ul>
</div>
</body>
</html>