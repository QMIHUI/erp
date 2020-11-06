<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>职位管理</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="${pageContext.request.contextPath}/sys/users/userList.jsp">系统管理</a></li>
    <li><a href="deptList.jsp">职位管理</a></li>
    <li><a href="#">修改</a></li>
  </ul>
</div>
<form action="${pageContext.request.contextPath}/updateJob.do" method="get">
  <div class="formbody">
    <div class="formtitle"><span>职位信息</span></div>
    <ul class="forminfo">
      <li>
        <label>职位ID</label>
        <input name="jobId" type="text" value="${job.jobId}" class="dfinput" readonly="readonly"/>
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>职位名称</label>
        <input name="jobName" type="text" value="${job.jobName}" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li> <label>所属部门：</label>
        <select class="dfselect" name="deptId">
          <option value="0">请选择部门</option>
          <c:forEach items="${listDept}" var="ld">
            <c:if test="${job.jobDeptId == ld.deptId}">
              <option value="${ld.deptId}" selected="selected">${ld.deptName}</option>
            </c:if>
            <c:if test="${job.jobDeptId != ld.deptId}">
              <option value="${ld.deptId}">${ld.deptName}</option>
            </c:if>
          </c:forEach>
        </select>
      </li>
      <li>
        <label>&nbsp;</label>
        <input name="" type="submit" class="btn" value="确认"/>
        <input name="" type="button" class="btn" value="返回"  onclick="window.history.go(-1);"/>
      </li>
    </ul>
  </div>
</form>
</body>
</html>
