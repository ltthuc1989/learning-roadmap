#!/usr/bin/env python3
"""PostToolUse hook (Edit|Write): format file Kotlin vừa sửa bằng ktlint nếu máy có ktlint.

Chạy trên đúng 1 file (nhanh). Không có ktlint thì bỏ qua, không làm hỏng session.
Cài ktlint: `brew install ktlint`. Nếu repo dùng detekt formatting thay ktlint, đổi lệnh ở FORMAT_CMD.
"""
import json
import shutil
import subprocess
import sys

FORMAT_CMD = ["ktlint", "--format"]


def main() -> int:
    try:
        event = json.load(sys.stdin)
    except json.JSONDecodeError:
        return 0
    path = (event.get("tool_input") or {}).get("file_path") or ""
    if not path.endswith((".kt", ".kts")) or shutil.which(FORMAT_CMD[0]) is None:
        return 0
    result = subprocess.run(FORMAT_CMD + [path], capture_output=True, text=True, timeout=55)
    if result.returncode != 0:
        # Exit 0 + in ra stdout: chỉ thông báo, không chặn. Lỗi lint còn lại để detekt trong CI bắt.
        print(f"ktlint reported issues in {path}:\n{result.stdout[-1500:]}")
    return 0


if __name__ == "__main__":
    sys.exit(main())
