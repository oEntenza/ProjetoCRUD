FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY pom.xml .

COPY src ./src

COPY mvnw .

COPY .mvn .mvn

RUN chmod +x mvnw

RUN ./mvnw package -DskipTests

CMD ["java", "-jar", "target/DemoMVCKAO-0.0.1-SNAPSHOT.jar"]
