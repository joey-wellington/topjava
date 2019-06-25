<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<html>
<head>
    <title>Add/Edit meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Add/Edit meal</h2>

<form method="POST" action="meals" name="AddMealForm">
    Meal ID: <input type="text" readonly="readonly" name="id" value="${meal.id}"><br />
    Timestamp: <input type="datetime" name="dateTime" value="${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm')}"><br />
    Description: <input type="text" name="description" value="${meal.description}"><br />
    Calories: <input type="number" name="calories" value="${meal.calories}"><br />
    <input type="submit" value="Save">
</form>

</body>
</html>
