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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts-all.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>数据统计</li>
        <li>厂商采购统计</li>
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <ul class="tools">
            <li> 所属区域：
                <select>
                    <option value="0">请选择省份</option>
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
            <li> 厂商名称:
                <input type="text" />
            </li>
            <li> 时间:
                <input type="text" />-<input type="text" />
            </li>

            <li class="subBut" onclick="window.location.href='purchaseStatis.html'"><img src="../../images/t06.png" />查询</li>
        </ul>
        <table class="tablelist">
            <thead>
            <tr>
                <th>序号</th>
                <th>厂商名称</th>
                <th>负责人</th>
                <th>所属区域</th>
                <th>采购单数量</th>
                <th>金额</th>
                <th>采购单明细</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${firmList}" var="firm" varStatus="index">
                <tr>
                    <td>${index.index+1+(pageIndex-1)*5}</td>
                    <td>${firm.firmName}</td>
                    <td>${firm.firmFounder}</td>
                    <td>${firm.city.province.pName}${firm.city.cName}</td>
                    <td>${firm.purchaseList.size()}</td>
                    <c:set var="sum" value="0"/>
                    <c:forEach items="${firm.purchaseList}" var="purchase">
                        <c:set var="sum" value="${sum+purchase.totalMoney}"/>
                    </c:forEach>
                        <fmt:parseNumber var="i"  type="number" value="${sum}" />
                    <td>￥<c:out value="${i}"/> </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/getFirmById.do?id=${firm.firmId}" class="tablelink">查看详情</a>
                    </td>
                </tr>
            </c:forEach>


            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">${countFirm}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllFirms.do?pageIndex=1">首页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllFirms.do?pageIndex=${pageIndex-1}">上页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllFirms.do?pageIndex=${pageIndex+1}">下页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllFirms.do?pageIndex=${row}">末页</a></li>
            </ul>
        </div>
    </form>
</div>
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    $(function () {
        var myChart = echarts.init($("#main")[0]);
        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data: ['采购单总额',  '采购单数量']
            },
            xAxis: [
                {
                    type: 'category',
                    data: ${sessionScope.FirmNames},
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '采购单总额',
                    min: 0,
                    /* max: 250,
                     interval: 50,*/
                    axisLabel: {
                        formatter: '￥{value} '
                    }
                },
                {
                    type: 'value',
                    name: '采购单数量',
                    min: 0,
                    /*max: 25,*/
                    interval: 2,
                    axisLabel: {
                        formatter: '{value}'
                    }
                }
            ],
            series: [
                {
                    name: '采购单总额',
                    type: 'bar',
                    data: ${sessionScope.FirmPurchaseTotalMoney}
                },

                {
                    name: '采购单数量',
                    type: 'line',
                    yAxisIndex: 1,
                    data:${sessionScope.FirmPurchaseCount}
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    })
</script>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
