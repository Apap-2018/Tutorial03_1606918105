package com.apap.tutorial3.service;
import java.util.List;
import com.apap.tutorial3.model.CarModel;

//akses model 
//method dasar
public interface CarService {
	void addCar(CarModel car);
	List<CarModel> getCarList();
	CarModel getCarDetail(String id);
	CarModel updateCarAmount(String id, Integer amount);
	boolean deleteCar(String id);
	
}

