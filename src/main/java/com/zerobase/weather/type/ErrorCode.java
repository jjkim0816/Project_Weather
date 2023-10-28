package com.zerobase.weather.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	INTERNAL_SERVER_ERROR("내부 서버 에러 입니다."),
	WEATHER_DATA_NOT_FOUND("데이터가 없습니다.")
	;
	
	private String description;
}
