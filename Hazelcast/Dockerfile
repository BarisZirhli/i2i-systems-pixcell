# Use an official Maven image as the base image
FROM maven:3.6.0-jdk-17 as builder

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Copy the source code
COPY src/main/java/com/i2i/intern/pixcell/hazelcastoperation /app/src/main/java/com/i2i/intern/pixcell/hazelcastoperation

# Build the project
RUN mvn clean package

# Use an official OpenJDK 11 image as the base image
FROM openjdk:17

# Copy the JAR file from the builder stage
COPY ./target/HazelcastOperation-1.0.jar /HazelcastOperation.jar

# Set the default command to run the JAR file
CMD ["java", "-jar", "HazelcastOperation.jar"]