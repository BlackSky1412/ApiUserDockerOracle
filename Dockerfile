FROM maven:latest

# Install git
RUN apt-get update && apt-get install -y git

# Create and set the working directory
RUN mkdir /app
WORKDIR /app

# Copy the application source code to the container
COPY . .

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application using Maven
CMD ["mvn", "spring-boot:run"]
