# Simple RAG: Ollama and Weavite

Simple RAG with LLMs via Ollama and Weaviate vector store.

## Running the application

The application relies on Ollama for providing LLMs. You can run the native Ollama app locally on your laptop,
or rely on the Docker Compose/Testcontainers support in Spring Boot to spin up an Ollama service automatically
at startup time.

### When using Ollama as a dev service with Testcontainers

The application relies on the native Testcontainers support in Spring Boot to spin up an Ollama service
with the _llama3_ model at startup time.

```shell
./mvnw spring-boot:test-run
```

Notice that the first time you run this command, it will take a while to download the Ollama container image.

### When using Ollama as a dev service with Docker Compose

The application can optionally rely on the native Docker Compose support in Spring Boot to spin up
an Ollama service with the _llama3_ model at startup time. To enable that, uncomment this dependency
in the `pom.xml` file.

```xml
<dependency>
    <groupId>io.thomasvitale.langchain4j</groupId>
    <artifactId>langchain4j-spring-boot-docker-compose</artifactId>
    <version>${langchain4j.spring.version}</version>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

Then, run the Spring Boot application.

```shell
./mvnw spring-boot:run
```

Notice that the first time you run this command, it will take a while to download the Ollama container image.

### When using Ollama as a native application

First, make sure you have [Ollama](https://ollama.ai) installed on your laptop.
Then, use Ollama to run the _llama3_ large language model.

```shell
ollama run llama3
```

Finally, run the Spring Boot application.

```shell
./mvnw spring-boot:run
```

## Calling the application

You can now call the application that will use Ollama and _llama3_ to generate a vector representation (embeddings)
of your question, perform a similarity search on Weaviate, and then answer based on the retrieved contextual information.
This example uses [httpie](https://httpie.io) to send HTTP requests.

```shell
http --raw "Who is Mocha Mike?" :8080/chat
```

Try passing another prompt and check the result. The agent has memory of your previous conversations.

```shell
http --raw "What have I just asked?" :8080/chat
```
