# Sprint 0: Planning

## Product Vision
A simple API that lets a user track daily habits, mark them complete, and see their streak helping them build consistency.

## Product Backlog

| # | User Story | Priority | Points |
|---|---|---|---|
| 1 | As a user, I want to create a habit, so I can start tracking it | High | 2 |
| 2 | As a user, I want to mark a habit as done for today, so my streak updates | High | 3 |
| 3 | As a user, I want to see my current streak for a habit, so I feel motivated | Medium | 2 |
| 4 | As a user, I want to list all my habits, so I can see my progress at a glance | High | 1 |
| 5 | As a user, I want to delete a habit I no longer track, so my list stays relevant | Medium | 1 |
| 6 | As a user, I want a health check endpoint, so the system's status is observable | Medium | 1 |
| 7 | As a user, I want my streak to reset if I miss a day, so tracking stays honest | Low | 5 |


## Acceptance Criteria
### Story 1: Create a habit
- POST /habits with a name returns 201 and the created habit, including id and createdDate
- Returns 400 if name is missing or empty

### Story 2: Mark habit complete
- POST /habits/{id}/complete returns 200 and increments streak by 1
- Completing twice in the same day does not double increment
- Returns 404 if habit does not exist

### Story 3: View streak
- GET /habits/{id}/streak returns 200 with the current streak count
- Returns 404 if habit does not exist

### Story 4: List habits
- GET /habits returns 200 with an array of all habits
- Returns an empty array if none exist, not an error

### Story 5: Delete a habit
- DELETE /habits/{id} returns 204 on success
- Returns 404 if habit does not exist

### Story 6: Health check
- GET /actuator/health returns 200 with status UP when the service is running

### Story 7: Streak reset on missed day
- If a habit is completed today but was not completed yesterday, and it was completed on some earlier day, streak resets to 1 instead of incrementing


## Definition of Done
- Code committed with a descriptive message
- Unit test written and passing
- CI pipeline runs green on the commit
- No hardcoded/test only values left in code

## Sprint 1 Plan
Selected stories: #1 (Create habit), #2 (Mark complete), #4 (List habits)
