<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>客户管理</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script type="text/javascript">
        function deltipOpen(id) {
            $("#deltip").fadeIn(200);
            $("input[name='delete']").bind("click",function () {
                window.location.href="${pageContext.request.contextPath}/delPurchase.do?id="+id;
            })
        }
        function deltipClose() {
            $("#deltip").fadeOut(200);
        }
        function examinetipOpen() {
            $("#examinetip").fadeIn(200);
        }
        function examinetipclose() {
            $("#examinetip").fadeOut(200);
        }
    </script>
</head>

<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>采购管理</li>
        <li>采购单管理</li>
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <ul class="tools">
            <li> 采购单编号:
                <input type="text" />
            </li>
            <li> 采购时间:
                <input type="text" />-<input type="text" />
            </li>
            <li> 金额:
                <input type="text" class="stinput" />-<input type="text" class="stinput" />
            </li>
            <li> 审核状态：
                <select>
                    <option>请选择</option>
                    <option value="1">未审核</option>
                    <option value="2">审核中</option>
                    <option value="3">审核通过</option>
                    <option value="4">审核未通过</option>
                </select>
            </li>
            <li class="subBut" onclick="window.location.href='purchaseList.html'"><img src="../../images/t06.png" />查询</li>
            <c:if test="${user.jobId!=7}">
                <li class="subBut" onclick="window.location.href='${pageContext.request.contextPath}/toAddPurchase.do'"><img src="../../images/t01.png" />添加</li>
            </c:if>

        </ul>
        <table class="tablelist">
            <thead>
                <tr>
                    <th>序号</th>
                    <th>采购单编号</th>
                    <th>采购时间</th>
                    <th>金额</th>
                    <th>操作人</th>
                    <th>审核状态</th>
                    <th>审核人</th>
                    <th>审核时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${purchaseList}" var="purchase" varStatus="index">
                <tr>
                    <td>${index.index+1+(pageIndex-1)*5}</td>
                    <td>${purchase.purchaseId}</td>
                    <td><fmt:formatDate value="${purchase.purchaseTime}" pattern="yyyy-MM-dd"/></td>
                    <td>￥${purchase.totalMoney}</td>
                    <td>${purchase.buyer.uname}</td>
                    <c:if test="${purchase.checkStatus==1}">
                        <td>未审核</td>
                    </c:if>
                    <c:if test="${purchase.checkStatus==2}">
                        <td>审核中</td>
                    </c:if>
                    <c:if test="${purchase.checkStatus==3}">
                        <td>审核通过</td>
                    </c:if>
                    <c:if test="${purchase.checkStatus==4}">
                        <td>审核不通过</td>
                    </c:if>
                    <td>${purchase.checker.uname}</td>
                    <td><fmt:formatDate value="${purchase.checkTime}" pattern="yyyy-MM-dd"/></td>
                    <td>
                        <c:if test="${user.jobId==7}">
                            <a href="${pageContext.request.contextPath}/purchaseView.do?id=${purchase.purchaseId}" class="tablelink">查看详情</a>
                        </c:if>
                        <c:if test="${user.jobId!=7}">
                            <c:if test="${purchase.checkStatus==1||purchase.checkStatus==4}">
                                <a href="${pageContext.request.contextPath}/updatePurchase.do?id=${purchase.purchaseId}" class="tablelink">修改</a>
                            </c:if>
                            <c:if test="${purchase.checkStatus==1}">
                                <a href="javascript:void(0);" class="tablelink" onclick="deltipOpen('${purchase.purchaseId}')">删除</a>
                            </c:if>
                            <c:if test="${purchase.checkStatus==1||purchase.checkStatus==4}">
                                <a href="${pageContext.request.contextPath}/submitPurchase.do?id=${purchase.purchaseId}" class="tablelink" <%--onclick="examinetipOpen()"--%>>提交审核</a>
                            </c:if>
                            <c:if test="${purchase.checkStatus==2||purchase.checkStatus==3}">
                                <a href="${pageContext.request.contextPath}/purchaseView.do?id=${purchase.purchaseId}" class="tablelink">查看详情</a>
                            </c:if>
                            <%--<c:if test="${purchase.checkStatus==3&&purchase.cgState==2}">
                                <a href="${pageContext.request.contextPath}/${purchase.purchaseId}/stockView.do" class="tablelink">入库详情</a>
                            </c:if>--%>

                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">${countPurchase}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllPurchases.do?pageIndex=1">首页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllPurchases.do?pageIndex=${pageIndex-1}">上页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllPurchases.do?pageIndex=${pageIndex+1}">下页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllPurchases.do?pageIndex=${row}">末页</a></li>
            </ul>
        </div>
    </form>
    <!-- 删除提示框 -->
    <div id="deltip" class="tip">
        <div class="tiptop"><span>提示信息</span><a onclick="deltipClose()"></a></div>
        <div class="tipinfo"> <span><img src="../../images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认删除此条信息？</p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
        </div>
        <div class="tipbtn">
            <input name="delete" type="button"  class="sure" value="确定" />
            <input name="" type="button"  class="cancel" value="取消" onclick="deltipClose()" />
        </div>
    </div>

    <!-- 审批提示框 -->
    <div id="examinetip" class="tip">
        <div class="tiptop">
            <span>提示信息</span><a onclick="examinetipclose()"></a>
        </div>
        <div class="tipinfo1">

            部门：
            <select class="dfselect">
                <option>请选择</option>
                <option>销售部</option>
                <option>采购部</option>
                <option>财务部</option>
            </select>
            <p/>
            职位：
            <select class="dfselect">
                <option>请选择</option>
                <option>部门经理</option>
                <option>部门副经理</option>
                <option>部门主管</option>
            </select>
            <p/>
            职位：
            <select class="dfselect">
                <option>请选择</option>
                <option>刘备</option>
                <option>曹操</option>
            </select>
            <p/>
        </div>
        <div class="tipbtn">
            <input name="" type="button"  class="sure" value="确定" onclick="examinetipclose()" />
            &nbsp;
            <input name="" type="button"  class="cancel" value="取消" onclick="examinetipclose()" />
        </div>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
