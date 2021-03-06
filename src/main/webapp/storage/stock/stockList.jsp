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
    <li>入库管理</li>
    <li>基本内容</li>
  </ul>
</div>
<div class="rightinfo">
  <form action="" method="post">
    <ul class="tools">
      <li>仓库名称:
        <input type="text" />
      </li>
      <li>采购单编号:
        <input type="text" />
      </li>
      <li>状态:
        <select>
        	<option>请选择</option>
            <option value="1">未入库</option>
            <option value="2">已入库</option>
        </select>
      </li>
      <li class="subBut" onclick="window.location.href='stockList.html'"><img src="../../images/t06.png" />查询</li>
      <li class="subBut" onclick="window.location.href='${pageContext.request.contextPath}/${user.uId}/addRkwarehouse.do'"><img src="../../images/t01.png" />添加入库</li>
    </ul>
    <table class="tablelist">
      <thead>
        <tr>
          <th>序号</th>
          <th>采购单编号</th>
          <th>金额</th>
          <th>仓库名称</th>
          <th>入库时间</th>
          <th>入库人</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listRkWarehouse}" var="lr">
        <tr>
          <td>${lr.id}</td>
          <td>${lr.rkIndent}</td>
          <td>￥${lr.purchase.totalMoney}</td>
          <td>${lr.warehouse.name}</td>
          <td>${lr.rkDate}</td>
          <td>${lr.users.uname}</td>
          <td>
            <c:if test="${lr.state==1}">
            未入库
            </c:if>
            <c:if test="${lr.state==2}">
              已入库
            </c:if>
            <c:if test="${lr.state==3}">
              已取消订单
            </c:if>
          </td>
          <td>
          	<a href="${pageContext.request.contextPath}/${lr.rkIndent}/stockView.do" class="tablelink">查看详情</a>
            <c:if test="${lr.state==1}">
              <a href="${pageContext.request.contextPath}/${lr.id}/${lr.state}/${user.uId}/${lr.rkIndent}/${lr.warehouseId}/updateRkstate.do" class="tablelink" >入库</a>
              <a href="${pageContext.request.contextPath}/${lr.id}/${lr.state}/${user.uId}/${lr.rkIndent}/updateRkWarehousestate.do" class="tablelink" >取消入库</a>
            </c:if>
            <c:if test="${lr.state==2}">
              <a href="javascript:void(0)" class="tablelink" >已入库</a>
            </c:if>
            <c:if test="${lr.state==3}">
              <a href="javascript:void(0)" class="tablelink" >本次采购已取消</a>
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
