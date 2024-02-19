package com.thomasvitale.langchain4j;

import io.thomasvitale.langchain4j.testcontainers.service.containers.OllamaContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestApplication {

    @Bean
    @ServiceConnection
    OllamaContainer ollama() {
        return new OllamaContainer("ghcr.io/thomasvitale/ollama-llama2");
    }

    @Bean
    @ServiceConnection(name = "chroma")
    GenericContainer<?> chroma() {
        return new GenericContainer<>("ghcr.io/chroma-core/chroma:0.4.22")
                .withExposedPorts(8000);
    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main).with(TestApplication.class).run(args);
    }

}
