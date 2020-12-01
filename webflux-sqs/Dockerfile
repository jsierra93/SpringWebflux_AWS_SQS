FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Jorge Sierra "jsierra93@hotmail.com"
VOLUME  /tmp
EXPOSE 8084
ARG JAR_FILE=./build/libs/webflux-sqs-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]