package com.example.ecomerceApp.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data 
public class PriceDTO {
	
	private Integer brandId;	
	private LocalDateTime startDate;	
	private LocalDateTime endDate;
	private Integer priceList;	
	private Integer productId;
	private Double price;
	

}
