<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录</title>
</head>
<body bgcolor='#085DA3' style='text-align: center;'>
<div style="margin-top: 160px;">
<img src='../resource/img/logo.png'/>
</div>
<div style="margin-top: 0px;">
<div style="width: 484px; height: 242px; margin-left: auto; margin-right: auto; text-align: left; font-family: Microsoft YaHei; font-size: small; font-weight: bold; background: url('../resource/img/box-bg.jpg') no-repeat;">
<div style="padding: 35px 0px 0px 205px;">
<form action="<c:url value='/user/dologin' />" name="loginForm" id="loginForm" method="post">
用户名：<input name="j_username" tabindex="1" id="username" value="admin"  type="text" style="font-size:18px;width:200px;margin-top:20px;"/>
<br/>
密　码：<input name="j_password" tabindex="2" id="password" value="admin" type="password" style="font-size:18px;width:200px;margin-top:10px;"/>
记住我：<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true"/> 
<br/>
<div style="text-align: center;">
<p class="error" style="color:red;font-size: smaller;">&nbsp;
<c:if test="${not empty errorMsg}">
${errorMsg}
</c:if>
</p>
</div>
<div style="text-align: center;">
<input type="submit" value="提交" style="width: 100px; height: 30px; margin-top:5px; font-family: Microsoft YaHei; background-color: #57aefc; background-image: linear-gradient(to bottom, #99d1fc, #3594e9); background-repeat: repeat-x; border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.2) rgba(0, 0, 0, 0.5); color: #ffffff; text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.35); border-radius: 4px; border-width: 1px;" />
<input type="reset" value="取消" style="width: 100px; height: 30px; margin-top:5px; font-family: Microsoft YaHei; background-color: #d4d9e1; background-image: linear-gradient(to bottom, #ffffff, #cbd0da); background-repeat: repeat-x; border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.2) rgba(0, 0, 0, 0.5); color: #000000; text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.35); border-radius: 4px; border-width: 1px;" />
</div>
</form>
</div>
</div>
</div>
</body>
</html>