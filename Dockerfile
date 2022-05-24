FROM openjdk:18

COPY ./build/libs/ec-user-api-0.0.1-SNAPSHOT.jar ec-user-api-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "ec-user-api-0.0.1-SNAPSHOT.jar"]