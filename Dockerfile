# 1️⃣ Build stage: compile project using Maven & JDK 17
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src
# Build JAR file
RUN if [ -f mvnw ]; then chmod +x mvnw && ./mvnw -B clean package -DskipTests; else mvn -B clean package -DskipTests; fi

# 2️⃣ Runtime stage: run only the JAR using lightweight JRE
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
