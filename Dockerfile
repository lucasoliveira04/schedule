FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim


COPY --from=build /target/agenda_api-1.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]