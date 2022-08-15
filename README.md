<h2 align="center">CJ MATE</h2>

<p align="center">
  <img src="https://github.com/CJ-SECOND/CJ_APP/blob/master/img/CJ_APP.png" alt="CJ Mate's logo" width="400px" height="400px"/>
</p>


**프로젝트 주제** 크라우드 소싱 모바일 배송 어플리케이션 구현 (주문 받은 고객 상품을 일반인이 배송할 수 있도록 지원해주는 서비스 플랫폼 개발)

## 개발환경 :pushpin:

|  backend                                            | short reads   | long reads   |
| :-------------------------------------------------- | :------------ | :----------- |
| [Java SE Development Kit (JDK) 11](https://www.oracle.com/java/technologies/downloads/#java11)  | required      | required     |
| [Apaceh Tomcat 9.0.64](https://tomcat.apache.org/download-90.cgi) | required      | required     |
| [MySQL 5.7.37](https://github.com/bcgsc/ntCard]())  | required      | required     |


* JDK 11 버전 
* tomcat 버전 8,9 중 선택 사용 가능
* mysql 5.x 버전 

|  frontend                                            | short reads   | long reads   |
| :-------------------------------------------------- | :------------ | :----------- |
| [Java SE Development Kit (JDK) 11](https://www.oracle.com/java/technologies/downloads/#java11)  | required      | required     |
| [Android Studio Dolphin(2021.3.1)](https://developer.android.com/studio/preview) | required      | required     |


## 개발 환경 세팅 :running:

:warning: backend(Spring), frontend(Android Studio), DB(MySQL) 수정 사항 리스트 

### (1) Backend(Spring):

* 1-1. maven build
  * maven clean -> maven install
  * maven install Failure 발생시 pom.xml dependency version 3.1.0 으로 수정 필요
 
 ```xml
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
	<version>4.0.0</version>
</dependency>
```

* 1-2. tomcat server 연결
  * server.xml Context-path -> "/app" -> "" 로 수정 필요
```xml 
<Host appBase="webapps" autoDeploy="true" name="localhost" unpackWARs="true">
	<Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs" pattern="%h %l %u %t &quot;%r&quot; %s %b" prefix="localhost_access_log" suffix=".txt"/>

	<Context docBase="spring_mybatis_config" path="" reloadable="true" source="org.eclipse.jst.jee.server:spring_mybatis_config"/>
</Host>
```

* 1-3. DB Connection 정보 수정
  * src/main/java/backend/app/business/config/MvcConfig.java 파일 내
 
```java 
HikariConfig hikariConfig = new HikariConfig();
 		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
 		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:[Mysql Port]/[MySQL DB NAME]?serverTimezone=UTC&useSSL=false");
 		hikariConfig.setUsername([MySQL Username]);
 		hikariConfig.setPassword([MySQL Password]);
```

### (2) Frontend(Android Studio):

* 2-1. ipconfig -> wi-fi 무선 LAN IPv4 주소 설정
  * 안드로이드 기기로 앱 접속시 DeskTop or 노트북의 접속시 동일 wifi로 접속 (필수 사항)
  * cmd -> "ipconfig" 명령어 입력 -> "IPv4 주소 . . . . . . . . . : [ip주소]" Android Studio의 모든 프로젝트의 baseUrl을 수정 필요(Android studio - "Ctrl + Shift + R" 키로 한번에 변경 가능) 

```java
Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("http://[IPv4 주소]:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
```

### (3) DB(MySQL):

* 3-1. DB 생성

```sql
CREATE DATABASE cjapp;
```

* 3-2. DB Table 생성 
 
```shell
mysql -u [username] -p cjapp < CJ_APP.SQL
```

* 3-3. DB Dump file insert (배송 관련 데이터 insert)
  * /DATASET 폴더 내에 있는 파일 "db_dump_insert.ipynb"파일 실행 필요
  * jupyter notebook 실행후 "db_dump_insert.ipynb" 실행시 자동 db dump data 생성 됨.(dump data insert 전 db 및 table 생성 선행 필요)

