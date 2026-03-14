#stage build
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# copiamos los archivos de gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

#copiamos el codigo fuente
COPY src src

#damos permisos de ejecuacion para el binario
RUN chmod +x gradlew

# compilamos el proyecto
RUN ./gradlew build -x test

#runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# copiar el jar generado
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
