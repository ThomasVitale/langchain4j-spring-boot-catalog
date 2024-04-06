package com.thomasvitale.langchain4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.weaviate.WeaviateContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestApplication {

    @Bean
    @ServiceConnection
    OllamaContainer ollama() {
        return new OllamaContainer(DockerImageName.parse("ghcr.io/thomasvitale/ollama-llama2")
                .asCompatibleSubstituteFor("ollama/ollama"));
    }

    @Bean
    @ServiceConnection
    WeaviateContainer weaviate() {
        return new WeaviateContainer("semitechnologies/weaviate:1.24.7");
    }

    public static void main(String[] args) {
        SpringApplication.from(Application::main).with(TestApplication.class).run(args);
    }

}
