<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <li>商品管理</li>
        <li>添加</li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>商品类型信息</span></div>
    <ul class="forminfo">
        <li>
            <label>品牌名称</label>
            <select class="dfselect" name="brand">
                <c:forEach items="${brandList}" var="brand">
                    <option value="${brand.brandId}">${brand.brandName}</option>
                </c:forEach>
            </select>
            <i>必选</i>
        </li>
        <li>
            <label>商品类型</label>
            <select class="dfselect" name="type">
                <c:forEach items="${typeList}" var="type">
                    <option value="${type.typeId}">${type.typeName}</option>
                </c:forEach>
            </select>
            <i>必选</i>
        </li>
        <li>
            <label>商品型号</label>
            <input name="" type="text" class="dfinput" />
            <i>必填，不能超过50个字符</i>
        </li>
        <li>
            <label>单位</label>
            <input name="" type="text" class="dfinput" />
            <i>必填，不能超过10个字符</i>
        </li>
        <li>
            <label>状态</label>
            <select class="dfselect">
                <option value="1">可用</option>
                <option value="2">不可用</option>
            </select>
            <i>必选</i>
        </li>
        <li>
            <label>创建人</label>
            <input name="" type="text" value="马云" readonly="readonly" class="roinput" />
            <i>不能编辑</i>
        </li>
        <li>
            <label>创建时间</label>
            <input name="" type="text" value="2017-11-18 15:36:10" readonly="readonly" class="roinput" />
            <i>不能编辑</i>
        </li>
        <li>
            <label>&nbsp;</label>
            <input name="" type="button" class="btn" value="确定"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="" type="button" class="btn" value="返回" onclick="window.location.href='productList.html'"/>
        </li>
    </ul>
</div>
</body>
</html>
