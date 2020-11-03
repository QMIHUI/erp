<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery.js"></script>
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
      <li> 省:
        <input type="text" />
      </li>
      <li> 市:
        <input type="text" />
      </li>
      <li class="subBut" onclick="window.location.href='brandList.jsp'"><img src="../../images/t06.png" />查询</li>
    </ul>
    <table class="tablelist">
      <thead>
        <tr>
          <th>序号</th>
          <th>省编号</th>
          <th>省名称</th>
          <th>市编号</th>
          <th>市名称</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>110000</td>
          <td>北京市</td>
          <td>110100</td>
          <td>市辖区</td>
        </tr>
        <tr>
          <td>2</td>
          <td>110000</td>
          <td>北京市</td>
          <td>110200</td>
          <td>县</td>
        </tr>
        <tr>
          <td>3</td>
          <td>130000</td>
          <td>河北省</td>
          <td>130100</td>
          <td>石家庄市</td>
        </tr>
        <tr>
          <td>4</td>
          <td>130000</td>
          <td>河北省</td>
          <td>130200</td>
          <td>唐山市</td>
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
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
