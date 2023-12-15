<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Создать нового автора</title>
</head>
<body>
<h1>Создать нового автора</h1>
<form action="createAuthor" method="POST">
    <label for="name">Имя автора:</label>
    <input type="text" id="name" name="name" required><br>
    <button type="submit">Создать автора</button>
</form>
<a href="authors">Вернуться к списку авторов</a>
</body>
</html>
