# Habit Tracker API

A Spring Boot REST API for tracking daily habits, marking them complete, viewing streaks, and removing habits you no longer track. Built across two simulated Agile sprints as part of the AmaliTech DEG apprenticeship Agile & DevOps assessment.

## Endpoints
- POST /habits - create a new habit
- GET /habits - list all habits
- POST /habits/{id}/complete - mark a habit complete for today
- GET /habits/{id}/streak - view a habit's current streak
- DELETE /habits/{id} - delete a habit
- GET /actuator/health - health check

## Running locally
./mvnw spring-boot:run

## Running tests
./mvnw test

## Project Documentation
All Agile and DevOps process documentation lives in `docs/`:
- [Sprint 0: Planning](./docs/sprint0-planning.md) - product vision, backlog, acceptance criteria, Definition of Done
- [Sprint 1: Review](./docs/sprint1-review.md) - stories delivered, evidence, testing
- [Sprint 1: Retrospective](./docs/sprint1-retro.md)
- [Sprint 2: Review](./docs/sprint2-review.md) - stories delivered, evidence, testing
- [Sprint 2: Retrospective](./docs/sprint2-retro.md)
- Evidence screenshots: `docs/sprint1-evidence/` and `docs/sprint2-evidence/`

## CI/CD
GitHub Actions workflow at `.github/workflows/habit-tracker-ci.yml` runs all tests automatically on every push to this project.