package com.bookinventorymanagementapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookinventorymanagementapp.model.GBItemsWrapper;
import com.bookinventorymanagementapp.model.GBVolumeInfoWrapper;
import com.bookinventorymanagementapp.model.GBWrapper;

@Service
public class GoogleSearchServiceImpl implements GoogleSearchService {

	private String googleBookAPI = "https://www.googleapis.com/books/v1/volumes?q=";
	
	private RestTemplate restTemplate = new RestTemplate(); 
	
	@Override
	public Map<String, String> searchGoogleBookServiceBy(String name) {
		
		ResponseEntity entity = restTemplate.getForEntity(googleBookAPI + name, GBWrapper.class);
		 
		Map<String, String> booksIdToName = new HashMap<>();
		GBWrapper gbWrapper = (GBWrapper) entity.getBody();
		GBItemsWrapper[] items = gbWrapper.getItems();
		
		for (GBItemsWrapper item : items) {
			String id = item.getId();
			GBVolumeInfoWrapper volumeInfo = item.getVolumeInfo();
			String bookName = volumeInfo.getTitle();
			booksIdToName.put(id, bookName);
		}
		
		return booksIdToName;
	}
}
