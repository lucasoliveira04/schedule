# Estágio de build
FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build ./target/agenda_api-1.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
