<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <li>营销管理</li>
        <li>订购单管理</li>
        <li>订购单详情</li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>订购单信息</span></div>
    <ul class="forminfo">
        <li>
            <label>订单编号</label>
            <cite>${ckWarehouse.indent}</cite>
        </li>
        <li>
            <label>客户姓名</label>
            <cite>${ckWarehouse.custom.customname}</cite>
        </li>
        <li>
            <label>联系电话</label>
            <cite>${ckWarehouse.custom.telephone}</cite>
        </li>
        <li>
            <label>订购时间</label>
            <cite>${ckWarehouse.orders.ordertime}</cite>
        </li>
        <li>
            <label>总金额</label>
            <cite>￥${ckWarehouse.orders.ordermoney}</cite>
        </li>
        <li>
            <label>操作人</label>
            <cite>
                ${ckWarehouse.users.names}</cite>
        </li>
        <li>
            <label>审核状态</label>
            <cite>
                <c:if test="${ckWarehouse.orders.dstatus==1}">
                    审核中
                </c:if>
                <c:if test="${ckWarehouse.orders.dstatus==2}">
                    审核通过
                </c:if>
                <c:if test="${ckWarehouse.orders.dstatus==3}">
                    审核未通过
                </c:if>
                <c:if test="${ckWarehouse.orders.dstatus==4}">
                    未审核
                </c:if>
            </cite>
        </li>
        <li>
            <label>审核意见</label>
            <cite>${ckWarehouse.orders.opinion}</cite>
        </li>
        <li>
            <label>审核人</label>
            <cite>${ckWarehouse.users.uname}</cite>
        </li>
        <li>
            <label>审核时间</label>
            <cite>${ckWarehouse.orders.chectime}</cite>
        </li>
        <li>
            <label>出货仓库</label>
            <cite>${ckWarehouse.warehouse.name}</cite>
        </li>
        <li>
            <label>出库时间</label>
            <cite>${ckWarehouse.cDate}</cite>
        </li>
        <li>
            <label>出库人</label>
            <cite>${user.uname}</cite>
        </li>
        <li>
            <label>出库状态</label>
            <cite>
                <c:if test="${ckWarehouse.state==1}">
                    未发货
                </c:if>
                <c:if test="${ckWarehouse.state==2}">
                    已发货
                </c:if>
                <c:if test="${ckWarehouse.state==3}">
                    已回款
                </c:if>
                <c:if test="${ckWarehouse.state==4}">
                    取消订单
                </c:if>
                <c:if test="${ckWarehouse.state==5}">
                    已退货
                </c:if>
            </cite>
        </li>
    </ul>
    <table class="tablelist">
        <thead>
        <tr>
            <th>序号</th>
            <th>品牌</th>
            <th>类型</th>
            <th>型号</th>
            <th>数量</th>
            <th>单位</th>
            <th>单价</th>
            <th>金额</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listOrderdetails}" var="lo">
            <tr>
                <td>${lo.detailsId}</td>
                <td>${lo.brand.brandName}</td>
                <td>${lo.type.typeName}</td>
                <td>${lo.product.productModel}</td>
                <td>${lo.purchaseNum}</td>
                <td>台</td>
                <td>${lo.proprice}</td>
                <td>${lo.prototal}</td>
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
