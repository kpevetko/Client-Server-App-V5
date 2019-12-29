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

Зайти в систему
<div>
    <input id="info"/>
    <p> Логин: <input id="userLogin" type="text" placeholder="Логин" required
                      pattern="^[0-9a-zA-Z]+$"/></p>
    <p> Пароль: <input id="userPassword" type="password" placeholder="Пароль" required
                       pattern="^[0-9a-zA-Z]+$"/></p>
    <p>
        <input type="submit" value="ВойтиНЕ ЖМИ ЕЁ!!!!!"/>
        <button onclick="enterToChat()">ВХОД</button>
    </p>
</div>

<button onclick="backToIndex()">Назад</button>
</body>
<script>
    function enterToChat() {
        var xhttp = new XMLHttpRequest();
        var login = document.getElementById("userLogin").value;
        var pass = document.getElementById("userPassword").value;

        xhttp.open("POST", "/login?" + "log=" + login + "&pass=" + pass, true);
        xhttp.send();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                if (xhttp.responseText.trim() === "Accepted") {
                    document.location.href = "/Chat"
                } else {
                    document.getElementById("info").value = xhttp.responseText;
                }
            }
        };
    }

    function backToIndex() {
        document.location.href = "/"
    }
</script>
</html>
