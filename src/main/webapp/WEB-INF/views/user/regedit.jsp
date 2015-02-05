<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>注册页面</title>
  </head>
  <body>
    <h3>欢迎注册</h3>
    <form action="<c:url value='/user/doRegedit' />" method="post" >
       <table>
          <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
          </tr>
          <tr>
            <td>密码</td>
            <td><input type="password" name="pwd">密码不得少于6位</td>
          </tr>
          <tr>
            <td>性别</td> 
            <td><input type="radio" name="sex" value="男">男
                <input type="radio" name="sex" value="女">女</td>
          </tr>
          <tr>
            <td><input type="submit" value="注册"></td>
            <td><input type="reset" value="取消"></td>
          </tr>
       </table>
    </form>
  </body>
</html>
