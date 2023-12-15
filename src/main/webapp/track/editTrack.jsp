<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактировать трек</title>
</head>
<body>
<h1>Редактировать трек</h1>
<form action="editTrack" method="POST">
    <input type="hidden" id="id" name="id" value="${tracks.getId()}">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" value="${tracks.getName()}" required><br>
    <label for="duration">Длительность (в секундах):</label>
    <input type="number" id="duration" name="duration" value="${tracks.getDuration()}" required><br>
    <label for="album">ID альбома</label>
    <input type="number" id="album" name="album" value="${tracks.getAlbum().getId()}" required><br>
    <button type="submit">Сохранить изменения</button>
</form>
<a href="tracks">Вернуться к списку треков</a>
</body>
</html>
