<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://example.com/functions" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table>
    <thead>
        <tr>
            <th>Timestamp</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan="2">Action</th>
        </tr>
    </thead>
    <c:forEach items="${requestScope.meals}" var="meal">
        <tr style="color: ${meal.excess ? 'indianred' : 'forestgreen'}">
            <td><c:out value="${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm')}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Edit</a></td>
            <td><a href="meals?action=delete&id=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="meals?action=add">Add meal</a></p>

</body>
</html>
