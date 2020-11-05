<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../../js/jquery.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>采购单管理</li>
        <li>采购单详情</li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>采购单信息</span></div>
    <ul class="forminfo">
        <li>
            <label>采购单编号</label>
            <cite>${purchase.purchaseId}CG201711180001</cite>
        </li>
        <li>
            <label>订购时间</label>
            <cite>${purchase.purchaseTime}2017-11-18 15:36:10</cite>
        </li>
        <li>
            <label>总金额</label>
            <cite>￥${purchase.totalMoney}9,876,582</cite>
        </li>
        <li>
            <label>操作人</label>
            <cite>${purchase.buyer.uname}关羽</cite>
        </li>
        <li>
            <label>审核状态</label>
            <c:if test="${purchase.cgState==1}">
                <cite>未审核</cite>
            </c:if>
            <c:if test="${purchase.cgState==2}">
                <cite>审核中</cite>
            </c:if>
            <c:if test="${purchase.cgState==3}">
                <cite>已审核通过</cite>
            </c:if>
            <c:if test="${purchase.cgState==4}">
                <cite>审核未通过</cite>
            </c:if>

        </li>
        <li>
            <label>审核意见</label>
            <cite>${purchase.totalMoney}做的很详细，同意通过</cite>
        </li>
        <li>
            <label>审核人</label>
            <cite>${purchase.checkOpinion}曹操</cite>
        </li>
        <li>
            <label>审核时间</label>
            <cite>${purchase.checkTime}2017-01-30 12:05:05</cite>
        </li>
    </ul>
    <table class="tablelist">
        <thead>
            <tr>
                <th>序号</th>
                <th>品牌</th>
                <th>类型</th>
                <th>型号</th>
                <th>厂商</th>
                <th>数量</th>
                <th>单位</th>
                <th>单价</th>
                <th>金额</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${purchase.detailsList}" var="details" varStatus="index">
                <tr>
                    <td>${index}</td>
                    <td>${details.product.type.brand.brandName}</td>
                    <td>${details.product.type.typeName}</td>
                    <td>${details.product.productModel}</td>
                    <td>${details.product.firm.firmName}</td>
                    <td>${details.count}</td>
                    <td>${details.product.productUnit}</td>
                    <td>${details.purchasePrice}</td>
                    <td>${details.totalMoney}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div style="margin-top:20px; margin-left:20px;">
        <input name="" type="button" class="btn" value="返回" onclick="window.history.go(-1);"/>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
