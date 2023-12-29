package com.cdac.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Restaurant;
import com.cdac.exception.RestaurnatServiceException;
import com.cdac.repository.RestaurantRepository;

@Service
public class RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public int register(Restaurant restaurant) throws RestaurnatServiceException {
		//suppose we need to check if this customer has already registered before
		Optional<Restaurant> restaurantCheck = restaurantRepository.findByName(restaurant.getName());
		if(restaurantCheck.isEmpty()) {
			Restaurant savedRestaurant = restaurantRepository.save(restaurant);
			return savedRestaurant.getRestaurantId();
		}
		else
			throw new RestaurnatServiceException("Customer already registered!");
	}

	public void login() {
		
		
	}

}
