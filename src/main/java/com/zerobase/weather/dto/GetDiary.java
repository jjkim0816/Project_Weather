package com.zerobase.weather.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetDiary {
	private long id;
	private String weather;
	private String icon;
	private double temperature;
	private String text;
	private LocalDate date;
}
