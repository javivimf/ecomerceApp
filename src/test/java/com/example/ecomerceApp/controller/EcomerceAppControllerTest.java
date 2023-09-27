package com.example.ecomerceApp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.ecomerceApp.dto.PriceDTO;
import com.example.ecomerceApp.error.ErrorConstants;
import com.example.ecomerceApp.error.exceptIon.CustomException;
import com.example.ecomerceApp.service.IEcomerceAppService;
import com.example.ecomerceApp.utils.UtilsTest;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(MockitoExtension.class)
class EcomerceAppControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	IEcomerceAppService ecomerceAppService;
	
	@Spy
	ObjectMapper objectMapper = new ObjectMapper();
	
	@InjectMocks
	EcomerceAppController ecomerceAppController;
	
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ecomerceAppController).build();
	}
	
	
	@Test
	void findBFilterTestOK() throws Exception {
		
		PriceDTO priceDto = UtilsTest.createPriceDTO();
				
		when(ecomerceAppService.findByFilter(Mockito.anyInt(), Mockito.any(), Mockito.anyInt())).thenReturn(priceDto);
		
		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
				requestParams.add("idProduct", String.valueOf( UtilsTest.IDPRODUCT));
				requestParams.add("appDate",  UtilsTest.DATENOW.toString());
				requestParams.add("brandId", String.valueOf( UtilsTest.BRANDID));

		final ResultActions response = mockMvc.perform(get("/prices").queryParams(requestParams)).andExpect(status().isOk());
			
		PriceDTO priceDTOResult = objectMapper.readValue(response.andReturn().getResponse().getContentAsString(), PriceDTO.class);//FIXME

		assertEquals(38.95, priceDTOResult.getPrice().doubleValue());		
		
	}
	
	
	@Test
	void findBFilterTestPriceNotFound() throws Exception {
				
		when(ecomerceAppService.findByFilter(Mockito.anyInt(), Mockito.any(), Mockito.anyInt()))
			.thenThrow(new CustomException(ErrorConstants.ERROR_NO_SEARCH_RESULTS_DES, ErrorConstants.ERROR_NO_SEARCH_RESULTS_CODE));
		
		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
				requestParams.add("idProduct", String.valueOf(222));
				requestParams.add("appDate", UtilsTest.DATENOW.toString());
				requestParams.add("brandId", String.valueOf( UtilsTest.BRANDID));

		final ResultActions response = mockMvc.perform(get("/prices").queryParams(requestParams)).andExpect(status().isBadRequest());
			
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.andReturn().getResponse().getStatus());
		
	}	
 
}
