<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>区域管理</li>
    <%--<li>品牌管理</li>
    <li>基本内容</li>--%>
  </ul>
</div>
<div class="rightinfo">
  <form action="${pageContext.request.contextPath }/getProCityByCon.do" method="get">
    <ul class="tools">
      <li>
        <label>省:</label>
        <select class="dfselect" name="province">
          <option value="0">请选择</option>
          <c:forEach items="${listPro}" var="province">
            <option value="${province.id}">${province.pName}</option>
          </c:forEach>
        </select>
        <label>市:</label>
        <select class="dfselect" name="city">
          <option value="0">请选择</option>
        </select>
        <script type="text/javascript">
          $("select[name='province']").change(function () {
            $.ajax({
              dataType:"json",
              data: {"id":$(this).val()},
              url:"${pageContext.request.contextPath}/getAllCitiesByProvinceId.do",
              type:"post",
              success:function (result) {
                $("select[name='city']").empty();
                $("select[name='city']").append("<option value='0'>请选择</option>");
                $.each(result,function (key,value) {
                  $("select[name='city']").append("<option value='"+value.cId+"'>"+value.cName+"</option>")
                })
              }
            })
          })
        </script>
      </li>
      <li style="width: 100px;height: 35px;margin-top: -10px">
        <input value="查 询" type="submit" id="searchbutton" class="subBut">
      </li>
      <%--<li class="subBut" onclick="window.location.href='brandList.jsp'"><img src="${pageContext.request.contextPath }/images/t06.png" />查询</li>--%>
    </ul>
  </form>
    <table class="tablelist">
      <thead>
        <tr>
          <th>省ID</th>
          <th>省名称</th>
          <th>市ID</th>
          <th>市名称</th>
          <th>编号</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${listCity}" var="lc">
          <tr>
            <td>${lc.pId}</td>
            <td>${lc.province.pName}</td>
            <td>${lc.cId}</td>
            <td>${lc.cName}</td>
            <td>${lc.cNumber}</td>
          </tr>
        </c:forEach>
       <%-- <tr>
          <td>1</td>
          <td>110000</td>
          <td>北京市</td>
          <td>110100</td>
          <td>市辖区</td>
        </tr>--%>
      </tbody>
    </table>
    <div class="pagin">
      <div class="message">共<i class="blue">${countCity}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
      <ul class="paginList">
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllProCity.do?pageIndex=1">首页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllProCity.do?pageIndex=${pageIndex-1}">上页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllProCity.do?pageIndex=${pageIndex+1}">下页</a></li>
        <li class="paginItem"><a href="${pageContext.request.contextPath }/queryAllProCity.do?pageIndex=${rowCity}">末页</a></li>
      </ul>
    </div>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
