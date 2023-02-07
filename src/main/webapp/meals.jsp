<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams" %>
<%@ page import="java.time.LocalTime" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%
    List<Meal> meals = (List<Meal>) request.getAttribute("meals");
    List<MealTo> mealsTo = filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
%>


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
<table>
    <tr>
        <th>Дата и время</th>
        <th>Наименование</th>
        <th>Калорийность</th>
        <th colspan=2>Action</th>
    </tr>
    <% for (MealTo meal : mealsTo) { %>
    <tr style="color:<%= meal.isExcess()? "red" : "green" %>;">
    <td>
            <javatime:format value="<%= meal.getDateTime() %>" pattern="yyyy-MM-dd HH:mm"/>
        </td>
        <td><%= meal.getDescription() %></td>
        <td><%= meal.getCalories() %></td>
        <td><a href="mealEdit.jsp?id=<%=meal.getId()%>">Update</a></td>
        <td><a href="UserController?action=delete&userId=<%=meal.getId()%>">Delete</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>