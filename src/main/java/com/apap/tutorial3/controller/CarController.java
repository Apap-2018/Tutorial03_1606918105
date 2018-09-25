package com.apap.tutorial3.controller;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService mobilService; //panggil car service
	
	@RequestMapping("/car/add")
	public String add(@RequestParam(value = "id", required = true) String id,
					  @RequestParam(value="brand",required = true) String brand,
					  @RequestParam(value="type", required = true) String type,
					  @RequestParam(value = "price", required = true) Long price,
					  @RequestParam(value = "amount", required = true) Integer amount) {
		
	CarModel car = new CarModel(id,brand,type,price,amount);
	mobilService.addCar(car);
	return "add";
	}
						
	


//@RequestMapping("/car/view")
//public String view(@RequestParam("id") String id, Model model) {
//		CarModel archive = mobilService.getCarDetail(id);
//		
//		model.addAttribute("car",archive);
//		return "view-car";
//	}

@RequestMapping("/car/viewall")
public String viewall(Model model) {
	List<CarModel> archive = mobilService.getCarList();
	
	model.addAttribute("listCar",archive);
	return "viewall-car";
	
	
}

@RequestMapping(value = { "/car/view","/car/view/{id}"})
public String viewfrompar(@PathVariable Optional<String> id, Model model) {
	
	if (id.isPresent()) {
		CarModel inputdata = mobilService.getCarDetail(id.get());
		if (inputdata == null) {
			model.addAttribute("message","Id Tidak ditemukan");
			return "message";
		}
		else {
			model.addAttribute("car",inputdata);
			return "view-car";
		}
		
	}
	else {
		return "erorhandling";
	}
	
}

@RequestMapping("/car/update/{id}/amount/{amount}")
public String updateamount(@PathVariable String id, @PathVariable Integer amount, Model model) {
	CarModel updatedCar = mobilService.updateCarAmount(id, amount);
	if (updatedCar!=null) {
//		CarModel inputdata = mobilService.getCarDetail(id);
		model.addAttribute("message", "Data Berhasil di Update");
		model.addAttribute("car",updatedCar);
		return "view-car";
	}else {
		model.addAttribute("message","Tidak terdapat id yang dituju");
		return "message";
	}
	
	
}

@RequestMapping(value = {"/car/delete/{id}"})
public String deleteCar(@PathVariable String id, Model model) {
		
	boolean deletedCar = mobilService.deleteCar(id);
	if (deletedCar == true) {
		model.addAttribute("message","Berhasil");
		return "message";
	}
	model.addAttribute("message","Id yang dituju tidak ada");
	return "message";
	

		
	}
}



