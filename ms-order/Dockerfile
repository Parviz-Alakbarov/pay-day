FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=ms-order/build/libs/ms-order-*.jar
COPY ${JAR_FILE} ms-order.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-order.jar"]