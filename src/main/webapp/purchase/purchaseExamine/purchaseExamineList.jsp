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

<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>采购单审核</li>
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <ul class="tools">
            <li> 采购单编号:
                <input type="text" />
            </li>
            <li> 采购时间:
                <input type="text" />-<input type="text" />
            </li>
            <li> 金额:
                <input type="text" class="stinput" />-<input type="text" class="stinput" />
            </li>
            <li class="subBut" onclick="window.location.href='purchaseExamineList.html'">
                <img src="../../images/t06.png" />查询
            </li>
        </ul>
        <table class="tablelist">
            <thead>
            <tr>
                <th>序号</th>
                <th>采购单编号</th>
                <th>采购时间</th>
                <th>金额</th>
                <th>操作人</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${purchaseList}" var="purchase" varStatus="index">
                <tr>
                    <td>${index.index+1+(pageIndex-1)*5}</td>
                    <td>${purchase.purchaseId}</td>
                    <td><fmt:formatDate value="${purchase.purchaseTime}" pattern="yyyy-MM-dd HH:MM:ss"/></td>
                    <td>￥${purchase.totalMoney}</td>
                    <td>${purchase.buyer.uname}</td>
                    <c:if test="${purchase.checkStatus==1}">
                        <td>未审核</td>
                    </c:if>
                    <c:if test="${purchase.checkStatus==2}">
                        <td>审核中</td>
                    </c:if>
                    <c:if test="${purchase.checkStatus==3}">
                        <td>已审核通过</td>
                    </c:if>
                    <c:if test="${purchase.checkStatus==4}">
                        <td>审核未通过</td>
                    </c:if>
                    <td>
                        <a href="${pageContext.request.contextPath}/toPurchaseExamine.do?id=${purchase.purchaseId}" class="tablelink">审核</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">${countPurchaseStatus2}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllPurchaseStatus2.do?pageIndex=1">首页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllPurchaseStatus2.do?pageIndex=${pageIndex-1}">上页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllPurchaseStatus2.do?pageIndex=${pageIndex+1}">下页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllPurchaseStatus2.do?pageIndex=${row}">末页</a></li>
            </ul>
        </div>
    </form>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
