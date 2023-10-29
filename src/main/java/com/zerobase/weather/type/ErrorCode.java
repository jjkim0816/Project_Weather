package com.zerobase.weather.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	INTERNAL_SERVER_ERROR("내부 서버 에러 입니다."),
	WEATHER_DATA_NOT_FOUND("데이터가 없습니다."),
	INVALID_INPUT_DATE_AFTER_YEAR("다음해 년도 입력은 불가합니다."),
	INVALID_INPUT_END_DATE_BEFORE_START_DATE("시작날짜는 종료날짜 보다 이후 일 수 없습니댜.")
	;
	
	private String description;
}
