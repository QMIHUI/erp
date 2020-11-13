<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>营销管理</li>
    <li>订购单审核管理</li>
    <li>审核</li>
  </ul>
</div>
<div class="formbody">
  <form action="${pageContext.request.contextPath}/orderEamine.do" method="get">
    <div class="formtitle"><span>订购单审核</span></div>
    <ul class="forminfo">
      <li>
        <label>订单编号</label>
        <cite>${order.orderId}</cite>
      </li>
      <li>
        <label>顾客姓名</label>
        <a href="${pageContext.request.contextPath}/getOneCust.do?customId=${order.custom.customid}&op=查看" title="点击查看客户详细信息" class="tablelink">
          ${order.custom.customname}
        </a>
      </li>
      <li>
        <label>联系电话</label>
        <cite>${order.custom.telephone}</cite>
      </li>
      <li>
        <label>订购时间</label>
        <cite>${order.ordertime}</cite>
      </li>
      <li>
        <label>总金额</label>
        <cite>${order.ordermoney}</cite>
      </li>
      <li>
        <label>操作人</label>
        <cite>${order.operatorid.uname}</cite>
      </li>
    </ul>
    <table class="tablelist">
        <thead>
          <tr>
            <th>序号</th>
            <th>品牌</th>
            <th>数量</th>
            <th>单位</th>
            <th>单价</th>
            <th>金额</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${orderDetailsList}" var="orderDeList" varStatus="index">
            <tr>
              <td>${index.index+1}</td>
              <td>${orderDeList.product.productModel}</td>
              <td>${orderDeList.purchaseNum}</td>
              <td>${orderDeList.product.productUnit}</td>
              <td>${orderDeList.proprice}</td>
              <td>${orderDeList.prototal}</td>
            </tr>
          </c:forEach>
        </tbody>
    </table>
      <ul class="forminfo">
        <li>
          <input name="orderId" type="hidden" value="${order.orderId}"/>
          <input name="uId" type="hidden" value="${user.uId}"/>
          <label>审核意见</label>
          <textarea class="textinput" name="opinion"></textarea>
        </li>
        <li>
          <label>审核状态</label>
          <select class="dfselect" name="dstatus">
              <option>请选择</option>
              <option value="2">通过</option>
              <option value="3">不通过</option>
          </select>
        </li>
        <li>
          <label>&nbsp;</label>
          <input name="" type="submit" class="btn" value="审核"/>
          <input name="" type="button" class="btn" value="返回" onclick="window.location.href='customerList.jsp'"/>
        </li>
      </ul>
  </form>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
