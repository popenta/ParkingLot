<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>
    <form method="POST" action="${pageContext.request.contextPath}/Cars">
    <a class="btn btn-primary btn-lg" href="/ParkingLot/AddCar" role="button">Add car &raquo;</a>
    <button class="btn btn-danger" type="submit">Delete cars</button>
    <c:forEach var="car" items="${cars}" varStatus="status">
        <div class="row">
            <div class="col-md">
                <input type="checkbox" name="car_ids" value="${car.id}" />
            </div>
            <div class="col-md-3">
                ${car.licensePlate}
            </div>
            <div class="col-md-3">
                ${car.parkingSpot}
            </div>
            <div class="col-md-3">
                ${car.username}
            </div>
            <div class="col-md-2">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditCar?id=${car.id}" role="button">Edit car</a>
            </div>
        </div>
    </c:forEach>
    </form>
</t:pageTemplate>