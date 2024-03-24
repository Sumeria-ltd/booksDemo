# Step 1: Use the official Maven image with a tag specifying the JDK version you need
FROM maven:3.8.4-openjdk-17 as build

# Step 2: Set the working directory in the Docker image
WORKDIR /app

# Step 3: Copy the Maven configuration file into the image
COPY pom.xml .

# Step 4: Copy the source code of the application into the image
COPY src ./src

# Step 5: Build the application using Maven
# This will package the application and create an executable JAR file
RUN mvn clean package -DskipTests

# Step 6: Use the official OpenJDK image to create a lightweight image of just the runtime
FROM openjdk:17

# Step 7: Set the working directory in the Docker image
WORKDIR /app

# Step 8: Copy the JAR file from the build stage into the runtime image
COPY --from=build /app/target/*.jar app.jar

# Step 9: Expose the port the application runs on
EXPOSE 8080

# Step 10: Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
