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
    <li>订购单管理</li>
    <li>订购单详情</li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>订购单信息</span></div>
  <ul class="forminfo">
    <li>
      <label>订单编号</label>
      <cite>${order.orderId}</cite>
    </li>
    <li>
      <label>客户姓名</label>
      <cite>
        <a href="${pageContext.request.contextPath}/getOneCust.do?customId=${order.custom.customid}&op=查看" title="点击查看客户详细信息" class="tablelink">
          ${order.custom.customname}
        </a>
      </cite>
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
    <li>
      <label>审核状态</label>
      <c:if test="${order.dstatus==1}">
        <cite>审核中</cite>
      </c:if>
      <c:if test="${order.dstatus==2}">
        <cite>审核通过</cite>
      </c:if>
      <c:if test="${order.dstatus==3}">
        <cite>审核不通过</cite>
      </c:if>
      <c:if test="${order.dstatus==4}">
        <cite>未审核</cite>
      </c:if>
    </li>
    <li>
      <label>审核意见</label>
      <cite>${order.opinion}</cite>
    </li>
    <li>
      <label>审核人</label>
      <cite>${order.checkid.uname}</cite>
    </li>
    <li>
      <label>审核时间</label>
      <cite>${order.chectime}</cite>
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
      <c:forEach items="${orderDetailsList}" var="orderDeList">
        <tr>
          <td>${orderDeList.detailsId}</td>
          <td>${orderDeList.product.productModel}</td>
          <td>${orderDeList.purchaseNum}</td>
          <td>${orderDeList.product.productUnit}</td>
          <td>${orderDeList.proprice}</td>
          <td>${orderDeList.prototal}</td>
        </tr>
      </c:forEach>
        <%--<tr>
          <td>1</td>
          <td>联想</td>
          <td>笔记本电脑</td>
          <td>T470</td>
          <td>10</td>
          <td>台</td>
          <td>9998</td>
          <td>99980</td>
        </tr>--%>
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
