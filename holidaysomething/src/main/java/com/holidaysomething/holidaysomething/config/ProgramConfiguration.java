package com.holidaysomething.holidaysomething.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
@EnableWebMvc
public class ProgramConfiguration {

  /**
   * Thymeleaf 에서 LocalDateTime 포맷 지정해주기 위해 필요한 config 파일. Temporal 쓰기 위해 필요한것?
   * 참조 : https://www.baeldung.com/spring-dataIntegrityviolationexception
   */
  @Bean
  public Java8TimeDialect java8TimeDialect() {
    return new Java8TimeDialect();
  }
}