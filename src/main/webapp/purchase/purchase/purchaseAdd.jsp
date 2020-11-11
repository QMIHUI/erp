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
    <li>采购单管理</li>
    <li>添加</li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>采购单信息</span></div>
  <ul class="forminfo">
    <li>
      <label>采购单编号</label>
      <input name="" type="text" value="CG201711180001" readonly="readonly" class="roinput" />
      <i>自动生成不能编辑</i>
    </li>
    <li>
      <label>采购时间</label>
      <input name="" type="text" value="2017-11-18 15:36:10" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
      <label>创建人</label>
      <input name="" type="text" value="马云" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
       <label>采购仓库</label>
       <select name="" class="roinput">
           <option>请选择</option>
       </select>
    </li>
    <li>
      <label>审核状态</label>
      <input type="text" value="未审核" readonly="readonly" class="roinput" />
      <input name="" type="hidden" value="1" />
      <i>初始状态，不能编辑</i>
    </li>
    <li>
      <label>总金额</label>
      <input name="" type="text" readonly="readonly" class="roinput" />
       <i>自动运算，不能编辑</i>
    </li>
    <li>
      <input type="button" value="新增" class="smallbtn" />
      &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="删除" class="smallbtn" />
       &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="保存" class="smallbtn" />
       &nbsp;&nbsp;&nbsp;&nbsp;
      <input type="button" value="返回" class="smallbtn" onclick="window.location.href='purchaseList.jsp'"/>
    </li>
  </ul>
  <table class="tablelist">
      <thead>
        <tr>
          <th>序号</th>
          <th>品牌</th>
          <th>类型</th>
          <th>型号</th>
          <th>厂商</th>
          <th>数量</th>
          <th>单位</th>
          <th>单价</th>
          <th>金额</th>
        </tr>
      </thead>
      <tbody>
      	<tr>
          <td>1</td>
          <td>
          	<select>
            	<option>请选择</option>
                <option>联想</option>
                <option>海尔</option>
                <option>小米</option>
            </select>
          </td>
          <td>
          	<select>
            	<option>请选择</option>
                <option>笔记本电脑</option>
                <option>台式电脑</option>
                <option>手机</option>
            </select>
          </td>
          <td>
          	<select>
            	<option>请选择</option>
                <option>X260</option>
                <option>E470</option>
                <option>T470</option>
            </select>
          </td>
          <td>
          	<select>
            	<option>请选择</option>
                <option>北京联想科技股份有限公司</option>
                <option>北京京东商贸股份有限公司</option>
            </select>
          </td>
          <td><input type="text" /></td>
          <td>台</td>
          <td><input type="text" /></td>
          <td><input type="text" /></td>
        </tr>
      </tbody>
  </table>
</div>
<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
</script>
</body>
</html>
