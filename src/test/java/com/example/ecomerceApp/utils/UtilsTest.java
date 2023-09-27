package com.example.ecomerceApp.utils;

import java.time.LocalDateTime;

import com.example.ecomerceApp.dto.PriceDTO;
import com.example.ecomerceApp.model.Price;

public class UtilsTest {	
	
	public static final LocalDateTime DATENOW = LocalDateTime.now();
	public static final LocalDateTime STARTDATE = DATENOW.minusDays(1);
	public static final LocalDateTime ENDDATE = DATENOW.plusDays(1);
	
	public static final Integer BRANDID = 1;
	public static final Integer IDPRODUCT = 35455;
	public static final Double PRICE = 38.95;
	
	
	public static PriceDTO createPriceDTO() {
		
		PriceDTO priceDto = new PriceDTO();
		priceDto.setProductId(IDPRODUCT);
		priceDto.setBrandId(BRANDID);
		priceDto.setStartDate(STARTDATE);
		priceDto.setEndDate(ENDDATE);
		priceDto.setPriceList(1);
		priceDto.setPrice(PRICE);
		return priceDto;
	} 
	
	
	public static Price createPrice() {
		
		Price price = new Price();
		price.setProductId(IDPRODUCT);
		price.setBrandId(BRANDID);
		price.setStartDate(STARTDATE);
		price.setEndDate(ENDDATE);
		price.setPriceList(1);
		price.setPrice(PRICE);
		return price;
	} 
}
