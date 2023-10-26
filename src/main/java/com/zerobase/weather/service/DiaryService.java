package com.zerobase.weather.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.dto.DiaryDto;
import com.zerobase.weather.repository.DiaryRepository;
import com.zerobase.weather.utils.OpenWeather;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@Getter
@RequiredArgsConstructor
public class DiaryService {
	private final DiaryRepository diaryRepository;
	
	private String region = "Gwangju";
	@Value("${openweather.key}")
	private String apiKey;

	@Transactional
	public List<DiaryDto> findDiary(LocalDate date) {
		
		List<Diary> diaries = diaryRepository.findAllByDate(date);
		
		return diaries.stream()
				.map(DiaryDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Transactional
	public DiaryDto createDiary(LocalDate date, String text) {
		Diary diary = OpenWeather.getWeatherDateFromApi(region, apiKey);
		diary.setDate(date);
		diary.setText(text);
		
		return DiaryDto.fromEntity(diaryRepository.save(diary));
	}
}
