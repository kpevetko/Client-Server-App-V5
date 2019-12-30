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
    </style>
</head>
<body>
<div>
    <label class="info" id="info"></label>
    <p> Логин: <input id="userLogin" type="text" placeholder="Логин" required
                      pattern="^[0-9a-zA-Z]+$"/></p>
    <p> Пароль: <input id="userPassword" type="password" placeholder="Пароль" required
                       pattern="^[0-9a-zA-Z]+$"/></p>

    <p>
        <button onclick="registrationInChat()">Войти</button>
        <button onclick="backToIndex()">Назад</button>
    </p>
</div>


</body>
<script>

    function registrationInChat() {
        if (document.getElementById("userLogin").value != null && document.getElementById("userLogin").value !== "" && document.getElementById("userPassword").value != null && document.getElementById("userPassword").value !== "") {
            var xhttp = new XMLHttpRequest();
            var login = document.getElementById("userLogin").value;
            var pass = document.getElementById("userPassword").value;

            xhttp.open("POST", "/SingUp?" + "log=" + login + "&pass=" + pass, true);
            xhttp.send();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    if (xhttp.responseText.trim() === "CreateNew") {
                        document.location.href = "/Chat";
                    } else {
                        document.getElementById("info").innerText = "Такой пользователь уже существует";
                    }
                }
            };
        } else {
            document.getElementById("info").innerText = "Введите логин и пароль";
        }

    }

    function backToIndex() {
        document.location.href = "/"
    }
</script>
</html>
