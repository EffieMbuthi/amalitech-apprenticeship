# Sprint 1 Review

## Stories Delivered
- Story 1: Create a habit
- Story 2: Mark a habit complete
- Story 4: List all habits

## Evidence

### Create Habit (POST /habits)
Returns 201 with the created habit.
Validation confirmed: empty name returns 400 with a clear field error message.

### List Habits (GET /habits)
Returns 200 with an array of all habits.

### Complete Habit (POST /habits/{id}/complete)
Returns 200, increments streak from 0 to 1 on first completion.
Confirmed same-day completion does not double increment, streak stayed at 1 on repeat call.
Confirmed 404 returned with a clear message when completing a nonexistent habit id.

### Health Check (GET /actuator/health)
Returns 200 with status UP, satisfying Story 6 early.

## Testing
6 unit tests written for HabitService, covering:
- Create habit
- List habits
- Complete habit: first time completing
- Complete habit: already completed today
- Complete habit: completed yesterday, streak increments
- Complete habit: missed more than one day, streak resets
- Complete habit: habit not found, throws exception

All tests passing.

## CI/CD
GitHub Actions pipeline configured to run tests automatically on every push to habit-tracker/.
Pipeline is green, latest run passing in 30 seconds.