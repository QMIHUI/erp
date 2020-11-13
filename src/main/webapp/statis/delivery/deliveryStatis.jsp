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
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <ul class="tools">
            <li> 所属区域：
                <select>
                    <option value="0">请选择</option>
                    <c:forEach items="${provinceList}" var="province" >
                        <option value="${province.id}">${province.pName}</option>
                    </c:forEach>
                </select>
                <select>
                    <option value="0">请选择城市</option>
                    <c:forEach items="${cityList}" var="city" >
                        <option value="${city.cId}">${city.cName}</option>
                    </c:forEach>
                </select>
            </li>
            <li> 仓库名称:
                <input type="text" />
            </li>
            <li> 时间:
                <input type="text" />-<input type="text" />
            </li>

            <li class="subBut" onclick="window.location.href='deliveryStatis.html'"><img src="../../images/t06.png" />查询</li>
        </ul>
        <table class="tablelist">
            <thead>
            <tr>
                <th>序号</th>
                <th>仓库名称</th>
                <th>负责人</th>
                <th>所属区域</th>
                <th>出库数量</th>
                <th>金额</th>
                <th>出库明细</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${warehouseList}" var="warehouse" varStatus="index">
                <tr>
                    <td>${index.index+1+(pageIndex-1)*5}</td>
                    <td>${warehouse.name}</td>
                    <td>${warehouse.principal.uname}</td>
                    <td>${warehouse.province.pName}${warehouse.city.cName}</td>
                    <td>${warehouse.ckWarehouseList.size()}</td>
                    <c:set var="sum" value="0"/>
                    <c:forEach items="${warehouse.ckWarehouseList}" var="ckWarehouse">
                        <c:set var="sum" value="${sum+ckWarehouse.order.ordermoney}"/>
                    </c:forEach>
                    <fmt:parseNumber var="i"  type="number" value="${sum}" />
                    <td>￥<c:out value="${i}"/> </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/getCkWarehouseByWarehouseId.do?id=${warehouse.id}" class="tablelink">查看详情</a>
                    </td>
                </tr>
            </c:forEach>


            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">${countWarehouse}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="${pageContext.request.contextPath }/outWarehouseStatis.do?pageIndex=1">首页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/outWarehouseStatis.do?pageIndex=${pageIndex-1}">上页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/outWarehouseStatis.do?pageIndex=${pageIndex+1}">下页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/outWarehouseStatis.do?pageIndex=${row}">末页</a></li>
            </ul>
        </div>
    </form>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
