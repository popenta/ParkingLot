
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>
    <div class="row">
        <div class="col-md-4">
            Car 1
        </div>
        
        <div class="col-md-4">
            Spot 1
        </div>
        
        <div>
            User 1
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-4">
            Car 2
        </div>
        
        <div class="col-md-4">
            Spot 2
        </div>
        
        <div>
            User 2
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-4">
            Car 3
        </div>
        
        <div class="col-md-4">
            Spot 3
        </div>
        
        <div>
            User 3
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-4">
            Car 4
        </div>
        
        <div class="col-md-4">
            Spot 4
        </div>
        
        <div>
            User 4
        </div>
    </div>
    
    <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
    
</t:pageTemplate>
