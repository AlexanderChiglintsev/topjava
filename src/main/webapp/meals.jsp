<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<br/>
<table>
    <tr>
        <th>Дата</th>
        <th>Описание</th>
        <th>Количество калорий</th>
        <th></th>
        <th></th>
    </tr>
    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach var="meal" items="${list}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <c:set var="color" value="${meal.excess == true ? 'redrow' : 'greenrow'}"/>
        <tr class="${color}">
            <td><javatime:format value="${meal.dateTime}" style="MS" pattern="yyyy-MM-dd hh:mm"/></td>
            <td>${meal.description}</td>
            <td id="cal">${meal.calories}</td>
            <td><a href="meals?action=edit&id=${meal.id}">Редактировать</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Удалить</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<form method="get" action="meals" enctype="application/x-www-form-urlencoded">
    <fieldset>
        <legend>Добавить прием пищи</legend>
        <table>
            <tr>
                <td class="form"><label for="date">Дата</label></td>
                <td class="form"><input type="datetime-local" name="date" id="date" required></td>
            </tr>
            <tr>
                <td class="form"><label for="description">Описание</label></td>
                <td class="form"><input type="text" name="description" id="description" required></td>
            </tr>
            <tr>
                <td class="form"><label for="calories">Количество калорий</label></td>
                <td class="form"><input type="number" min="0" max="1000" step="10" name="calories" id="calories"
                                        required></td>
            </tr>
            <tr>
                <td class="form"><input type="hidden" name="action" value="add"></td>
                <td class="form">
                    <button type="submit">Добавить</button>
                </td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</html>