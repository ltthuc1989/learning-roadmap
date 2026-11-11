#!/usr/bin/env python3
"""Stop hook: ghi 1 dòng log mỗi khi agent kết thúc một lượt, để đo việc dùng AI.

Ghi vào .claude/ai-metrics.csv (thêm file này vào .gitignore). Cuối tuần đếm số lượt, số session,
rồi so với số PR merge và số PR phải sửa lại (xem tuần 4 trong 24-AI-SDLC-1-Month.md).
"""
import csv
import datetime
import json
import os
import subprocess
import sys


def git(*args: str) -> str:
    try:
        return subprocess.run(["git", *args], capture_output=True, text=True, timeout=5).stdout.strip()
    except Exception:
        return ""


def main() -> int:
    try:
        event = json.load(sys.stdin)
    except json.JSONDecodeError:
        event = {}
    project = os.environ.get("CLAUDE_PROJECT_DIR", os.getcwd())
    out = os.path.join(project, ".claude", "ai-metrics.csv")
    new_file = not os.path.exists(out)
    changed = git("diff", "--shortstat")
    with open(out, "a", newline="", encoding="utf-8") as fh:
        w = csv.writer(fh)
        if new_file:
            w.writerow(["timestamp", "session_id", "branch", "uncommitted_diff"])
        w.writerow([
            datetime.datetime.now().isoformat(timespec="seconds"),
            event.get("session_id", ""),
            git("rev-parse", "--abbrev-ref", "HEAD"),
            changed,
        ])
    return 0


if __name__ == "__main__":
    sys.exit(main())
