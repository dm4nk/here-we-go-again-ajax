<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактировать альбом</title>
</head>
<body>
<h1>Редактировать альбом</h1>
<form action="editAlbum" method="POST">
    <input type="hidden" id="id" name="id" value="${album.getId()}">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" value="${album.getName()}" required><br>
    <label for="genre">Жанр:</label>
    <input type="text" id="genre" name="genre" value="${album.getGenre()}" required><br>
    <button type="submit">Сохранить изменения</button>
</form>
<a href="albums">Вернуться к списку альбомов</a>
</body>
</html>