FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=ms-gw/build/libs/ms-gw-*.jar
COPY ${JAR_FILE} ms-gw.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-gw.jar"]