<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery.js"></script>
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>采购管理</li>
    <li>采购单管理</li>
    <li>添加</li>
  </ul>
</div>
<script type="text/javascript">
    $(function () {
        $("form").submit(function () {
            var count=0;
            $("input[name='productPrice']").each(function () {
                if ($(this).val()==""){
                    alert("商品类型不得为空！")
                    count++;
                }
            });
            $("input[name='count']").each(function () {
                if ($(this).val()==""){
                    alert("商品数量不得为空！")
                    count++;
                }
            });
            if (count>0){
                return false;
            }
        })
    })

</script>
<form action="${pageContext.request.contextPath}/addPurchase.do" method="post">
    <div class="formbody">
        <div class="formtitle"><span>采购单信息</span></div>
        <ul class="forminfo">
            <li>
                <label>采购单编号</label>
                <input name="purchaseId" type="text" value="${purchaseIdAdd}" readonly="readonly" class="roinput" />
                <i>自动生成不能编辑</i>
            </li>
           <%-- <li>
                <label>采购时间</label>
                <input name="" type="text" value="2017-11-18 15:36:10" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>--%>
            <li>
                <label>创建人</label>
                <input name="buyer" type="text" value="${user.uname}" readonly="readonly" class="roinput" />
                <i>不能编辑</i>
            </li>
            <li>
                <label>采购仓库</label>
                <select name="warehouse" class="roinput">
                    <c:forEach items="${warehouseList}" var="warehouse">
                        <option value="${warehouse.id}">${warehouse.name}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                <label>审核状态</label>
                <input type="text" value="未审核" readonly="readonly" class="roinput" />
                <input name="" type="hidden" value="1" />
                <i>初始状态，不能编辑</i>
            </li>
            <li>
                <label>总金额</label>
                <input name="purchaseTotalMoney" type="text" readonly="readonly" class="roinput" />
                <i>自动运算，不能编辑</i>
            </li>
            <li>
                <input type="button" value="新增" class="smallbtn" name="addLine"/>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="保存" class="smallbtn" />
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" value="返回" class="smallbtn" onclick="window.location.href='purchaseList.jsp'"/>
            </li>
        </ul>
        <script type="text/javascript">
            $(function () {
                $("input[name='addLine']").click(function () {
                    var child="<tr><td><select name='brand' onchange='changeBrand(this)'><c:forEach items='${brandList}' var='brand'><option value='${brand.brandId}'>${brand.brandName}</option></c:forEach></select></td><td><select name='type' onchange='changeType(this)'><c:forEach items='${typeList}' var='type'><option value='${type.typeId}'>${type.typeName}</option></c:forEach></select></td><td><select name='product' onchange='changeProduct(this)'><c:forEach items='${productList}' var='product'><option value='${product.productId}'>${product.productModel}</option></c:forEach></select></td><td><input type=\"number\" name=\"count\" onblur=\"changeCount(this) \" oninput=\"value=value.replace(/[^\\d]/g,'')\"/></td><td>${productList[0].productUnit}</td><td>￥<input type='text' name='productPrice' value='${productList[0].productPrice}' readonly/></td><td>￥<input type='text' name='productTotalMoney' readonly/></td><td><input type='button' value='删除' onclick='delLine(this)'/></td></tr>";
                    $("tbody").append(child);
                })
            })
            function changeBrand(thisobj){
                $.ajax({
                    dataType:"json",
                    data: {"id":$(thisobj).val()},
                    url:"${pageContext.request.contextPath}/getAllTypesByBrandIdPurchase.do",
                    type:"get",
                    success:function (result) {
                        var typeList=result.typeList;
                        var productList=result.productList;
                        $(thisobj).parent("td").next("td").find("select").empty();
                        $(thisobj).parent("td").next("td").next("td").find("select").empty();
                        $.each(typeList,function (index,type) {
                            $(thisobj).parent("td").next("td").find("select").append("<option value='"+type.typeId+"'>"+type.typeName+"</option>");
                        })
                        $.each(productList,function (index,product) {
                            $(thisobj).parent("td").next("td").next("td").find("select").append("<option value='"+product.productId+"'>"+product.productModel+"</option>");
                        })
                        $(thisobj).parent("td").next("td").next("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").next("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").next("td").next("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").next("td").next("td").find("input").val(productList[0].productPrice);


                    }
                })
            }
            function changeType(thisobj){
                $.ajax({
                    dataType:"json",
                    data: {"id":$(thisobj).val()},
                    url:"${pageContext.request.contextPath}/getAllProductsByTypeIdPurchase.do",
                    type:"get",
                    success:function (result) {
                        var productList=result.productList;
                        $(thisobj).parent("td").next("td").find("select").empty();
                        $.each(productList,function (index,product) {
                            $(thisobj).parent("td").next("td").find("select").append("<option value='"+product.productId+"'>"+product.productModel+"</option>");
                        })
                        $(thisobj).parent("td").next("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").next("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").next("td").find("input").val(productList[0].productPrice);
                    }
                })
            }
            function changeProduct(thisobj){
                $.ajax({
                    dataType:"json",
                    data: {"id":$(thisobj).val()},
                    url:"${pageContext.request.contextPath}/getProductByIdPurchase.do",
                    type:"get",
                    success:function (result) {
                        $(thisobj).parent("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").find("input").val("");
                        $(thisobj).parent("td").next("td").next("td").next("td").find("input").val(result.productPrice);

                        var result=parseFloat(0);
                        $("input[name='productTotalMoney']").each(function () {
                            if ($(this).val()!=""){
                                result+=parseFloat($(this).val());
                            }
                        })
                        $("input[name='purchaseTotalMoney']").val(result);
                    }
                })
            }
            function changeCount(thisobj) {
                var count=$(thisobj).val();
                var productPrice=$(thisobj).parent("td").next("td").next("td").find("input").val();
                $(thisobj).parent("td").next("td").next("td").next("td").find("input").val(count*productPrice);
                var result=parseFloat(0);
                $("input[name='productTotalMoney']").each(function () {
                    if ($(this).val()!=""){
                        result+=parseFloat($(this).val());
                    }
                })
                $("input[name='purchaseTotalMoney']").val(result);
            }
        </script>
        <table class="tablelist">
            <thead>
            <tr>
                <th>品牌</th>
                <th>类型</th>
                <th>型号</th>
                <th>数量</th>
                <th>单位</th>
                <th>单价</th>
                <th>金额</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody name="purchaseForm">
            <tr>
                <td>
                    <select name="brand" onchange="changeBrand(this)">
                        <c:forEach items="${brandList}" var="brand">
                            <option value="${brand.brandId}">${brand.brandName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="type" onchange='changeType(this)'>
                        <c:forEach items="${typeList}" var="type">
                            <option value="${type.typeId}">${type.typeName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select name="product" onchange='changeProduct(this)'>
                        <c:forEach items="${productList}" var="product">
                            <option value="${product.productId}">${product.productModel}</option>
                        </c:forEach>
                    </select>
                </td>
                <%--<td>
                    <select>
                      <option>请选择</option>
                      <option>北京联想科技股份有限公司</option>
                      <option>北京京东商贸股份有限公司</option>
                  </select>
                </td>--%>
                <td><input type="number" name="count" onblur="changeCount(this) " oninput="value=value.replace(/[^\d]/g,'')"/></td>
                <td>${productList[0].productUnit}</td>
                <td>￥<input type="text" name="productPrice" value="${productList[0].productPrice}" readonly/></td>
                <td>￥<input type="text" name="productTotalMoney" readonly/></td>
                <td><input type="button" value="删除" onclick="delLine(this)"/></td>
            </tr>
            </tbody>
        </table>
    </div>
    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
        function delLine(i) {
            if($("tbody").children().length!=1){
                $(i).closest("tr").remove();
                var result=parseFloat(0);
                $("input[name='productTotalMoney']").each(function () {
                    if ($(this).val()!=""){
                        result+=parseFloat($(this).val());
                    }
                })
                $("input[name='purchaseTotalMoney']").val(result);
            }else{
                alert("采购单商品列表不得为空！")
            }
        }
    </script>
</form>

</body>
</html>
