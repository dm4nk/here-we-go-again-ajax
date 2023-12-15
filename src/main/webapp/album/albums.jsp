<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список альбомов</title>
</head>
<body>
<a href="/">На главную</a>
<br/>
<h1>Список альбомов</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Название альбома</th>
        <th>Жанр</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${albums}" var="album">
        <tr>
            <td>${album.getId()}</td>
            <td>
                <a href="#" class="album-link" data-album-id="${album.id}">${album.name}</a>
                <div class="track-list" style="display: none;"></div>
            </td>
            <td>${album.getGenre()}</td>
            <td>
                <a href="editAlbum?id=${album.id}">Редактировать</a>
                <a href="deleteAlbum?id=${album.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="createAlbum">Создать новый альбом</a>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        $(".album-link").hover(
            function() {
                var albumId = $(this).data("album-id");
                var trackListDiv = $(this).siblings(".track-list");

                $.ajax({
                    url: "getTrackNamesByAlbumId?id=" + albumId,
                    type: "GET",
                    success: function(trackNames) {
                        trackListDiv.html("Треки: " + trackNames.join(", "));
                        trackListDiv.show();
                    }
                });
            },
            function() {
                $(this).siblings(".track-list").hide();
            }
        );
    });
</script>

</body>
</html>