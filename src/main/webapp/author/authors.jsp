
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список авторов</title>
</head>
<body>
<a href="/">На главную</a>
<br/>
<h1>Список авторов</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Имя автора</th>
        <th>Кол-во альбомов</th>
        <th>Действия</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach items="${authors}" var="author">
        <tr>
            <td>${author.getId()}</td>
            <td>${author.getName()}</td>
            <td>${author.getAlbums()}</td>
            <td>
                <a href="editAuthor?id=${author.id}">Редактировать</a>
                <a href="deleteAuthor?id=${author.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="createAuthor">Создать нового автора</a>
</body>
</html>

