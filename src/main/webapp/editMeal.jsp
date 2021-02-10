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
<h2>Edit meal</h2>
<br/>
<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form method="post" action="meals" enctype="application/x-www-form-urlencoded">
    <fieldset>
        <legend>Редактировать прием пищи</legend>
        <table>
            <tr>
                <td><label for="date">Дата</label></td>
                <td><input type="datetime-local" name="date" id="date"
                           required value="${meal.dateTime}"></td>
            </tr>
            <tr>
                <td><label for="description">Описание</label></td>
                <td><input type="text" name="description" id="description"
                           required value="${meal.description}"></td>
            </tr>
            <tr>
                <td><label for="calories">Количество калорий</label></td>
                <td><input type="number" min="0" max="1000" step="10" name="calories" id="calories"
                           required value="${meal.calories}"></td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="id" value="${meal.id}">
                </td>
                <td>
                    <button type="submit">Сохранить</button>
                    <button onclick="window.history.back()">Отменить</button>
                </td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</html>