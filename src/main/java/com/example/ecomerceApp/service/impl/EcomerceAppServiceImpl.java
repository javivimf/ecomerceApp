package com.example.ecomerceApp.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecomerceApp.dto.PriceDTO;
import com.example.ecomerceApp.error.ErrorConstants;
import com.example.ecomerceApp.error.exceptIon.CustomException;
import com.example.ecomerceApp.model.Price;
import com.example.ecomerceApp.repository.EcomerceAppRepository;
import com.example.ecomerceApp.service.IEcomerceAppService;


@Service
public class EcomerceAppServiceImpl implements IEcomerceAppService{

	EcomerceAppRepository ecomerceAppRepository;
	
	public EcomerceAppServiceImpl(EcomerceAppRepository ecomerceAppRepository) {
		this.ecomerceAppRepository = ecomerceAppRepository;
	}
	
	
	@Override
	public PriceDTO findByFilter(final Integer idProduct, final String appDate, final Integer franchiseId) throws Exception {
		
		LocalDateTime localDateTime = convertStringToLocalDateTime(appDate);
		
        Optional<List<Price>> resultado = ecomerceAppRepository.findBFilter(idProduct, localDateTime, franchiseId);
		
       
        PriceDTO priceDto;
        
        if(resultado.isPresent() && !resultado.get().isEmpty())
        {
        	List<Price> listPrice = resultado.get();
        	
        	if(listPrice.size() > 1) {
        		
        		/*NOTA: El enunciado no dice nada si coinciden mas de 2 tarifas en un rango, asi que supondremos que solo hay 2*/
        		Price  priceFinal = listPrice.get(0).getPriority() > listPrice.get(1).getPriority() ? listPrice.get(0) : listPrice.get(1);
        		
        		priceDto = mapToDTO(priceFinal);
        		
        	} else if(listPrice.size() == 1) {
        		        		
        		priceDto = mapToDTO(listPrice.get(0));
        		
        	}else {
        		throw new CustomException(ErrorConstants.ERROR_NO_SEARCH_RESULTS_DES, ErrorConstants.ERROR_NO_SEARCH_RESULTS_CODE);
        	}
        }else {
        	throw new CustomException(ErrorConstants.ERROR_NO_SEARCH_RESULTS_DES, ErrorConstants.ERROR_NO_SEARCH_RESULTS_CODE);
    	}
		return priceDto;
		  
	}
	

	private LocalDateTime convertStringToLocalDateTime(final String appDate) {

		return LocalDateTime.of(
				LocalDate.of(Integer.valueOf(appDate.substring(0,4)), Integer.valueOf(appDate.substring(5,7)), Integer.valueOf(appDate.substring(8,10))), 
				LocalTime.of(Integer.valueOf(appDate.substring(11,13)), Integer.valueOf(appDate.substring(14,16)), Integer.valueOf(appDate.substring(17,19))));
		
		
	}
	

	private PriceDTO mapToDTO(Price priceFinal) {
		
		PriceDTO priceDto = new PriceDTO();
		priceDto.setProductId(priceFinal.getProductId());
		priceDto.setBrandId(priceFinal.getBrandId());
		priceDto.setStartDate(priceFinal.getStartDate());
		priceDto.setEndDate(priceFinal.getEndDate());
		priceDto.setPriceList(priceFinal.getPriceList());
		priceDto.setPrice(priceFinal.getPrice());
		return priceDto;
	} 

}