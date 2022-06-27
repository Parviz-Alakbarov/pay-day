FROM openjdk:11-jre-slim-buster
VOLUME /tmp
ARG JAR_FILE=./build/libs/ms-notification-*.jar
COPY ${JAR_FILE} ms-notification.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-notification.jar"]