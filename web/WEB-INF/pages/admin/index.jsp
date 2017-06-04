<%--
  Created by IntelliJ IDEA.
  User: lirf
  Date: 2017/5/24
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>$Title$</title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/asset/js/jquery.min.js"></script>
    </head>
    <body>
        <a href="/test.form">hello world</a>
        <h1 id="title"></h1>
    </body>
    <script>
        $("#title").html("${name}");
    </script>
</html>
