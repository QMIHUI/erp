<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
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
    <li><a href="#">系统管理</a></li>
    <li><a href="#">用户管理</a></li>
  </ul>
</div>
<div class="rightinfo">
  <form action="" method="post">
    <ul class="tools">
      <li> <label>员工编号:</label>
        <input type="text" />
      </li>
      <li> <label>员工姓名:</label>
        <input type="text" />
      </li>
      <li> <label>所属部门：</label>
        <select name="">
          <option>请选择部门</option>
          <option value="">研发</option>
          <option value="">销售</option>
          <option value="">财务</option>
        </select>
      </li>
      <li> <label>状态：</label>
        <select name="">
          <option>请选择</option>
          <option value="1">在职</option>
          <option value="0">离职</option>
        </select>
      </li>
      <li class="subBut" onclick=""><img src="${pageContext.request.contextPath }/images/t06.png" />查询</li>
      <li class="subBut" onclick="window.location.href='sys/users/userAdd.jsp'"><img src="${pageContext.request.contextPath }/images/t01.png" />添加</li>
    </ul>
    <table class="tablelist">
      <thead>
        <tr>
          <th>员工编号</th>
          <th>员工姓名</th>
          <th>联系电话</th>
          <th>所属部门</th>
          <th>职位</th>
          <th>性别</th>
          <th>状态</th>
          <th>入职时间</th>
          <th>离职时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${listUsers}" var="user">
          <tr>
            <td>${user.uId}</td>
            <td>${user.uname}</td>
            <td>${user.utelephone}</td>
            <td>${user.dept.deptName}</td>
            <td>${user.job.jobName}</td>
            <td>${user.sex}</td>
            <c:if test="${user.status==1}">
              <td>在职</td>
            </c:if>
            <c:if test="${user.status==2}">
              <td>离职</td>
            </c:if>
            <td>${user.hiredate}</td>
            <td>${user.leavedate}</td>
            <td>
               <a href="sys/users/userUpdate.jsp" class="tablelink">修改</a>
               <a href="javascript:void(0);" class="tablelink" onclick="tipOpen('您确定此员工离职吗？')">离职</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="pagin">
      <div class="message">共<i class="blue">${countUsers}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
      <ul class="paginList">
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllUser.do?pageIndex=1">首页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllUser.do?pageIndex=${pageIndex-1}">上页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllUser.do?pageIndex=${pageIndex+1}">下页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllUser.do?pageIndex=${row}">末页</a></li>
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
