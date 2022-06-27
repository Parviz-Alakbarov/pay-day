FROM openjdk:11-jre-slim-buster
VOLUME /tmp
ARG JAR_FILE=./build/libs/ms-stock-*.jar
COPY ${JAR_FILE} ms-stock.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-stock.jar"]