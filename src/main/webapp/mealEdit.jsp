<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
  <style>
    .ui-datepicker {
      background-color: #FFFF;
    }
  </style>
  <title>Update</title>
</head>
<body>
<script>
  $(function() {
    $('input[name=dateTime]').datetimepicker({
      dateFormat: "MM/dd/yy",
      timeFormat: "HH:mm",
      showTimepicker: true
    });
  });
</script>

<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
<%
  int selectedId = Integer.parseInt(request.getParameter("id"));
%>
<form action="meals" method="POST">
  <input type="hidden" name="id" value="<%=selectedId%>">
  <table>
    <tr>
      <td style="padding-right: 20px;">DateTime:</td>
      <td><input type="text" name="dateTime" <fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${meal.date}"  /></td>
    </tr>
    <tr>
      <td style="padding-right: 20px;">Description:</td>
      <td><input type="text" name="description" <c:out value="${meal.description}" /></td>
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