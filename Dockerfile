# Estágio de build
FROM ubuntu:latest AS build

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY . .

RUN mvn clean install

# Estágio de produção
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /target/agenda_api-1.0-SNAPSHOT.jar app.jar

# Corrigido: ENTRYPOINT para iniciar o JAR corretamente
ENTRYPOINT ["java", "-jar", "app.jar"]
