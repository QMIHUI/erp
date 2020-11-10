<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        var reg=/^(([1-9][0-9]*)|((([1-9][0-9]*)|0)\.[0-9]{1,2}))$/;
        $("form").submit(function () {
            if ($("input[name='productModel']").val()===""){
                alert("商品型号不能为空");
                return false;
            }else if ($("input[name='productModel']").val().length>50){
                alert("商品型号字符数不能大于50");
                return false;
            }else if ($("input[name='productUnit']").val()===""){
                alert("单位不能为空");
                return false;
            }else if ($("input[name='productUnit']").val().length>10){
                alert("单位字符数不能大于10");
                return false;
            }else if ($("input[name='productPrice']").val()===""){
                alert("进价不能为空");
                return false;
            }else if (!reg.test($("input[name='productPrice']").val())){
                alert("进价格式不正确");
                return false;
            }
        })
    })

</script>
<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>商品管理</li>
        <li>添加</li>
    </ul>
</div>
<form action="${pageContext.request.contextPath}/addProduct.do" method="post">
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
                <label>商品类型</label>
                <select class="dfselect" name="type">
                    <c:forEach items="${typeList}" var="type">
                        <option value="${type.typeId}">${type.typeName}</option>
                    </c:forEach>
                </select>
                <i>必选</i>
            </li>
            <script type="text/javascript">
                $("select[name='brand']").change(function () {
                    $.ajax({
                        dataType:"json",
                        data: {"id":$(this).val()},
                        url:"${pageContext.request.contextPath}/getAllTypesByBrandId.do",
                        type:"post",
                        success:function (result) {
                            $("select[name='type']").empty();
                            $.each(result,function (key,value) {
                                $("select[name='type']").append("<option value='"+value.typeId+"'>"+value.typeName+"</option>")
                            })
                        }
                    })
                })
            </script>
            <li>
                <label>供应厂商</label>
                <select class="dfselect" name="firm">
                    <c:forEach items="${firmList}" var="frim">
                        <option value="${frim.firmId}">${frim.firmName}</option>
                    </c:forEach>
                </select>
                <i>必选</i>
            </li>
            <li>
                <label>商品型号</label>
                <input name="productModel" type="text" class="dfinput" />
                <i>必填，不能超过50个字符</i>
            </li>
            <li>
                <label>单位</label>
                <input name="productUnit" type="text" class="dfinput" />
                <i>必填，不能超过10个字符</i>
            </li>
            <li>
                <label>进价</label>
                <input name="productPrice" type="text" class="dfinput" />
                <i>必填</i>
            </li>
            <li>
                <label>状态</label>
                <select class="dfselect" name="productStatus">
                    <option value="1">可用</option>
                    <option value="2">不可用</option>
                </select>
                <i>必选</i>
            </li>
            <li>
                <label>创建人</label>
                <input name="creater" type="text" value="${user.uname}" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <%--<li>
                <label>创建时间</label>
                <input name="" type="text" value="2017-11-18 15:36:10" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>--%>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确定"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="" type="button" class="btn" value="返回" onclick="window.location.href='purchase/product/productList.jsp'"/>
            </li>
        </ul>
    </div>
</form>

</body>
</html>
