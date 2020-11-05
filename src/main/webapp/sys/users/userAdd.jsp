<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/laydate/laydate.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<style type="text/css">
  #birthday{
    padding-right: 0;
    height: 32px;
    line-height: 32px;
  }
</style>
 <%-- <script type="text/javascript">
    $(function () {
      $.ajax({
        url: "getAllDept.do",
        data: {},
        type: "get",
        contentType: 'application/json',
        success:function (data) {
          $(data).each(function (index) {
            $("#deptId").append(
                    '&lt;option value="'+data[index].deptId+'">'+data[index].deptName+'</option>'
            )
          })
        }
      })
    })
  </script>--%>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="userList.jsp">系统管理</a></li>
    <li><a href="userList.jsp">用户管理</a></li>
    <li><a href="#">添加</a></li>
  </ul>
</div>
<form action="${pageContext.request.contextPath }/addUser.do" method="get">
  <div class="formbody">
    <div class="formtitle"><span>员工信息</span></div>
    <ul class="forminfo">
      <li>
        <label>员工姓名</label>
        <input name="name" type="text" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>密码</label>
        <input name="password" type="password" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>性别</label>
        <cite>
            <input name="gender" type="radio" value="男" checked="checked" />男
            <input name="gender" type="radio" value="女" />女
        </cite>
      </li>
      <li>
        <label>联系方式</label>
        <input name="telephone" type="text"  class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>出生年月</label>
        <input name="birthday" class="dfinput laydate-icon" id="birthday" />
      </li>
      <li>
        <label>所属部门</label>
        <select name="deptId" id="deptId" class="dfselect">
            <option value="0">请选择部门</option>
          <%--<c:forEach items="${listDept}" var="ld">
            <option value="${ld.deptId}">${ld.deptName}</option>
          </c:forEach>--%>
          <%--<option value="">研发</option>
          <option value="">销售</option>
          <option value="">财务</option>--%>
        </select>
      </li>
      <li>
        <label>职位</label>
        <select name="jobId" id="jobId" class="dfselect">
            <option value="0">请选择职位</option>
          <%--<c:forEach items="${listJob}" var="lj">
            <option value="${lj.jobId}">${lj.jobName}</option>
          </c:forEach>--%>
          <%--<option value="" selected="selected">经理</option>
          <option value="">高级工程师</option>
          <option value="">中级工程师</option>
          <option value="">初级工程师</option>--%>
        </select>
      </li>
      <li>
        <label>&nbsp;</label>
        <input  type="submit" class="btn" value="保存"/>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input  type="reset" class="btn" value="重置" />
      </li>
    </ul>
  </div>
</form>
<script type="text/javascript">
  !function(){
    laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
    laydate({elem: '#birthday'});//绑定元素
  }();
</script>
</body>
</html>
