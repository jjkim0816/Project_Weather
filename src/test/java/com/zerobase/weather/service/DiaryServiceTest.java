package com.zerobase.weather.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zerobase.weather.domain.Diary;
import com.zerobase.weather.repository.DiaryRepository;
import com.zerobase.weather.utils.OpenWeather;

@ExtendWith(MockitoExtension.class)
@DisplayName("날씨 일기 서비스 테스트")
class DiaryServiceTest {
	@Mock
	private DiaryRepository diaryRepository;
	
	@Mock
	private OpenWeather openWeather;
	
	@InjectMocks
	private DiaryService diaryService;
	
	@Test
	@DisplayName("날씨 일기 - 일기 생성 성공")
	void weatherDiary_success() {
		// given
		Diary diary = Diary.builder()
				.weather("Clouds")
				.icon("04n")
				.temperature(289.16)
				.text("first Diary")
				.date(LocalDate.now())
				.build();
		

		// when
		Diary result = diaryRepository.save(diary);

		// then
		assertNull(result);
	}
}
