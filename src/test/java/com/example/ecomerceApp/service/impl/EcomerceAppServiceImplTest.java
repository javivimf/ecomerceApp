package com.example.ecomerceApp.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ecomerceApp.dto.PriceDTO;
import com.example.ecomerceApp.error.ErrorConstants;
import com.example.ecomerceApp.error.exceptIon.CustomException;
import com.example.ecomerceApp.model.Price;
import com.example.ecomerceApp.repository.EcomerceAppRepository;
import com.example.ecomerceApp.utils.UtilsTest;


@ExtendWith(SpringExtension.class)
class EcomerceAppServiceImplTest {

	@InjectMocks
	private EcomerceAppServiceImpl ecomerceAppServiceImpl;

	@Mock
	EcomerceAppRepository ecomerceAppRepository;

	@Test
	void testFindByFilterOK() throws Exception {

		List<Price> listPrice = new ArrayList<Price>();
		listPrice.add(UtilsTest.createPrice());
			
		Optional<List<Price>> optionalListPrices = Optional.of((List<Price>) listPrice);
		Mockito.<Optional<List<Price>>>when(ecomerceAppRepository.findBFilter(Mockito.anyInt(), any(LocalDateTime.class), Mockito.anyInt())).thenReturn(optionalListPrices);
		

		final Double price = UtilsTest.PRICE;

		final PriceDTO priceDTO = this.ecomerceAppServiceImpl.findByFilter(UtilsTest.IDPRODUCT, UtilsTest.DATENOW.toString(), UtilsTest.BRANDID);

		assertThat(priceDTO.getPrice()).isEqualTo(price);

	}
	
	
	@Test
	void testFindByFilterPriceNotFound() throws Exception {
		
		final CustomException customException = assertThrows(CustomException.class,
				() -> this.ecomerceAppServiceImpl.findByFilter(1002, UtilsTest.DATENOW.toString(), UtilsTest.BRANDID));

		assertThat(customException.getLocalizedMessage()).isEqualTo(ErrorConstants.ERROR_NO_SEARCH_RESULTS_DES);

	}	
	

}
