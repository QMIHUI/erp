﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson .header").click(function(){
		var $parent = $(this).parent();
		$(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();
		
		$parent.addClass("active");
		if(!!$(this).next('.sub-menus').size()){
			if($parent.hasClass("open")){
				$parent.removeClass("open").find('.sub-menus').hide();
			}else{
				$parent.addClass("open").find('.sub-menus').show();	
			}
		}
	});
	
	// 三级菜单点击
	$('.sub-menus li').click(function(e) {
        $(".sub-menus li.active").removeClass("active")
		$(this).addClass("active");
    });
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
})	
</script>
</head>

<body style="background:#fff3e1;">
	<div class="lefttop">
    	<span></span><a href="index.jsp" target="rightFrame">首页</a>
    </div>
    <c:if test="${user.deptId==1}">
        <dl class="leftmenu">
            <dd>
                <div class="title">营销管理</div>
                <ul class="menuson">
                    <c:if test="${user.jobId==2||user.jobId==1}">
                        <li><cite></cite>
                            <a href="${pageContext.request.contextPath}/queryAllCustom.do" target="rightFrame">客户管理</a>
                            <i></i>
                        </li>
                    </c:if>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/getAllCustomerBro.do" target="rightFrame">客户浏览</a>
                        <i></i>
                    </li>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllOrder.do" target="rightFrame">订购单管理</a>
                        <i></i>
                    </li>
                    <c:if test="${user.jobId==2||user.jobId==1}">
                        <li>
                            <cite></cite>
                            <a href="${pageContext.request.contextPath}/getAllExamineOrder.do" target="rightFrame">订购单审核</a>
                            <i></i>
                        </li>
                    </c:if>
                </ul>
            </dd>
            <dd>
                <div class="title">仓库管理</div>
                <ul class="menuson">
                    <li><cite></cite><a href="${pageContext.request.contextPath}/storageList.do" target="rightFrame">仓库管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/${user.uId}/storageBrowse.do" target="rightFrame">仓库浏览</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/${user.uId}/deliveryList.do" target="rightFrame">出库管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/${user.uId}/stockList.do" target="rightFrame">入库管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/${user.uId}/inventoryList.do" target="rightFrame">库存管理</a><i></i></li>
                </ul>
            </dd>
            <dd>
                <div class="title">采购管理</div>
                <ul class="menuson">
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllPurchases.do" target="rightFrame">采购单管理</a><i></i></li>
                    <c:if test="${user.jobId==7||user.jobId==8||user.jobId==9||user.jobId==1}">
                        <li><cite></cite><a href="${pageContext.request.contextPath}/getAllPurchaseStatus2.do" target="rightFrame">采购单审核</a><i></i></li>
                    </c:if>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllBrands.do" target="rightFrame">品牌管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllTypes.do" target="rightFrame">商品类型管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllProduct.do" target="rightFrame">商品管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllFirm.do" target="rightFrame">厂商管理</a><i></i></li>
                </ul>
            </dd>
            <dd>
                <div class="title">数据统计</div>
                <ul class="menuson">
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllcustomsStatis.do" target="rightFrame">客户销量统计</a><i></i></li>
                   <%-- <li><cite></cite><a href="${pageContext.request.contextPath}/getAllFirms.do" target="rightFrame">厂商采购统计</a><i></i></li>--%>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/outWarehouseStatis.do" target="rightFrame">出库统计</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/intoWarehouseStatis.do" target="rightFrame">入库统计</a><i></i></li>
                </ul>
            </dd>
            <dd>
                <div class="title">系统管理</div>
                <ul class="menuson">
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllUser.do" target="rightFrame">用户管理</a>
                        <i></i>
                    </li>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllDept.do" target="rightFrame">部门管理</a>
                        <i></i>
                    </li>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllJob.do" target="rightFrame">职位管理</a>
                        <i></i>
                    </li>
                        <%--<li><cite></cite><a href="sys/modules/moduleList.jsp" target="rightFrame">模块管理</a><i></i></li>--%>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllJournal.do" target="rightFrame">日志管理</a>
                        <i></i>
                    </li>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllProCity.do" target="rightFrame">区域管理</a>
                        <i></i>
                    </li>
                </ul>
            </dd>
        </dl>
    </c:if>
    <c:if test="${user.deptId==2}">
        <dl class="leftmenu">
            <dd>
                <div class="title">营销管理</div>
                <ul class="menuson">
                    <c:if test="${user.jobId==2||user.jobId==1}">
                        <li><cite></cite>
                            <a href="${pageContext.request.contextPath}/queryAllCustom.do" target="rightFrame">客户管理</a>
                            <i></i>
                        </li>
                    </c:if>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/getAllCustomerBro.do" target="rightFrame">客户浏览</a>
                        <i></i>
                    </li>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllOrder.do" target="rightFrame">订购单管理</a>
                        <i></i>
                    </li>
                    <c:if test="${user.jobId==2||user.jobId==1}">
                        <li>
                            <cite></cite>
                            <a href="${pageContext.request.contextPath}/getAllExamineOrder.do" target="rightFrame">订购单审核</a>
                            <i></i>
                        </li>
                    </c:if>
                </ul>
            </dd>
        </dl>
    </c:if>
    <c:if test="${user.deptId==3}">
        <dl class="leftmenu">
            <dd>
                <div class="title">采购管理</div>
                <ul class="menuson">
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllPurchases.do" target="rightFrame">采购单管理</a><i></i></li>
                    <c:if test="${user.jobId==7||user.jobId==8||user.jobId==9||user.jobId==1}">
                        <li><cite></cite><a href="${pageContext.request.contextPath}/getAllPurchaseStatus2.do" target="rightFrame">采购单审核</a><i></i></li>
                    </c:if>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllBrands.do" target="rightFrame">品牌管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllTypes.do" target="rightFrame">商品类型管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllProduct.do" target="rightFrame">商品管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/getAllFirm.do" target="rightFrame">厂商管理</a><i></i></li>
                </ul>
            </dd>
        </dl>
    </c:if>

    <c:if test="${user.deptId==4}">
        <dl class="leftmenu">
            <dd>
                <div class="title">仓库管理</div>
                <ul class="menuson">
                    <li><cite></cite><a href="${pageContext.request.contextPath}/storageList.do" target="rightFrame">仓库管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/${user.uId}/storageBrowse.do" target="rightFrame">仓库浏览</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/${user.uId}/deliveryList.do" target="rightFrame">出库管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/${user.uId}/stockList.do" target="rightFrame">入库管理</a><i></i></li>
                    <li><cite></cite><a href="${pageContext.request.contextPath}/${user.uId}/inventoryList.do" target="rightFrame">库存管理</a><i></i></li>
                </ul>
            </dd>
        </dl>
    </c:if>

    <c:if test="${user.deptId==5}">
        <dl class="leftmenu">
            <dd>
                <div class="title">系统管理</div>
                <ul class="menuson">
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllUser.do" target="rightFrame">用户管理</a>
                        <i></i>
                    </li>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllDept.do" target="rightFrame">部门管理</a>
                        <i></i>
                    </li>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllJob.do" target="rightFrame">职位管理</a>
                        <i></i>
                    </li>
                    <%--<li><cite></cite><a href="sys/modules/moduleList.jsp" target="rightFrame">模块管理</a><i></i></li>--%>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllJournal.do" target="rightFrame">日志管理</a>
                        <i></i>
                    </li>
                    <li>
                        <cite></cite>
                        <a href="${pageContext.request.contextPath}/queryAllProCity.do" target="rightFrame">区域管理</a>
                        <i></i>
                    </li>
                </ul>
            </dd>
        </dl>
    </c:if>
</body>
</html>
