<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户管理</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>营销管理</li>
    <li>订购单审核管理</li>
    <li>基本内容</li>
  </ul>
</div>
<div class="rightinfo">
  <form action="${pageContext.request.contextPath}/getExamineOrderByCon.do" method="get">
    <ul class="tools">
      <li> 订单编号:
        <input type="text" name="orderId" />
      </li>
     <%-- <li> 客户姓名:
        <input type="text" />
      </li>--%>
      <li>
        <label>订购时间:</label>
        <input name="startDate" type="text" class="laydate-icon" id="logStartTime"/>
        <label>-</label>
        <input name="enddate" type="text" class="laydate-icon" id="logEndTime"/>
      </li>
      <li> 金额:
        <input type="text" class="stinput" name="sPrice" />-<input type="text" class="stinput" name="bPrice" />
      </li>
      <li style="width: 100px;height: 35px;margin-top: -10px">
        <input value="查 询" type="submit" id="searchbutton" class="subBut">
      </li>
    </ul>
    <table class="tablelist">
      <thead>
        <tr>
          <th>订单编号</th>
          <th>客户姓名</th>
          <th>联系电话</th>
          <th>订购时间</th>
          <th>金额</th>
          <th>操作人</th>
          <th>审核状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listExamOrder}" var="leo">
        <tr>
          <td>${leo.orderId}</td>
          <td>${leo.custom.customname}</td>
          <td>${leo.custom.telephone}</td>
          <td>${leo.ordertime}</td>
          <td>${leo.ordermoney}</td>
          <td>${leo.operatorid.uname}</td>
          <td>审核中</td>
          <td><a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${leo.orderId}&op=审核" class="tablelink">审核</a></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <div class="pagin">
      <div class="message">共<i class="blue">${countExamOrder}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
      <ul class="paginList">
        <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllExamineOrder.do?pageIndex=1">首页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllExamineOrder.do?pageIndex=${pageIndex-1}">上页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllExamineOrder.do?pageIndex=${pageIndex+1}">下页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllExamineOrder.do?pageIndex=${rowExamOrder}">末页</a></li>
      </ul>
    </div>
  </form>
</div>
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
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
