package com.zerobase.weather.dto;

import java.time.LocalDate;

import com.zerobase.weather.domain.Diary;

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
public class DiaryDto {
	private long id;
	private String weather;
	private String icon;
	private double temperature;
	private String text;
	private LocalDate date;
	
	public static DiaryDto fromEntity(Diary diary) {
		return DiaryDto.builder()
				.id(diary.getId())
				.weather(diary.getWeather())
				.icon(diary.getIcon())
				.temperature(diary.getTemperature())
				.text(diary.getText())
				.date(diary.getDate())
				.build();
	}
}
