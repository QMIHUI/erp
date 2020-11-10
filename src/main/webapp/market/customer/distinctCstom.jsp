<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>营销管理</li>
        <li>客户管理</li>
        <li>分配</li>
    </ul>
</div>
<div class="formbody">
    <form action="${pageContext.request.contextPath }/distrctCustSucc.do" method="get">
        <div class="formtitle"><span>客户信息</span></div>
        <ul class="forminfo">
            <li>
                <input name="uId" type="hidden" class="dfinput" value="${user.uId}"  />
                <label>姓名</label>
                <input name="custId" type="text" value="${custom.customid}" class="dfinput" readonly />
                <i>不可修改</i>
            </li>
            <li>
                <label>姓名</label>
                <input name="custName" type="text" value="${custom.customname}" class="dfinput" readonly />
                <i>不能修改</i>
            </li>
            <li>
                <label>性别</label>
                <cite>
                    <c:if test="${custom.sex=='男'}">
                        <input name="custSex" type="radio" value="男" checked="checked" readonly />男
                    </c:if>
                    <c:if test="${custom.sex=='女'}">
                        <input name="custSex" type="radio" value="女" checked="checked" readonly/>女
                    </c:if>
                </cite>
            </li>
            <li>
                <label>所属公司名称</label>
                <input name="custCompany" type="text" value="${custom.company}" class="dfinput" readonly/>
                <i>不能修改</i>
            </li>
            <li>
                <label>联系方式</label>
                <input name="custTel" type="text" value="${custom.telephone}" class="dfinput" readonly />
                <i>不能修改</i>
            </li>
            <li>
                <label>联系地址</label>
                <input name="custHomeAddress" type="text" value="${custom.homeaddress}" class="dfinput" readonly />
                <i>不能修改</i>
            </li>
            <li>
                <label>所属区域</label>
                <select class="dfselect" name="province" >
                    <option value="${custom.province.id}">${custom.province.pName}</option>
                </select>
                省
                <i>不能修改</i>
            </li>
            <li>
                <label>描述</label>
                <textarea class="textinput" name="desc" readonly>${custom.distract}</textarea>
                <i>不能修改</i>
            </li>
            <li>
                <label>状态</label>
                <select class="dfselect" name="status">
                    <option value="${custom.status}" selected="selected">${custom.status}</option>
                </select>
                <i>不能修改</i>
            </li>
            <li>
                <label>创建人</label>
                <input name="" type="text" value="${custom.users.uname}" readonly="readonly" class="roinput" />
                <i>不能修改</i>
            </li>
            <li>
                <label>创建时间</label>
                <input name="" type="text" value="${custom.createtime}" readonly="readonly" class="roinput" />
                <i>不能修改</i>
            </li>
            <li>
                <label>分配</label>
                部门：
                <select class="dfselect" disabled="disabled">
                    <option>请选择</option>
                    <option selected="selected">市场部</option>
                </select>
                <p/>
                姓名：
                <select class="dfselect" name="luId">
                    <option value="0">请选择</option>
                    <c:forEach items="${listUsersMarket}" var="lum">
                        <c:if test="${lum.status==1}">
                            <c:if test="${lum.jobId!=2}">
                                <option value="${lum.uId}">${lum.uname}</option>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </select>
                <p/>
                <i>必填</i>
            </li>
            <li>
                <label>&nbsp;</label>
                <input name="" type="submit" class="btn" value="确定"/>
                <input name="" type="button" class="btn" value="返回" onclick="window.location.href='customerList.jsp'"/>
            </li>
        </ul>
    </form>
</div>
</body>
</html>
