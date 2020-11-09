<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日志管理</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/laydate/laydate.js"></script>
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
    <li><a href="${pageContext.request.contextPath}/sys/users/userList.jsp">系统管理</a></li>
    <li><a href="#">日志管理</a></li>
  </ul>
</div>
<div class="rightinfo">
  <form action="${pageContext.request.contextPath}/getJournalByCon.do" method="get">
    <ul class="tools">
     <%-- <li> <label>修改者员工ID:</label>
        <select name="uId">
          <option value="0">请选择部门</option>
          <c:forEach items="${listJournal}" var="lJ">
            <option value="${lJ.uId}">${lJ.uId}</option>
          </c:forEach>
        </select>
      </li>--%>
      <li> <label>被修改者员工姓名:</label>
        <input type="text" name="uname" />
      </li>
      <li> <label>日志内容：</label>
        <input type="text" name="jcontent" />
      </li>
      <li> <label>记录时间:</label>
        <input name="startDate" type="text" class="laydate-icon" id="logStartTime"/>
        <label>-</label>
        <input name="enddate" type="text" class="laydate-icon" id="logEndTime"/>
      </li>
      <li style="width: 100px;height: 35px;margin-top: -10px">
        <input value="查 询" type="submit" id="searchbutton" class="subBut">
      </li>
     <%-- <li class="subBut" onclick=""><img src="../../images/t06.png" />查询</li>--%>
    </ul>
  </form>
    <table class="tablelist">
      <thead>
        <tr>
          <th>序号</th>
          <th>被修改员工姓名</th>
          <th>修改者员工ID</th>
          <th>日志内容</th>
          <th>记录时间</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
      <c:forEach items="${listJournal}" var="listJ">
        <tr>
          <td>${listJ.jId}</td>
          <td>${listJ.bname}</td>
          <td>${listJ.uId}</td>
          <td>${listJ.jcontent}</td>
          <td>${listJ.jdate}</td>
          <td>
            <a href="${pageContext.request.contextPath }/getOneJournal.do?jId=${listJ.jId}" class="tablelink">查看日志信息</a>
          </td>
        </tr>
      </c:forEach>
       <%-- <tr>
          <td>1</td>
          <td>9527</td>
          <td>唐寅</td>
          <td>用户管理</td>
          <td>进行了添加用户xxx的操作</td>
          <td>2016-11-20 15:05:29</td>
          <td>
            <a href="logView.jsp" class="tablelink">查看日志信息</a>
          </td>
        </tr>
--%>
      </tbody>
    </table>
    <div class="pagin">
      <div class="message">共<i class="blue">${countJournal}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
      <ul class="paginList">
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllJournal.do?pageIndex=1">首页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllJournal.do?pageIndex=${pageIndex-1}">上页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllJournal.do?pageIndex=${pageIndex+1}">下页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllJournal.do?pageIndex=${rowJournal}">末页</a></li>
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
</body>
</html>
