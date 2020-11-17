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
<script type="text/javascript">
function tipOpen(content) {
	$(".tipright p").text(content);
	$("#tip").fadeIn(200);
}
function tipClose() {
	$("#tip").fadeOut(200);
}
</script>
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
      <li>订单编号:
        <input type="text" />
      </li>
      <li>状态:
        <select>
        	<option>请选择</option>
            <option value="1">未发货</option>
            <option value="2">已发货</option>
            <option value="3">已回款</option>
            <option value="4">取消订单</option>
            <option value="5">已退货</option>
        </select>
      </li>
      <li class="subBut" onclick="window.location.href='deliveryList.html'"><img src="../../images/t06.png" />查询</li>
      <li class="subBut" onclick="window.location.href='${pageContext.request.contextPath}/${user.uId}/addCkwarehouse.do'"><img src="../../images/t01.png" />添加出库</li>
    </ul>
    <table class="tablelist">
      <thead>
        <tr>
          <th>序号</th>
          <th>订单编号</th>
          <th>金额</th>
          <th>仓库名称</th>
          <th>出库时间</th>
          <th>出库人</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listCkWarehouse}" var="lc">
        <tr>
          <td>${lc.id}</td>
          <td>${lc.indent}</td>
          <td>￥${lc.orders.ordermoney}</td>
          <td>${lc.warehouse.name}</td>
          <td>${lc.cDate}</td>
          <td>${lc.users.uname}</td>
          <td>
            <c:if test="${lc.state==1}">
              未发货
            </c:if>
            <c:if test="${lc.state==2}">
              已发货
            </c:if>
            <c:if test="${lc.state==3}">
              已回款
            </c:if>
            <c:if test="${lc.state==4}">
              取消订单
            </c:if>
            <c:if test="${lc.state==5}">
              已退货
            </c:if>
          </td>
          <td>
          	<a href="${pageContext.request.contextPath}/${lc.indent}/deliveryView.do" class="tablelink">查看详情</a>
            <c:if test="${lc.state==1}">
              <a href="${pageContext.request.contextPath}/${lc.id}/${lc.state}/${user.uId}/${lc.indent}/updateCKstate.do" class="tablelink" >发货</a>
              <a href="${pageContext.request.contextPath}/${lc.id}/${lc.state}/${user.uId}/${lc.indent}/updateCKwarehouseState.do" class="tablelink">取消订单</a>
            </c:if>
            <c:if test="${lc.state==2}">
              <a href="" class="tablelink" onclick="tipOpen('确定此订单发货吗？')">已发货</a>
              <a href="${pageContext.request.contextPath}/${lc.id}/${lc.state}/${user.uId}/${lc.indent}/updateCKwarehouseState.do" class="tablelink">取消订单</a>
              <a href="${pageContext.request.contextPath}/${lc.id}/${lc.state}/${user.uId}/${lc.indent}/updateCKstate.do" class="tablelink" >确认回款</a>
            </c:if>
            <c:if test="${lc.state==3}">
              <a href="" class="tablelink" onclick="tipOpen('确定此订单发货吗？')">已回款</a>
            </c:if>
            <c:if test="${lc.state==4}">
              <a href="${pageContext.request.contextPath}/${lc.id}/${lc.state}/${user.uId}/${lc.indent}/updateCKwarehouseState.do" class="tablelink">确认已退货</a>
            </c:if>
            <c:if test="${lc.state==5}">
              <a href="" class="tablelink" onclick="tipOpen('确定此订单发货吗？')">已退货</a>
            </c:if>
          </td>
        </tr>
      </c:forEach>
      </tbody>

    </table>
    <div class="pagin">
      <%--<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>--%>
      <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem current"><a href="javascript:;">1</a></li>
        <li class="paginItem "><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
      </ul>
    </div>
  </form>
  <!-- 提示框 -->
  <div id="tip" class="tip">
    <div class="tiptop"><span>提示信息</span><a onclick="tipClose()"></a></div>
    <div class="tipinfo"> <span><img src="../../images/ticon.png" /></span>
      <div class="tipright">
        <p></p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="" type="button"  class="sure" value="确定" onclick="tipClose()" />
      &nbsp;
      <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
    </div>
  </div>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
