/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parkinglot.ejb;

import com.mycompany.parkinglot.common.CarDetails;
import com.mycompany.parkinglot.common.PhotoDetails;
import com.mycompany.parkinglot.entity.Car;
import com.mycompany.parkinglot.entity.User;
import com.park.parkinglot.entity.Photo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alex
 */
@Stateless
public class CarBean {

    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());
    @PersistenceContext
    private EntityManager em;
    
    public void addPhotoToCar(Integer carId, String filename, String fileType, byte[] fileContent){
        LOG.info("addPhotoToCar");
        Photo photo = new Photo();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        
        Car car = em.find(Car.class, carId);
        car.setPhoto(photo);
        
        photo.setCar(car);
        em.persist(photo);
    }
    
    public CarDetails findById(Integer carId){
        Car car = em.find(Car.class, carId);
        return new CarDetails(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getUser().getUsername());
    }
    
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
    
    public void createCar(String licensePlate, String parkingSpot, Integer userId){
        LOG.info("createCar");
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        
        User user = em.find(User.class, userId);
        user.getCars().add(car);
        car.setUser(user);
        
        em.persist(car);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
    public void updateCar(int carId, String licensePlate, String parkingSpot, int userId) {
        LOG.info("updateCar");
        Car car = em.find(Car.class, carId);
        
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        
        User oldUser = car.getUser();
        oldUser.getCars().remove(car);
        
        User user = em.find(User.class, userId);
        user.getCars().add(car);
        car.setUser(user);
        
    }

    public void deleteCarsById(Collection<Integer> ids) {
        LOG.info("deleteCarsById");
        for(Integer id : ids){
            Car car = em.find(Car.class, id);
            em.remove(car);
        }
    }
    
    public PhotoDetails findPhotoByCarId(Integer carId){
        
        TypedQuery<Photo> typedQuery = em.createQuery("SELECT p FROM Photo p where p.car.id = :id", Photo.class).setParameter("id", carId);
        List<Photo> photos = typedQuery.getResultList();
        
        if(photos.isEmpty()){
            return null;
        }
        
        Photo photo = photos.get(0);
        return new PhotoDetails(photo.getId(), photo.getFilename(), photo.getFileType(), photo.getFileContent());
    }    
}
