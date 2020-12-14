FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Jorge Sierra "jsierra93@hotmail.com"
VOLUME  /tmp
ARG JAR_FILE=./build/libs/webflux-sqs-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]