<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>职位管理</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
  function tipOpen(content,id) {
      $(".tipright p").text(content);
      $("input[name='cancel']").bind("click",function () {
        window.location.href="${pageContext.request.contextPath}/cancelJob.do?jid="+id;
      })
      $("#tip").fadeIn(200);
  }
  function tipOpen01(content,id) {
    $(".tipright01 p").text(content);
    $(".tipright01 p").text(content).css("font-weight","bold");
    $(".tipright01 p").text(content).css("font-size","14px");
    $("input[name='recover']").bind("click",function () {
      window.location.href="${pageContext.request.contextPath}/recoverJob.do?jid="+id;
    })
    $("#tip01").fadeIn(200);
  }
  function tipClose() {
      $("#tip").fadeOut(200);
      $("#tip01").fadeOut(200);
  }
</script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="${pageContext.request.contextPath}/sys/users/userList.jsp">系统管理</a></li>
    <li><a href="#">职位管理</a></li>
  </ul>
</div>
<div class="rightinfo">
  <form action="${pageContext.request.contextPath}/getJobByCon.do" method="get">
    <ul class="tools">
      <li> <label>职位名称:</label>
        <input type="text" name="jobName" />
      </li>
      <li> <label>所属部门：</label>
        <select name="deptId">
          <option value="0">请选择部门</option>
          <c:forEach items="${listDept}" var="ld">
            <option value="${ld.deptId}">${ld.deptName}</option>
          </c:forEach>
        </select>
      </li>
      <li style="width: 100px;height: 35px;margin-top: -10px">
        <input value="查 询" type="submit" id="searchbutton" class="subBut">
      </li>
      <%--<li class="subBut" onclick=""><img src="../../images/t06.png" />查询</li>--%>
      <li class="subBut" onclick="window.location.href='${pageContext.request.contextPath}/sys/dept/positionAdd.jsp'"><img src="${pageContext.request.contextPath}/images/t01.png" />添加</li>
    </ul>
  </form>
    <table class="tablelist">
      <thead>
        <tr>
          <th>序号</th>
          <th>职位</th>
          <th>所属部门</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listJob}" var="lj">
        <tr>
          <td>${lj.jobId}</td>
          <td>${lj.jobName}</td>
          <td>${lj.dept.deptName}</td>
          <td>${lj.jobState}</td>
          <td>
            <a href="${pageContext.request.contextPath}/getOneJob.do?jid=${lj.jobId}" class="tablelink">修改</a>
            <%--<a href="positionGrant.jsp" class="tablelink">赋权</a>--%>
            <c:if test="${lj.jobState=='正常'}">
              <a href="javascript:void(0)" class="tablelink" onclick="tipOpen('是否确认注销这个职位？',${lj.jobId})">注销</a>
            </c:if>
            <c:if test="${lj.jobState=='注销'}">
              <a href="javascript:void(0)" class="tablelink" onclick="tipOpen01('是否确认恢复这个职位？',${lj.jobId})">恢复</a>
            </c:if>
          </td>

        </tr>
      </c:forEach>
      </tbody>
    </table>
    <div class="pagin">
      <div class="message">共<i class="blue">${countJob}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
      <ul class="paginList">
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllJob.do?pageIndex=1">首页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllJob.do?pageIndex=${pageIndex-1}">上页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllJob.do?pageIndex=${pageIndex+1}">下页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllJob.do?pageIndex=${rowJob}">末页</a></li>
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
      <input name="cancel" type="button"  class="sure" value="确定" />
      <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
    </div>
  </div>

  <!-- 提示框 -->
  <div id="tip01" class="tip">
    <div class="tiptop"><span>提示信息</span><a onclick="tipClose()"></a></div>
    <div class="tipinfo"> <span><img src="${pageContext.request.contextPath }/images/ticon.png" /></span>
      <div class="tipright01">
        <p></p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
    </div>
    <div class="tipbtn">
      <input name="recover" type="button"  class="sure" value="确定" />
      <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
    </div>
  </div>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
