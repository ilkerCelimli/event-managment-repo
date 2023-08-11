FROM amazoncorretto:20-alpine

COPY target/*.jar app.jar

ENTRYPOINT ["java", "-jar","/app.jar"]

EXPOSE 8761