package com.zerobase.weather.utils;

import com.zerobase.weather.domain.DateWeatherDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OpenWeather {
	public static DateWeatherDao getWeatherDateFromApi(String region, String apiKey) {
		String  weatherData = getWeatherString(region, apiKey);
		
		Map<String, Object> parseWeather = parseJsonWeather(weatherData);
		
		return DateWeatherDao.builder()
				.date(LocalDate.now())
				.weather(Objects.requireNonNull(parseWeather).get("main").toString())
				.icon(parseWeather.get("icon").toString())
				.temperature(Double.parseDouble(parseWeather.get("temp").toString()))
				.build();
	}
	
	/**
	 * Open Weather API를 이용한 데이터 수집 
	 */
	private static String getWeatherString(String region, String apiKey) {
		String apiUrl =  "https://api.openweathermap.org/data/2.5/weather?q=" + region + "&appid=" + apiKey; 
		
		try {
			URL url = new URL(apiUrl);
			
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod(RequestMethod.GET.toString());
			
			int responseCode = connection.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}
			
			String inputLine;
			StringBuilder sb = new StringBuilder();
			while((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();
			
			return sb.toString();
		} catch(Exception e) {
			return "failt to get response";
		}
	}
	
	/**
	 * Open Weather 데이터 jsonParsing 처리
	 */
	private static Map<String, Object> parseJsonWeather(String jsonString) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject;
		
		try {
			jsonObject = (JSONObject) jsonParser.parse(jsonString);
			
			Map<String, Object> resultMap = new HashMap<>();
			
			JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
			JSONObject weatherData = (JSONObject) weatherArray.get(0);
			resultMap.put("main", weatherData.get("main"));
			resultMap.put("icon", weatherData.get("icon"));
			
			JSONObject mainData = (JSONObject) jsonObject.get("main");
			resultMap.put("temp", mainData.get("temp"));

			return resultMap;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
