FROM openjdk:11
EXPOSE 8080
ADD target/week-ten-task.jar week-ten-task.jar
ENTRYPOINT ["java", "-jar", "/week-ten-task.jar"]