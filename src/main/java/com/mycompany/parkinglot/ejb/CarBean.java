/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parkinglot.ejb;

import com.mycompany.parkinglot.common.CarDetails;
import com.mycompany.parkinglot.entity.Car;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alex
 */
@Stateless
public class CarBean {

    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    public List<CarDetails> getAllCars() throws Exception{
        LOG.info("getAllCars");
        try{
            List<Car> cars = (List<Car>) em.createQuery("SELECT c from Car c").getResultList();
            return copyCarsToDetails(cars);
        }catch(Exception ex){
            throw new Exception(ex);
        }
    }
    
    private List<CarDetails> copyCarsToDetails(List<Car> cars){
        List<CarDetails> detailList = new ArrayList<>();
        for (Car car : cars){
            CarDetails carDetails = new CarDetails(car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot(),
                car.getUser().getUsername());
            detailList.add(carDetails);
        }
        return detailList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
