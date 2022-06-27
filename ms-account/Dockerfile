FROM openjdk:11-jre-slim-buster
VOLUME /tmp
ARG JAR_FILE=./build/libs/ms-account-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ms-account.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-account.jar"]