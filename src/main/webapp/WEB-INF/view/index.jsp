<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>在线云盘</title>
    <!--支持IE-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--支持移动设备,禁用缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 关键词 -->
    <meta name="keywords" content="在线云盘 cloud driver" />
    <!-- 描述语 -->
    <meta name="Description" content="在线云盘">
    <jsp:include page="include/header.jsp"/>
    <style>
        html, body{
            height: 100%;
            width: 100%;
        }
        .driver-side{
            padding-top: 50px;
            height: 95%;
            width: 150px;
            float:left;
        }
        .driver-content{
            padding-top: 50px;
            height: 95%;
            width: auto;
            margin-left: 150px;
            overflow-y: auto;
            overflow-x: auto;
        }
        .driver-foot{
            height: 5%;
            width: 100%;
            text-align: center;
            background-color: rgba(30, 54, 76, 0.63);
        }
        .menu-list{
            list-style: none;
            margin: 0;
            padding: 0;
        }
        .item-href{
            color: #424e67;
            height: 38px;
            line-height: 38px;
            display: block;
            position: relative;
            padding: 0 0 0 15px;
            font-size: 14px;
            text-decoration: none;
            zoom: 1;
            background-color: #eff4f8;

        }
        .list-href-active{
            color: #3b8cff;
            background-color: #e4e9ec;
        }
        .item-href:hover{
            background-color: #e4e9ec;
            text-decoration: none;
        }
        .item-href:active{
            background: rgba(0,0,0,.05);
            text-decoration: none;
        }
        .item-text{
            position: relative;
            display: block;
            height: 45px;
            width: 115px;
            padding-left: 38px;
            cursor: pointer;
        }
        .item-icon{
            top: 12px;
            position: absolute;
            left: 10px;
            font-size: 16px;
            speak: none;
            font-style: normal;
            font-weight: 400;
            font-variant: normal;
            text-transform: none;
            line-height: 1;
            -webkit-font-smoothing: antialiased;
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
    <ul class="menu-list">
        <li class="list-item"><a class="item-href list-href-active"><span class="item-text"><span class="item-icon glyphicon glyphicon-info-sign"></span><span class="item-title">全部文件</span></span></a></li>
        <li class="list-item"><a class="item-href"><span class="item-text"><span class="item-title">图片</span></span></a></li>
        <li class="list-item"><a class="item-href"><span class="item-text"><span class="item-icon glyphicon glyphicon-info-sign"></span><span class="item-title">文档</span></span></a></li>
        <li class="list-item"><a class="item-href"><span class="item-text"><span class="item-icon glyphicon glyphicon-info-sign"></span><span class="item-title">视频</span></span></a></li>
        <li class="list-item"><a class="item-href"><span class="item-text"><span class="item-icon glyphicon glyphicon-info-sign"></span><span class="item-title">种子</span></span></a></li>
        <li class="list-item"><a class="item-href"><span class="item-text"><span class="item-icon glyphicon glyphicon-info-sign"></span><span class="item-title">音乐</span></span></a></li>
        <li class="list-item"><a class="item-href"><span class="item-text"><span class="item-icon glyphicon glyphicon-info-sign"></span><span class="item-title">其他</span></span></a></li>
        <li class="list-item"><a class="item-href"><span class="item-text"><span class="item-icon glyphicon glyphicon-info-sign"></span><span class="item-title">我的分享</span></span></a></li>
        <li class="list-item"><a class="item-href"><span class="item-text"><span class="item-icon glyphicon glyphicon-info-sign"></span><span class="item-title">回收站</span></span></a></li>
    </ul>
</div>

<!-- 正文 -->
<div class="driver-content">
    content
</div>

<!-- 底部 -->
<div class="driver-foot">
    Powered by Amayadream v3.3 | Copyright ©
</div>

</body>
</html>
