FROM maven:3.9.6-eclipse-temurin-21 AS build

COPY pom.xml ./

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:21

WORKDIR /app

COPY --from=build target/*.war .

EXPOSE 8080

CMD ["java", "-jar", "file-similarity-0.0.1-SNAPSHOT.jar"]
