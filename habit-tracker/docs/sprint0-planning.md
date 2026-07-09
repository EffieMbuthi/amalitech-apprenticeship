# Sprint 0 – Planning

## Product Vision
A simple API that lets a user track daily habits, mark them complete, and see their streak — helping them build consistency.

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

## Acceptance Criteria (example — Story 2)
- POST /habits/{id}/complete returns 200 and increments streak by 1
- Completing twice in the same day does not double-increment
- Returns 404 if habit doesn't exist

## Definition of Done
- Code committed with a descriptive message
- Unit test written and passing
- CI pipeline runs green on the commit
- No hardcoded/test-only values left in code

## Sprint 1 Plan
Selected stories: #1 (Create habit), #2 (Mark complete), #4 (List habits)
