<%@ page import="java.util.Date" %>
<%@ page import="java.time.Year" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../../js/jquery-1.8.3.min.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        var reg=/^1[3-9]\d{9}$/;
        $("form").submit(function () {
            if ($("input[name='firmName']").val()===""){
                alert("厂商名称不得为空!");
                return false;
            }else if ($("input[name='firmFounder']").val()===""){
                alert("负责人姓名不得为空!");
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
            }
        })
    })

</script>
<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>厂商管理</li>
        <li>添加</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/addFirm.do" method="post">
    <div class="formbody">
        <div class="formtitle"><span>厂商信息</span></div>
        <ul class="forminfo">
            <li>
                <label>厂商名称</label>
                <input name="firmName" type="text" class="dfinput" />
                <i>必填，不能超过50个字符</i>
            </li>
            <li>
                <label>负责人</label>
                <input name="firmFounder" type="text" class="dfinput" />
                <i>必填，不能超过30个字符</i>
            </li>
            <li>
                <label>联系方式</label>
                <input name="firmTel" type="text" class="dfinput" />
                <i>必填，不能超过30个字符</i>
            </li>
            <li>
                <label>联系地址</label>
                <input name="firmAddress" type="text" class="dfinput" />
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
                            url:"${pageContext.request.contextPath}/getAllCitiesByProvinceId.do",
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
                <textarea class="textinput" name="firmContent"></textarea>
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
            <li>
                <label>创建人</label>
                <input name="user" type="text" value="${user.uname}" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <%--<li>
                <label>创建时间</label>
                <input name="createTime" type="text"  readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>--%>

            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确定"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="" type="button" class="btn" value="返回" onclick="window.location.href='manufacturerList.html'"/>
            </li>
        </ul>
    </div>
</form>

</body>
</html>
