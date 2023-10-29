package com.zerobase.weather.exception;

import com.zerobase.weather.type.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class WeatherException extends RuntimeException {
	private final ErrorCode errorCode;
	private final String errorMessage;
	
	public WeatherException(ErrorCode errorCode) {
		this.errorCode = errorCode;
		this.errorMessage = errorCode.getDescription();
	}
}
