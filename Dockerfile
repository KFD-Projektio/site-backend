FROM gradle:8.11-jdk21 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:21-ea-21-jdk-slim

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/ /app/

ENTRYPOINT ["java","-jar","/app/backend-0.0.1-SNAPSHOT.jar"]