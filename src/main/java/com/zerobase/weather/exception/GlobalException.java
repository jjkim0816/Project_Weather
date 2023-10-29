package com.zerobase.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zerobase.weather.dto.ErrorResponse;
import com.zerobase.weather.type.ErrorCode;

@RestControllerAdvice
public class GlobalException {
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(WeatherException.class)
	public ErrorResponse handlerWeatherException(WeatherException e) {
		return new ErrorResponse(e.getErrorCode(), e.getErrorMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponse handlerException(Exception e) {
		return new ErrorResponse(
			ErrorCode.INTERNAL_SERVER_ERROR,
			ErrorCode.INTERNAL_SERVER_ERROR.getDescription());
	}
}
