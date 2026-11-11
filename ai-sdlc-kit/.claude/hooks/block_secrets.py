#!/usr/bin/env python3
"""PreToolUse hook (Edit|Write): chặn agent ghi vào file chứa secret.

Claude Code gửi JSON của tool call qua stdin. Exit code 2 = chặn, stderr được gửi lại cho Claude làm lý do.
Đây là luật "không bao giờ", nên đặt ở hook (deterministic), không đặt ở CLAUDE.md (model có thể bỏ qua).
"""
import json
import os
import sys

BLOCKED_NAMES = {"local.properties", "google-services.json", "GoogleService-Info.plist", "keystore.properties"}
BLOCKED_SUFFIXES = (".jks", ".keystore", ".pepk", ".p12", ".pem")


def main() -> int:
    try:
        event = json.load(sys.stdin)
    except json.JSONDecodeError:
        return 0  # không đọc được input thì không chặn nhầm
    path = (event.get("tool_input") or {}).get("file_path") or ""
    name = os.path.basename(path)
    if name in BLOCKED_NAMES or name.startswith(".env") or name.endswith(BLOCKED_SUFFIXES):
        print(f"Blocked by policy: '{name}' holds secrets or signing material. "
              "Ask the human to change it manually.", file=sys.stderr)
        return 2
    return 0


if __name__ == "__main__":
    sys.exit(main())
