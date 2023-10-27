package com.zerobase.weather.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zerobase.weather.domain.DateWeatherDao;
import com.zerobase.weather.domain.DiaryDao;
import com.zerobase.weather.dto.DiaryDto;
import com.zerobase.weather.repository.DateWeatherRepository;
import com.zerobase.weather.repository.DiaryRepository;
import com.zerobase.weather.utils.OpenWeather;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@Getter
@RequiredArgsConstructor
public class DiaryService {
	private final DiaryRepository diaryRepository;
	private final DateWeatherRepository dateWeatherRepository;
	
	private String region = "Gwangju";
	@Value("${openweather.key}")
	private String apiKey;

	@Transactional
	public List<DiaryDto> findDiary(LocalDate date) {
		
		List<DiaryDao> diaries = diaryRepository.findAllByDate(date);
		
		return diaries.stream()
				.map(DiaryDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<DiaryDto> findDiaries(LocalDate startDate, LocalDate endDate) {
		List <DiaryDao> diaries = diaryRepository.findAllByDateBetween(startDate, endDate);

		return diaries.stream()
				.map(DiaryDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Transactional
	public DiaryDto createDiary(LocalDate date, String text) {
		DateWeatherDao dateWeatherDao = getDateWeather(date);
		
		return DiaryDto.fromEntity(diaryRepository.save(DiaryDao.builder()
				.weather(dateWeatherDao.getWeather())
				.icon(dateWeatherDao.getIcon())
				.temperature(dateWeatherDao.getTemperature())
				.text(text)
				.date(date)
				.build()));
	}

	private DateWeatherDao getDateWeather(LocalDate date) {
		DateWeatherDao dateWeatherDao;
		List<DateWeatherDao> dateWeathers = dateWeatherRepository.findAllByDate(date);
		if (dateWeathers.size() > 0) {
			dateWeatherDao = dateWeathers.get(0);
		} else {
			dateWeatherDao = OpenWeather.getWeatherDateFromApi(region, apiKey);
		}

		return dateWeatherDao;
	}

	@Transactional
	public DiaryDto modifyDiary(LocalDate date, String text) {
		DiaryDao diary = diaryRepository.findFirstByDate(date);
		diary.setText(text);
		return DiaryDto.fromEntity(diaryRepository.save(diary));
	}

	@Transactional
	public Integer deleteDiary(LocalDate date) {
		return diaryRepository.deleteAllByDate(date);
	}
	
	@Transactional
	@Scheduled(cron = "0 0 1 * * *")
	public void saveWeatherDataPerDay() {
		dateWeatherRepository.save(OpenWeather.getWeatherDateFromApi(region, apiKey));
	}
}
