package com.zerobase.weather.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.zerobase.weather.dto.DiaryDto;
import com.zerobase.weather.service.DiaryService;

@WebMvcTest(DiaryController.class)
@DisplayName("날씨 일기 관련 컨트롤러 테스트")
class DiaryControllerTest {
	@MockBean
	private DiaryService diaryService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@DisplayName("날씨 일기 저장하기")
	void successCreateDiary() throws Exception {
		// given
		given(diaryService.createDiary(any(), anyString()))
			.willReturn(DiaryDto.builder()
					.weather("Clouds")
					.icon("04d")
					.temperature(295.78)
					.date(LocalDate.now())
					.text("first diary")
					.build())
		;
		// when
		// then
		mockMvc.perform(post("/create/diary?date=" + LocalDate.now())
				.contentType(MediaType.APPLICATION_JSON)
				.content("first Diary"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("해당 날짜 날씨 일기 목록 조회")
	void successGetWeatherDiaryByDate() throws Exception {
		// given
		given(diaryService.findDiary(any()))
			.willReturn(Arrays.asList(
				DiaryDto.builder()
					.weather("Clear")
					.icon("01d")
					.temperature(293.52)
					.text("I want to learn spring boot")
					.build(),
				DiaryDto.builder()
					.weather("Clouds")
					.icon("04d")
					.temperature(291.61)
					.text("I want to learn spring boot12313123123123")
					.build()
		))
		;
		// when
		// then
		mockMvc.perform(get("/read/diary?date=" + LocalDate.now()))
		.andDo(print())
		.andExpect(jsonPath("$[0].weather").value("Clear"))
		.andExpect(jsonPath("$[0].icon").value("01d"))
		.andExpect(jsonPath("$[0].temperature").value(293.52))
		;
	}
}
