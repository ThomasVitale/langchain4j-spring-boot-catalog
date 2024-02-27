package com.thomasvitale.langchain4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.chromadb.ChromaDBContainer;

import io.thomasvitale.langchain4j.testcontainers.service.containers.OllamaContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestApplication {

    @Bean
    @ServiceConnection
    OllamaContainer ollama() {
        return new OllamaContainer("ghcr.io/thomasvitale/ollama-llama2");
    }

    @Bean
    @ServiceConnection
    ChromaDBContainer chroma() {
        return new ChromaDBContainer("ghcr.io/chroma-core/chroma:0.4.22");
    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main).with(TestApplication.class).run(args);
    }

}
