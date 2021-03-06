<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文</title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <script type="text/javascript">
        function tipOpen(content) {
            $(".tipright p").text(content);
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
        <li>仓库管理</li>
        <li>仓库管理</li>
        <li>基本内容</li>
    </ul>
</div>
<div class="rightinfo">
    <form action="" method="post">
        <ul class="tools">
            <li>仓库名称:
                <input type="text" name="name" value="" />
            </li>
            <li> 所属区域：
                    <select  name="provinceId">
                        <option selected="selected">请选择</option>
                        <c:forEach items="${listprovince}" var="lp">
                            <option value="${lp.id}">${lp.pName}</option>
                        </c:forEach>
                    </select>

                <select name="cityId">
                    <option selected="selected">请选择城市</option>
                    <c:forEach items="${listcity}" var="lc">
                        <option value="${lc.cId}">${lc.cName}</option>
                    </c:forEach>
                </select>
            </li>
            <li class="subBut" onclick="window.location.href='${pageContext.request.contextPath}/storageListlike.do'"><img src="../../images/t06.png" />查询</li>
            <li class="subBut" onclick="window.location.href='${pageContext.request.contextPath}/addSelectWarehouse.do'"><img src="../../images/t01.png" />添加</li>
        </ul>
        <table class="tablelist">
            <thead>
            <tr>
                <th>序号</th>
                <th>仓库名称</th>
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
            <c:forEach items="${warehouseList}" var="lw">
                <tr>
                    <td>${lw.id}</td>
                    <td>${lw.name}</td>
                    <td>${lw.users.uname}</td>
                    <td>${lw.phone}</td>
                    <td>${lw.province.pName}${lw.city.cName}</td>
                    <td>
                        <c:if test="${lw.state==1}">可用</c:if>
                        <c:if test="${lw.state==0}">不可用</c:if>
                    </td>
                    <td>${lw.creationTime}</td>
                    <td>${lw.users.names}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/${lw.id}/storageView.do" class="tablelink">查看详情</a>
                        <c:if test="${lw.state==1}">
                            <a href="${pageContext.request.contextPath}/${lw.id}/storageUpdate.do">修改</a>
                            <a href="${pageContext.request.contextPath}/${lw.id}/${lw.state}/storageState.do" class="tablelink" >注销</a>
                        </c:if>
                        <c:if test="${lw.state==0}">
                            <a href="${pageContext.request.contextPath}/${lw.id}/${lw.state}/storageState.do" class="tablelink" >恢复</a>
                        </c:if>

                    </td>
                </tr>
            </c:forEach>



            </tbody>
        </table>
        <div class="pagin">
           <%-- <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>--%>
            <ul class="paginList">
                <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                <li class="paginItem current"><a href="javascript:;">1</a></li>
                <li class="paginItem "><a href="javascript:;">2</a></li>
                <li class="paginItem"><a href="javascript:;">3</a></li>
                <li class="paginItem"><a href="javascript:;">4</a></li>
                <li class="paginItem"><a href="javascript:;">5</a></li>
                <li class="paginItem more"><a href="javascript:;">...</a></li>
                <li class="paginItem"><a href="javascript:;">10</a></li>
                <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
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
            <input name="" type="button"  class="sure" value="确定" onclick="tipClose()" />
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
