<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/jquery-ui-timepicker-addon@1.6.3/dist/jquery-ui-timepicker-addon.min.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <title>Update Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
<form action="meals" method="POST">
  <input type="hidden" name="meal" value="${meal}" />
  <input type="hidden" name="id" value="${id}" />
  <table>
    <tr>
      <td style="padding-right: 20px;">DateTime:</td>
      <td>
        <p><input type="datetime-local" pattern="MM-DD-YYYY HH:mm" name="dateTime" value="<c:out value="${meal.dateTime}" />" />
      </td>
    </tr>
    <tr>
      <td style="padding-right: 20px;">Description:</td>
      <td><input type="text" name="description" value="<c:out value="${meal.description}" />" /></td>
    </tr>
    <tr>
      <td style="padding-right: 20px;">Calories:</td>
      <td><input type="text" name="calories" value="${meal.calories}" /></td>
    </tr>
    <tr>
      <td>
        <input type="submit" value="Save">
        <input type="button" value="Cancel" onclick="location.href='index.html'">
      </td>
    </tr>
  </table>
</form>
</body>
</html>