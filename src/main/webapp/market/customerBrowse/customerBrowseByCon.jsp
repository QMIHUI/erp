<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>客户管理</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript">
        function tipOpen(content,custId) {
            $(".tipright p").text(content);
            $("input[name='cancelCust']").bind("click",function () {
                window.location.href="${pageContext.request.contextPath}/cancelCust.do?custId="+custId;
            })
            $("#tip").fadeIn(200);
        }
        function tipOpen1(content,custId) {
            $(".tipright1 p").text(content);
            $("input[name='cancelCust']").bind("click",function () {
                window.location.href="${pageContext.request.contextPath}/recoverCust.do?custId="+custId;
            })
            $("#tip").fadeIn(200);
        }

        function tipClose() {
            $("#tip").fadeOut(200);
            $("#tip01").fadeOut(200);
        }
        function allottipOpen(content,custId) {
            $(".tipinfo1 p").text(content);
            $("input[name='distributionCust']").bind("click",function () {
                window.location.href="${pageContext.request.contextPath}/distributionCust.do?custId="+custId;
            })
            $("#allottip").fadeIn(200);

        }
        function allottipclose() {
            $("#allottip").fadeOut(200);
        }
    </script>
</head>
<body>
<div class="place"> <span>位置：</span>
    <ul class="placeul">
        <li>营销管理</li>
        <li>客户管理</li>
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="${pageContext.request.contextPath}/getCustBroByCon.do" method="get">
        <ul class="tools">
            <li> 公司名称:
                <input name="custCom" type="text" size="12" />
            </li>
            <li> 客户姓名:
                <input name="custName" type="text" size="12" />
            </li>
            <li> 所属区域：
                <select name="province">
                    <option value="0">请选择省份</option>
                    <c:forEach items="${provinceList}" var="province">
                        <option value="${province.id}">${province.pName}</option>
                    </c:forEach>
                </select>
            </li>
            <li style="width: 100px;height: 35px;margin-top: -10px">
                <input value="查 询" type="submit" id="searchbutton" class="subBut">
            </li>
            <li class="subBut" onclick="window.location.href='${pageContext.request.contextPath}/market/customer/customerAdd.jsp'">
                <img src="${pageContext.request.contextPath}/images/t01.png" />添加
            </li>
        </ul>
    </form>
    <table class="tablelist">
        <thead>
        <tr>
            <th>序号</th>
            <th>客户姓名</th>
            <th>性别</th>
            <th>联系电话</th>
            <th>所属公司</th>
            <th>所属区域</th>
            <th>分配时间</th>
            <th>负责人ID</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCustomBroByCon}" var="customerBroByCon">
            <tr>
                <td>${customerBroByCon.customid}</td>
                <td>${customerBroByCon.customname}</td>
                <td>${customerBroByCon.sex}</td>
                <td>${customerBroByCon.telephone}</td>
                <td>${customerBroByCon.company}</td>
                <td>${customerBroByCon.province.pName}</td>
                <td>${customerBroByCon.distractime}</td>
                <td>${customerBroByCon.leading}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/getOneCust.do?customId=${customerBroByCon.customid}&op=查看" class="tablelink">查看详情</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagin">
        <div class="message">共<i class="blue">${countCustBroByCon}</i>条记录，当前显示第&nbsp;<i class="blue">${pageIndex}&nbsp;</i>页</div>
        <ul class="paginList">
            <li class="paginItem"><a href="${pageContext.request.contextPath }/getCustBroByCon.do?pageIndex=1&custCom=${custCom}&custName=${custName}&province=${province}">首页</a></li>
            <li class="paginItem"><a href="${pageContext.request.contextPath }/getCustBroByCon.do?pageIndex=${pageIndex-1}&custCom=${custCom}&custName=${custName}&province=${province}">上页</a></li>
            <li class="paginItem"><a href="${pageContext.request.contextPath }/getCustBroByCon.do?pageIndex=${pageIndex+1}&custCom=${custCom}&custName=${custName}&province=${province}">下页</a></li>
            <li class="paginItem"><a href="${pageContext.request.contextPath }/getCustBroByCon.do?pageIndex=${rowCustBroByCon}&custCom=${custCom}&custName=${custName}&province=${province}">末页</a></li>
        </ul>
    </div>
    <!-- 提示框 -->
    <div id="tip" class="tip">
        <div class="tiptop"><span>提示信息</span><a onclick="tipClose()"></a></div>
        <div class="tipinfo"> <span><img src="${pageContext.request.contextPath }/images/ticon.png" /></span>
            <div class="tipright">
                <p></p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
        </div>
        <div class="tipbtn">
            <input name="cancelCust" type="button"  class="sure" value="确定" />
            <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
        </div>
    </div>
    <!-- 提示框 -->
    <div id="tip1" class="tip">
        <div class="tiptop"><span>提示信息</span><a onclick="tipClose()"></a></div>
        <div class="tipinfo"> <span><img src="${pageContext.request.contextPath }/images/ticon.png" /></span>
            <div class="tipright1">
                <p></p>
                <cite>如果是请点击确定按钮 ，否则请点取消。</cite> </div>
        </div>
        <div class="tipbtn">
            <input name="cancelCust" type="button"  class="sure" value="确定" />
            <input name="" type="button"  class="cancel" value="取消" onclick="tipClose()" />
        </div>
    </div>
    <!-- 审批提示框 -->
    <div id="allottip" class="tip">
        <div class="tiptop">
            <span>提示信息</span><a onclick="allottipclose()"></a>
        </div>
        <div class="tipinfo1">
            <p></p>
        </div>
        <div class="tipbtn">
            <input name="distributionCust" type="button"  class="sure" value="确定" />
            <input name="" type="button"  class="cancel" value="取消" onclick="allottipclose()" />
        </div>
    </div>
</div>
<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
