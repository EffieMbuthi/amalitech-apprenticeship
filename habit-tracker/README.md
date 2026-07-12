# Habit Tracker API

A Spring Boot REST API for tracking daily habits, marking them complete, and tracking completion streaks.

## Endpoints
- POST /habits - create a new habit
- GET /habits - list all habits
- POST /habits/{id}/complete - mark a habit complete for today

## Running locally
./mvnw spring-boot:run

## Running tests
./mvnw test