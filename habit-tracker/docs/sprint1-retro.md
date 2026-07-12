# Sprint 1 Retrospective

## What Went Well
- All 3 selected stories delivered and working, verified with real API tests
- Full test coverage for the streak logic, all 5 completion scenarios plus create and list
- CI pipeline is genuinely working end to end, not just configured but proven green

## What Was Difficult
- CI/CD setup was the hardest part of this sprint. This was my first time configuring a GitHub Actions pipeline, and I hit repeated failures before getting it working, first a mvnw permission error, then a CRLF line ending issue specific to generating a Spring Boot project on Windows and running it on a Linux CI runner. Each failure required reading the actual error logs carefully rather than guessing.
- Git commit discipline did not come naturally. I had to be reminded to commit in small increments rather than working for a while and committing everything at once. I am improving at this but it still requires conscious effort, not habit yet.

## Improvements for Sprint 2
1. Commit immediately after finishing each individual piece, one file or one small logical change, rather than waiting until a whole story is done. Treat committing as part of finishing the work, not a separate step to remember afterward.
2. Prioritize process and planning as much as code. Before writing a Sprint 2 story, write out the logic in plain English first, and confirm the architecture decision, before touching code, rather than coding first and correcting structure afterward.
3. Since the CI pipeline is now already working and proven, Sprint 2 should not require repeating that debugging process, apply the same working pipeline directly to the new stories.