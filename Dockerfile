FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /home/app

COPY . /home/app/ProjetoAuth

RUN cd ProjetoAuth && ./gradlew clean build

FROM eclipse-temurin:21-jre-alpine

WORKDIR /home/app

EXPOSE 8080

COPY --from=builder /home/app/ProjetoAuth/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
