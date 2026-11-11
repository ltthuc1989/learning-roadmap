---
name: spec-to-tasks
description: Turn a feature request or PRD into a short spec (acceptance criteria, edge cases, non-functional requirements) and a list of small tasks with a test for each. Use at the start of a feature, before any code is written.
argument-hint: <path to request or pasted text>
---
Input: $ARGUMENTS

1. Read the request and the related code (search for existing screens, models, APIs). Do not write production code in this skill.
2. Write `docs/specs/<feature>.md` with:
   - Problem and user outcome (2-3 sentences).
   - Acceptance criteria as Given/When/Then (5-10 items).
   - Edge cases: offline, empty, error, permission denied, process death, small screen, RTL/long text, accessibility.
   - Non-functional: performance budget, analytics events, privacy/data, rollout flag.
   - Open questions for the PM or designer.
3. Add a task list: each task under half a day, ordered, each with the test that proves it done.
4. Stop and ask the human to approve the spec before implementation.
