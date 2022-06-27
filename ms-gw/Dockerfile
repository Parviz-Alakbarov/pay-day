FROM openjdk:11-jre-slim-buster
VOLUME /tmp
ARG JAR_FILE=./build/libs/ms-gw-*.jar
COPY ${JAR_FILE} ms-gw.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-gw.jar"]