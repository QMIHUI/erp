<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门管理</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../laydate/laydate.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="../users/userList.jsp">系统管理</a></li>
    <li><a href="deptList.jsp">部门管理</a></li>
    <li><a href="#">修改</a></li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>部门信息</span></div>
  <ul class="forminfo">
    <li>
      <label>部门编号</label>
      <input name="" type="text" value="001" class="dfinput" readonly/>
      <i>必填，不能超过30个字符</i>
    </li>
    <li>
      <label>部门名称</label>
      <input name="" type="text" value="研发部" class="dfinput" />
      <i>必填，不能超过30个字符</i>
    </li>
    <li>
      <label>所属区域</label>
      <select class="dfselect">
          <option>请选择</option>
          <option>北京</option>
          <option selected="selected">江苏</option>
          <option>天津</option>
      </select>
      省
      <select class="dfselect">
          <option>请选择</option>
          <option>北京</option>
          <option selected="selected">南京</option>
          <option>天津</option>
      </select>
      市
      <i>必选</i>
    </li>

    <li>
      <label>&nbsp;</label>
      <input name="" type="button" class="btn" value="确认保存"/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="" type="button" class="btn" value="返回"  onclick="window.history.go(-1);"/>
    </li>
  </ul>
</div>
</body>
</html>