package com.example.demoappfinal.config;

import dev.cerbos.sdk.CerbosBlockingClient;
import dev.cerbos.sdk.CerbosClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CerbosConfig {

  @Value("${cerbos.host}")
  private String cerbosHost;

  @Bean
  public CerbosBlockingClient cerbosBlockingClient() {
    try {
      return new CerbosClientBuilder(cerbosHost)
          .withPlaintext()
          .buildBlockingClient();
    } catch (CerbosClientBuilder.InvalidClientConfigurationException e) {
      e.printStackTrace();
      return null;
    }
  }
}
