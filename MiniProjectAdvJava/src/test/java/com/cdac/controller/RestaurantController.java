package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.cdac.dto.RegistrationStatusRestaurant;

import com.cdac.entity.Restaurant;
import com.cdac.exception.RestaurnatServiceException;
import com.cdac.service.RestaurantService;

@RestController
@CrossOrigin
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	//spring can read the incoming json, parse it and store the data
	//in an object for us
	//in postman after select post method and entering the url,
	//select body, then select raw, then select json and enter the json data:
	/*
		 {
	  		"name" : "Majrul",
	  		"email" : "majrul@gmail.com",
	  		"password" : "123"
		}
	 */
	@PostMapping("/register")
	public RegistrationStatusRestaurant register(@RequestBody Restaurant restaurant) {
		try {
			int id = restaurantService.register(restaurant);
			
			RegistrationStatusRestaurant status = new RegistrationStatusRestaurant();
			status.setStatus(true);
			status.setStatusMessage("Customer registered successfully!");
			status.setRestaurantId(id);
			return status;
		}
		catch (RestaurnatServiceException e) {
			RegistrationStatusRestaurant status = new RegistrationStatusRestaurant();
			status.setStatus(false);
			status.setStatusMessage(e.getMessage());
			return status;			
		}
	}
}
