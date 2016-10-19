<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>首页</title>
    <!--支持IE-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--支持移动设备,禁用缩放-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- 关键词 -->
    <meta name="keywords" content="cloud driver" />
    <!-- 描述语 -->
    <meta name="Description" content="在线云盘">
    <jsp:include page="include/header.jsp"/>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="${ctx}/home">home</a></li>
</ol>

</body>
</html>
