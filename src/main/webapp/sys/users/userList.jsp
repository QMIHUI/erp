<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    function tipOpen(content,uid) {
        $(".tipright p").text(content);
        $("input[name='cancelUser']").bind("click",function () {
          window.location.href="${pageContext.request.contextPath}/forbiddenUser.do?uid="+uid;
        })
        $("#tip").fadeIn(200);
    }
    function tipOpen1(content,uid) {
      $(".tipright1 p").text(content);
      $(".tipright1 p").text(content).css("font-weight","bold");
      $(".tipright1 p").text(content).css("font-size","14px");
      $("input[name='cancelUser']").bind("click",function () {
        window.location.href="${pageContext.request.contextPath}/recoverUser.do?uid="+uid;
      })
      $("#tip1").fadeIn(200);
    }
    function tipClose() {
        $("#tip").fadeOut(200);
        $("#tip1").fadeOut(200);
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
  <form action="${pageContext.request.contextPath}/getUsersByCon.do" method="get">
    <ul class="tools">
     <%-- <li> <label>员工编号:</label>
        <input type="text" name="uid"  />
      </li>--%>
      <li> <label>员工姓名:</label>
        <input type="text" name="uname" />
      </li>
      <li>
        <label>所属部门：</label>
        <select name="deptId">
          <option value="0">请选择部门</option>
          <c:forEach items="${listDept}" var="ld">
            <option value="${ld.deptId}">${ld.deptName}</option>
          </c:forEach>
          <%--<option value="">研发</option>
          <option value="">销售</option>
          <option value="">财务</option>--%>
        </select>
      </li>
      <li> <label>状态：</label>
        <select name="status">
          <option value="0">请选择</option>
          <option value="1">在职</option>
          <option value="2">离职</option>
        </select>
      </li>
      <li style="width: 100px;height: 35px;margin-top: -10px">
        <input value="查 询" type="submit" id="searchbutton" class="subBut">
      </li>
      <%--<li class="subBut" onclick=""><img src="${pageContext.request.contextPath }/images/t06.png" />查询</li>--%>
      <li class="subBut" onclick="window.location.href='sys/users/userAdd.jsp'"><img src="${pageContext.request.contextPath }/images/t01.png" />添加</li>
    </ul>
  </form>
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
        <c:forEach items="${listUsers}" var="users">
          <tr>
            <td>${users.uId}</td>
            <td>${users.uname}</td>
            <td>${users.utelephone}</td>
            <td>${users.dept.deptName}</td>
            <td>${users.job.jobName}</td>
            <td>${users.sex}</td>
            <c:if test="${users.status==1}">
              <td>在职</td>
            </c:if>
            <c:if test="${users.status==2}">
              <td>离职</td>
            </c:if>
            <td>${users.hiredate}</td>
            <td>${users.leavedate}</td>
            <td>
                <a href="${pageContext.request.contextPath }/selectOneUser.do?uid=${users.uId}&did=${users.deptId}" class="tablelink">修改</a>
                <c:if test="${users.status==1}">
                  <a href="javascript:void(0);" class="tablelink" onclick="tipOpen('您确定此员工离职吗？',${users.uId})">离职</a>
                </c:if>
              <c:if test="${users.status==2}">
                <a href="javascript:void(0);" class="tablelink" onclick="tipOpen1('您确定要恢复此员工吗？',${users.uId})">恢复</a>
              </c:if>

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

  <!-- 提示框 -->
  <div id="tip" class="tip">
    <div class="tiptop"><span>提示信息</span><a onclick="tipClose()"></a></div>
    <div class="tipinfo"> <span><img src="${pageContext.request.contextPath }/images/ticon.png" /></span>
      <div class="tipright">
        <p></p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="cancelUser" type="button"  class="sure" value="确定" />
      <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
    </div>
  </div>

  <!-- 提示框 -->
  <div id="tip1" class="tip">
    <div class="tiptop"><span>提示信息</span><a onclick="tipClose()"></a></div>
    <div class="tipinfo"> <span><img src="${pageContext.request.contextPath }/images/ticon.png" /></span>
      <div class="tipright1">
        <p></p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="cancelUser" type="button"  class="sure" value="确定" />
      <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
    </div>
  </div>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
