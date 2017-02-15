<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>clouddriver</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include page="include/header.jsp"/>
</head>
<body>
<div class="ui menu secondary">
    <div class="header item"><i class="cloud icon"></i>clouddriver</div>
    <a class="active item">个人</a>
    <a class="item">共享</a>
    <div class="right menu">
        <div class="item">
            <div class="ui action left icon input">
                <i class="search icon"></i>
                <input type="text" placeholder="Search">
                <button class="ui button">Submit</button>
            </div>
        </div>
        <div class="item">
            <div class="ui button"><i class="desktop icon"></i>登陆</div>
        </div>
        <div class="ui dropdown item" tabindex="0">
            Amayadream
            <i class="dropdown icon"></i>
            <div class="menu" tabindex="-1">
                <div class="item"><i class="info icon"></i>个人信息</div>
                <div class="divider"></div>
                <div class="item"><i class="power icon"></i>注销</div>
            </div>
        </div>
    </div>
</div>

<div class="ui grid">
    <div class="two wide column">
        <ul>
            <li>1</li>
            <li>2</li>
            <li>3</li>
            <li>4</li>
            <li>5</li>
        </ul>
    </div>
    <div class="fourteen wide column">
        <div class="urlbar">
            <div class="ui big breadcrumb">
                <a class="section">Home</a>
                <i class="right chevron icon divider"></i>
                <a class="section">Registration</a>
                <i class="right chevron icon divider"></i>
                <div class="active section">Personal Information</div>
            </div>
        </div>
        <div class="toolbar">
            <div class="ui buttons">
                <button class="ui button"><i class="folder icon"></i>新建文件夹</button>
                <button class="ui button"><i class="upload icon"></i>上传文件</button>
                <div class="ui icon top left pointing dropdown button">
                    <i class="ellipsis vertical icon"></i>
                    <div class="menu">
                        <div class="item"><i class="folder open icon"></i>打开</div>
                        <div class="item"><i class="external icon"></i>共享</div>
                        <div class="item"><i class="download icon"></i>下载</div>
                        <div class="item"><i class="zip icon"></i>压缩</div>
                        <div class="ui divider"></div>
                        <div class="item"><i class="copy icon"></i>复制</div>
                        <div class="item"><i class="cut icon"></i>剪切</div>
                        <div class="item"><i class="paste icon"></i>粘贴</div>
                        <div class="item"><i class="paste icon"></i>重命名</div>
                        <div class="item"><i class="trash icon"></i>删除</div>
                        <div class="ui divider"></div>
                        <div class="item"><i class="star icon"></i>收藏</div>
                        <div class="ui divider"></div>
                        <div class="item"><i class="info icon"></i>属性</div>
                    </div>
                </div>
            </div>
            <div class="ui floating labeled icon dropdown button">
                <i class="filter icon"></i>
                <span class="text">类型</span>
                <div class="menu">
                    <div class="header"><i class="tags icon"></i> 根据类型过滤 </div>
                    <div class="divider"></div>
                    <div class="item"><i class="file icon"></i> 文档 </div>
                    <div class="item"><i class="image icon"></i> 图片 </div>
                    <div class="item"><i class="zip icon"></i> 压缩包 </div>
                </div>
            </div>
            <div class="ui buttons">
                <button class="ui button"><i class="grid layout icon"></i></button>
                <button class="ui button"><i class="list layout icon"></i></button>
            </div>
        </div>





    </div>
</div>

<script>
    $('.dropdown')
        .dropdown()
    ;
</script>
</body>
</html>
