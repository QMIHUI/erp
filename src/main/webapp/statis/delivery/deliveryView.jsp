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
        <li>数据统计</li>
        <li>出库统计</li>
        <li>出库详情</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <table class="tablelist">
            <thead>
            <tr>
                <th>序号</th>
                <th>仓库名称</th>
                <th>订购单编号</th>
                <th>商品数量</th>
                <th>金额</th>
                <th>出库时间</th>
                <th>出库人</th>
                <th>订购单详情</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${ckWarehouseList}" var="ckWarehouse" varStatus="index">
                    <tr>
                        <td>${index.index+1+(pageIndex-1)*5}</td>
                        <td>${ckWarehouse.warehouse.name}</td>
                        <td>${ckWarehouse.order.orderId}</td>
                        <td>${ckWarehouse.order.orderdetailsList.size()}</td>
                        <td>￥${ckWarehouse.order.ordermoney}</td>
                        <td><fmt:formatDate value="${ckWarehouse.ckDate}" pattern="yyyy-MM-dd HH:MM:ss"/></td>
                        <td>${ckWarehouse.outUser.uname}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${ckWarehouse.order.orderId}&op=查看" class="tablelink">查看详情</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">${countCkWarehouse}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getCkWarehouseByWarehouseId.do?pageIndex=1&id=${warehouseId}">首页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getCkWarehouseByWarehouseId.do?pageIndex=${pageIndex-1}&id=${warehouseId}">上页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getCkWarehouseByWarehouseId.do?pageIndex=${pageIndex+1}&id=${warehouseId}">下页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getCkWarehouseByWarehouseId.do?pageIndex=${row}&id=${warehouseId}">末页</a></li>
            </ul>
        </div>
    </form>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
