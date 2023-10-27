package com.zerobase.weather.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerobase.weather.domain.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long>{
	List<Diary> findAllByDate(LocalDate date);
	
	Diary findFirstByDate(LocalDate date);

	List<Diary> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
