<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>部门管理</title>
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
    <li><a href="${pageContext.request.contextPath }/sys/users/userList.jsp">系统管理</a></li>
    <li><a href="#">部门管理</a></li>
  </ul>
</div>
<div class="rightinfo">
  <form action="${pageContext.request.contextPath }/getDeptByName.do" method="get">
    <ul class="tools">
      <li> <label>部门名称:</label>
        <input type="text" name="dname" />
      </li>
      <li style="width: 100px;height: 30px;margin-top: -5px">
        <input	value="查 询" type="submit" id="searchbutton" class="subBut">
      </li>
      <%--<li class="subBut" onclick="window.location.href='getDeptByName.do'">
        <img src="${pageContext.request.contextPath }/images/t06.png" />查询
      </li>--%>
      <li class="subBut" onclick="window.location.href='sys/dept/deptAdd.jsp'"><img src="${pageContext.request.contextPath }/images/t01.png" />添加</li>
    </ul>
  </form>
    <table class="tablelist">
      <thead>
        <tr>
          <th>部门编号</th>
          <th>部门名称</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${listDeptPager}" var="ldp">
          <tr>
            <td>${ldp.deptId}</td>
            <td>${ldp.deptName}</td>
            <td>${ldp.deptState}</td>
            <td>
              <a href="${pageContext.request.contextPath }/getOneDept.do?did=${ldp.deptId}" class="tablelink">修改</a>
              <c:if test="${ldp.deptState=='正常'}">
               <a href="${pageContext.request.contextPath }/forbiddenDept.do?did=${ldp.deptId}" class="tablelink" onclick="tipOpen('是否确认注销此条信息？')">注销</a>
              </c:if>
              <c:if test="${ldp.deptState=='注销'}">
                <a href="${pageContext.request.contextPath }/recoverDept.do?did=${ldp.deptId}" class="tablelink" onclick="tipOpen('是否确认恢复此条信息？')">恢复</a>
              </c:if>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="pagin">
      <div class="message">共<i class="blue">${countDept}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
      <ul class="paginList">
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllDept.do?pageIndex=1">首页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllDept.do?pageIndex=${pageIndex-1}">上页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllDept.do?pageIndex=${pageIndex+1}">下页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllDept.do?pageIndex=${rowDept}">末页</a></li>
      </ul>
    </div>

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
