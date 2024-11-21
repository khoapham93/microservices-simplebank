## Stage 1: Build the application
#FROM openjdk:17-jdk-slim AS build
#
## Set the working directory
#WORKDIR /app
#
## Copy the pom.xml and download dependencies
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
## Copy the source code and build the application
#COPY src/ ./src/
#RUN mvn package -DskipTests
#
## Stage 2: Create the final image with JRE only
#FROM openjdk:17-jdk-slim
#
## Information around who maintains the image
#LABEL maintainer="khoapham.io"
#
## Copy the JAR file from the build stage
#COPY --from=build /app/target/accounts-0.0.1-SNAPSHOT.jar app.jar
#
## Execute the application
#ENTRYPOINT ["java", "-jar", "app.jar"]




## Stage 1: Build the application
#FROM maven:3.8.8-eclipse-temurin-17 AS build
#
## Set the working directory
#WORKDIR /app
#
## Copy the pom.xml and download dependencies
#COPY pom.xml .
#RUN mvn dependency:go-offline
#
## Copy the source code and build the application
#COPY src/ ./src/
#RUN mvn package -DskipTests
#
## Stage 2: Create the final image with JRE only
#FROM eclipse-temurin:17-jre
#
## Information around who maintains the image
#LABEL maintainer="khoapham.io"
#
## Copy the JAR file from the build stage
#COPY --from=build /app/target/accounts-0.0.1-SNAPSHOT.jar app.jar
#
## Execute the application
#ENTRYPOINT ["java", "-jar", "app.jar"]


# Custom Java runtime using jlink in a multi-stage container build
FROM eclipse-temurin:17 as jre-build

# Create a custom Java runtime
RUN $JAVA_HOME/bin/jlink \
         --add-modules jdk.unsupported,java.base,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

# Define your base image
FROM debian:stretch-slim
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"
COPY --from=jre-build /javaruntime $JAVA_HOME


COPY target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]

