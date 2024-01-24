FROM maven as builder

WORKDIR /app

COPY . .

RUN mvn clean package

FROM openjdk:23-slim-bullseye

RUN apt-get update && apt-get install -y procps

WORKDIR /app

COPY --from=builder /app/target/amq-tester-*.jar /app

CMD ["java", "-jar", "amq-tester-1.0-SNAPSHOT.jar"]