1. 개발환경
   ㄱ.  sts-4.17.2.RELEASE
   ㄴ.  jdk-11.0.17
   ㄷ.  MySQL80
    ㄹ. JPA
   ㅁ. Thymeleaf 

2. Spring Boot Version : 2.7.8 ( Spring Framework 5.x )

3. Project Dependencies
    ㄱ. Spring Boot DevTools
    ㄴ. Spring Data JPA
    ㄷ. Spring Web
    ㄹ. Lombok 
    ㅁ. MySQL Driver 
    ㅂ. Thymeleaf

4. https://www.w3schools.com 의 BS5 사용
    ㄱ. boards.html - https://www.w3schools.com/bootstrap5/bootstrap_templates.php
    ㄴ. list.html        - https://www.w3schools.com/bootstrap5/bootstrap_tables.php
                               - https://www.w3schools.com/bootstrap5/bootstrap_pagination.php 
 

11. application.properties

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/gugucoding?useSSL=false
spring.datasource.username=scott
spring.datasource.password=tiger
spring.session.store-type=none

spring.jpa.open-in-view=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.database=mysql
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

logging.level.org.hibernate=info

spring.thymeleaf.cache=false

logging.level.org.springframework.web=info
logging.level.org.zerock=info

server.port: 80



12. pom.xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.7.8</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>org.zerock</groupId>
	<artifactId>boot06</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>boot06</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<!-- https://mvnrepository.com/artifact/nz.net.ultraq.thymeleaf/thymeleaf-layout-dialect -->
		<dependency>
		    <groupId>nz.net.ultraq.thymeleaf</groupId>
		    <artifactId>thymeleaf-layout-dialect</artifactId>
		</dependency>
		
		<!--p 265-->
		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-apt</artifactId>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>com.querydsl</groupId>
			<artifactId>querydsl-jpa</artifactId>
		</dependency>



	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
			
			<!--p 265 Qdomain 생성하는 코드 생성 플러그인-->
			<plugin>
				<groupId>com.mysema.maven</groupId>
				<artifactId>apt-maven-plugin</artifactId>
				<version>1.1.3</version>
				<executions>
					<execution>
						<goals>
							<goal>process</goal>
						</goals>
						<configuration>
							<outputDirectory>target/generated-sources/java</outputDirectory>
							<processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

</project>

