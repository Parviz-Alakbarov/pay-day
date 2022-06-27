FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=ms-user/build/libs/ms-user-*.jar
COPY ${JAR_FILE} ms-user.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-user.jar"]