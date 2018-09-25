package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.apap.tutorial3.model.CarModel;
import org.springframework.stereotype.Service;

@Service
public class CarInMemoryService implements CarService{
	private List<CarModel> archiveCar;
	
	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
	}

	@Override
	public void addCar(CarModel car) {
		archiveCar.add(car);
		
	}

	@Override
	public List<CarModel> getCarList() {
		return archiveCar;
	}

	@Override
	public CarModel getCarDetail(String id) {
		if (archiveCar.size() == 0){
			return null;
		}
		for (int i = 0 ; i< archiveCar.size() ; i++) {
			if (id.equals(archiveCar.get(i).getId())) {
				return archiveCar.get(i);
			}
		}
		return null;
	}

	@Override
	public CarModel updateCarAmount(String id, Integer amount) {
		for (int i = 0 ; i< archiveCar.size() ; i++) {
			if (id.equals(archiveCar.get(i).getId())) {
	
				archiveCar.get(i).setAmount(amount);
	
//				System.out.println("NEMU UPDATE : "+ archiveCar.get(i).getAmount() + " " + amount);
				return archiveCar.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean deleteCar(String id) {
		return archiveCar.remove(getCarDetail(id));	
	}

}
