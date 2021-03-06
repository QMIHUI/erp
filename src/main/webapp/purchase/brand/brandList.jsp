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
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script type="text/javascript">
        function tipOpen(content,id) {
            $(".tipright p").text(content);
            $("input[name='delete']").bind("click",function () {
                window.location.href="${pageContext.request.contextPath}/delBrand.do?id="+id;
            })
            $("#tip").fadeIn(200);
        }
        function tipClose() {
            $("#tip").fadeOut(200);
        }
    </script>
</head>

<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>品牌管理</li>
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <ul class="tools">
            <li> 品牌名称:
                <input type="text" />
            </li>
            <li> 状态：
                <select>
                    <option>请选择</option>
                    <option value="1">可用</option>
                    <option value="0">不可用</option>
                </select>
            </li>
            <li class="subBut" onclick="window.location.href='brandList.html'"><img src="../../images/t06.png" />查询</li>
            <li class="subBut" onclick="window.location.href='brandAdd.jsp'"><img src="../../images/t01.png" />添加</li>
        </ul>
        <table class="tablelist">
            <thead>
            <tr>
                <th>序号</th>
                <th>品牌名称</th>
                <th>状态</th>
                <th>创建时间</th>
                <th>创建人</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${brandList}" var="brand" varStatus="index">
                <tr>
                    <td>${index.index+1+(pageIndex-1)*5}</td>
                    <td>${brand.brandName}</td>
                    <c:if test="${brand.brandStatus==1}">
                        <td>可用</td>
                    </c:if>
                    <c:if test="${brand.brandStatus==2}">
                        <td>不可用</td>
                    </c:if>
                    <td><fmt:formatDate value="${brand.createTime}" pattern="yyyy-MM-dd HH:MM:ss"/></td>
                    <td>${brand.creater.uname}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/toBrandUpdate.do?id=${brand.brandId}" class="tablelink">修改</a>
                        <c:if test="${brand.brandStatus==1}">
                            <a href="javascript:void(0)" class="tablelink" onclick="tipOpen('是否确认注销此条信息？',${brand.brandId})">注销</a>
                        </c:if>
                        <c:if test="${brand.brandStatus==2}">
                            <a href="${pageContext.request.contextPath}/recoverBrand.do?id=${brand.brandId}" class="tablelink" >恢复</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">${countBrand}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllBrands.do?pageIndex=1">首页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllBrands.do?pageIndex=${pageIndex-1}">上页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllBrands.do?pageIndex=${pageIndex+1}">下页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllBrands.do?pageIndex=${row}">末页</a></li>
            </ul>
        </div>
    </form>
    <!-- 提示框 -->
    <div id="tip" class="tip">
        <div class="tiptop"><span>提示信息</span><a onclick="tipClose()"></a></div>
        <div class="tipinfo"> <span><img src="../../images/ticon.png" /></span>
            <div class="tipright">
                <p></p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
        </div>
        <div class="tipbtn">
            <input name="delete" type="button"  class="sure" value="确定"  />
            &nbsp;
            <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
        </div>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
