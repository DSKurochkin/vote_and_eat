FROM openjdk:15
ADD v4e.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]