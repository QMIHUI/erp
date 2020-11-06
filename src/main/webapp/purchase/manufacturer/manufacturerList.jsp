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
                    <option>请选择省份</option>
                    <option>北京</option>
                    <option>江苏</option>
                    <option>天津</option>
                </select>
                <select>
                    <option>请选择城市</option>
                    <option>北京</option>
                    <option>南京</option>
                    <option>天津</option>
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
                        <td>${index.index+1}</td>
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
                        <td><fmt:formatDate value="${firm.createTime}" pattern="yyyy-MM-dd hh:MM:ss"/></td>
                        <td>${firm.user.uname}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/getFirmDetails.do?id=${firm.firmId}" class="tablelink">查看详情</a>
                            <a href="manufacturerUpdate.jsp" class="tablelink">修改</a>
                            <a href="javascript:void(0)" class="tablelink" onclick="tipOpen('是否确认注销此条信息？',${firm.firmId})">注销</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagin">
            <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
            <ul class="paginList">
                <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                <li class="paginItem"><a href="javascript:;">1</a></li>
                <li class="paginItem current"><a href="javascript:;">2</a></li>
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
