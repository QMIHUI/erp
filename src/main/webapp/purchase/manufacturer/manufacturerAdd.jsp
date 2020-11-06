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

<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>厂商管理</li>
        <li>添加</li>
    </ul>
</div>
<div class="formbody">
    <div class="formtitle"><span>厂商信息</span></div>
    <ul class="forminfo">
        <li>
            <label>厂商名称</label>
            <input name="" type="text" class="dfinput" />
            <i>必填，不能超过50个字符</i>
        </li>
        <li>
            <label>负责人</label>
            <input name="" type="text" class="dfinput" />
            <i>必填，不能超过30个字符</i>
        </li>
        <li>
            <label>联系方式</label>
            <input name="" type="text" class="dfinput" />
            <i>必填，不能超过30个字符</i>
        </li>
        <li>
            <label>联系地址</label>
            <input name="" type="text" class="dfinput" />
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
                <option>请选择</option>
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
                                $("select[name='city']").append("<option value='"+value.id+"'>"+value.cName+"</option>")
                            })

                        }
                    })
                })
            </script>
            <i>必选</i>
        </li>
        <li>
            <label>描述</label>
            <textarea class="textinput"></textarea>
            <i>可选</i>
        </li>
        <li>
            <label>状态</label>
            <select class="dfselect">
                <option value="1">可用</option>
                <option value="0">不可用</option>
            </select>
            <i>必选</i>
        </li>
        <li>
            <label>创建人</label>
            <input name="" type="text" value="马云" readonly="readonly" class="roinput" />
            <i>不能编辑</i>
        </li>
        <li>
            <label>创建时间</label>
            <input name="" type="text" value="2017-11-18 15:36:10" readonly="readonly" class="roinput" />
            <i>不能编辑</i>
        </li>
        <li>
            <label>&nbsp;</label>
            <input name="" type="button" class="btn" value="确定"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="" type="button" class="btn" value="返回" onclick="window.location.href='manufacturerList.html'"/>
        </li>
    </ul>
</div>
</body>
</html>
