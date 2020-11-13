<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                window.location.href="${pageContext.request.contextPath}/delFirm.do?id="+id;
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
        <li>厂商管理</li>
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <ul class="tools">
            <li> 公司名称:
                <input type="text" />
            </li>
            <li> 负责人姓名:
                <input type="text" />
            </li>
            <li> 所属区域：
                <select>
                    <option value="0">请选择省份</option>
                    <option value="0">请选择</option>
                    <c:forEach items="${provinceList}" var="province" >
                        <option value="${province.id}">${province.pName}</option>
                    </c:forEach>
                </select>
                <select>
                    <option value="0">请选择城市</option>
                    <c:forEach items="${cityList}" var="city" >
                        <option value="${city.cId}">${city.cName}</option>
                    </c:forEach>
                </select>
            </li>
            <li> 状态：
                <select>
                    <option>请选择</option>
                    <option value="1">可用</option>
                    <option value="0">不可用</option>
                </select>
            </li>
            <li class="subBut" onclick="window.location.href='manufacturerList.html'"><img src="../../images/t06.png" />查询</li>
            <li class="subBut" onclick="window.location.href='${pageContext.request.contextPath}/goToAddFirm.do'"><img src="../../images/t01.png" />添加</li>
        </ul>
        <table class="tablelist">
            <thead>
                <tr>
                    <th>序号</th>
                    <th>厂商名称</th>
                    <th>负责人</th>
                    <th>联系电话</th>
                    <th>所属区域</th>
                    <th>状态</th>
                    <th>创建时间</th>
                    <th>创建人</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${firmList}" var="firm" varStatus="index">
                    <tr>
                        <td>${index.index+1+(pageIndex-1)*5}</td>
                        <td>${firm.firmName}</td>
                        <td>${firm.firmFounder}</td>
                        <td>${firm.firmTel}</td>
                        <td>${firm.city.province.pName}${firm.city.cName}</td>
                        <c:if test="${firm.status==1}">
                            <td>可用</td>
                        </c:if>
                        <c:if test="${firm.status==2}">
                            <td>不可用</td>
                        </c:if>
                        <td><fmt:formatDate value="${firm.createTime}" pattern="yyyy-MM-dd HH:MM:ss"/></td>
                        <td>${firm.user.uname}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/getFirmDetails.do?id=${firm.firmId}" class="tablelink">查看详情</a>
                            <a href="${pageContext.request.contextPath}/firmUpdate.do?id=${firm.firmId}" class="tablelink">修改</a>
                            <c:if test="${firm.status==1}">
                                <a href="javascript:void(0)" class="tablelink" onclick="tipOpen('是否确认注销此条信息？',${firm.firmId})">注销</a>
                            </c:if>
                            <c:if test="${firm.status==2}">
                                <a href="${pageContext.request.contextPath}/recoverFirm.do?id=${firm.firmId}" class="tablelink" >恢复</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">${countFirm}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllFirm.do?pageIndex=1">首页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllFirm.do?pageIndex=${pageIndex-1}">上页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllFirm.do?pageIndex=${pageIndex+1}">下页</a></li>
                <li class="paginItem"><a href="${pageContext.request.contextPath }/getAllFirm.do?pageIndex=${row}">末页</a></li>
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
            <input name="delete" type="button"  class="sure" value="确定" />
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
