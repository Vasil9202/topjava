<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html lang="ru">
<head>
    <title>Users</title>
</head>
<style>
    table {
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid black;
        padding: 8px;
    }
</style>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h3><a href="mealEdit.jsp">AddMeal</a></h3>
<table>
    <tr>
        <th>Дата и время</th>
        <th>Наименование</th>
        <th>Калорийность</th>
        <th colspan=2>Action</th>
    </tr>
    <c:forEach items="${meals}" var="meal" varStatus="mealStatus">
        <tr style="color:${ meal.isExcess()? "red" : "green" };">
            <td>
                <javatime:format value="${ meal.getDateTime() }" pattern="yyyy-MM-dd HH:mm"/>
            </td>
            <td> ${ meal.getDescription()}</td>
            <td> ${ meal.getCalories() }</td>
            <td><a href="<c:url value='/meals'><c:param name='id' value='${mealStatus.index}'/></c:url>">Update</a></td>
            <td><a href="<c:url value='/meals'><c:param name='idDelete' value='${mealStatus.index}'/></c:url>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>