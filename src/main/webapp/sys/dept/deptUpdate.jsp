<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门管理</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/laydate/laydate.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="${pageContext.request.contextPath }/sys/users/userList.jsp">系统管理</a></li>
    <li><a href="deptList.jsp">部门管理</a></li>
    <li><a href="#">修改</a></li>
  </ul>
</div>
<form action="${pageContext.request.contextPath }/updateDept.do" method="get">
  <div class="formbody">
    <div class="formtitle"><span>部门信息</span></div>
    <ul class="forminfo">
      <li>
        <label>部门编号</label>
        <input name="deptId" type="text" value="${dept.deptId}" class="dfinput" readonly/>
        <i>不可修改</i>
      </li>
      <li>
        <label>部门名称</label>
        <input name="deptName" type="text" value="${dept.deptName}" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>&nbsp;</label>
        <input type="submit" class="btn" value="确认"/>
        <input type="button" class="btn" value="返回"  onclick="window.history.go(-1);"/>
      </li>
    </ul>
  </div>
</form>
</body>
</html>
