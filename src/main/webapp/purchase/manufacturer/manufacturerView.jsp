<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <li>采购管理</li>
        <li>厂商管理</li>
        <li>厂商详情</li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>厂商信息</span></div>
    <ul class="forminfo">
        <li>
            <label>厂商名称</label>
            <cite>${firm.firmName}</cite>
        </li>
        <li>
            <label>负责人</label>
            <cite>${firm.firmFounder}</cite>
        </li>
        <li>
            <label>联系方式</label>
            <cite>${firm.firmTel}</cite>
        </li>
        <li>
            <label>联系地址</label>
            <cite>${firm.firmAddress}</cite>
        </li>
        <li>
            <label>所属区域</label>
            <cite>${firm.city.province.pName}${firm.city.cName}</cite>
        </li>
        <li>
            <label>描述</label>
            <cite>${firm.firmContent}</cite>
        </li>
        <li>
            <label>状态</label>
            <c:if test="${firm.status==1}">
                <cite><td>可用</td></cite>
            </c:if>
            <c:if test="${firm.status==2}">
                <cite><td>不可用</td></cite>
            </c:if>
        </li>
        <li>
            <label>创建人</label>
            <cite>${firm.user.uname}</cite>
        </li>
        <li>
            <label>创建时间</label>
            <cite><fmt:formatDate value="${firm.createTime}" pattern="yyyy-MM-dd hh:MM:ss"/></cite>
        </li>
        <li>
            <label>&nbsp;</label>
            <input name="" type="button" class="btn" value="返回" onclick="window.location.href='manufacturerList.jsp'"/>
        </li>
    </ul>
</div>
</body>
</html>
