package com.example.ecomerceApp.error.exceptIon;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.ecomerceApp.error.ErrorDto;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorDto error;
	
	private final String code;

	public CustomException(String code) {
	        super();
	        this.code = code;
    }
	public CustomException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }
    public CustomException(String message, String code) {
        super(message);
        this.code = code;
    }
    public CustomException(Throwable cause, String code) {
        super(cause);
        this.code = code;
    }
    
    
    public CustomException(final ErrorDto error) {
        this(null, error.getDescription(), error);
    }

    public CustomException(final String msg, final ErrorDto error) {
        this(null, msg, error);
    }
  

    public CustomException(final Throwable exception, final String msg, final ErrorDto error) {
        super(msg, exception);
        this.error = error;
		this.code = "";
      
    }
    
    public String getCode() {
        return this.code;
    }
    
            
    public ErrorDto getError() {
		return error;
	}
       
	public void setError(ErrorDto error) {
		this.error = error;
	}
	
 
}
