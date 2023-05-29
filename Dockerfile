FROM debian:bullseye-slim

ENV SPRING_PROFILES_ACTIVE=prod
ARG SPRING_PROFILES_ACTIVE

WORKDIR /app

RUN apt-get update && apt-get install -y openjdk-17-jdk maven

COPY . .

RUN mvn package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/cashflower-0.0.1-SNAPSHOT.jar"]