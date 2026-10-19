# 18. AI Study Workflow: dùng Claude Code làm giám khảo, không làm người trả lời hộ

> Vấn đề: code của bạn được Claude Code viết, nên trong phỏng vấn bạn phải chứng minh mình hiểu nó hơn cả AI. Giải pháp: đảo vai. AI hỏi, bạn trả lời, AI chấm. Mọi prompt dưới đây dán thẳng vào Claude Code (mở trong thư mục repo tương ứng). Quy trình này cũng chính là câu chuyện "AI-SDLC" để kể ở vòng leadership.

## 1. Ba quy tắc

1. **AI-off khi trả lời.** Lúc trả lời câu hỏi, đóng mọi AI. Chỉ mở lại để chấm.
2. **Ghi âm tiếng Anh.** Mọi câu trả lời nói to bằng tiếng Anh, ghi âm (điện thoại là đủ), nghe lại 1 lần trước khi cho AI chấm.
3. **Một lỗ hổng, một thẻ Anki.** Chỗ AI chấm "thiếu", viết thành 1 thẻ Anki hoặc 1 dòng trong `PROGRESS.md` mục "Điểm yếu phát hiện".

## 2. Lịch dùng trong tuần

| Ngày | Việc | Prompt |
|---|---|---|
| Thứ 2 | Quiz về repo của bạn (15 phút) | P1 |
| Thứ 3 | Mock 5 câu về chủ đề tuần (file `0N`) | P2 |
| Thứ 5 | AI dạy 1 khái niệm khó rồi kiểm tra lại | P3 |
| Thứ 7 | Chấm bản ghi âm dài (system design hoặc STAR) | P4, P5, P6 |
| Chủ nhật | Sinh Anki từ Q&A, review tuần | `tools/make_anki.py`, P7 |

## 3. Prompt templates

### P1. Quiz me on my repo (chạy trong thư mục repo)
```
You are a Staff Android interviewer. Read this repository's structure (settings.gradle.kts, build-logic, core/*, feature/*, libs.versions.toml, CLAUDE.md) but do NOT explain anything to me yet.
Generate 10 deep-dive interview questions about THIS codebase, ordered from Senior to Staff level, covering: module dependency rules, DI choice and scoping, data layer and migrations, navigation/state, iOS integration (expect/actual, Koin init on iOS), build/secrets, performance risks, testing gaps, and one "what would you change" question.
Ask ONE question at a time. Wait for my answer. After each answer, grade it 0-4 (0 wrong, 2 basic, 3 mechanism + trade-off, 4 could teach it), point out what a Staff answer would add in at most 5 lines, then ask the next question. At the end, give me a table of scores and the 3 weakest areas with the exact files I should re-read.
Do not accept vague answers; ask a follow-up if I say "it just works".
```

### P2. Mock interviewer for topic X (chạy trong thư mục Luyen phong vấn)
```
Read file 0N-<topic>.md. Act as an interviewer for a Senior/Staff Android role at a foreign product company. Pick 5 questions from its Q&A section (do not show me the model answers), mixing difficulty. Ask one at a time in English. I will answer in English. After each answer: grade 0-4 against the model answer, list what I missed in at most 5 bullets, and give one follow-up question an interviewer would ask. Keep your own text short; I need speaking practice, not reading practice. At the end, output a 5-row table: question, my score, gap, Anki card text (front/back).
```

### P3. Teach then test (cho khái niệm khó: recomposition, TLS handshake, structured concurrency)
```
Teach me <concept> the way a Staff engineer would explain it to a strong Senior: mechanism first, then the 3 most common mistakes, then 2 trade-offs, in under 400 words, with one small Kotlin example if relevant. Then STOP and ask me to explain it back in my own words. Grade my explanation 0-4, correct only what is wrong, and give me 3 interview questions on it that I must answer without your help in my next message.
```

### P4. Grade my recorded answer (dán transcript ghi âm, hoặc tóm tắt bạn nói gì)
```
Here is a transcript of my spoken answer (English, unedited) to the interview question: "<question>".
<transcript>
Grade it with this rubric, 0-4 each: correctness, depth (mechanism + trade-off), structure (answer first, then why, then example), concision (under 2 minutes), English clarity (grammar and vocabulary that would confuse a listener).
Then rewrite my answer as a 90-second model answer in my own voice, keeping my real examples. Finally list 5 phrases I should practise saying.
```

### P5. System design grader (dán ghi chú và mô tả diagram bạn đã vẽ)
```
I did a 45-minute mobile system design for: "<prompt>". Here are my notes and the diagram described in text:
<notes>
Act as a Staff interviewer at Grab. Grade against: requirements clarification, NFRs (offline, latency, battery, data, security), high-level design, API contract, data model, deep dives chosen, trade-offs stated, failure modes, metrics. Score each 0-4. Tell me the 3 things that would have made this a Staff-level answer, and 3 follow-up questions you would have asked that I did not cover. Keep it under 500 words.
```

### P6. STAR story polisher
```
Here is a draft STAR story for the question "<behavioral question>":
<draft>
Tighten it to 2 minutes spoken: Situation 2 sentences, Task 1, Action 4-5 with what I personally did (not "we"), Result with a number or a concrete outcome, plus 1 sentence on what I learned. Keep my facts; do not invent numbers; where a number is missing, insert [NUMBER] so I fill it. Then give me 3 follow-up questions an interviewer would ask about this story.
```

### P7. Weekly review (chạy trong thư mục Luyen phong vấn)
```
Read PROGRESS.md. For week <N>: list what is done and not done, compare with 03-Roadmap-12-Weeks.md, and propose next week's top 3 priorities given my weakest scores. If I logged real interview questions, tell me which file should be updated and draft the Q&A entry in the same format as the other files. Keep it under 300 words.
```

### P8. Review code AI sinh ra (quy trình bạn sẽ kể trong phỏng vấn)
```
Review this diff as a Staff Android engineer who assumes it was AI-generated. Check in this order: (1) correctness vs the stated requirement, (2) concurrency and lifecycle (scopes, cancellation, main-thread), (3) security (secrets, input validation, exported components), (4) performance (recomposition, allocations in hot paths, IO on main), (5) tests present and meaningful, (6) consistency with CLAUDE.md rules. Output a table: issue, severity, file:line, fix. Do not rewrite the code.
```

## 4. Cách biến quy trình này thành câu chuyện phỏng vấn

Interviewer hỏi "How do you use AI in your work?" hoặc "How would you lead a team that uses AI agents?". Trả lời theo 4 lớp, mỗi lớp có bằng chứng thật từ repo của bạn:

1. **Rules at the repo level:** `CLAUDE.md` chứa rule kiến trúc, rule "rules flow upstream", bảng failure-first. "Every bug that cost real debugging time becomes a rule in the same session." (Có thật trong `compose-template/CLAUDE.md`.)
2. **Skills and commands for repeatable work:** `/grabee`, `/scaffold-unit`, `ios-app-port`, `app-catalog-scan`. "Repeatable, boring work is encoded once; humans review the output."
3. **Review process for AI output:** checklist P8, chạy detekt và test trong CI trước khi merge, không merge code mình không giải thích được. "If I cannot explain it in a review, it does not ship."
4. **Keeping skills sharp:** AI-off drills, quiz mode (P1), re-implement by hand hằng tuần. "AI writes faster than I read; the risk is skill erosion, so we schedule AI-off blocks and pair juniors on design before they prompt."

Thêm 1 câu về đo lường: "We track PR cycle time, defect escape rate and the share of AI-generated PRs that needed rework; without metrics you cannot tell speed from noise." Nếu chưa có số, nói thật là chưa đo và bạn sẽ đo thế nào (`22`).

## 5. Công cụ khác đáng biết (để trả lời câu hỏi "bạn dùng tool gì")
- **Claude Code**: CLAUDE.md, skills, slash commands, MCP servers, hooks; chạy trong terminal, IDE extension, hoặc web.
- **Gemini in Android Studio (Rabbit 1, 10/2026)**: Agent Mode (MCP, `AGENTS.md`, permission controls, Android Knowledge Base), Journeys (E2E test bằng ngôn ngữ tự nhiên), unit test generation, Update dependencies agent, Next Edit Prediction, `.aiexclude`. Rabbit 2 Canary: Bring Your Own Agent (Claude Agent, Codex, Antigravity).
- **Android CLI 1.0**: CLI cho agent, tạo app từ template, render Compose preview, chạy Journeys.
- **Cursor / Copilot / Codex**: biết để so sánh; nhiều JD ghi tên.
- Số liệu để trích: Stack Overflow 2026, Claude Code 66%, Copilot 59%, 73% dùng AI hằng ngày, 93% cần source attribution.

## 6. Checklist
- [ ] Đã chạy P1 trên `kmp-template-impl` ít nhất 1 lần, lưu bảng điểm vào `PROGRESS.md`
- [ ] Mỗi tuần có ít nhất 1 bản ghi âm được chấm bằng P4
- [ ] Đã viết checklist review code AI của riêng mình (từ P8) thành 10 dòng, thuộc lòng
- [ ] Kể được câu chuyện AI-SDLC 4 lớp trong 2 phút bằng tiếng Anh
- [ ] Anki có ≥ 300 thẻ sau tuần 4, review 15 phút/ngày
