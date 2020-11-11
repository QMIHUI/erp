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
<script type="text/javascript">
    /*function deltipOpen(orderId) {
        $("input[name='delOrder']").bind("click",function () {
          window.location.href="/deleteOrder.do?orderId="+orderId;
        })
        $("#deltip").fadeIn(200);
    }*/
    function tipOpen(content,custId) {
      $(".tipright p").text(content);
      $("input[name='cancelCust']").bind("click",function () {
        window.location.href="${pageContext.request.contextPath}/cancelCust.do?custId="+custId;
      })
      $("#tip").fadeIn(200);
    }
    function deltipClose() {
        $("#deltip").fadeOut(200);
    }
    function examinetipOpen() {
        $("#examinetip").fadeIn(200);
    }
    function examinetipclose() {
        $("#examinetip").fadeOut(200);
    }
</script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>营销管理</li>
    <li>订购单管理</li>
    <li>基本内容</li>
  </ul>
</div>
<div class="rightinfo">
  <form action="" method="post">
    <ul class="tools">
      <li> 订单编号:
        <input type="text" />
      </li>
      <li> 客户姓名:
        <input type="text" />
      </li>
      <li> 订购时间:
        <input type="text" />-<input type="text" />
      </li>
      <li> 金额:
        <input type="text" class="stinput" />-<input type="text" class="stinput" />
      </li>
      <li> 审核状态：
        <select>
          <option>请选择</option>
          <option value="1">未审核</option>
          <option value="2">审核中</option>
          <option value="3">审核通过</option>
          <option value="4">审核未通过</option>
        </select>
      </li>
      <li class="subBut" onclick="window.location.href='orderList.html'"><img src="../../images/t06.png" />查询</li>
      <li class="subBut" onclick="window.location.href='orderAdd.jsp'"><img src="../../images/t01.png" />添加</li>
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
          <th>审核人</th>
          <th>审核时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listOrder}" var="lorder">
        <tr>
          <td>${lorder.orderId}</td>
          <td>${lorder.custom.customname}</td>
          <td>${lorder.custom.telephone}</td>
          <td>${lorder.ordertime}</td>
          <td>${lorder.ordermoney}</td>
          <td>${lorder.operatorid.uname}</td>
          <c:if test="${lorder.dstatus==1}">
            <td>审核中</td>
          </c:if>
          <c:if test="${lorder.dstatus==2}">
            <td>审核通过</td>
          </c:if>
          <c:if test="${lorder.dstatus==3}">
            <td>审核不通过</td>
          </c:if>
          <c:if test="${lorder.dstatus==4}">
            <td>未审核</td>
          </c:if>
          <td>${lorder.checkid.uname}</td>
          <td>${lorder.chectime}</td>
          <td>
            <c:if test="${lorder.dstatus==4}">
              <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
              <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=更新" class="tablelink">修改</a>
              <%--<a href="javascript:void(0);" class="tablelink" onclick="tipOpen(${lorder.orderId})">删除</a>--%>
              <a href="javascript:void(0)" class="tablelink" onclick="tipOpen('是否确认注销此条信息？',${lorder.orderId}">注销</a>
              <a href="javascript:void(0);" class="tablelink" onclick="examinetipOpen()">提交审核</a>
            </c:if>
            <c:if test="${lorder.dstatus==1}">
              <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
            </c:if>
            <c:if test="${lorder.dstatus==2}">
              <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
              <a href="${pageContext.request.contextPath}/storage/delivery/deliveryView.jsp" class="tablelink">出库详情</a>
            </c:if>
            <c:if test="${lorder.dstatus==3}">
              <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
              <a href="orderUpdate.jsp" class="tablelink">修改</a>
              <a href="javascript:void(0);" class="tablelink" onclick="deltipOpen()">删除</a>
              <a href="javascript:void(0);" class="tablelink" onclick="examinetipOpen()">提交审核</a>
            </c:if>
          </td>
        </tr>
      </c:forEach>
        <%--<tr>
          <td>1</td>
          <td>DJ201701270001</td>
          <td>王金平</td>
          <td>17370899727</td>
          <td>2017-01-25 15:05:05</td>
          <td>￥9,876,582</td>
          <td>关羽</td>
          <td>未审核</td>
          <td></td>
          <td></td>
          <td>
          	<a href="orderView.jsp" class="tablelink">查看详情</a>
            <a href="orderUpdate.jsp" class="tablelink">修改</a>
            <a href="javascript:void(0);" class="tablelink" onclick="deltipOpen()">删除</a>
            <a href="javascript:void(0);" class="tablelink" onclick="examinetipOpen()">提交审核</a>
            </td>
        </tr>--%>
      </tbody>
    </table>
    <div class="pagin">
      <div class="message">共<i class="blue">${countOrder}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
      <ul class="paginList">
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllOrder.do?pageIndex=1">首页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllOrder.do?pageIndex=${pageIndex-1}">上页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllOrder.do?pageIndex=${pageIndex+1}">下页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllOrder.do?pageIndex=${rowOrder}">末页</a></li>
      </ul>
    </div>
  </form>
  <!-- 提示框 -->
  <div id="tip" class="tip">
    <div class="tiptop"><span>提示信息</span><a onclick="tipClose()"></a></div>
    <div class="tipinfo"> <span><img src="${pageContext.request.contextPath }/images/ticon.png" /></span>
      <div class="tipright">
        <p></p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="cancelCust" type="button"  class="sure" value="确定" />
      <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
    </div>
  </div>

  <!-- 删除提示框 -->
  <div id="deltip" class="tip">
    <div class="tiptop"><span>提示信息</span><a onclick="deltipClose()"></a></div>
    <div class="tipinfo"> <span><img src="${pageContext.request.contextPath}/images/ticon.png" /></span>
      <div class="tipright">
        <p>是否确认删除此条信息？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="delOrder" type="button"  class="sure" value="确定"  />
      <input name="" type="button"  class="cancel" value="取消" onclick="deltipClose()" />
    </div>
  </div>
  
  <!-- 审批提示框 -->
  <div id="examinetip" class="tip">
    <div class="tiptop">
    	<span>提示信息</span><a onclick="examinetipclose()"></a>
    </div>
    <div class="tipinfo1">
        部门：
            <select class="dfselect">
            	<option>请选择</option>
                <option>市场部</option>
                <option>采购部</option>
                <option>财务部</option>
            </select>
			<p/>
            职位：
            <select class="dfselect">
            	<option>请选择</option>
                <option>部门经理</option>
                <option>部门副经理</option>
                <option>部门主管</option>
            </select>
   			<p/>
            姓名：
            <select class="dfselect">
            	<option>请选择</option>
                <option>刘备</option>
                <option>曹操</option>
            </select>
			<p/>
    </div>
    <div class="tipbtn">
      <input name="" type="button"  class="sure" value="确定" onclick="examinetipclose()" />
      &nbsp;
      <input name="" type="button"  class="cancel" value="取消" onclick="examinetipclose()" />
    </div>
  </div>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
