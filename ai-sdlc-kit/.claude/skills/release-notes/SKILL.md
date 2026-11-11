---
name: release-notes
description: Draft Play Store and App Store release notes (English and Vietnamese) plus an internal changelog from git history between two refs. Use when preparing a release or when asked for release notes or a changelog.
argument-hint: <from-ref> <to-ref>
disable-model-invocation: true
allowed-tools: Bash(git log*) Bash(git describe*) Bash(git tag*) Read
---
Range: `$0..$1`. If no arguments, use the last tag (`git describe --tags --abbrev=0`) to HEAD.

1. Run `git log --no-merges --pretty=format:'%h %s' <range>`.
2. Group by Conventional Commit type. Drop chore/test/ci from user-facing notes.
3. Write:
   - Store notes: at most 500 characters per language, user benefit wording, no ticket ids; en-US then vi-VN.
   - Internal changelog: every commit hash grouped by type, plus "Risk / needs QA" for anything touching billing, auth, migrations or analytics.
4. Never invent features that are not in the log. Unclear commits go under "Needs clarification".
