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
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Update the repository sources to use the Debian archive
RUN sed -i 's/deb.debian.org/archive.debian.org/g' /etc/apt/sources.list && \
    echo "deb http://archive.debian.org/debian stretch main" > /etc/apt/sources.list && \
    echo "Acquire::Check-Valid-Until \"false\";" > /etc/apt/apt.conf.d/99no-check-valid-until && \
    apt-get update && apt-get install -y curl && apt-get clean

# Copy the custom Java runtime
COPY --from=jre-build /javaruntime $JAVA_HOME

COPY target/*.jar app.jar
COPY src/main/resources/config/* config/
CMD ["java", "-jar", "app.jar"]
