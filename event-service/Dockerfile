#
# Build stage
#
FROM maven:3-amazoncorretto-20 AS build
COPY src /home/app/
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM amazoncorretto:20-alpine
COPY --from=build /home/app/target/*.jar /usr/local/lib/event-service.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","/usr/local/lib/event-service.jar"]
