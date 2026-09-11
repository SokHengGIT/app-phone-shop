# Use lightweight Java image
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy war file
COPY target/*.war app.war

# Run app
ENTRYPOINT ["java", "-jar", "app.war"]