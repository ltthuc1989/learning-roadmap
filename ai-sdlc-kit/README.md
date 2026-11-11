# AI-SDLC kit: đáp án tham khảo cho các bài lab trong `24-AI-SDLC-1-Month.md`

> **Đừng copy kit này vào repo trước khi tự làm lab.** Mỗi lab trong file 24 yêu cầu bạn tự viết phiên bản của mình trước, chạy thử, đo, rồi mới mở file tương ứng ở đây để so. Mục đích là bạn hiểu vì sao từng dòng tồn tại; copy cấu hình người khác là cách nhanh nhất để có một setup bạn không giải thích được trong phỏng vấn.
> Kiểm tra ngày 11/11/2026 với docs Claude Code (cần bản ≥ 2.1.28x cho auto mode và một số trường hook; máy bạn đang ở 2.1.114, chạy `claude update`).

## Cấu trúc và lý do thiết kế

| File | Vai trò | Vì sao thiết kế như vậy | Lựa chọn khác và đánh đổi |
|---|---|---|---|
| `AGENTS.md.template` | Luật chung cho mọi agent (Claude Code, Codex, Copilot, Cursor, Gemini in Android Studio) | AGENTS.md là chuẩn mở (thuộc Agentic AI Foundation, Linux Foundation), nhiều tool đọc được. Viết 1 lần, team dùng tool nào cũng theo cùng luật. | Chỉ dùng CLAUDE.md: tiện nhưng khoá vào 1 tool. Mỗi tool 1 file (`.cursor/rules`, `copilot-instructions.md`): sát tool hơn nhưng nhanh lệch nhau. |
| `CLAUDE.md.template` | Phần riêng cho Claude Code, import `@AGENTS.md` | Giữ < 200 dòng vì mọi dòng tốn context mỗi session. Chỉ ghi thứ Claude không đọc ra được từ code: lệnh test nhanh nhất, definition of done, convention ngầm. | File dài liệt kê mọi thứ: tốn token, model dễ bỏ qua phần giữa. Không có file: agent phải tự khám phá mỗi lần, chậm và đoán sai lệnh build. |
| `.claude/settings.json` (permissions) | Cho phép lệnh an toàn chạy không hỏi, hỏi trước lệnh có hậu quả, cấm đọc secret | Luật "không bao giờ" phải đặt ở chỗ harness ép buộc (deny rule, hook), không ở CLAUDE.md vì model có thể bỏ qua prompt. Allow list hẹp giúp làm việc nhanh mà không mở toang. | `bypassPermissions`: nhanh nhất nhưng chỉ an toàn trong container dùng 1 lần. Không allow gì: an toàn nhưng phải bấm duyệt liên tục, người dùng mệt và bấm "yes" mù quáng (rủi ro thật). |
| `.claude/hooks/block_secrets.py` (PreToolUse) | Chặn agent ghi vào keystore, `local.properties`, `.env` | Hook là code chạy chắc chắn trước mỗi lần ghi file; exit 2 chặn và trả lý do cho model. Viết bằng Python vì máy không có `jq`. | Chỉ dùng deny rule `Edit(...)`: đơn giản hơn, nhưng hook cho phép logic (tên file, đuôi, nội dung) và thông báo rõ. |
| `.claude/hooks/format_kotlin.py` (PostToolUse) | Format file Kotlin vừa sửa | Rẻ, chạy trên 1 file, không làm hỏng session nếu thiếu ktlint. Không chạy cả Gradle mỗi lần sửa vì quá chậm. | Chạy detekt/test trong hook: kiểm soát chặt hơn nhưng làm mỗi lần sửa chậm hàng chục giây; để việc đó cho bước verify cuối hoặc CI. |
| `.claude/hooks/log_session.py` (Stop) | Ghi log mỗi lượt agent để đo | Không đo thì không biết AI giúp hay hại (bài học METR 2025: dev thấy nhanh hơn 20% nhưng thực tế chậm hơn 19%). | Dùng `/usage` thủ công: không cần code nhưng không có lịch sử. Dùng OpenTelemetry của Claude Code: đầy đủ hơn, cần backend. |
| `.claude/agents/*.md` (subagents) | Reviewer, test writer, security reviewer có context riêng và quyền tối thiểu | Subagent chạy trong context window riêng, không bị "thiên vị" bởi những gì agent chính vừa viết, nên review độc lập hơn. Reviewer chỉ có quyền đọc. | Nhờ agent chính tự review: rẻ hơn nhưng nó hay bảo vệ code của chính nó. Session thứ hai trong worktree khác: độc lập nhất, tốn thao tác hơn. |
| `.claude/skills/*/SKILL.md` | Quy trình lặp lại: scaffold module, release notes, fix crash, ADR, spec | Skill chỉ nạp nội dung khi cần (description khớp task), nên không tốn context thường trực như CLAUDE.md. Mỗi skill có bước verify và điểm dừng. | Slash command (`.claude/commands`): cũ hơn, đã gộp vào skills. Script thuần: chắc chắn hơn nhưng không linh hoạt với input mơ hồ. |
| `.github/workflows/claude-pr-review.yml` | AI review mọi PR (vòng đầu) | Dùng plugin `code-review` chính thức, chỉ cho tool comment inline, giới hạn số lượt, huỷ run cũ khi push mới. Người vẫn review và approve. | Copilot code review / CodeRabbit: ít setup hơn, ít tuỳ biến hơn, giá theo user. Không có AI review: reviewer người tốn thời gian cho lỗi máy bắt được. |
| `.github/workflows/claude-mention.yml` | `@claude` trong issue/PR để giao việc | Chỉ người trong repo kích hoạt được (`author_association`), vì comment từ người lạ có thể chứa prompt injection. Cài JDK + Gradle để agent chạy test được. | Copilot coding agent (gán issue cho Copilot): tích hợp GitHub sâu hơn, ít kiểm soát prompt hơn. |
| `.github/workflows/ai-release-notes.yml` | Tag mới thì AI soạn release notes, tạo draft release | Mẫu "AI soạn, người duyệt": draft, không tự publish. Prompt cấm bịa tính năng, chỉ dùng git log. | Skill `release-notes` chạy local: chủ động hơn, không tự động. |
| `scripts/ai/crash_triage.py` | Phân loại hàng loạt crash bằng `claude -p` + JSON schema | Mẫu workflow: code điều khiển vòng lặp, model chỉ làm 1 bước có cấu trúc, chỉ có quyền đọc, có trần ngân sách. Dễ test, dễ đo, khó "chạy lạc". | Cho 1 agent tự do đọc cả thư mục crash: ít code hơn nhưng khó kiểm soát chi phí và chất lượng. |
| `scripts/ai/pr_describe.py` | Sinh PR description từ diff | In ra để người sửa, không tự tạo PR. | `gh pr create --fill` kèm AI: nhanh hơn nhưng dễ đăng nội dung chưa đọc. |
| `kotlin-agent-loop/` | Agent loop tự viết bằng Kotlin gọi Messages API | Để hiểu tool use từ bên trong: model chỉ đề xuất, code của bạn chạy tool. Tool chỉ đọc, có sandbox đường dẫn, giới hạn vòng. | Agent SDK (Python/TS): có sẵn tool, hooks, sessions. Koog / ADK Kotlin: framework Kotlin, có observability. Tự viết chỉ để học; production nên dùng SDK/framework. |

## Cách dùng sau khi đã làm lab
1. Đọc file trong kit, so với bản bạn viết: khác ở đâu, vì sao, bản nào tốt hơn cho repo của bạn.
2. Chỉ lấy phần bạn giải thích được. Sửa theo repo thật (module, lệnh Gradle, đường dẫn).
3. Copy vào repo: `AGENTS.md`, `CLAUDE.md`, `.claude/`, `.github/workflows/`, `scripts/ai/`. Thêm `.claude/settings.local.json` và `.claude/ai-metrics.csv` vào `.gitignore`.
4. Chạy `/context` và `/usage` trong Claude Code để xem cấu hình tốn bao nhiêu context.
5. Ghi lại quyết định vào `docs/adr/` (dùng skill `write-adr`) để kể được trong phỏng vấn.

## Đã kiểm tra
- `settings.json` là JSON hợp lệ; 3 hook chạy đúng (chặn `local.properties`, `*.jks`; cho qua `.kt`; ghi CSV).
- 3 subagent và 5 skill có frontmatter `name` + `description`.
- 3 workflow YAML hợp lệ; version action (`checkout@v6`, `setup-java@v5`, `setup-gradle@v5`, `claude-code-action@v1`) tồn tại.
- `kotlin-agent-loop` compile được (Kotlin 2.2.20, JDK 17 toolchain). Chưa chạy với API key thật.
