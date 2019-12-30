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

        .chatWindow {

            border: 3px solid #eee;
            margin: 0.3em 0.5em;
            padding: 0.3em 0.5em;
            display: inline-block;
            vertical-align: top;
            text-align: justify;
            box-sizing: border-box;
            min-width: 100px;
            float: left;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="chatWindow">
    <label class="info" id="info"></label>
    <p> Логин: <input id="userLogin" type="text" placeholder="Логин" required
                      pattern="^[0-9a-zA-Z]+$"/></p>
    <p> Пароль: <input id="userPassword" type="password" placeholder="Пароль" required
                       pattern="^[0-9a-zA-Z]+$"/></p>

    <p>
        <button onclick="registrationInChat()">Зарегистрироваться</button>
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
                        document.getElementById("info").innerText = "Пользователь создан, теперь вы можете зайти в систему";
                        document.getElementById("userLogin").value = "";
                        document.getElementById("userPassword").value = "";
                    } else {
                        document.getElementById("info").innerText = "Такой пользователь уже существует";
                        document.getElementById("userLogin").value = "";
                        document.getElementById("userPassword").value = "";
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
