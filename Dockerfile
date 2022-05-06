FROM openjdk:11
VOLUME /main-app
ADD target/demo-1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app.jar"]