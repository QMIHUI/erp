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
    <li>出库管理</li>
    <li>添加出库</li>
  </ul>
</div>
<form action="${pageContext.request.contextPath }/${user.uId}/deliveryAdd.do" method="post">
<div class="formbody">
  <div class="formtitle"><span>出库信息</span></div>
  <ul class="forminfo">
    <li>
      <label>订单编号</label>
      <select class="dfinput" name="indent">
        <option selected="selected">请选择</option>
        <c:forEach items="${listOrders}" var="lo">
        <option value="${lo.orderId}">${lo.orderId}</option>
        </c:forEach>
      </select>
      &nbsp;&nbsp;
      <i>必须是审核通过的订单</i>
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
      <label>出库时间</label>
      <%!
        String getCurrentTime(Date d){
          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
          return sdf.format(d);
        }
      %>
      <input name="cDate" type="text" value="<%=getCurrentTime(new Date()) %>" readonly="readonly" class="dfinput" />
      <i>必填</i>
    </li>
    <li>
      <label>出库人</label>
      <select class="roinput" name="userId" onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseover="this.releaseCapture();">
        <option value="${user.uId}">${user.uname}</option>
      </select>
      <i>不能编辑</i>
    <li>
      <label>&nbsp;</label>
      <input type="hidden" name="state" value="1">
      <input name="" type="submit" class="btn" value="确定"/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="" type="button" class="btn" value="返回" onclick="window.location.href='deliveryList.jsp'"/>
    </li>

  </ul>
</div>
</form>
</body>
</html>
