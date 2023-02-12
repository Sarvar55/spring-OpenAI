FROM openjdk:14-jdk-alpine

WORKDIR /app

COPY target/chatGPT-0.0.1-SNAPSHOT.jar /app/chatGPT-0.0.1-SNAPSHOT.jar


CMD ["java", "-jar", "chatGPT-0.0.1-SNAPSHOT.jar"]