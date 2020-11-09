<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>营销管理</li>
    <li>客户管理</li>
    <li>客户详情</li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>客户信息</span></div>
  <ul class="forminfo">
    <li>
      <label>姓名</label>
      <cite>${custom.customname}</cite>
    </li>
    <li>
      <label>性别</label>
      <cite>${custom.sex}</cite>
    </li>
    <li>
      <label>所属公司名称</label>
      <cite>${custom.company}</cite>
    </li>
    <li>
      <label>联系方式</label>
      <cite>${custom.telephone}</cite>
    </li>
    <li>
      <label>联系地址</label>
      <cite>${custom.homeaddress}</cite>
    </li>
    <li>
      <label>所属区域</label>
      <cite>${custom.province.pName}</cite>
    </li>
    <li>
      <label>描述</label>
      <cite>${custom.distract}</cite>
    </li>
    <li>
      <label>状态</label>
      <cite>${custom.status}</cite>
    </li>
    <li>
      <label>创建人</label>
      <cite>${custom.users.uname}</cite>
    </li>
    <li>
      <label>创建时间</label>
      <cite>${custom.createtime}</cite>
    </li>
    <li>
      <label>&nbsp;</label>
      <input name="" type="button" class="btn" value="返回" onclick="window.history.go(-1);"/>
    </li>
  </ul>
</div>
</body>
</html>
