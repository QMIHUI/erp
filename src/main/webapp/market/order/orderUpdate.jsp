<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <li>修改</li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>订购单信息</span></div>
  <ul class="forminfo">
    <li>
      <label>订单编号</label>
      <input name="" type="text" value="${order.orderId}" readonly="readonly" class="roinput" />
      <i>自动生成不能编辑</i>
    </li>
    <li>
      <label>客户姓名</label>
        <select name="custName">
            <c:forEach items="${listCust}" var="cust">
                <c:if test="${cust.customid==order.customid}">
                    <option value="${cust.customid}" selected="selected">${cust.customname}</option>
                </c:if>
                <c:if test="${cust.customid!=order.customid}">
                    <option value="${cust.customid}">${cust.customname}</option>
                </c:if>
            </c:forEach>
        </select>
      <i>不能为空</i>
    </li>
    <li>
      <label>订购时间</label>
      <input name="" type="text" value="${order.ordertime}" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
      <label>创建人</label>
      <input name="" type="text" value="${order.operatorid.uname}" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
      <label>审核状态</label>
        <c:if test="${order.dstatus==1}">
            <input type="text" value="审核中" readonly="readonly" class="roinput" />
        </c:if>
        <c:if test="${order.dstatus==2}">
            <input type="text" value="审核通过" readonly="readonly" class="roinput" />
        </c:if>
        <c:if test="${order.dstatus==3}">
            <input type="text" value="审核不通过" readonly="readonly" class="roinput" />
        </c:if>
        <c:if test="${order.dstatus==4}">
            <input type="text" value="未审核" readonly="readonly" class="roinput" />
        </c:if>
      <i>初始状态，不能编辑</i>
    </li>
    <li>
      <label>总金额</label>
      <input name="" type="text" value="${order.ordermoney}" readonly="readonly" class="roinput" />
       <i>不能编辑</i>
    </li>
    <%--<li>
      <input type="button" value="新增" class="smallbtn" />
      <input type="button" value="删除" class="smallbtn" />

    </li>--%>
  </ul>
  <table class="tablelist">
      <thead>
        <tr>
            <th>序号</th>
            <th>品牌</th>
            <th>类型</th>
            <th>型号</th>
            <th>数量</th>
            <th>单位</th>
            <th>单价</th>
            <th>金额</th>
        </tr>
      </thead>
      <tbody>
      <tr>
          <td>1</td>
          <td>
              <select>
                  <option>请选择</option>
                  <option selected="selected">联想</option>
                  <option>海尔</option>
                  <option>小米</option>
              </select>
          </td>
          <td>
              <select>
                  <option>请选择</option>
                  <option selected="selected">笔记本电脑</option>
                  <option>台式电脑</option>
                  <option>手机</option>
              </select>
          </td>
          <td>
              <select>
                  <option>请选择</option>
                  <option>X260</option>
                  <option>E470</option>
                  <option selected="selected">T470</option>
              </select>
          </td>
          <td><input type="text" value="10" /></td>
          <td>台</td>
          <td><input type="text" value="9998" /></td>
          <td><input type="text" value="99980" /></td>
      </tr>
      <tr>
          <td>2</td>
          <td>
              <select>
                  <option>请选择</option>
                  <option selected="selected">联想</option>
                  <option>海尔</option>
                  <option>小米</option>
              </select>
          </td>
          <td>
              <select>
                  <option>请选择</option>
                  <option selected="selected">笔记本电脑</option>
                  <option>台式电脑</option>
                  <option>手机</option>
              </select>
          </td>
          <td>
              <select>
                  <option>请选择</option>
                  <option selected="selected">X260</option>
                  <option>E470</option>
                  <option>T470</option>
              </select>
          </td>
          <td><input type="text" value="5" /></td>
          <td>台</td>
          <td><input type="text" value="5500" /></td>
          <td><input type="text" value="27500" /></td>
      </tr>
      <%--<c:forEach items="${orderDetailsList}" var="orderDeList">
          <tr>
              <td>${orderDeList.detailsId}</td>
              <td>
              <select>
                  <c:forEach items="${listProduct}" var="product">
                      <c:if test="${orderDeList.detailsId==product.productId}">
                          <option value="${product.productId}">${product.productModel}</option>
                      </c:if>
                      <c:if test="${orderDeList.detailsId!=product.productId}">
                          <option value="${product.productId}">${product.productModel}</option>
                      </c:if>
                  </c:forEach>
              </select>
              </td>
              <td><input style="width: 200px;height: 30px" type="text" value="${orderDeList.purchaseNum}" /></td>
              <td>${orderDeList.product.productUnit}</td>
              <td><input style="width: 200px;height: 30px" type="text" value="${orderDeList.proprice}" /></td>
          </tr>
      </c:forEach>--%>

      </tbody>
  </table>
    <div style="margin-left: 450px;margin-top: 20px">
        <input type="submit" value="保存" class="smallbtn" />
        <input type="button" value="返回" class="smallbtn" onclick="window.location.href='orderList.jsp'"/>
    </div>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
