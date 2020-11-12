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
        <li>客户销量统计</li>
        <li>销量详情</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <table class="tablelist">
            <thead>
            <tr>
                <th>序号</th>
                <th>客户姓名</th>
                <th>订购单编号</th>
                <th>商品数量</th>
                <th>金额</th>
                <th>订购时间</th>
                <th>操作人</th>
                <th>订购单详情</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ordersList}" var="order" varStatus="index">
                <tr>
                    <td>${index.index+1}</td>
                    <td>${order.custom.customname}</td>
                    <td>${order.orderId}</td>
                    <td>${order.orderdetailsList.size()}</td>
                    <td>￥${order.ordermoney}</td>
                    <td>${order.ordertime}</td>
                    <td>${order.operatorid.uname}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${order.orderId}&op=查看" class="tablelink">查看详情</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                <li class="paginItem"><a href="javascript:;">1</a></li>
                <li class="paginItem current"><a href="javascript:;">2</a></li>
                <li class="paginItem"><a href="javascript:;">3</a></li>
                <li class="paginItem"><a href="javascript:;">4</a></li>
                <li class="paginItem"><a href="javascript:;">5</a></li>
                <li class="paginItem more"><a href="javascript:;">...</a></li>
                <li class="paginItem"><a href="javascript:;">10</a></li>
                <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
            </ul>
        </div>
    </form>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
