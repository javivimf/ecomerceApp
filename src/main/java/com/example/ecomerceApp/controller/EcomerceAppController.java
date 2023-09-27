package com.example.ecomerceApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecomerceApp.dto.PriceDTO;
import com.example.ecomerceApp.service.IEcomerceAppService;

import lombok.extern.java.Log;

@Log
@RestController
public class EcomerceAppController {
	
	private IEcomerceAppService ecomerceAppService;
	
	public EcomerceAppController(IEcomerceAppService ecomerceAppService) {
		this.ecomerceAppService = ecomerceAppService;
	}
			
			
	@GetMapping(value = "/prices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PriceDTO> findBFilter(
			@RequestParam(name = "idProduct") Integer idProduct,
			@RequestParam(name = "appDate") String appDate,
			@RequestParam(name = "brandId") Integer brandId) throws Exception {
		
		log.info("EcomerceAppController - findBFilter idProduct: " + idProduct + " appDate: " + appDate + " brandId: " + brandId);
		PriceDTO priceDto = ecomerceAppService.findByFilter(idProduct, appDate, brandId);
		
		return new ResponseEntity<>(priceDto, HttpStatus.OK);				
	}	
	
}
