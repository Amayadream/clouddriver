<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>主页</title>
    <!--支持IE-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--支持移动设备,禁用缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 关键词 -->
    <meta name="keywords" content="cloud driver" />
    <!-- 描述语 -->
    <meta name="Description" content="在线云盘">
    <jsp:include page="include/header.jsp"/>
    <style>
        html, body{
            /*height: 100%;*/
            /*width: 100%;*/
        }
        .driver-side{
            padding-top: 50px;
            /*height: 95%;*/
            height: auto;
            width: 150px;
            margin-bottom: 100px;
            float:left;
        }
        .driver-content{
            padding-top: 50px;
            /*height: 95%;*/
            width: auto;
            margin-left: 150px;
            height: auto;
            margin-bottom: 100px;
            overflow-y: auto;
            overflow-x: auto;
        }
        .driver-foot{
            width: 100%;
            height: 100px;
            text-align: center;
            background-color: rgba(30, 54, 76, 0.63);
        }
    </style>
</head>
<body>
<!-- 头部 -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <h1 class="navbar-brand">
                <img src="${ctx}/static/source/image/logo.png" width="24" height="24" alt="在线云盘">
                <a href="${ctx}/driver/home">CloudDriver</a>
                <small>/ CloudDriver</small>
            </h1>
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${ctx}/driver/home" class="menu" name="${ctx}/contact.html">联系我们</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Amayadream <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#" class="menu" name="${ctx}/auth/profile"><span class="glyphicon glyphicon-user"></span> 个人信息</a></li>
                        <li><a href="#" class="menu" name="${ctx}/auth/modify"><span class="glyphicon glyphicon-retweet"></span> 修改密码</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#" class="menu" name="${ctx}/sale/cart"><span class="glyphicon glyphicon-shopping-cart"></span> 购物车</a></li>
                        <li><a href="#" class="menu" name="${ctx}/sale/record"><span class="glyphicon glyphicon-th"></span> 订单中心</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="${ctx}/auth/logout"><span class="glyphicon glyphicon-off"></span> 注销</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- 侧边栏 -->
<div class="driver-side">
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</div>

<!-- 正文 -->
<div class="driver-content">
</div>

<!-- 底部 -->
<div class="driver-foot">
    Powered by Amayadream
</div>

<script>
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    var zNodes =[
        { id:1, pId:0, name:"父节点1 - 展开", open:true},
        { id:11, pId:1, name:"父节点11 - 折叠"},
        { id:111, pId:11, name:"叶子节点111"},
        { id:112, pId:11, name:"叶子节点112"},
        { id:113, pId:11, name:"叶子节点113"},
        { id:114, pId:11, name:"叶子节点114"},
        { id:12, pId:1, name:"父节点12 - 折叠"},
        { id:121, pId:12, name:"叶子节点121"},
        { id:122, pId:12, name:"叶子节点122"},
        { id:123, pId:12, name:"叶子节点123"},
        { id:124, pId:12, name:"叶子节点124"},
        { id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true},
        { id:2, pId:0, name:"父节点2 - 折叠"},
        { id:21, pId:2, name:"父节点21 - 展开", open:true},
        { id:211, pId:21, name:"叶子节点211"},
        { id:212, pId:21, name:"叶子节点212"},
        { id:213, pId:21, name:"叶子节点213"},
        { id:214, pId:21, name:"叶子节点214"},
        { id:22, pId:2, name:"父节点22 - 折叠"},
        { id:221, pId:22, name:"叶子节点221"},
        { id:222, pId:22, name:"叶子节点222"},
        { id:223, pId:22, name:"叶子节点223"},
        { id:224, pId:22, name:"叶子节点224"},
        { id:23, pId:2, name:"父节点23 - 折叠"},
        { id:231, pId:23, name:"叶子节点231"},
        { id:232, pId:23, name:"叶子节点232"},
        { id:233, pId:23, name:"叶子节点233"},
        { id:234, pId:23, name:"叶子节点234"},
        { id:3, pId:0, name:"父节点3 - 没有子节点", isParent:true}
    ];

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
</script>
</body>
</html>
