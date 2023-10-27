# Project_Weather
실전 날씨 일기 프로젝트

# 기술 스택
- Java 11
- Spring Boot v2.7.17
- MariaDB 11.1.2
- JPA
- logBack
- lombok
- validate
- Swagger
- Junit5

# 개발 내용
### 목적
- 날씨 일기를 작성/조회/수정/삭제 하는 백엔드를 구현한다.

### 필수 요건
- [X] 테스트 코드 작성
- [X] 외부 오픈 API 활용
- [X] JPA 방식으로 MySQL 사용하기
- [X] DB 와 관련된 함수는 트랜잭션 처리
- [X] 매일 새벽 1시에 날씨 데이터를 외부 API 받아서 DB에 저장
- [ ] logBack을 이용하여 로그 남기기
- [ ] ExceptionHandler 를 이용하여 예외처리
- [ ] Swagger 를 이용하여 API documentation

### 구현 API
- [X] POST /create/diary
  - param : date 를 LocalDate(yyyy-MM-dd)
  - body : text
  - 외부 API 에서 받아온 날씨 데이터와 함께 DB에 저장
  
- [X] GET /read/diary
  - param : date 를 LocalDate(yyyy-MM-dd)
  - response : 해당 날짜의 일기 List 형식으로 반환
  
- [X] GET /read/diaries
  - param: startDate, endDate 를 LocalDate (yyyy-MM-dd)
  - response : 해당 기간의 일기를 List 형식으로 반환
  
- [X] PUT /update/diary
  - param : date 를 LocalDate(yyyy-MM-dd)
  - body : text
  - 해당 날짜의 첫번째 일기 글을 새로 받아온 일기글로 수정

- [X] DELETE
  - param : date 를 LocalDate(yyyy-MM-dd)
  - 해당 날짜의 일기를 모두 지워 삭제 
