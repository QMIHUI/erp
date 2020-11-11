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
    <script type="text/javascript" src="../../js/jquery.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>采购单审核</li>
        <li>审核</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/purchaseExamine.do?id=${purchase.purchaseId}" method="post">
    <div class="formbody">
        <div class="formtitle"><span>采购单审核</span></div>
        <ul class="forminfo">
            <li>
                <label>采购单编号</label>
                <cite>${purchase.purchaseId}</cite>
            </li>
            <li>
                <label>采购购时间</label>
                <cite><fmt:formatDate value="${purchase.purchaseTime}" pattern="yyyy-MM-dd HH:MM:ss"/></cite>
            </li>
            <li>
                <label>总金额</label>
                <cite>￥${purchase.totalMoney}</cite>
            </li>
            <li>
                <label>操作人</label>
                <cite>${purchase.buyer.uname}</cite>
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
                    <td>${index.index+1}</td>
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
        <ul class="forminfo">
            <li>
                <label>审核意见</label>
                <textarea class="textinput" name="checkOpinion"></textarea>
            </li>
            <li>
                <label>审核状态</label>
                <select class="dfselect" name="checkStatus">
                    <option>请选择</option>
                    <option value="3">通过</option>
                    <option value="4">不通过</option>
                </select>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="审核"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="" type="button" class="btn" value="返回" onclick="window.location.href='purchaseExamineList.jsp'"/>
            </li>
        </ul>
    </div>
</form>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
