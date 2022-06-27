FROM openjdk:11-jre-slim-buster
VOLUME /tmp
ARG JAR_FILE=./build/libs/ms-order-*.jar
COPY ${JAR_FILE} ms-order.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-order.jar"]