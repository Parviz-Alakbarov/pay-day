FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=ms-notification/build/libs/ms-notification-*.jar
COPY ${JAR_FILE} ms-notification.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-notification.jar"]