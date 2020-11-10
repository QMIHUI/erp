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

<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>商品类型管理</li>
        <li>修改</li>
    </ul>
</div>
<script type="text/javascript">
    $(function () {
        $("select[name='brand']").val(${type.brand.brandId});
        $("select[name='typeStatus']").val(${type.typeStatus});
        $("form").submit(function () {
            if ($("typeName").val()===""){
                alert("商品类型名称不得为空！");
                return false;
            }else if ($("typeName").val().length>50){
                alert("商品类型名称长度不得大于50字符！");
                return false;
            }
        })
    })
</script>
<form action="${pageContext.request.contextPath}/updateType.do?id=${type.typeId}" method="post">
    <div class="formbody">
        <div class="formtitle"><span>商品类型信息</span></div>
        <ul class="forminfo">
            <li>
                <label>品牌名称</label>
                <select class="dfselect" name="brand">
                    <c:forEach items="${brandList}" var="brand">
                        <option value="${brand.brandId}">${brand.brandName}</option>
                    </c:forEach>
                </select>
                <i>必选</i>
            </li>
            <li>
                <label>商品类型名称</label>
                <input name="typeName" type="text" value="${type.typeName}" class="dfinput" />
                <i>必填，不能超过50个字符</i>
            </li>
            <li>
                <label>状态</label>
                <select class="dfselect" name="typeStatus">
                    <option value="1">可用</option>
                    <option value="2">不可用</option>
                </select>
                <i>必选</i>
            </li>
            <li>
                <label>创建人</label>
                <input name="creater" type="text" value="${type.creater.uname}" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <li>
                <label>创建时间</label>
                <input name="createTime" type="text" value="<fmt:formatDate value="${type.createTime}" pattern="yyyy-MM-dd HH:MM:ss"/>" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确定"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="" type="button" class="btn" value="返回" onclick="window.location.href='productTypeList.jsp'"/>
            </li>
        </ul>
    </div>
</form>
</body>
</html>
