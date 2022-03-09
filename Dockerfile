FROM openjdk:11.0.4-jre-slim
COPY build/libs/*.jar app.jar
ARG JAR_FILE=basic-crud-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]