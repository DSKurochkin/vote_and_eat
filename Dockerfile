FROM openjdk:15
ADD /target/v4e.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]