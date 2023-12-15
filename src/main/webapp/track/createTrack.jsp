<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Создать новый трек</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#albumName").on("input", function() {
                var inputText = $(this).val();
                var selectElement = $("#album");
                var url = "getSimilarAlbums?input=" + inputText;

                $.ajax({
                    url: url,
                    type: "GET",
                    success: function(albums) {
                        selectElement.empty();
                        albums.forEach(function(album) {
                            var option = new Option(album.name, album.id);
                            selectElement.append(option);
                        });
                    }
                });
            });

            $("#album").on("change", function() {
                $("#album").val($(this).val());
                $("#albumName").val($(this).find(":selected").text());
            });
        });
    </script>
</head>
<body>
<h1>Создать новый трек</h1>
<form action="createTrack" method="POST">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="duration">Длительность (в секундах):</label>
    <input type="number" id="duration" name="duration" required><br>
    <label for="albumName">Название альбома</label>
    <input type="text" id="albumName" name="albumName" required><br>
    <select id="album" name="album"></select>
    <button type="submit">Создать трек</button>
</form>
<a href="tracks">Вернуться к списку треков</a>
</body>
</html>