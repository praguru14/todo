FROM maven:latest AS builder

WORKDIR /app

# Copy the pom.xml file first to cache dependencies
COPY pom.xml .

# Copy the entire project into the container
COPY src .

# Build the project
RUN mvn package

FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/todo-0.0.1-SNAPSHOT.jar /app/todo.jar

CMD ["java", "-jar", "todo.jar"]
