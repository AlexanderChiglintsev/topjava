<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/styleform.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Add meal</h2>
<br/>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    <fieldset>
        <legend>Добавить прием пищи</legend>
        <table>
            <tr>
                <td><label for="date">Дата</label></td>
                <td><input type="datetime-local" name="date" id="date" required></td>
            </tr>
            <tr>
                <td><label for="description">Описание</label></td>
                <td><input type="text" name="description" id="description" required></td>
            </tr>
            <tr>
                <td><label for="calories">Количество калорий</label></td>
                <td><input type="number" min="0" max="1000" step="10" name="calories" id="calories" required></td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="action" value="add">
                </td>
                <td>
                    <button type="submit">Добавить</button>
                    <button onclick="window.history.back()">Отменить</button>
                </td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</html>