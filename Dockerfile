FROM eclipse-temurin:17-jdk-jammy

ENV TZ="Europe/Moscow"
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src
COPY src/main/resources/application-ap.yaml ./src/main/resources/application.yaml

CMD ["./mvnw", "spring-boot:run"]