# Use eclipse-temurin:21-jdk-alpine for Java 21
FROM eclipse-temurin:21-jdk-alpine  

VOLUME /tmp

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
