<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <title>clouddriver</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/source/css/login.css"/>
</head>
<body>
<div id="box"></div>
<div class="cent-box">
    <div class="cent-box-header">
        <h1 class="main-title hide">千寻</h1>
        <h2 class="sub-title">生活热爱分享 - Thousands Find</h2>
    </div>

    <div class="cont-main clearfix">
        <div class="index-tab">
            <div class="index-slide-nav">
                <a href="${ctx}/auth/login" class="active">登录</a>
                <a href="${ctx}/auth/register">注册</a>
                <div class="slide-bar"></div>
            </div>
        </div>

        <form action="${ctx}/auth/login" method="POST">
            <div class="login form">
                <div class="group">
                    <div class="group-ipt email">
                        <input type="text" class="ipt" name="userId" placeholder="用户名" required>
                    </div>
                    <div class="group-ipt password">
                        <input type="password" class="ipt" name="password" placeholder="输入您的登录密码" required>
                    </div>
                    <div class="group-ipt verify">
                        <input type="text" class="ipt" name="verifyCode" placeholder="输入验证码" required>
                        <img src="${ctx}/common/createVerifyCode" class="imgcode">
                    </div>
                </div>
            </div>
            <div class="button">
                <button type="submit" class="login-btn register-btn" id="button">登录</button>
            </div>
        </form>

        <div class="remember clearfix">
            <label class="remember-me"><span class="icon"><span class="zt"></span></span><input type="checkbox" name="remember-me" id="remember-me" class="remember-mecheck" checked>记住我</label>
            <label class="forgot-password">
                <a href="#">忘记密码？</a>
            </label>
        </div>
    </div>
</div>

<div class="footer">
    <p>Amayadream - Thousands Find</p>
    <p>©2015-2016 <a href="http://www.amayadream.com">Amayadream</a> 版权所有</p>
</div>

<script type="text/javascript" src='${ctx}/static/source/js/particles.js'></script>
<script type="text/javascript"src='${ctx}/static/source/js/background.js'></script>
<script type="text/javascript" src="${ctx}/static/plugins/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src='${ctx}/static/plugins/layer/layer.js' ></script>
<script>
    if("${message}"){
        layer.msg('${message}', {
            offset: 0
        });
    }
    if("${error}"){
        layer.msg('${error}', {
            offset: 0,
            shift: 6
        });
    }

    $('.imgcode').hover(function(){
        layer.tips("看不清？点击更换", '.verify', {
            time: 6000,
            tips: [2, "#3c3c3c"]
        })
    },function(){
        layer.closeAll('tips');
    }).click(function(){
        $(this).attr('src','${ctx}/common/createVerifyCode?_=' + Math.random());
    });
    $("#remember-me").click(function(){
        var n = document.getElementById("remember-me").checked;
        if(n){
            $(".zt").show();
        }else{
            $(".zt").hide();
        }
    });
</script>
</body>
</html>
