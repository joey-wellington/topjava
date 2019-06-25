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
        </tr>
    </thead>
    <c:forEach items="${requestScope.meals}" var="meal">
        <tr style="color:
        <c:choose>
        <c:when test="${meal.excess}">
            indianred
        </c:when>
        <c:otherwise>
            forestgreen
        </c:otherwise>
        </c:choose>
            ">
            <td><c:out value="${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm')}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
