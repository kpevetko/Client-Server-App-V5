<%--
  Created by IntelliJ IDEA.
  User: WeAre
  Date: 14.12.2019
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="createNewUser" method="post">
    Зайти в систему
    <p> Логин: <input name="userLogin" type="text" placeholder="Логин" required
                      pattern="^[0-9a-zA-Z]+$"/></p>
    <p> Пароль: <input name="userPassword" type="password" placeholder="Пароль" required
                       pattern="^[0-9a-zA-Z]+$"/></p>
    <p>
        <input type="submit" value="Войти"/>
    </p>
</form>
<button type="submit" formaction="/">Назад</button>
</body>
</html>
