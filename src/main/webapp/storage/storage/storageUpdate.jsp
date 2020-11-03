<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../../css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="place"> <span>位置：</span>
  <ul class="placeul">
    <li>仓库管理</li>
    <li>仓库管理</li>
    <li>修改仓库</li>
  </ul>
</div>
<div class="formbody">
  <div class="formtitle"><span>仓库信息</span></div>
  <ul class="forminfo">
    <li>
      <label>仓库名称</label>
      <input name="" type="text" value="南京21号仓库" class="dfinput" />
      <i>必填，不能超过30个字符</i>
    </li>
    <li>
      <label>仓库地址</label>
      <input name="" type="text" value="江苏南京苏铺路180号泸州花园7栋3单元1801室" class="dfinput" />
      <i>必填，不能超过300个字符</i>
    </li>
    <li>
      <label>所属区域</label>
      <select class="dfselect">
          <option>请选择</option>
          <option>北京</option>
          <option selected="selected">江苏</option>
          <option>天津</option>
      </select>
      省
      <select class="dfselect">
          <option>请选择</option>
          <option>北京</option>
          <option selected="selected">南京</option>
          <option>天津</option>
      </select>
      市
      <i>必选</i>
    </li>
    <li>
      <label>负责人</label>
      <select class="dfselect">
          <option>请选择</option>
          <option selected="selected">周仓</option>
          <option>廖化</option>
          <option>张昭</option>
      </select>
       <i>只能是财务部仓库管理员职位</i>
    </li>
    <li>
      <label>联系方式</label>
      <input name="" type="text" value="17370899727" class="dfinput" />
       <i>必填，不能超过30个字符</i>
    </li>
    <li>
      <label>描述</label>
      <textarea class="textinput">天下第一仓</textarea>
      <i>可选</i>
    </li>
    <li>
      <label>状态</label>
      <select class="dfselect">
          <option value="1">可用</option>
          <option value="0">不可用</option>
        </select>
      <i>必选</i>
    </li>
    <li>
      <label>创建人</label>
      <input name="" type="text" value="马云" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
      <label>创建时间</label>
      <input name="" type="text" value="2017-11-18 15:36:10" readonly="readonly" class="roinput" />
      <i>不能编辑</i>
    </li>
    <li>
      <label>&nbsp;</label>
      <input name="" type="button" class="btn" value="确定"/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="" type="button" class="btn" value="返回" onclick="window.location.href='storageList.html'"/>
    </li>
  </ul>
</div>
</body>
</html>