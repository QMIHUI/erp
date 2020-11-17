<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文本</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>仓库管理</li>
    <li>出库管理</li>
    <li>基本内容</li>
  </ul>
</div>
<div class="rightinfo">
  <form action="" method="post">
    <ul class="tools">
      <li>仓库名称:
        <input type="text" />
      </li>
      <li>商品品牌:
        <input type="text" />
      </li>
      <li>商品类型:
        <input type="text" />
      </li>
      <li>商品名称:
        <input type="text" />
      </li>
      <li class="subBut" onclick="window.location.href='deliveryList.jsp'"><img src="../../images/t06.png" />查询</li>
    </ul>
    <table class="tablelist">
      <thead>
        <tr>
          <th>序号</th>
          <th>仓库名称</th>
          <th>商品品牌</th>
          <th>商品类型</th>
          <th>商品名称</th>
          <th>厂商名称</th>
          <th>商品数量</th>
          <th>单位</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listKcWarehouse}" var="lkw">
        <tr>
          <td>${lkw.id}</td>
          <td>${lkw.warehouse.name}</td>
          <td>${lkw.brand.brandName}</td>
          <td>${lkw.type.typeName}</td>
          <td>${lkw.product.productModel}</td>
          <td>${lkw.firm.firmName}</td>
          <td>${lkw.repertory}</td>
          <td>台</td>
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
