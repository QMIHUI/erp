<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ERP仓储管理系统</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#df7611; background-image:url(${pageContext.request.contextPath }/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  

	<div class="logintop">    
        <span>ERP仓储管理系统</span>   
    </div>
    <form action="login.do" method="post">
        <div class="loginbody">
            <span class="systemlogo"></span>
            <div class="loginbox loginbox1">
                <ul>
                    <li>
                        <input name="uname" type="text" class="loginuser" placeholder="admin" <%--onclick="JavaScript:this.value=''"--%>/>
                    </li>
                    <li>
                        <input name="upassword" type="password" class="loginpwd" placeholder="密码" <%--onclick="JavaScript:this.value=''"--%>/>
                    </li>
                    <%--<li class="yzm">
                        <span>
                            <input name="" type="text" value="验证码" onclick="JavaScript:this.value=''"/>
                        </span>
                        <cite>X3D5S</cite>
                    </li>--%>
                    <li>
                        <input name="" type="submit" class="loginbtn" value="登录" />
                        <label>
                            <input name="" type="checkbox" value="" checked="checked"/>记住密码
                        </label>
                        <label>
                            <a href="#">忘记密码？</a>
                        </label>
                    </li>
                </ul>
            </div>
        </div>
    </form>

    <div class="loginbm">版权归南昌朝腾科技所有，仅供学习交流，勿用于任何商业用途</div>
</body>

</html>
