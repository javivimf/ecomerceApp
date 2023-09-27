package com.example.ecomerceApp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data 
@Entity
public class Price {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "brand_id")
	private Integer brandId;	
	
	@Column(name = "start_date")
	private LocalDateTime startDate;	
	
	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(name = "price_list")
	private Integer priceList;	
	
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "priority")
	private Integer priority;	
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "curr")
	private String curr;

}
