<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <li>营销管理</li>
    <li>采购单管理</li>
    <li>采购单详情</li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>采购单信息</span></div>
  <ul class="forminfo">
    <li>
      <label>采单编号</label>
      <cite>${rkWarehouse.rkIndent}</cite>
    </li>
    <li>
      <label>采购时间</label>
      <cite><fmt:formatDate value="${rkWarehouse.purchase.checkTime}" pattern="yyyy-MM-dd HH:MM:ss"/></cite>
    </li>
    <li>
      <label>总金额</label>
       <cite>￥${rkWarehouse.purchase.totalMoney}</cite>
    </li>
    <li>
      <label>操作人</label>
      <cite>${rkWarehouse.users.uname}</cite>
    </li>
    <li>
      <label>审核状态</label>
      <cite>
        <c:if test="${rkWarehouse.purchase.checkStatus==1}">
          未审核
        </c:if>
        <c:if test="${rkWarehouse.purchase.checkStatus==2}">
          审核中
        </c:if>
        <c:if test="${rkWarehouse.purchase.checkStatus==3}">
          审核通过
        </c:if>
        <c:if test="${rkWarehouse.purchase.checkStatus==4}">
          审核未通过
        </c:if>
      </cite>
    </li>
    <li>
      <label>审核意见</label>
      <cite>${rkWarehouse.purchase.checkOpinion}</cite>
    </li>
    <li>
      <label>审核人</label>
      <cite>${rkWarehouse.users.names}</cite>
    </li>
    <li>
      <label>审核时间</label>
      <cite><fmt:formatDate value="${rkWarehouse.purchase.checkTime}" pattern="yyyy-MM-dd HH:MM:ss"/></cite>
    </li>
    <li>
      <label>入货仓库</label>
      <cite>${rkWarehouse.warehouse.name}</cite>
    </li>
    <li>
      <label>入库时间</label>
      <cite>${rkWarehouse.rkDate}</cite>
    </li>
    <li>
      <label>入库人</label>
      <cite>${user.uname}</cite>
    </li>
    <li>
      <label>入库状态</label>
      <cite>
        <c:if test="${rkWarehouse.state==1}">
          未入库
        </c:if>
        <c:if test="${rkWarehouse.state==2}">
          已入库
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
          <th>厂商</th>
          <th>数量</th>
          <th>单位</th>
          <th>单价</th>
          <th>金额</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listDetails}" var="ld">
        <tr>
          <td>${ld.detailsId}</td>
          <td>${ld.brand.brandName}</td>
          <td>${ld.type.typeName}</td>
          <td>${ld.product.productModel}</td>
          <td>${ld.firm.firmName}</td>
          <td>${ld.count}</td>
          <td>台</td>
          <td>${ld.purchasePrice}</td>
          <td>${ld.totalMoney}</td>
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
