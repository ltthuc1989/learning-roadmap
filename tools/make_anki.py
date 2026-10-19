#!/usr/bin/env python3
"""Trích mọi cặp Q&A trong các file tài liệu thành CSV import vào Anki.

Cách dùng (từ thư mục gốc của bộ tài liệu):
    python3 -I tools/make_anki.py            # sinh anki/all.csv và anki/<file>.csv
    python3 -I tools/make_anki.py --tsv      # tab-separated (Anki nhận tốt hơn khi có dấu phẩy)

Định dạng nhận diện trong markdown:
    ### Q12. Question text
    **A:** answer ...
    **Follow-ups:** ...
    **Điểm interviewer muốn nghe:** ...
Thẻ Anki: Front = "[Tên file] Question", Back = phần A + follow-ups + điểm interviewer (markdown -> HTML đơn giản).
Import vào Anki: File > Import, chọn "Fields separated by: Comma (hoặc Tab)", "Allow HTML in fields" bật, Tags lấy từ cột 3.
"""
import argparse
import csv
import html
import re
import sys
from pathlib import Path

Q_RE = re.compile(r"^### Q(\d+)\.\s*(.+?)\s*$")
HEADING_RE = re.compile(r"^#{1,3} ")


def md_to_html(text: str) -> str:
    """Chuyển markdown rất đơn giản sang HTML để Anki hiển thị được."""
    out = []
    in_code = False
    for line in text.splitlines():
        if line.strip().startswith("```"):
            out.append("</pre>" if in_code else "<pre>")
            in_code = not in_code
            continue
        if in_code:
            out.append(html.escape(line))
            continue
        esc = html.escape(line)
        esc = re.sub(r"\*\*(.+?)\*\*", r"<b>\1</b>", esc)
        esc = re.sub(r"`([^`]+)`", r"<code>\1</code>", esc)
        if esc.strip().startswith("- "):
            esc = "&bull; " + esc.strip()[2:]
        out.append(esc)
    if in_code:
        out.append("</pre>")
    return "<br>".join(out)


def extract(path: Path):
    cards = []
    lines = path.read_text(encoding="utf-8").splitlines()
    i = 0
    while i < len(lines):
        m = Q_RE.match(lines[i])
        if not m:
            i += 1
            continue
        qnum, question = m.group(1), m.group(2)
        body = []
        i += 1
        while i < len(lines) and not HEADING_RE.match(lines[i]):
            body.append(lines[i])
            i += 1
        answer = "\n".join(body).strip()
        if answer:
            cards.append((qnum, question, answer))
    return cards


def main() -> int:
    ap = argparse.ArgumentParser()
    ap.add_argument("--root", default=".", help="thư mục chứa các file 0N-*.md")
    ap.add_argument("--tsv", action="store_true", help="xuất TSV thay vì CSV")
    args = ap.parse_args()
    root = Path(args.root).resolve()
    out_dir = root / "anki"
    out_dir.mkdir(exist_ok=True)
    delim = "\t" if args.tsv else ","
    ext = "tsv" if args.tsv else "csv"

    files = sorted(p for p in root.glob("[0-9][0-9]-*.md"))
    total = 0
    all_rows = []
    for f in files:
        cards = extract(f)
        if not cards:
            continue
        tag = f.stem.split("-", 1)[0]
        topic = f.stem
        rows = []
        for qnum, q, a in cards:
            front = f"[{topic}] Q{qnum}. {q}"
            back = md_to_html(a)
            rows.append([front, back, f"interview {topic}"])
        with (out_dir / f"{topic}.{ext}").open("w", encoding="utf-8", newline="") as fh:
            csv.writer(fh, delimiter=delim).writerows(rows)
        all_rows.extend(rows)
        total += len(rows)
        print(f"{topic}: {len(rows)} thẻ")
    with (out_dir / f"all.{ext}").open("w", encoding="utf-8", newline="") as fh:
        csv.writer(fh, delimiter=delim).writerows(all_rows)
    print(f"Tổng: {total} thẻ -> {out_dir}/all.{ext}")
    return 0 if total else 1


if __name__ == "__main__":
    sys.exit(main())
