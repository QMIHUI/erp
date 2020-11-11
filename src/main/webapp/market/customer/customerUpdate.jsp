<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>营销管理</li>
    <li>客户管理</li>
    <li>修改</li>
  </ul>
</div>
<div class="formbody">
  <form action="${pageContext.request.contextPath }/updateCust.do" method="get">
    <div class="formtitle"><span>客户信息</span></div>
    <ul class="forminfo">
      <li>
        <input name="uId" type="hidden" class="dfinput" value="${user.uId}"  />
        <label>姓名</label>
        <input name="custId" type="text" value="${custom.customid}" class="dfinput" readonly />
        <i>不可修改</i>
      </li>
      <li>
        <label>姓名</label>
        <input name="custName" type="text" value="${custom.customname}" class="dfinput" />
        <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>性别</label>
        <cite>
          <c:if test="${custom.sex=='男'}">
            <input name="custSex" type="radio" value="男" checked="checked" />男
            <input name="custSex" type="radio" value="女" />女
          </c:if>
          <c:if test="${custom.sex=='女'}">
            <input name="custSex" type="radio" value="男" />男
            <input name="custSex" type="radio" value="女" checked="checked" />女
          </c:if>
        </cite>
      </li>
      <li>
        <label>所属公司名称</label>
        <input name="custCompany" type="text" value="${custom.company}" class="dfinput" />
         <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>联系方式</label>
        <input name="custTel" type="text" value="${custom.telephone}" class="dfinput" />
         <i>必填，不能超过30个字符</i>
      </li>
      <li>
        <label>联系地址</label>
        <input name="custHomeAddress" type="text" value="${custom.homeaddress}" class="dfinput" />
         <i>必填，不能超过130个字符</i>
      </li>
      <li>
        <label>所属区域</label>
        <select class="dfselect" name="province">
          <c:forEach items="${provinceList}" var="province">
            <option value="${province.id}">${province.pName}</option>
          </c:forEach>
        </select>
        省
        <select class="dfselect" name="city">
          <c:forEach items="${cityList}" var="city">
            <option value="${city.cId}">${city.cName}</option>
          </c:forEach>
        </select>
        市
        <script type="text/javascript">
          $("select[name='province']").change(function () {
            $.ajax({
              dataType:"json",
              data: {"id":$(this).val()},
              url:"${pageContext.request.contextPath}/getCitiesByProvinceId.do",
              type:"post",
              success:function (result) {
                $("select[name='city']").empty();
                $.each(result,function (key,value) {
                  $("select[name='city']").append("<option value='"+value.cId+"'>"+value.cName+"</option>")
                })
              }
            })
          })
        </script>
        <i>必选</i>
      </li>
      <li>
        <label>描述</label>
        <textarea class="textinput" name="desc">${custom.distract}</textarea>
        <i>可选</i>
      </li>
      <li>
        <label>状态</label>
        <select class="dfselect" name="status">
            <c:if test="${custom.cstatus==1}">
              <option value="1" selected="selected">可用</option>
              <option value="2">不可用</option>
            </c:if>
            <c:if test="${custom.cstatus=='2'}">
              <option value="1" >可用</option>
              <option value="2" selected="selected">不可用</option>
            </c:if>
          </select>
        <i>必选</i>
      </li>
      <li>
        <label>创建人</label>
        <input name="" type="text" value="${custom.users.uname}" readonly="readonly" class="roinput" />
        <i>不能修改</i>
      </li>
      <li>
        <label>创建时间</label>
        <input name="" type="text" value="${custom.createtime}" readonly="readonly" class="roinput" />
        <i>不能修改</i>
      </li>
      <li>
        <label>&nbsp;</label>
        <input name="" type="submit" class="btn" value="确定"/>
        <input name="" type="button" class="btn" value="返回" onclick="window.location.href='customerList.jsp'"/>
      </li>
    </ul>
  </form>
</div>
</body>
</html>
