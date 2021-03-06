<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户管理</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
    function deltipOpen(orderId) {
      $("#deltip").fadeIn(200);
      $("input[name='delete']").bind("click",function () {
        window.location.href="${pageContext.request.contextPath}/deleteOrder.do?orderId="+orderId;
      })
    }
    function deltipClose() {
        $("#deltip").fadeOut(200);

    }
    function examinetipOpen(orderId) {
        $("#examinetip").fadeIn(200);
        $("input[name='examine']").bind("click",function () {
          window.location.href="${pageContext.request.contextPath}/examineOrder.do?orderId="+orderId;
        })
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
  <form action="getOrderByCon.do" method="get">
    <ul class="tools">
      <li> 订单编号:
        <input type="text" name="orderId" />
      </li>
     <%-- <li> 客户姓名:
        <input type="text" name="custName" />
      </li>--%>
      <li> <label>记录时间:</label>
        <input name="startDate" type="text" class="laydate-icon" id="logStartTime"/>
        <label>-</label>
        <input name="enddate" type="text" class="laydate-icon" id="logEndTime"/>
      </li>
      <li> 金额:
        <input type="text" class="stinput" name="sPrice" />-<input type="text" class="stinput" name="bPrice" />
      </li>
      <li> 审核状态：
        <select name="dstatus">
          <option value="0">请选择</option>
          <option value="4">未审核</option>
          <option value="1">审核中</option>
          <option value="2">审核通过</option>
          <option value="3">审核未通过</option>
        </select>
      </li>
      <li style="width: 100px;height: 35px;margin-top: -10px">
        <input value="查 询" type="submit" id="searchbutton" class="subBut">
      </li>
      <c:if test="${user.jobId!=2}">
       <li class="subBut" onclick="window.location.href='addOrder.do'">
         <img src="${pageContext.request.contextPath}/images/t01.png" />添加</li>
      </c:if>
    </ul>
  </form>
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
            <c:if test="${user.jobId==2}">
              <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
            </c:if>
            <c:if test="${user.jobId!=2}">
              <c:if test="${lorder.dstatus==4}">
                <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
                <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=更新" class="tablelink">修改</a>
                <a href="javascript:void(0);" class="tablelink" onclick="deltipOpen('${lorder.orderId}')">删除</a>
                <a href="javascript:void(0);" class="tablelink" onclick="examinetipOpen('${lorder.orderId}')">提交审核</a>
              </c:if>
              <c:if test="${lorder.dstatus==1}">
                <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
              </c:if>
              <c:if test="${lorder.dstatus==2}">
                <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
                <a href="${pageContext.request.contextPath}/chuKuXiangQing.do?orderId=${lorder.orderId}" class="tablelink">出库详情</a>
              </c:if>
              <c:if test="${lorder.dstatus==3}">
                <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=查看" class="tablelink">查看详情</a>
                <a href="${pageContext.request.contextPath}/getOneOrder.do?orderId=${lorder.orderId}&op=更新" class="tablelink">修改</a>
                  <a href="javascript:void(0);" class="tablelink" onclick="deltipOpen('${lorder.orderId}')">删除</a>
                <a href="javascript:void(0);" class="tablelink" onclick="examinetipOpen('${lorder.orderId}')">提交审核</a>
              </c:if>
            </c:if>
          </td>
        </tr>
      </c:forEach>
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
  <!-- 删除提示框 -->
  <div id="deltip" class="tip">
    <div class="tiptop"><span>提示信息</span><a onclick="deltipClose()"></a></div>
    <div class="tipinfo"> <span><img src="${pageContext.request.contextPath}/images/ticon.png" /></span>
      <div class="tipright">
        <p>是否确认删除此条信息？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="delete" type="button"  class="sure" value="确定" />
      <input name="" type="button"  class="cancel" value="取消" onclick="deltipClose()" />
    </div>
  </div>

  <!-- 审批提示框 -->
  <div id="examinetip" class="tip">
    <div class="tiptop"><span>提示信息</span><a onclick="deltipClose()"></a></div>
    <div class="tipinfo"> <span><img src="${pageContext.request.contextPath}/images/ticon.png" /></span>
      <div class="tipright">
        <p>是否提交审核此条信息？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="examine" type="button"  class="sure" value="确定" />
      <input name="" type="button"  class="cancel" value="取消" onclick="deltipClose()" />
    </div>
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
</div>
</body>
</html>
