package com.zerobase.weather.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zerobase.weather.dto.GetDiary;
import com.zerobase.weather.service.DiaryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DiaryController {
	private final DiaryService diaryService;
	
	@ApiOperation(value = "나의 날씨 일기를 저장", notes = "해당 날짜의 날씨 일기 데이터를 저장할 수 있다.")
	@PostMapping("/create/diary")
	public void createDiary(
		@RequestParam @DateTimeFormat(iso = ISO.DATE)
		@ApiParam(value = "yyyy-MM-dd", example = "2023-10-29")
			LocalDate date,
		@RequestBody String text
	) {
		diaryService.createDiary(date, text);
	}
	
	@ApiOperation(value = "나의 일기를 조회", notes = "해당 날짜의 날씨 일기 목록을 조회 할 수 있다.")
	@GetMapping("/read/diary")
	public List<GetDiary> readDiary(
		@RequestParam @DateTimeFormat(iso = ISO.DATE)
		@ApiParam(value = "yyyy-MM-dd", example = "2023-10-29")
			LocalDate date
	) {
		return diaryService.findDiary(date)
				.stream()
				.map(diaryDto -> GetDiary.builder()
						.id(diaryDto.getId())
						.weather(diaryDto.getWeather())
						.icon(diaryDto.getIcon())
						.temperature(diaryDto.getTemperature())
						.text(diaryDto.getText())
						.date(diaryDto.getDate())
						.build())
				.collect(Collectors.toList());
	}
	
	@ApiOperation(value = "기간별 나의 일기 조회", notes = "설정한 기간의 날씨 일기 목록을 조회 할 수 있다.")
	@GetMapping("/read/diaries")
	public List<GetDiary> readDiaries(
		@RequestParam @DateTimeFormat(iso = ISO.DATE)
		@ApiParam(value = "시작날짜 yyyy-MM-dd", example = "2023-10-29")
			LocalDate startDate,
		@ApiParam(value = "종료날짜 yyyy-MM-dd", example = "2023-10-31")
		@RequestParam @DateTimeFormat(iso = ISO.DATE)
			LocalDate endDate
	) {
		return diaryService.findDiaries(startDate, endDate)
				.stream()
				.map(diaryDto -> GetDiary.builder()
						.id(diaryDto.getId())
						.weather(diaryDto.getWeather())
						.icon(diaryDto.getIcon())
						.temperature(diaryDto.getTemperature())
						.text(diaryDto.getText())
						.date(diaryDto.getDate())
						.build())
				.collect(Collectors.toList());
	}
	
	@ApiOperation(value = "나의 일기 갱신", notes = "해당 날짜의 날씨 일기 내용을 수정 할 수 있다.")
	@PutMapping("/update/diary")
	public void updateDiary(
		@RequestParam @DateTimeFormat(iso = ISO.DATE)
		@ApiParam(value = "yyyy-MM-dd", example = "2023-10-29")
			LocalDate date,
		@RequestBody String text
	) {
		diaryService.modifyDiary(date, text);
	}
	
	@ApiOperation(value = "나의 일기 삭제하기", notes = "해당 날짜의 날씨 일기를 삭제할 수 있다.")
	@DeleteMapping("/delete/diary")
	public void deleteDiary(
		@RequestParam @DateTimeFormat(iso = ISO.DATE)
		@ApiParam(value = "yyyy-MM-dd", example = "2023-10-29")
			LocalDate date
	) {
		diaryService.deleteDiary(date);
	}
}
