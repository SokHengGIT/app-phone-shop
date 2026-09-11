mvn clean package -DskipTests
docker build -t sv4.14-app-phone-shop .
docker compose up -d --build