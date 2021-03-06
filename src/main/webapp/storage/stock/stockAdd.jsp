<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <li>仓库管理</li>
    <li>入库管理</li>
    <li>添加入库</li>
  </ul>
</div>
<form action="${pageContext.request.contextPath }/${user.uId}/stockAdd.do" method="post">
<div class="formbody">
  <div class="formtitle"><span>入库信息</span></div>
  <ul class="forminfo">
    <li>
      <label>采购单编号</label>
      <select class="dfinput" name="rkIndent">
        <option selected="selected">请选择</option>
        <c:forEach items="${listPurchase}" var="lp">
          <option value="${lp.purchaseId}">${lp.purchaseId}</option>
        </c:forEach>
      </select>
      &nbsp;&nbsp;
      <i>必须是审核通过的采购单</i>
    </li>
    <li>
      <label>仓库</label>
      <select class="dfselect" name="warehouseId">
        <option>请选择</option>
        <c:forEach items="${listWarehouse}" var="lw">
          <option value="${lw.id}">${lw.name}</option>
        </c:forEach>
      </select>
      <i>必选</i>
    </li>
    <li>
      <label>入库时间</label>
      <%!
        String getCurrentTime(Date d){
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
          return sdf.format(d);
        }
      %>
      <input name="rkDate" type="text" value="<%=getCurrentTime(new Date()) %>" class="dfinput" />
      <i>必填</i>
    </li>
    <li>
      <label>入库人</label>
      <select class="roinput" name="userId" onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseover="this.releaseCapture();">
        <option value="${user.uId}">${user.uname}</option>
      </select>
      <i>不能编辑</i>
    <li>
      <label>&nbsp;</label>
    <input type="hidden" name="state" value="1">
      <input name="" type="submit" class="btn" value="确认"/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="" type="button" class="btn" value="返回" onclick="window.location.href='stockList.jsp'"/>
    </li>
  </ul>
</div>
</form>
</body>
</html>
