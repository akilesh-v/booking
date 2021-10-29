#!/usr/bin/env bash
# Database Setup
set -e
echo "Dropping existing databases.."
dropdb -U postgres -h localhost --if-exists booking
echo "Creating base databases"
createdb -U postgres -h localhost  booking

#Build Project
./gradlew clean build

#Start the service
java -Dspring.config.location=src/main/resources/application.yml  -jar build/libs/room-0.0.1-SNAPSHOT.jar