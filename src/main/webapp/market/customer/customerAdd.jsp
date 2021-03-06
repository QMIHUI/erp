<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <li>添加</li>
  </ul>
</div>
<div class="formbody">
  <form action="${pageContext.request.contextPath}/addCustomer.do" method="get">
    <div class="formtitle"><span>客户信息</span></div>
    <ul class="forminfo">
      <li>
        <label>姓名</label>
        <input name="custName" type="text" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>性别</label>
        <cite>
            <input name="custSex" type="radio" value="男" checked="checked" />男
            <input name="custSex" type="radio" value="女" />女
        </cite>
      </li>
      <li>
        <label>所属公司名称</label>
        <input name="custCompany" type="text" class="dfinput" />
         <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>联系方式</label>
        <input name="custTelephone" type="text" class="dfinput" />
         <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>联系地址</label>
        <input name="custHomeAddress" type="text" class="dfinput" />
         <i>必填，不能超过130个字符</i>
      </li>
      <li>
        <label>所属区域</label>
        <select class="dfselect" name="custProvince">
            <option>请选择</option>
            <c:forEach items="${provinceList}" var="province">
              <option value="${province.id}">${province.pName}</option>
            </c:forEach>
        </select>
        省
        <i>必选</i>
      </li>
      <li>
        <label>描述</label>
        <textarea class="textinput" name="custDesc"></textarea>
        <i>可选</i>
      </li>
      <li>
        <label>创建人</label>
        <input name="uId" type="text" value="${user.uId}" readonly="readonly" class="roinput" />
        <i>不能编辑</i>
      </li>
      <%--<li>
        <label>创建时间</label>
        <input name="" type="text" value="2017-11-18 15:36:10" readonly="readonly" class="roinput" />
        <i>不能编辑</i>
      </li>--%>
      <li>
        <label>&nbsp;</label>
        <input name="" type="submit" class="btn" value="确定"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="" type="button" class="btn" value="返回" onclick="window.location.href='customerList.html'"/>
      </li>
    </ul>
  </form>
</div>
</body>
</html>
