<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        var reg=/^1[3-9]\d{9}$/;
        $("form").submit(function () {
            if ($("input[name='firmName']").val()===""){
                alert("厂商名称不得为空!");
                return false;
            }else if ($("input[name='firmName']").val().length>50){
                alert("厂商名称不得多于50字符!");
                return false;
            }else if ($("input[name='firmFounder']").val()===""){
                alert("负责人姓名不得为空!");
                return false;
            }else if ($("input[name='firmFounder']").val().length>30){
                alert("负责人姓名不得多于30字符!");
                return false;
            }else if ($("input[name='firmTel']").val()===""){
                alert("联系方式不得为空!");
                return false;
            }else if (!reg.test($("input[name='firmTel']").val())){
                alert("联系方式格式不正确!");
                return false;
            }else if ($("input[name='firmAddress']").val()===""){
                alert("联系地址不得为空!");
                return false;
            }else if ($("input[name='firmName']").val().length>130){
                alert("联系地址不得多于130字符!");
                return false;
            }
        })
    })

</script>
<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>厂商管理</li>
        <li>修改</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/firmDoUpdate.do?id=${firm.firmId}" method="post">
    <div class="formbody">
        <div class="formtitle"><span>厂商信息</span></div>
        <ul class="forminfo">
            <li>
                <label>厂商名称</label>
                <input name="firmName" type="text" value="${firm.firmName}" class="dfinput" />
                <i>必填，不能超过50个字符</i>
            </li>
            <li>
                <label>负责人</label>
                <input name="firmFounder" type="text" value="${firm.firmFounder}" class="dfinput" />
                <i>必填，不能超过30个字符</i>
            </li>
            <li>
                <label>联系方式</label>
                <input name="firmTel" type="text" value="${firm.firmTel}" class="dfinput" />
                <i>必填，不能超过30个字符</i>
            </li>
            <li>
                <label>联系地址</label>
                <input name="firmAddress" type="text" value="${firm.firmAddress}" class="dfinput" />
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
                <i>必选</i>
            </li>
            <li>
                <label>描述</label>
                <textarea name="firmContent" class="textinput">${firm.firmContent}</textarea>
                <i>可选</i>
            </li>
            <li>
                <label>状态</label>
                <select class="dfselect" name="status">
                    <option value="1">可用</option>
                    <option value="2">不可用</option>
                </select>
                <i>必选</i>
            </li>
            <script type="text/javascript">
                $(function () {
                    $("select[name='province']").val(${firm.city.province.id});
                    $("select[name='city']").val(${firm.city.cId});
                    $("select[name='status']").val(${firm.status});
                })
            </script>
            <li>
                <label>创建人</label>
                <input name="" type="text" value="${firm.user.uname}" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <li>
                <label>创建时间</label>
                <input name="user" type="text" value="<fmt:formatDate value="${firm.createTime}" pattern="yyyy-MM-dd hh:MM:ss"/>" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确定"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="" type="button" class="btn" value="返回" onclick="window.location.href='manufacturerList.jsp'"/>
            </li>
        </ul>
    </div>
</form>

</body>
</html>
