<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>仓库管理</li>
    <li>出库管理</li>
    <li>添加出库</li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>出库信息</span></div>
  <ul class="forminfo">
    <li>
      <label>订单编号</label>
      <input name="" type="text" class="dfinput" />
      &nbsp;&nbsp;
      <a href="../../market/order/orderView.jsp" title="点击查看客户详细信息" class="tablelink">详情</a>
      <i>必须是审核通过的订单</i>
    </li>
    <li>
      <label>仓库</label>
      <select class="dfselect">
          <option>请选择</option>
          <option>南京21号仓库</option>
          <option>武汉71号仓库</option>
          <option>天津83号仓库</option>
      </select>
      <i>必选</i>
    </li>
    <li>
      <label>出库时间</label>
      <input name="" type="text" class="dfinput" />
      <i>必填</i>
    </li>
    <li>
      <label>出库人</label>
      <input name="" type="text" value="马云" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    <li>
      <label>&nbsp;</label>
      <input name="" type="button" class="btn" value="确定"/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="" type="button" class="btn" value="返回" onclick="window.location.href='deliveryList.jsp'"/>
    </li>
  </ul>
</div>
</body>
</html>
