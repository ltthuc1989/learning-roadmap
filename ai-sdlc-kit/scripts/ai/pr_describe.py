#!/usr/bin/env python3
"""Sinh PR description (Why / What / How tested / Risk) từ diff của nhánh hiện tại so với main, bằng Claude Code headless.

Chạy: python3 -I scripts/ai/pr_describe.py [base-branch]   (mặc định: main)
Kết quả in ra stdout để bạn đọc, sửa, rồi mới dán vào PR. Không tự tạo PR: người vẫn là người chịu trách nhiệm.
"""
import subprocess
import sys

base = sys.argv[1] if len(sys.argv) > 1 else "main"
diff = subprocess.run(["git", "diff", f"{base}...HEAD"], capture_output=True, text=True).stdout
log = subprocess.run(["git", "log", "--oneline", f"{base}..HEAD"], capture_output=True, text=True).stdout
if not diff.strip():
    sys.exit(f"No diff against {base}.")

prompt = (
    "Write a pull request description in English with sections: Why, What changed (bullets per area), "
    "How tested (only what the diff or commits show; write 'Not shown in diff' otherwise), Risk and rollout, "
    "Screenshots needed (yes/no). Be factual; do not invent tests or tickets.\n"
    f"<commits>\n{log}\n</commits>\n<diff>\n{diff[:60000]}\n</diff>"
)
proc = subprocess.run(
    ["claude", "-p", prompt, "--permission-mode", "dontAsk", "--max-turns", "3", "--allowedTools", "Read"],
    capture_output=True, text=True, timeout=300,
)
print(proc.stdout if proc.returncode == 0 else proc.stderr)
