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
        $("form").submit(function () {
            if($("input[name='brandName']").val()===""){
                alert("品牌名称不得为空");
                return false;
            }else if ($("input[name='brandName']").val().length>50){
                alert("品牌名称长度不得超过50");
                return false;
            }
        })
    })
</script>
<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>品牌管理</li>
        <li>修改</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/brandUpdate.do?id=${brand.brandId}" method="post">
    <div class="formbody">
        <div class="formtitle"><span>品牌信息</span></div>
        <ul class="forminfo">
            <li>
                <label>品牌名称</label>
                <input name="brandName" type="text" value="${brand.brandName}" class="dfinput" />
                <i>必填，不能超过50个字符</i>
            </li>
            <li>
                <label>状态</label>
                <select class="dfselect" name="brandStatus">
                    <option value="1">可用</option>
                    <option value="2">不可用</option>
                </select>
                <i>必选</i>
            </li>
            <script type="text/javascript">
                $(function () {
                    $("select[name='brandStatus']").val(${brand.brandStatus});
                })
            </script>
            <li>
                <label>创建人</label>
                <input name="creater" type="text" value="${brand.creater.uname}" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <li>
                <label>创建时间</label>
                <input name="createTime" type="text" value="<fmt:formatDate value="${brand.createTime}" pattern="yyyy-MM-dd hh:MM:ss"/>" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确定"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="" type="button" class="btn" value="返回" onclick="window.location.href='brandList.jsp'"/>
            </li>
        </ul>
    </div>
</form>

</body>
</html>
