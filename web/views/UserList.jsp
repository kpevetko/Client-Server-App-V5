<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Yury
  Date: 22.12.2019
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<String> user = (List<String>) request.getAttribute("Users");

    if (!user.isEmpty() && user != null) {
        for (String s : user) {
            out.println("<li>" + s + "</li>");
        }
    }


%>
</body>
</html>
