package com.example.ecomerceApp.service;

import com.example.ecomerceApp.dto.PriceDTO;

public interface IEcomerceAppService {

	public PriceDTO findByFilter(final Integer idProduct, final String appDate, final Integer franchiseId) throws Exception;
}
