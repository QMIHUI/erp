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
    <li>营销管理</li>
    <li>客户浏览</li>
    <li>基本内容</li>
  </ul>
</div>
<div class="rightinfo">
  <form action="${pageContext.request.contextPath}/getCustBroByCon.do" method="get">
    <ul class="tools">
      <li> 公司名称:
        <input name="custCom" type="text" size="12" />
      </li>
      <li> 客户姓名:
        <input name="custName" type="text" size="12" />
      </li>
      <li> 所属区域：
        <select name="province">
          <option value="0">请选择省份</option>
          <c:forEach items="${provinceList}" var="province">
            <option value="${province.id}">${province.pName}</option>
          </c:forEach>
        </select>
      </li>
      <li style="width: 100px;height: 35px;margin-top: -10px">
        <input value="查 询" type="submit" id="searchbutton" class="subBut">
      </li>
    </ul>
    <table class="tablelist">
      <thead>
      <tr>
        <th>序号</th>
        <th>客户姓名</th>
        <th>性别</th>
        <th>联系电话</th>
        <th>所属公司</th>
        <th>所属区域</th>
        <th>分配时间</th>
        <th>负责人ID</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${listCustomNotNull}" var="customerNotNull">
        <tr>
          <td>${customerNotNull.customid}</td>
          <td>${customerNotNull.customname}</td>
          <td>${customerNotNull.sex}</td>
          <td>${customerNotNull.telephone}</td>
          <td>${customerNotNull.company}</td>
          <td>${customerNotNull.province.pName}</td>
          <td>${customerNotNull.distractime}</td>
          <td>${customerNotNull.leading}</td>
          <td>
             <a href="${pageContext.request.contextPath}/getOneCust.do?customId=${customerNotNull.customid}&op=查看" class="tablelink">查看详情</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <div class="pagin">
      <div class="message">共<i class="blue">${countCustNotNull}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
      <ul class="paginList">
        <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllCustomerBro.do?pageIndex=1">首页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllCustomerBro.do?pageIndex=${pageIndex-1}">上页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllCustomerBro.do?pageIndex=${pageIndex+1}">下页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllCustomerBro.do?pageIndex=${rowCustNotNull}">末页</a></li>
      </ul>
    </div>
  </form>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
