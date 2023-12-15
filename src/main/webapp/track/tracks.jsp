<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Композиции</title>
</head>
<body>
<a href="/">На главную</a>
<br/>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Название трека</th>
        <th>Длительность</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${tracks}" var="track">
        <tr>
            <td>${track.getId()}</td>
            <td>${track.getName()}</td>
            <td>${track.getDuration()}</td>
            <td>
                <a href="editTrack?id=${track.id}">Редактировать</a>
                <a href="deleteTrack?id=${track.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="createTrack">Создать новый трек</a>
</body>
</html>
