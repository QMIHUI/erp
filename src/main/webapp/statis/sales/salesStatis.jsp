<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts-all.js"></script>
</head>
<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>数据统计</li>
        <li>客户销量统计</li>
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="${pageContext.request.contextPath}/getCustomersByConStatis.do" method="get">
        <ul class="tools">
            <li> 所属区域：
                <select name="province">
                    <option value="0">请选择</option>
                    <c:forEach items="${provinceList}" var="province" >
                        <option value="${province.id}">${province.pName}</option>
                    </c:forEach>
                </select>
                <%--<select>
                    <option>请选择城市</option>
                    <option>北京</option>
                    <option>南京</option>
                    <option>天津</option>
                </select>--%>
            </li>
            <li> 客户姓名:
                <input type="text" name="custName"/>
            </li>
            <li> <label>时间:</label>
                <input name="startDate" type="text" class="laydate-icon" id="logStartTime"/>
                <label>-</label>
                <input name="enddate" type="text" class="laydate-icon" id="logEndTime"/>
            </li>
            <li style="width: 100px;height: 35px;margin-top: -10px">
                <input value="查 询" type="submit" id="searchbutton" class="subBut">
            </li>
            <%--<li class="subBut" onclick=""><img src="${pageContext.request.contextPath }/images/t06.png" />查询</li>--%>
        </ul>
    </form>
        <table class="tablelist">
            <thead>
            <tr>
                <th>序号</th>
                <th>客户姓名</th>
                <th>所属区域</th>
                <th>订购单数量</th>
                <th>金额</th>
                <th>订购单明细</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customList}" var="custom" varStatus="index">
                <tr>
                    <td>${index.index+1+(pageIndex-1)*5}</td>
                    <td>${custom.customname}</td>
                    <td>${custom.province.pName}</td>
                    <td>${custom.ordersList.size()}</td>
                    <c:set var="sum" value="0"/>
                    <c:forEach items="${custom.ordersList}" var="order">
                        <c:set var="sum" value="${sum+order.ordermoney}"/>
                    </c:forEach>
                    <fmt:parseNumber var="i"  type="number" value="${sum}" pattern="#.##"/>
                    <td>￥<fmt:formatNumber type="number" value="${i}" pattern="#.##"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/getOrdersByCustomId.do?id=${custom.customid}" class="tablelink">查看详情</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">${countCustom}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllcustomsStatis.do?pageIndex=1">首页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllcustomsStatis.do?pageIndex=${pageIndex-1}">上页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllcustomsStatis.do?pageIndex=${pageIndex+1}">下页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllcustomsStatis.do?pageIndex=${row}">末页</a></li>
            </ul>
        </div>

</div>
<div id="main" style="width: ${sessionScope.customNames.size()*200}px;height:400px;"></div>
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
                data: ['订购单总额',  '订购单数量']
            },
            xAxis: [
                {
                    type: 'category',
                    data: ${sessionScope.customNames},
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '订购单总额',
                    min: 0,
                   /* max: 250,
                    interval: 50,*/
                    axisLabel: {
                        formatter: '￥{value} '
                    }
                },
                {
                    type: 'value',
                    name: '订购单数量',
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
                    name: '订购单总额',
                    type: 'bar',
                    barWidth:60,
                    data: ${sessionScope.customOrderTotalMoney}
                },

                {
                    name: '订购单数量',
                    type: 'line',
                    yAxisIndex: 1,
                    data:${sessionScope.customOrderCount}
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    })
</script>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');

    var start = {
        elem: '#logStartTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        max: '2099-06-16', //最大日期
        istime: true,
        istoday: false,
        choose: function(datas){
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas; //将结束日的初始值设定为开始日
        }
    };

    var end = {
        elem: '#logEndTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        max: '2099-06-16',
        istime: true,
        istoday: false,
        choose: function(datas){
            start.max = datas; //结束日选好后，充值开始日的最大日期
        }
    };
    laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
    laydate(start);
    laydate(end);



</script>

</body>
</html>
