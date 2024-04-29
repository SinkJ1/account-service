FROM azul/zulu-openjdk:19

WORKDIR /app

COPY gradlew .
COPY gradle ./gradle

COPY build.gradle .
COPY settings.gradle .

COPY src ./src

RUN ./gradlew assemble