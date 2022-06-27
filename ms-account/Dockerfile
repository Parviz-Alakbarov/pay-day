FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=ms-account/build/libs/ms-account-*.jar
COPY ${JAR_FILE} ms-account.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-account.jar"]