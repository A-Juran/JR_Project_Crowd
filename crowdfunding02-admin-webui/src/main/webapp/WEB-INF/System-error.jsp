<%--
  Created by IntelliJ IDEA.
  User: Juran
  Date: 2021/12/31
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>映射异常处理</title>
</head>
<body>
    <%--  从请求与中去除Exception对象，再进一步访问message  --%>
    ${requestScope.exception.message}
</body>
</html>
