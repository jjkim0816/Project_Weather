i-- 권한
USE weather;

GRANT ALL PRIVILEGES ON weather.* to 'weather'@'localhost' IDENTIFIED BY 'weather1234';
GRANT ALL PRIVILEGES ON weather.* TO 'weather'@'%' IDENTIFIED BY 'weather1234';

-- 테이블 생성
CREATE TABLE diary (
	id 				INT 		 AUTO_INCREMENT COMMENT '순번',
	weather  		VARCHAR(50)  NOT NULL 		COMMENT '날씨 상태',
	icon 			VARCHAR(10)  NOT NULL 		COMMENT 'weather icon id',
	temperature 	DOUBLE 		 NOT NULL		COMMENT '온도',
	text 			VARCHAR(500) NOT NULL		COMMENT '일기 내용',
	date 			DATETIME(6)  NOT NULL		COMMENT '일기 작성 날짜',
	PRIMARY KEY (id)
);

CREATE TABLE date_weather (
	date 			DATETIME(6)				COMMENT '날씨 수집 날짜',
	weather  		VARCHAR(50) NOT NULL 	COMMENT '날씨 상태',
	icon 			VARCHAR(10) NOT NULL 	COMMENT 'weather icon id',
	temperature 	DOUBLE 					COMMENT '온도',
	PRIMARY KEY (date)
);