package com.zerobase.weather.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zerobase.weather.domain.DateWeatherDao;

@Repository
public interface DateWeatherRepository extends JpaRepository<DateWeatherDao, LocalDate>{

	List<DateWeatherDao> findAllByDate(LocalDate date);

}
