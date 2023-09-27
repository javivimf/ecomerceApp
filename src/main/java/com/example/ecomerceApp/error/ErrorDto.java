package com.example.ecomerceApp.error;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorDto implements Serializable {

	private static final long serialVersionUID = 1424911199995215164L;

	private String origin;

	private Integer httpCode;

	private String description;

	private Class<? extends Exception> exceptionType;
	

	public ErrorDto(String origin, Integer httpCode, String description, Class<? extends Exception> exceptionType) {
		super();
		this.origin = origin;
		this.httpCode = httpCode;
		this.description = description;
		this.exceptionType = exceptionType;

	}
}