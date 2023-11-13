package ru.yolkin.autoparts.config;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CoreFeignConfiguration {

  @Bean
  Encoder formEncoder() {
    return new FormEncoder();
  }
}
