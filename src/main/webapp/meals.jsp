<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <th>Удалить</th>
        <th>Редактировать</th>
    </tr>
    <jsp:useBean id="list" scope="request" type="java.util.List"/>
    <c:forEach var="meal" items="${list}">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <c:choose>
            <c:when test="${meal.excess==true}">
                <c:set var="color" value="lightcoral"/>
            </c:when>
            <c:otherwise>
                <c:set var="color" value="lightgreen"/>
            </c:otherwise>
        </c:choose>
        <tr bgcolor="${color}">
            <td>${meal.date}&nbsp;${meal.time}</td>
            <td>${meal.description}</td>
            <td id="calories">${meal.calories}</td>
            <td></td>
            <td></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>