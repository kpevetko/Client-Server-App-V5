<%--
  Created by IntelliJ IDEA.
  User: Yury
  Date: 22.12.2019
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Title</title>
    <!-- стили -->
    <style>
        .chatWindow {

            border: 3px solid #eee;
            margin: 0.3em 0.5em;
            padding: 0.3em 0.5em;
            display: inline-block;
            vertical-align: top;

            box-sizing: border-box;
            min-width: 100px;

            font-weight: bold;


        }


    </style>
</head>
<body>

<!--ChatBox-->

<table width="200">
    <tbody>
    <tr>
        <th><label for="chatBox"> Чат:</label></th>
        <th><label for="userBox"> Пользователи онлайн:</label></th>
    </tr>
    <tr>
        <td><textarea readonly name="chatBox" id="chatBox" cols="100" rows="20"></textarea></td>
        <td><textarea readonly name="userBox" id="userBox" cols="20" rows="20"></textarea></td>
    </tr>
    <tr>
        <td>
            <input size="100" type="text" name="textBox" id="textBox"/>
        </td>
        <td>
            <button onclick="sendMessageToChat()">Отправить сообщение</button>
        </td>
    </tr>
    </tbody>
</table>
<div>
    <p>


    </p>
    <button onclick="backToIndex()">Выход</button>
</div>
<script>
    var ws;
    var yourKey;
    connect = function () {
        //yourKey = document.getElementById('keyToConnect').value;
        //получаем имя из controllers/SingInController.java или controllers/SingUpController.java
        yourKey = ${name};
        var webSocketUrl = 'ws://localhost:8080/ex/' + yourKey;
        ws = new WebSocket(webSocketUrl);
        ws.onopen = function () {
            appendMessage('WebSocket connection opened!');
        };
        ws.onmessage = function (event) {
            appendMessage(event.data);
        };
        ws.onclose = function () {
            appendMessage('WebSocket closed');
        };
        ws.onerror = function (err) {
            appendMessage(err);
        };
    };
    //коннект сразу при загрузке страницы
    connect();

    appendMessage = function (text) {
        document.getElementById("chatBox").value += text + "\n";
    };

    //при нажатии на кнопку выход - переносит на index и убирает запись в мапе
    function backToIndex() {
        ws.close();
        document.location.href = "/"
    }

    //отправляем сообщение всем или конкретному человеку
    function sendMessageToChat() {
        if (document.getElementById("textBox").value != null && document.getElementById("textBox").value !== "" && yourKey != null) {
            var message = document.getElementById("textBox").value;
            document.getElementById("textBox").value = "";
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "/Chat?" + "msg=" + message + "&key=" + yourKey, true);
            xhttp.send();
        }
    }

    //список юзеров (обновляется раз в 10 секунд)
    function ses() {
        if (ws != null) {
            var xhttp = new XMLHttpRequest();
            xhttp.open("POST", "http://localhost:8080/UserList", true);
            xhttp.send();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("userBox").value = xhttp.responseText + "\n";
                }
            };
        }
    }

    //обновляет раз в 10 секунд список юзеров
    setInterval(ses, 10000);


</script>
</body>
</html>