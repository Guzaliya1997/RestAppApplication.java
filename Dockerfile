FROM openjdk:17-alpine
ADD /target/rest.jar rest_app.jar
ENTRYPOINT ["java", "-jar", "/rest_app.jar"]