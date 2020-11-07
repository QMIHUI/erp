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
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li><a href="userList.jsp">系统管理</a></li>
    <li><a href="userList.jsp">用户管理</a></li>
    <li><a href="#">修改</a></li>
  </ul>
</div>
<form action="${pageContext.request.contextPath }/updateUser.do" method="get">
  <div class="formbody">
    <div class="formtitle"><span>员工信息</span></div>
    <ul class="forminfo">
      <li>
        <label>员工ID</label>
        <input name="uid" type="text" value="${user.uId}" class="dfinput" readonly="readonly" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>员工姓名</label>
        <input name="uname" type="text" value="${user.uname}" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>密码</label>
        <input name="upwd" type="password" value="${user.upassword}" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>性别</label>
        <cite>
          <c:if test="${user.sex=='男'}">
            <input name="gender" type="radio" value="男" checked="checked" />男
            <input name="gender" type="radio" value="女" />女
          </c:if>
          <c:if test="${user.sex=='女'}">
            <input name="gender" type="radio" value="男" />男
            <input name="gender" type="radio" value="女" checked="checked"  />女
          </c:if>
        </cite>
      </li>
      <li>
        <label>联系方式</label>
        <input name="utelephone" type="text" value="${user.utelephone}" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>出生年月</label>
        <input name="birthday" class="dfinput laydate-icon" id="birthday" value="${user.birthday}">
      </li>
      <li>
        <label>所属部门</label>
        <select name="deptId" id="deptId" class="dfselect">
          <c:forEach items="${listDept}" var="ld">
            <c:if test="${ld.deptId==user.deptId}">
              <option value="${ld.deptId}" selected="selected">${ld.deptName}</option>
            </c:if>
            <c:if test="${ld.deptId!=user.deptId}">
              <c:if test="${ld.deptState=='正常'}">
                <option value="${ld.deptId}">${ld.deptName}</option>
              </c:if>
            </c:if>
          </c:forEach>
        </select>
      </li>
      <li>
        <label>职位</label>
        <select name="jobId" id="jobId" class="dfselect">
          <c:forEach  items="${listJobByDid}" var="ljbd">
            <c:if test="${user.jobId==ljbd.jobId}">
              <option selected="selected" value="${ljbd.jobId}">${ljbd.jobName}</option>
            </c:if>
            <c:if test="${user.jobId!=ljbd.jobId}">
              <option value="${ljbd.jobId}">${ljbd.jobName}</option>
            </c:if>
          </c:forEach>
        </select>
        <script type="text/javascript">
          $("select[name='deptId']").change(function (){
            $.ajax({
              dataType:"json",
              data: {"did":$(this).val()},
              url:"${pageContext.request.contextPath}/getJobByDeptId.do",
              type:"post",
              success:function (result) {
                $("select[name='jobId']").empty();
                $.each(result,function (key,value) {
                  $("select[name='jobId']").append("<option value='"+value.jobId+"'>"+value.jobName+"</option>")
                })
              }
            })
          })
        </script>
      </li>
      <li>
        <label>&nbsp;</label>
        <input name="" type="submit" class="btn" value="确认"/>
        <input name="" type="reset" class="btn" value="重置"  onclick="window.history.go(-1);"/>
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
