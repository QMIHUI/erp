<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
    <li>修改仓库</li>
  </ul>
</div>
<form action="${pageContext.request.contextPath }/updateWarehouse.do" method="post">
    <input type="hidden" name="id" value="${warehouse.id}">
<div class="formbody">
  <div class="formtitle"><span>仓库信息</span></div>
  <ul class="forminfo">
    <li>
      <label>仓库名称</label>
      <input name="name" type="text" value="${warehouse.name}" class="dfinput" />
      <i>必填，不能超过30个字符</i>
    </li>
    <li>
      <label>仓库地址</label>
      <input name="cAddress" type="text" value="${warehouse.cAddress}" class="dfinput" />
      <i>必填，不能超过300个字符</i>
    </li>
    <li>
      <label>所属区域</label>
      <select class="dfselect" name="provinceId">
          <option>请选择</option>
          <c:forEach items="${listprovince}" var="lp">
              <c:if test="${warehouse.provinceId==lp.id}">
                <option value="${lp.id}" selected="selected">${lp.pName}</option>
              </c:if>
              <c:if test="${warehouse.provinceId!=lp.id}">
                  <option value="${lp.id}" >${lp.pName}</option>
              </c:if>
          </c:forEach>
      </select>
      省
      <select class="dfselect" name="cityId">
          <option>请选择</option>
          <c:forEach items="${listcity}" var="lc">
              <c:if test="${warehouse.cityId==lc.cId}">
                  <option value="${lc.cId}" selected="selected">${lc.cName}</option>
              </c:if>
              <c:if test="${warehouse.cityId!=lc.cId}">
                  <option value="${lc.cId}" >${lc.cName}</option>
              </c:if>
          </c:forEach>
      </select>
      市
      <i>必选</i>
    </li>
    <li>
      <label>负责人</label>
      <select class="dfselect" name="usreId">
          <option>请选择</option>
          <c:forEach items="${listusers}" var="ls">
              <c:if test="${ls.uId==warehouse.usreId}">
                <option value="${ls.uId}" selected="selected">${ls.uname}</option>
              </c:if>
              <c:if test="${ls.uId!=warehouse.usreId}">
                  <option value="${ls.uId}">${ls.uname}</option>
              </c:if>
          </c:forEach>
      </select>
       <i>只能是财务部仓库管理员职位</i>
    </li>
    <li>
      <label>联系方式</label>
      <input name="phone" type="text" value="${warehouse.phone}" class="dfinput" />
       <i>必填，不能超过30个字符</i>
    </li>
    <li>
      <label>描述</label>
      <textarea name="details" class="textinput">${warehouse.details}</textarea>
      <i>可选</i>
    </li>
    <li>
      <label>状态</label>
      <select name="state" class="dfselect">
          <c:if test="${warehouse.state==1}">
              <option selected="selected" value="1">可用</option>
              <option value="0">不可用</option>
          </c:if>
          <c:if test="${warehouse.state==0}">
              <option value="1">可用</option>
              <option selected="selected" value="0">不可用</option>
          </c:if>
        </select>
      <i>必选</i>
    </li>
    <li>
      <label>创建人</label>
      <input name="usersId" type="text" value="${warehouse.usersId}" placeholder="${warehouse.users.names}" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
      <label>创建时间</label>

      <input name="creationTime" type="text" value="${warehouse.creationTime}" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
      <label>&nbsp;</label>
      <input name="" type="submit" class="btn" value="确定"/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="" type="button" class="btn" value="返回" onclick="window.location.href='storageList.html'"/>
    </li>
  </ul>
</div>
</form>
</body>
</html>
