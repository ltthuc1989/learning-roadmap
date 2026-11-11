---
name: write-adr
description: Write an Architecture Decision Record in docs/adr/ from a discussion, a plan, or a set of options. Use when a technical decision has been made or needs to be documented.
argument-hint: <decision title>
---
Write `docs/adr/NNNN-<kebab-title>.md` for: $ARGUMENTS

1. Take the next number from existing files in `docs/adr/` (start at 0001).
2. Sections: Status, Date, Context (forces, constraints, numbers if known), Options considered (at least two, pros and cons), Decision, Consequences (positive, negative, follow-ups), Revisit when (a concrete trigger).
3. Ask for any missing fact instead of inventing it. Keep it under one page.
