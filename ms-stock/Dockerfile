FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE=ms-stock/build/libs/ms-stock-*.jar
COPY ${JAR_FILE} ms-stock.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/ms-stock.jar"]