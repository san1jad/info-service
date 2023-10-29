FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} info-service.jar
ENTRYPOINT ["java", "-jar", "/info-service.jar"]
EXPOSE 9090