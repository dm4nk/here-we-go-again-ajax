<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Создать новый альбом</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#authorName").on("input", function() {
                var inputText = $(this).val();
                var selectElement = $("#author");
                var url = "getSimilarAuthors?input=" + inputText;

                $.ajax({
                    url: url,
                    type: "GET",
                    success: function(authors) {
                        selectElement.empty();
                        authors.forEach(function(author) {
                            var option = new Option(author.name, author.id);
                            selectElement.append(option);
                        });
                    }
                });
            });

            $("#author").on("change", function() {
                // Устанавливаем id автора в скрытое поле
                $("#author").val($(this).val());
                // Устанавливаем имя автора в поле ввода
                $("#authorName").val($(this).find(":selected").text());
            });
        });
    </script>
</head>
<body>
<h1>Создать новый альбом</h1>
<form action="createAlbum" method="POST">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="genre">Жанр:</label>
    <input type="text" id="genre" name="genre" required><br>
    <label for="authorName">Имя исполнителя:</label>
    <input type="text" id="authorName" name="authorName" placeholder="Введите имя автора" required>
    <select id="author" name="author"></select>
    <button type="submit">Создать альбом</button>
</form>
<a href="albums">Вернуться к списку альбомов</a>
</body>
</html>
