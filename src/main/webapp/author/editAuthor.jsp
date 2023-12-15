<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактировать автора</title>
</head>
<body>
<h1>Редактировать автора</h1>
<form action="editAuthor" method="POST">
    <input type="hidden" id="id" name="id" value="${author.getId()}">
    <label for="name">Имя автора:</label>
    <input type="text" id="name" name="name" value="${author.getName()}" required><br>
    <button type="submit">Сохранить изменения</button>
</form>
<a href="authors">Вернуться к списку авторов</a>
</body>
</html>

