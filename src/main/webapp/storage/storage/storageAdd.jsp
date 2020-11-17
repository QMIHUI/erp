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
    <li>仓库管理</li>
    <li>添加仓库</li>
  </ul>
</div>
<form action="${pageContext.request.contextPath }/addWarehouse.do" method="post">
<div class="formbody">
  <div class="formtitle"><span>仓库信息</span></div>
  <ul class="forminfo">
    <li>
      <label>仓库名称</label>
      <input name="name" type="text" class="dfinput" />
      <i>必填，不能超过30个字符</i>
    </li>
    <li>
      <label>仓库地址</label>
      <input name="cAddress" type="text" class="dfinput" />
      <i>必填，不能超过300个字符</i>
    </li>
    <li>
      <label>所属区域</label>
      <select class="dfselect" name="provinceId">
          <option selected="selected">请选择</option>
          <c:forEach items="${listprovince}" var="lp">
          <option value="${lp.id}">${lp.pName}</option>
          </c:forEach>
      </select>
      省
      <select class="dfselect" name="cityId">
          <option selected="selected">请选择</option>
        <c:forEach items="${listcity}" var="lc">
          <option value="${lc.cId}">${lc.cName}</option>
        </c:forEach>
      </select>
      市
      <i>必选</i>
    </li>
    <li>
      <label>负责人</label>
      <select class="dfselect" name="usreId">
          <option selected="selected">请选择</option>
          <c:forEach items="${listusers}" var="ls">
          <option value="${ls.uId}">${ls.uname}</option>
        </c:forEach>
      </select>
       <i>只能是财务部仓库管理员职位</i>
    </li>
    <li>
      <label>联系方式</label>
      <input name="phone" type="text" class="dfinput" />
       <i>必填，不能超过30个字符</i>
    </li>
    <li>
      <label>描述</label>
      <textarea class="textinput" name="details"></textarea>
      <i>可选</i>
    </li>
    <li>
      <label>状态</label>
      <select class="dfselect" name="state">
          <option value="1">可用</option>
          <option value="0">不可用</option>
        </select>
      <i>必选</i>
    </li>
    <li>
      <label>创建人</label>
      <select class="dfselect" name="usersId" onbeforeactivate="return false;" onfocus="this.blur();" onmouseover="this.setCapture();" onmouseover="this.releaseCapture();">
        <option value="${user.uId}">${user.uname}</option>

      </select>
      <%--<input name="usersId" type="text" value="hh" readonly="readonly" class="roinput" />--%>
      <i>不能编辑</i>
    </li>
    <li>
      <label>创建时间</label>
        <%!
            String getCurrentTime(Date d){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(d);
            }
        %>
      <input name="creationTime" type="text" value="<%=getCurrentTime(new Date()) %>" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
      <label>&nbsp;</label>
      <input name="" type="submit" class="btn" value="确定"/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="" type="button" class="btn" value="返回" onclick="window.location.href='storageList.jsp'"/>
    </li>
  </ul>
</div>
</form>
</body>
</html>
