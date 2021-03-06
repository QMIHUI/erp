<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>模块管理</title>
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
    <li><a href="../users/userList.jsp">系统管理</a></li>
    <li><a href="#">模块管理</a></li>
  </ul>
</div>
<div class="rightinfo">
  <form action="" method="post">
    <ul class="tools">
      <li> <label>模块名称:</label>
        <input type="text" />
      </li>

      <li class="subBut" onclick=""><img src="../../images/t06.png" />查询</li>
      <li class="subBut" onclick="window.location.href='moduleAdd.html'"><img src="../../images/t01.png" />添加</li>
    </ul>
    <table class="tablelist">
      <thead>
        <tr>
          <th>序号</th>
          <th>模块名称</th>
          <th>父模块</th>
          <th>URL</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>系统管理</td>
          <td>无</td>
          <td>#</td>
          <td>正常</td>
          <td>
            <a href="moduleUpdate.jsp" class="tablelink">修改</a>
            <a href="javascript:void(0);" class="tablelink" onclick="tipOpen('是否确禁用此模块？')">禁用</a>
          </td>
        </tr>
        <tr>
          <td>2</td>
          <td>用户管理</td>
          <td>系统管理</td>
          <td>sys/users/userList.html</td>
          <td>正常</td>
          <td>
            <a href="moduleUpdate.jsp" class="tablelink">修改</a>
            <a href="javascript:void(0);" class="tablelink" onclick="tipOpen('是否确禁用此模块？')">禁用</a>
          </td>
        </tr>
        <tr>
          <td>3</td>
          <td>部门管理</td>
          <td>系统管理</td>
          <td>sys/dept/deptList.html</td>
          <td>正常</td>
          <td>
            <a href="moduleUpdate.jsp" class="tablelink">修改</a>
            <a href="javascript:void(0);" class="tablelink" onclick="tipOpen('是否确禁用此模块？')">禁用</a>
          </td>
        </tr>
        <tr>
          <td>3</td>
          <td>测试管理</td>
          <td>无</td>
          <td>#</td>
          <td>禁用</td>
          <td>
            <a href="moduleUpdate.jsp" class="tablelink">修改</a>
            <a href="javascript:void(0);" class="tablelink" onclick="tipOpen('是否确启用此模块？')">启用</a>
          </td>
        </tr>
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
