# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Make port 8080 available to the world outside this container
EXPOSE 8080
EXPOSE 3306

# Run spring-boot application when the container launches
CMD java -jar target/\vending-machine-0.0.1-SNAPSHOT.jar