---
name: fix-crash
description: Reproduce and fix a crash from a stack trace (Crashlytics, Play Console, logcat). Use when the user pastes a stack trace or a crash id and asks for a fix.
argument-hint: <path-to-stacktrace or pasted trace>
---
Crash input: $ARGUMENTS

1. Find the top frame in this repo's code and the exception type. If the trace is obfuscated, ask for the mapping file or run retrace; do not guess.
2. Explain the root cause in three sentences: which input or state leads there.
3. Write a failing unit test that reproduces it (delegate to the test-writer subagent if setup is large). Run it and show it fails.
4. Fix with the smallest change. Run the test again plus the module's tests.
5. Report root cause, fix, test name, and other call sites with the same pattern (search for it).
