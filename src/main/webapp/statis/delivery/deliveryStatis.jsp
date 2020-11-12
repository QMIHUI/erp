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
                    <option>请选择省份</option>
                    <option>北京</option>
                    <option>江苏</option>
                    <option>天津</option>
                </select>
                <select>
                    <option>请选择城市</option>
                    <option>北京</option>
                    <option>南京</option>
                    <option>天津</option>
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
            <c:forEach items="${ckWarehouseList}" var="ckWarehouse" varStatus="index">
                <tr>
                    <td>${index.index+1}/td>
                    <td>${ckWarehouse.warehouse.name}</td>
                    <td>${ckWarehouse.warehouse.principal.uname}</td>
                    <td>${ckWarehouse.warehouse.province.pName}${ckWarehouse.warehouse.city.cName}</td>
                    <td>${ckWarehouse}</td>
                    <td>￥9,876,582</td>
                    <td>
                        <a href="deliveryView.jsp" class="tablelink">查看详情</a>
                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td>2</td>
                <td>武汉71号仓库</td>
                <td>柳传志</td>
                <td>湖北武汉</td>
                <td>8</td>
                <td>￥9,876,582</td>
                <td>
                    <a href="deliveryView.jsp" class="tablelink">查看详情</a>
                </td>
            </tr>
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
