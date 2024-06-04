FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml .

COPY src ./src

COPY mvnw .

COPY .mvn .mvn

RUN chmod +x mvnw

RUN ./mvnw package

CMD ["java", "-jar", "target\DemoMVCKAO-0.0.1-SNAPSHOT.jar"]