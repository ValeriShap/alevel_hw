<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
      <title>This all Сars</title>
<head>
<body>
<h1>All Сars:</h1>
<c:if test="${empty cars}">
         <p>No cars found</p>
      </c:if>
      <c:if test="${not empty cars}">
      <table>
      <tr>
                     <th>Id</th>
                     <th>Type</th>
                     <th>Manufacturer</th>
                     <th>Color</th>
                     <th>Count</th>
                     <th>Price</th>
                     <th>Engine</th>
                     <th>Passenger Count</th>
                     <th>Load Capacity<</th>
                  </tr>
<c:forEach var="car" items="${cars}">
               <tr>
                  <td>${car.id}</td>
                  <td>${car.type}</td>
                  <td>${car.manufacturer}</td>
                  <td>${car.color}</td>
                  <td>${car.count}</td>
                  <td>${car.price}</td>
                  <td>${car.engine}</td>
                  <td><c:choose>
                  <c:when test="${car.type == 'CAR'}">${car.passengerCount}
                  </c:when>
                  <c:when test="${car.type == 'TRUCK'}">${car.loadCapacity}
                  </c:when>
                  <c:otherwise>
                  None
                  </c:otherwise>
                  </c:choose></td>
                  </tr>
</c:forEach>
 </c:if>
 </body>
 </html>
