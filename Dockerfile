FROM openjdk:latest

LABEL authors="anton"

ARG APP_JAR=docker/*.jar

COPY ${APP_JAR} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
