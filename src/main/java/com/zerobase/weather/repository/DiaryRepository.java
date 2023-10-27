package com.zerobase.weather.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerobase.weather.domain.DiaryDao;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryDao, Long>{
	List<DiaryDao> findAllByDate(LocalDate date);
	
	DiaryDao findFirstByDate(LocalDate date);

	List<DiaryDao> findAllByDateBetween(LocalDate startDate, LocalDate endDate);

	Integer deleteAllByDate(LocalDate date);
}
