<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../../js/jquery.js"></script>
    <style type="text/css">

        #treeMenu ul{
            margin-left: 50px;
        }

        #treeMenu a{
            font-size: 14px;
        }

        .hidden{
            display: none;
        }
    </style>

    <script type="text/javascript">

        $(function(){
            $("#treeMenu").children("li").children(":checkbox").click(function(){
                var flag = $(this).attr("checked");
                $(this).siblings("ul").show();
                $(this).siblings("ul").children("li").children(":checkbox").attr("checked", flag);
            }).siblings("a").click(function(){
                $(this).siblings("ul").toggle();
            });
        });
    </script>
</head>
<body>
    <div class="place"> <span>位置：</span>
        <ul class="placeul">
            <li><a href="../users/userList.jsp">系统管理</a></li>
            <li><a href="positionList.jsp">职位管理</a></li>
            <li><a href="#">赋权</a></li>
        </ul>
    </div>
    <div class="formbody">
        <div class="formtitle"><span>职位赋权</span></div>
        <ul id="treeMenu" class="forminfo">
            <li>
                <input type="checkbox"/>
                <a href="javascript:void(0);">系统管理</a>

                <ul class="hidden">
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">用户管理</a>
                    </li>
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">日志管理</a>
                    </li>
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">模块管理</a>
                    </li>
                </ul>
            </li>

            <li>
                <input type="checkbox"/>
                <a href="javascript:void(0);">营销管理</a>

                <ul class="hidden">
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">客户管理</a>
                    </li>
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">订购单审核</a>
                    </li>
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">订购单管理</a>
                    </li>
                </ul>
            </li>

            <li>
                <input type="checkbox"/>
                <a href="javascript:void(0);">仓库管理</a>

                <ul class="hidden">
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">仓库管理</a>
                    </li>
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">出库管理</a>
                    </li>
                    <li>
                        <input type="checkbox" onclick=""/>
                        <a href="../modules/moduleView.jsp">入库管理</a>
                    </li>
                </ul>
            </li>
        </ul>

        <input name="" type="button" class="btn" value="确认赋权"/>
        <input name="" type="button" class="btn" value="返回" onclick="window.history.go(-1);"/>
    </div>
</body>
</html>