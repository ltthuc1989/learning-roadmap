# Luyện phỏng vấn Mobile Senior/Staff + Tech Lead, 12 tuần

> Bộ tài liệu này được xây từ research thị trường thật (ITviec, VietnamWorks, TopDev, 19/10/2026) cho một Android dev 10 năm kinh nghiệm muốn pass vòng kỹ thuật Senior/Staff ở công ty nước ngoài và vòng leadership cho Mobile Tech Lead. Giải thích bằng tiếng Việt, câu hỏi và câu trả lời mẫu bằng English. Bản đọc nhanh trên điện thoại: Claude Doc "Luyện phỏng vấn Mobile Lead 2026" (link ở mục 6).

## 1. Bắt đầu thế nào (30 phút đầu tiên)

1. Đọc `01-Market-Research-2026.md` mục 0 (10 dòng kết luận) để biết thị trường đòi gì.
2. Mở `02-Gap-Analysis-Positioning.md`, chấm skill matrix, đánh dấu gap đỏ.
3. Mở `03-Roadmap-12-Weeks.md`, đọc tuần 1 và khung thời gian mỗi ngày.
4. Mở `PROGRESS.md`, tick việc đầu tiên.
5. Cài Anki, chạy `python3 -I tools/make_anki.py`, import `anki/all.csv`.

## 2. Nguyên tắc dùng tài liệu

- **Theo tuần, không đọc lan.** Mỗi tuần roadmap chỉ định file và mục cần đọc.
- **Che đáp án trước khi đọc.** Mọi Q&A có câu hỏi EN rồi mới tới câu trả lời; tự trả lời to bằng tiếng Anh, ghi âm, rồi so.
- **Own your own code.** Code KMP của bạn do AI viết; 2 buổi/tuần tự giải thích repo của mình không có AI (`02` mục 7, `18`).
- **Hands-on trên repo thật.** Bài tập trong mỗi file ưu tiên làm trên `kmp-template-impl`, `compose-template`, hoặc side project (`16`).
- **Ghi câu hỏi thật.** Sau mỗi buổi phỏng vấn, ghi câu hỏi vào bảng trong `PROGRESS.md`; Claude Code sẽ bổ sung vào file liên quan.
- **Bạn là chủ tài liệu.** Sửa trực tiếp, thêm note, xoá phần không cần. Link chết thì tìm lại theo tên và sửa.

## 3. Danh mục file

| File | Nội dung | Đọc khi | Cỡ |
|---|---|---|---|
| `00-README.md` | File này | Đầu tiên | ngắn |
| `01-Market-Research-2026.md` | JD thị trường VN 10/2026: skill frequency, leadership requirements, AI trong JD, lương, công ty đang tuyển, ecosystem snapshot | Tuần 1, và trước khi apply | trung |
| `02-Gap-Analysis-Positioning.md` | Skill matrix tự chấm, định vị, 5 câu chuyện từ project thật, pitch EN, CV/LinkedIn, drill own-your-code | Tuần 1, 6, 12 | trung |
| `03-Roadmap-12-Weeks.md` | Lộ trình từng tuần, deliverable, nghi thức review, Fast-track 4 tuần | Mỗi tuần | dài |
| `04-Android-Kotlin-Deep-Dive.md` | Kotlin, Coroutines/Flow, Compose internals, architecture, DI, data, build/R8, Android 16/17, performance, legacy migration, release | Tuần 1-2, 4 | rất dài |
| `05-Cross-Platform-iOS-KMP-Flutter.md` | KMP/CMP học lại cho vững, iOS/Swift đọc-review, Flutter nền tảng, RN khái niệm, decision framework | Tuần 10 | rất dài |
| `06-Mobile-System-Design.md` | Framework + 11 bài giải mẫu (feed, chat, offline sync, image loader, downloader, ride-hailing, payment, push, analytics SDK, design system platform, AI feature) | Tuần 7-8 | rất dài |
| `07-Network.md` | Từ zero: TCP/TLS/HTTP, REST/GraphQL/gRPC/WebSocket, OkHttp/Ktor internals, retry, pinning, debug | Tuần 5 | rất dài |
| `08-Security.md` | MASVS, Keystore, pinning, PKCE, biometrics, R8/DexGuard/Play Integrity, IPC/WebView, fintech, bộ câu hỏi bank | Tuần 6 | rất dài |
| `09-Backend-Fullstack.md` | REST design, Ktor/Supabase/Spring đọc được, DB, cache, queue, Docker, cloud, web cơ bản | Tuần 8 | trung-dài |
| `10-AI-for-Mobile-Lead.md` | LLM fundamentals, AI trong sản phẩm (Gemini Nano, ML Kit GenAI, Firebase AI Logic, hybrid), AI trong workflow (Claude Code, Agent Mode, Journeys), Q&A thời AI | Tuần 9 | rất dài |
| `11-Leadership-Management.md` | Tech Lead vs Staff vs EM, ADR/RFC, review culture, mentoring, estimation, stakeholder, incident, release, hiring, first 90 days | Tuần 11 | dài |
| `12-Behavioral-STAR-Bank.md` | 40 câu behavioral + 20 story template từ kinh nghiệm thật của bạn | Tuần 11 | trung |
| `13-Coding-Interview.md` | 14 pattern DSA với template Kotlin, ~75 bài theo tuần, 12 bài kiểu mobile, 5 live-coding Android, online test, vòng cho dùng AI | Tuần 1-12 | dài |
| `14-Mock-Interview-Scripts.md` | Script 6 vòng, rubric, take-home, vòng review & refactor, câu hỏi ngược, đàm phán lương, logistics, full mock loop | Tuần 4, 7, 9, 11, 12 | dài |
| `15-Company-Playbook-Job-Search.md` | Playbook theo loại và từng công ty, job search ops: remote/global, headhunter, referral, tracking | Tuần 8 trở đi | trung |
| `16-Side-Project-Portfolio.md` | Project "Ledger" KMP + backend + AI hybrid + security + CI, milestone theo tuần, README, cách kể | Tuần 1 trở đi | trung |
| `17-Resources.md` | 6 sách phải đọc theo chương, 5 sách tuỳ chọn, nguồn miễn phí, link theo chủ đề, reading plan | Mỗi tuần | trung |
| `18-AI-Study-Workflow.md` | Claude Code làm giám khảo: 8 prompt template, lịch dùng, câu chuyện AI-SDLC | Tuần 1 | ngắn |
| `19-Testing-Deep-Dive.md` | Từ zero: test doubles, MockK, coroutines test, Turbine, Compose UI test, screenshot, KMP test, coverage, chiến lược test | Tuần 3 | rất dài |
| `20-Git-CICD.md` | Từ zero: Git (20 tình huống lỡ tay), GitHub Actions, CircleCI, release Play, iOS CI, DORA | Tuần 3-4 | rất dài |
| `21-UI-UX-Design-Adaptive.md` | Material 3, design tokens và design system, Figma, adaptive/foldable/large screen, accessibility, i18n, UX cho engineer | Tuần 2, 8, 10 | dài |
| `22-Product-Metrics-Compliance.md` | Android vitals, analytics, A/B, GDPR/Nghị định 13/Play Data safety/COPPA/PCI, licensing, cost, docs/ADR/RFC, ASO | Tuần 11 | trung |
| `23-English-for-Tech-Interviews.md` | 3 cấu trúc trả lời, 150 cụm từ theo vòng, 60 từ hay phát âm sai, luyện với AI voice, rubric | Tuần 1 trở đi | trung |
| `PROGRESS.md` | Nhật ký tuần, câu hỏi thật gặp khi phỏng vấn | Mỗi tuần | |
| `tools/make_anki.py` | Sinh thẻ Anki từ mọi Q&A | Chủ nhật | |
| `samples/review-exercise/` | Project Kotlin có 28 lỗi cố ý để luyện vòng "review & refactor" (đáp án trong `ANSWERS.md`) | Tuần 12 | |

## 4. Quy ước trong file chủ đề

Mỗi file `04`-`11`, `19`-`22` có cùng cấu trúc: Mục tiêu và mức cần đạt → Kiến thức cốt lõi (VI, có mermaid) → Interview Q&A (EN, định dạng `### Qn.` / `**A:**` / `**Follow-ups:**` / `**Điểm interviewer muốn nghe:**`) → Hands-on → Checklist tự đánh giá → Đọc thêm. Định dạng Q&A cố định để `tools/make_anki.py` trích được.

Mermaid hiển thị trong VS Code với extension "Markdown Preview Mermaid Support", hoặc trong Obsidian, GitHub.

## 5. Công cụ
- **Anki:** `python3 -I tools/make_anki.py` tạo `anki/all.csv` và từng file; import với "Allow HTML in fields".
- **Claude Code:** mở thư mục này, dùng prompt trong `18`. Cuối tuần: "review tuần N".
- **Ghi âm:** điện thoại là đủ; đặt tên file theo tuần và câu hỏi.
- **Mermaid:** xem mục 4.

## 6. Claude Doc (bản điện thoại)
"Luyện phỏng vấn Mobile Lead 2026": định vị, roadmap checklist, nguyên tắc học, top 50 câu hỏi, sách theo tuần. Link được ghi trong `PROGRESS.md` đầu file sau khi tạo; bạn cũng thấy trong danh sách artifact của claude.ai.

## 7. Sau 12 tuần
Tài liệu không hết hạn sau 12 tuần: khi đi phỏng vấn thật, mỗi câu hỏi mới là một dòng trong `PROGRESS.md` và một Q&A mới trong file liên quan. Mỗi 3 tháng chạy lại research thị trường (`01`) để cập nhật từ khoá và ecosystem.
