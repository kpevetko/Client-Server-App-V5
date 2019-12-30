<%@ page import="model.DataBaseModel" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: WeAre
  Date: 14.12.2019
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Регистрация</title>
    <!-- стили -->
    <style>
        .info {
            color: #5063ff;
        }

        .badinfo {
            color: #ff5f5d;
        }

        .goodinfo {
            color: #6b8e23;
        }

    </style>
</head>
<body>
Регистрация в системе
<form id="createNewUser" method="post">
    <%
        if (request.getAttribute("Answer").equals("NewConnect")) {
    %>
    <p class="info"><label> Введите данные </label></p>
    <%
    } else if (request.getAttribute("Answer").equals("CreatedNew")) {
    %>
    <p class="goodinfo"><label> Создан новый пользователь </label></p>
    <!--переход на 1 страницу-->
    <META HTTP-EQUIV="REFRESH" CONTENT="1; URL=/">
    <%
    } else if (request.getAttribute("Answer").equals("NotCreated")) {
    %>
    <p class="badinfo"><label> Такой пользователь уже существует </label></p>
    <%
    } else {
    %>
    <p class="badinfo"><label> Что-то пошло не так... </label></p>
    <%
        }
    %>

    <p> Логин: <input name="userLogin" type="text" placeholder="Логин" form="createNewUser" required
                      pattern="^[0-9a-zA-Z]+$"/></p>
    <p> Пароль: <input name="userPassword" type="password" placeholder="Пароль" form="createNewUser" required
                       pattern="^[0-9a-zA-Z]+$"/></p>
    <p>
        <input type="submit" value="Зарегистрироваться" form="createNewUser"/>
        <input type="reset" value="Стереть" form="createNewUser"/>
    </p>


</form>
<script>
    function x() {
        <%
        if(request.getAttribute("LL")!=null){

        }else {

        }
        %>
    }</script>
<form method="get">

    <button type="submit" formaction="/">Назад</button>
</form>


</body>
</html>
