FROM openjdk:11-jre-slim-buster
VOLUME /tmp
ARG JAR_FILE=./build/libs/ms-user-*.jar
COPY ${JAR_FILE} ms-user.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-user.jar"]