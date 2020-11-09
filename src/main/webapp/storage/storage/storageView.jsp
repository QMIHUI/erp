<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
    <li>仓库管理</li>
    <li>仓库详情</li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>仓库信息</span></div>
  <ul class="forminfo">
    <li>
      <label>仓库名称</label>
      <cite>${warehouse.name}</cite>
    </li>
    <li>
      <label>仓库地址</label>
      <cite>${warehouse.province.pName}省${warehouse.city.cName}市${warehouse.cAddress}</cite>
    </li>
    <li>
      <label>所属区域</label>
      <cite>${warehouse.province.pName}省${warehouse.city.cName}市</cite>
    </li>
    <li>
      <label>负责人</label>
      <cite>${warehouse.users.uname}</cite>
    </li>
    <li>
      <label>联系方式</label>
      <cite>${warehouse.phone}</cite>
    </li>
    <li>
      <label>描述</label>
      <cite>${warehouse.details}</cite>
    </li>
    <li>
      <label>状态</label>
      <cite>
        <c:if test="${warehouse.state==1}">可用</c:if>
        <c:if test="${warehouse.state==0}">不可用</c:if>
      </cite>
    </li>
    <li>
      <label>创建人</label>
      <cite>${warehouse.users.names}</cite>
    </li>
    <li>
      <label>创建时间</label>
      <cite>${warehouse.creationTime}</cite>
    </li>
    <li>
      <label>&nbsp;</label>
      <input name="" type="button" class="btn" value="返回" onclick="window.history.go(-1);"/>
    </li>
  </ul>
</div>
</body>
</html>
