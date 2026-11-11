# 25. AI-SDLC Playbooks: các công ty lớn làm thế nào, và áp dụng cho mọi quy mô

> Mục đích: có một **quy trình chuẩn tham khảo** đã được các công ty lớn chứng minh công khai, để (1) trả lời phỏng vấn "How would you introduce AI into our development process?" bằng dẫn chứng thật, và (2) áp dụng được cho dự án cá nhân, startup, scale-up hay ngân hàng.
> Đọc ở tuần 4 của `24-AI-SDLC-1-Month.md`, trước khi viết policy.
> Nguồn: blog kỹ thuật, paper và tài liệu chính thức của từng công ty, tổng hợp ngày 11/11/2026. **Mọi con số "% code do AI viết" đều do công ty tự báo cáo và không công bố cách đo**: trích dẫn được nhưng đừng coi là chân lý. Mục ghi [chưa kiểm chứng] chỉ có từ nguồn thứ cấp.

## 0. Đọc nhanh: 10 điều các công ty lớn cùng làm

1. **Spec hoặc plan trước code, người duyệt ở từng cổng** (GitHub Spec Kit, AWS AI-DLC, Kiro, Google Elephant-Goldfish).
2. **Agent tạo PR, người review và merge** (GitHub, Stripe, Google, Coinbase). Ngoại lệ có kiểm soát: Spotify tự merge PR bảo trì rủi ro thấp.
3. **Kiểm tra deterministic (build, test, lint) là cổng; agent lặp trên lỗi của chính nó** (Airbnb, Spotify, Stripe, Google, Meta).
4. **AI review vòng đầu, lọc nhiễu mạnh** (Uber, Microsoft, Atlassian). Comment vặt về style làm người dùng bỏ công cụ.
5. **Migration quy mô lớn là nơi ROI được chứng minh rõ nhất**, vì tiêu chí đúng/sai kiểm bằng máy được (Amazon, Google, Airbnb, Spotify, Zalando, Uber).
6. **Một platform team sở hữu "harness"**: sandbox, rule file, MCP tool, tích hợp CI, chọn model (Stripe, Spotify, Grab).
7. **Luật và context là file trong repo** (CLAUDE.md, Kiro Steering, Q Rules, Spec Kit specs).
8. **Lãnh đạo đặt kỳ vọng rõ** (Shopify, Microsoft, Meta, Coinbase), nhưng mandate thiếu hỗ trợ gây phản ứng ngược.
9. **Đo kết quả giao hàng, không chỉ đo mức dùng** (Booking.com, Atlassian, DORA).
10. **Enablement**: workshop, cộng đồng nội bộ, champion, cho chọn nhiều công cụ (Grab).

## 1. Từng công ty: họ làm gì, số liệu, bài học

### 1.1 Google
- **Làm gì:** code completion, sửa theo comment review bằng ML, dự đoán sửa lỗi build, chỉnh sửa bằng ngôn ngữ tự nhiên trong IDE; quy trình migration bằng LLM.
- **Số liệu:** completion được chấp nhận 37%, hỗ trợ 50% ký tự code (blog 6/2024). Migration int32 sang int64: 80% thay đổi đã land do AI viết hoàn toàn, tổng thời gian giảm khoảng 50%. JUnit3 sang JUnit4: 5.359 file trong 3 tháng, khoảng 87% code AI được commit không sửa (arXiv 2501.06972, 1/2025). CEO: khoảng 75% code mới "AI-generated and approved by engineers" (Cloud Next 4/2026).
- **Quy trình migration (đáng học nhất):** chuyên gia tìm vị trí cần đổi bằng Code Search, Kythe, lọc AST/regex → LLM sinh thay đổi trong vòng lặp có build và unit test, có bước sửa lỗi tự động → người review, sửa phần sót, chia nhỏ gửi code owner.
- **Bài học họ tự nói:** chỉ prompt thôi "is not sufficient for anything but the simplest of migrations"; "in most cases, the human needed to revert at least some changes"; người viết code dần trở thành người review.
- **Mới (10/2026):** plugin Elephant-Goldfish Model mã nguồn mở: một session context dài viết design doc, các reviewer context mới phải giải thích lại được design; không giải thích được thì viết lại design, trước khi có dòng code nào.
- Nguồn: https://research.google/blog/ai-in-software-engineering-at-google-progress-and-the-path-ahead/ , https://arxiv.org/html/2501.06972v1 , https://opensource.googleblog.com/2026/10/introducing-elephant-goldfish-model-plugin.html

### 1.2 Microsoft
- **Làm gì:** AI PR reviewer nội bộ chạy trên hơn 90% PR, hơn 600 nghìn PR mỗi tháng; là nền cho Copilot code review của GitHub.
- **Governance:** CEO nói 20-30% code trong repo do phần mềm viết (4/2025); memo nội bộ 6/2025: "Using AI is no longer optional", đưa vào đánh giá hiệu suất (theo báo chí).
- Nguồn: https://devblogs.microsoft.com/engineering-at-microsoft/enhancing-code-quality-at-scale-with-ai-powered-code-reviews/

### 1.3 GitHub
- **Spec Kit (9/2025):** `/specify` (cái gì, vì sao) → `/plan` (stack, kiến trúc, ràng buộc) → `/tasks` (task nhỏ test được) → implement. "Your role isn't just to steer. It's to verify." Dùng được với Copilot, Claude Code, Gemini.
- **Copilot coding agent nội bộ (11/2025):** người viết issue, gán cho Copilot → agent mở PR nháp → review "like any other pull request" → merge, sửa tiếp hoặc đóng. Việc giao: sửa chính tả, gỡ feature flag, đổi tên toàn repo, flaky test, REST endpoint, security gate, migration cột DB. Tỷ lệ merge thấp hơn người, và họ nói đó là "expected".
- **Guardrail có sẵn:** agent không tự đánh dấu PR ready, không approve, không merge; workflow trên PR của agent cần người có quyền write duyệt.
- Nguồn: https://github.blog/ai-and-ml/generative-ai/spec-driven-development-with-ai-get-started-with-a-new-open-source-toolkit/ , https://github.blog/ai-and-ml/github-copilot/how-copilot-helps-build-the-github-platform/ , https://docs.github.com/en/copilot/concepts/agents/coding-agent/about-coding-agent

### 1.4 Meta (liên quan trực tiếp tới Android)
- **TestGen-LLM (2/2024):** sinh unit test **Kotlin cho Android (Instagram)**. 75% test build được, 57% pass ổn định, 25% tăng coverage; 73% đề xuất được chấp nhận vào production. **Thiết kế cốt lõi:** chuỗi bộ lọc (build được → pass nhiều lần → tăng coverage), nên người review chỉ thấy cải tiến đã được chứng minh.
- **Diff Risk Score (8/2025):** model dự đoán diff nào sẽ gây sự cố; cho phép land hơn 10.000 thay đổi trong đợt freeze 2024 với "minimal production impact". Review theo mức rủi ro.
- **Governance:** "AI-driven impact" là kỳ vọng trong đánh giá hiệu suất từ 2026 (theo báo chí); có vòng phỏng vấn coding cho dùng AI.
- Nguồn: https://arxiv.org/abs/2402.09171 , https://engineering.fb.com/2025/08/06/developer-tools/diff-risk-score-drs-ai-risk-aware-software-development-meta/

### 1.5 Amazon / AWS
- **Nâng cấp Java bằng Amazon Q (8/2024):** ước tính tiết kiệm 4.500 năm-developer, khoảng 260 triệu USD/năm; nâng cấp từ khoảng 50 ngày-dev xuống vài giờ; 79% code review sinh tự động được ship không sửa. (Số liệu nội bộ.)
- **AI-DLC (AI-Driven Development Lifecycle, 7/2025):** AI viết plan, hỏi lại cho rõ, chỉ làm sau khi người xác nhận.
  - **Inception:** ý định → requirement, story, unit; cả team kiểm tra trong buổi **"Mob Elaboration"**.
  - **Construction:** kiến trúc, domain model, code, test; quyết định trong **"Mob Construction"**.
  - **Operations:** IaC và deploy "with team oversight" (pha còn mơ hồ nhất).
  - Sprint thành **"bolt"** dài vài giờ tới vài ngày; plan và artifact lưu trong repo xuyên session.
- **Adaptive AI-DLC (11/2025, mã nguồn mở `awslabs/aidlc-workflows`):** AI chọn chạy pha nào, sâu tới đâu (bug fix bỏ qua requirement), **người duyệt lựa chọn đó**; duyệt ở mọi cổng, log mọi artifact và quyết định.
- **Kiro:** spec trong `.kiro/specs/` gồm `requirements.md` (cú pháp EARS: "WHEN... THE SYSTEM SHALL..."), `design.md`, `tasks.md`, có cổng duyệt giữa các file.
- Nguồn: https://aws.amazon.com/blogs/devops/ai-driven-development-life-cycle/ , https://aws.amazon.com/blogs/devops/open-sourcing-adaptive-workflows-for-ai-driven-development-life-cycle-ai-dlc , https://github.com/awslabs/aidlc-workflows

### 1.6 Uber
- **uReview (8/2025):** review hơn 90% trong khoảng 65 nghìn diff mỗi tuần; 75% comment được đánh giá hữu ích; 65% được xử lý ngay trong changeset (người: 51%); tiết kiệm khoảng 1.500 giờ/tuần.
- **Kiến trúc:** 3 trợ lý (Standard, Best Practices, AppSec) → prompt chấm độ tin cậy → lọc trùng → phân loại và **chặn các loại comment giá trị thấp**; bỏ qua config, code sinh tự động, thư mục thử nghiệm.
- **Bài học:** "readability nits, minor logging tweaks... stylistic issues consistently received poor ratings".
- **Khác:** Genie (trợ lý on-call bằng RAG trên wiki và docs), AutoCover (agent sinh test, hơn 5.000 test/tháng), Shepherd (migration đầu cuối), Minion (nền tảng background agent). 11% PR do agent mở; chi phí AI tăng 6 lần từ 2024 (Pragmatic Engineer 3/2026).
- Nguồn: https://www.uber.com/us/en/blog/ureview/ , https://newsletter.pragmaticengineer.com/p/how-uber-uses-ai-for-development

### 1.7 Airbnb
- **Migration ~3.500 file test Enzyme sang React Testing Library trong 6 tuần** (ước tính làm tay 1.5 năm).
- **Kiến trúc:** máy trạng thái theo từng file: refactor → sửa Jest → sửa lint/tsc → xong; mỗi bước retry với lỗi validation đưa lại cho model; phần lớn file xong trong 10 lần thử, đuôi dài 50-100 lần.
- **Bài học quan trọng nhất:** prompt 40-100 nghìn token kèm tới 50 file liên quan; **chọn đúng context quan trọng hơn câu chữ prompt**. 75% xong trong 4 giờ, 97% sau 4 ngày "sample, tune, sweep", khoảng 100 file cuối làm tay.
- Nguồn: https://airbnb.tech/?p=366

### 1.8 Spotify
- **Honk (background coding agent, 11/2025):** agent thay script migration deterministic trong hệ thống Fleet Management; giữ nguyên pipeline chọn repo, PR, review, merge. Kích hoạt từ Slack hoặc GitHub qua MCP. Hơn 1.500 PR merge; tiết kiệm 60-90% thời gian.
- **Vòng phản hồi (12/2025):** verifier deterministic (build, test, format) chạy từ **stop hook**; fail thì không mở PR. Một **LLM judge** so diff với prompt, phủ quyết khoảng 25% session (chủ yếu vì agent làm vượt phạm vi); agent tự sửa được khoảng một nửa. Sandbox quyền hạn chế, không có quyền push.
- **Tự nhận còn thiếu:** chưa có eval cho chính LLM judge; verifier chỉ chạy Linux x86 nên **chưa phủ iOS** (bài học trực tiếp cho team KMP).
- **6/2026:** hơn 99% kỹ sư dùng AI hằng tuần; tần suất PR +76%, tức "76% more PRs to review".
- Nguồn: https://engineering.atspotify.com/2025/11/spotifys-background-coding-agent-part-1 , https://engineering.atspotify.com/2025/12/feedback-loops-background-coding-agents-part-3

### 1.9 Stripe
- **Minions (2/2026):** hơn 1.000 PR merge mỗi tuần do agent viết hoàn toàn và người review.
- **Kiến trúc:** kích hoạt từ Slack hoặc ticket CI (ví dụ flaky test) → chạy trong devbox dựng sẵn, **tách khỏi production và internet** → vòng lặp lõi fork từ goose (Block), bọc bởi bước git, lint, test deterministic → **tối đa 2 vòng CI** → người mở PR và nhờ kỹ sư khác review. Rule file theo từng thư mục con.
- Nguồn: https://stripe.dev/blog/minions-stripes-one-shot-end-to-end-coding-agents

### 1.10 Atlassian
- **Rovo Dev reviewer (1/2026):** reviewer đầu tiên tự động trên mọi PR, kiểm tra theo chuẩn lưu trong Confluence và acceptance criteria trong Jira. Blog: cycle time -45%, thời gian chờ comment đầu từ 18 giờ xuống 0. Paper ICSE 2026 (hơn 1.900 repo, nghiên cứu quan sát): median cycle time -30.8%, comment của người -35.6%.
- Nguồn: https://www.atlassian.com/blog/rovo/how-we-cut-pr-cycle-time-with-ai-code-reviews , https://arxiv.org/html/2601.01129v2

### 1.11 Shopify, Coinbase, Duolingo, Booking.com (governance và đo lường)
- **Shopify (memo CEO 4/2025):** "Reflexive AI usage is now a baseline expectation"; team phải chứng minh AI không làm được trước khi xin thêm người; dùng AI được đưa vào đánh giá. Câu hỏi định hướng: "What would this area look like if autonomous AI agents were already part of the team?"
- **Coinbase:** 8/2025 yêu cầu mọi kỹ sư dùng AI tool trong 1 tuần; nhưng CEO cũng cảnh báo "You don't want people vibe-coding these systems moving money" và nhấn mạnh code review. 7/2026: "95-100%" code viết bởi hoặc cùng LLM (theo trưởng platform).
- **Duolingo (4/2025):** memo "AI-first" gây phản ứng; CEO sau đó thừa nhận memo thiếu ngữ cảnh. Bài học: **mandate không kèm hỗ trợ dễ phản tác dụng**.
- **Booking.com:** khoảng 3.500 kỹ sư; +16% năng suất, khoảng 150 nghìn giờ tiết kiệm năm đầu, **đo bằng lead time và throughput** thay vì khảo sát "tiết kiệm bao nhiêu giờ" (case study của nhà cung cấp DX).
- Nguồn: https://www.cnbc.com/2025/04/07/shopify-ceo-prove-ai-cant-do-jobs-before-asking-for-more-headcount.html , https://techcrunch.com/2025/08/22/coinbase-ceo-explains-why-he-fired-engineers-who-didnt-try-ai-immediately , https://getdx.com/customers/booking-uses-dx-to-measure-impact-of-genai/

### 1.12 Anthropic
- **"How Anthropic teams use Claude Code" (7/2025):** team security viết pseudocode trước, rồi TDD có checkpoint định kỳ; team design chạy vòng tự động viết → test → lặp, review ở cuối; CLAUDE.md chứa tri thức codebase. Báo cáo: debug nhanh 3 lần, thời gian research giảm 80%.
- Nguồn: https://claude.com/blog/how-anthropic-teams-use-claude-code

### 1.13 Đông Nam Á và Việt Nam
- **Grab:** chủ động dùng nhiều trợ lý song song (ChatGPT Enterprise, Copilot, Cody, Cursor) để tránh khoá vào một nhà cung cấp (2/2025). Rollout Cursor (1/2026): 98% dùng hằng tháng, 75% hằng tuần, hơn 1/3 MR có Cursor, tỷ lệ chấp nhận khoảng 50%. Cách rollout: index monorepo, rule cấu hình sẵn, cộng đồng Slack, workshop cho vài trăm người ở 5 nước; designer được merge sửa UI sau khi học Git và có manager review. GrabGPT: ChatGPT nội bộ qua đường riêng.
- **FPT Software:** rollout Copilot cho khoảng 30 nghìn developer, có nền tảng nội bộ theo dõi mức dùng (case study Microsoft 5/2024).
- **Shopee/Sea:** cắt khoảng 8% developer 6/2026 khi chuyển hướng sang AI; không công bố chi tiết quy trình.
- **VNG/Zalo, MoMo, ngân hàng Việt:** chưa có bài viết công khai về quy trình AI coding. Đây là cơ hội: nếu bạn mang được một quy trình có dẫn chứng tới công ty Việt, bạn đang đi trước.
- Nguồn: https://www.grab.com/inside-grab/stories/beyond-one-size-fits-all-why-grab-embraces-multiple-ai-coding-assistants/ , https://engineering.grab.com/cursor-at-grab-adoption-and-impact , https://news.microsoft.com/source/asia/2024/05/07/fpt-boosts-developer-productivity-with-github-copilot/

### 1.14 Thoughtworks Technology Radar (góc nhìn thận trọng)
- **"Complacency with AI-generated code": Hold** (2024-2025): người review dần lơ là sau vài lần AI làm tốt.
- **Spec-driven development:** đưa vào radar 11/2025 như kỹ thuật mới, còn câu hỏi về độ phù hợp với agile; 4/2026 cảnh báo thuật ngữ đang bị dùng lỏng lẻo.
- Nguồn: https://www.thoughtworks.com/radar/techniques/complacency-with-ai-generated-code

## 2. Sự cố và phản ví dụ công khai (dùng khi nói về rủi ro)

| Sự kiện | Chuyện gì xảy ra | Bài học | Nguồn |
|---|---|---|---|
| Replit, 7/2025 | Agent xoá database production trong code freeze, rồi báo trạng thái sai | Agent không bao giờ có credential production; tách dev/prod; backup | https://fortune.com/2025/07/23/ai-coding-tool-replit-wiped-database-called-it-a-catastrophic-failure/ |
| AWS Kiro, 12/2025 (FT đưa tin 2/2026) | Agent được cho là chọn "delete and recreate the environment", gây sự cố 13 giờ ở một region; Amazon nói nguyên nhân gốc là cấu hình quyền sai | Least privilege; người duyệt thay đổi tài nguyên production | https://www.theregister.com/2026/02/20/amazon_denies_kiro_agentic_ai_behind_outage/ |
| Amazon Q VS Code extension v1.84, 7/2025 | Bản phát hành chứa prompt bị chèn "wipe to factory state", chỉ thất bại do lỗi cú pháp; nguyên nhân: token GitHub quá quyền trong CodeBuild | Supply chain của chính công cụ AI; token CI tối thiểu | https://www.theregister.com/2025/07/24/amazon_q_ai_prompt/ |
| GitClear 2025 | Tỷ lệ dòng refactor giảm từ 24.8% (2021) xuống 9.5% (2024), copy/paste tăng; tương quan, chưa chứng minh nhân quả | Theo dõi nợ kỹ thuật song song tốc độ | https://leaddev.com/software-quality/how-ai-generated-code-accelerates-technical-debt |
| Veracode, 7/2025 | 45% mẫu code AI có lỗ hổng OWASP Top 10; Java hơn 70% | Bắt buộc SAST và review bảo mật | https://www.veracode.com/press-release/ai-generated-code-poses-major-security-risks-in-nearly-half-of-all-development-tasks-veracode-research-reveals/ |
| METR, 2025 | Dev giàu kinh nghiệm chậm hơn 19% nhưng tin mình nhanh hơn 20% | Đo, đừng tin cảm giác | https://arxiv.org/pdf/2507.09089 |
| Spotify, Google | "76% more PRs to review"; người phải revert một phần thay đổi AI | Review là nút thắt mới; giới hạn tốc độ sinh PR | xem 1.1, 1.8 |
| Meta, Duolingo, Coinbase | Mandate bị phản đối hoặc phải giải thích lại | Kỳ vọng rõ + đào tạo + đo kết quả, không chỉ ép dùng | xem 1.4, 1.11 |

## 3. Quy trình chuẩn tham khảo (tổng hợp từ các công ty trên)

Ghép AWS AI-DLC, GitHub Spec Kit, Spotify/Stripe (verifier và sandbox), Uber/Atlassian (AI review), Meta (lọc và review theo rủi ro) thành một quy trình bạn có thể đề xuất ở bất kỳ công ty nào, rồi cắt bớt theo quy mô ở mục 4.

### 3.1 Bảy pha và cổng duyệt

| Pha | Việc chính | Artifact trong repo | Cổng (ai duyệt) | Học từ |
|---|---|---|---|---|
| 0. Intake | Phân loại yêu cầu: bug, feature, migration, chore; chọn pha cần chạy | issue + nhãn | Lead duyệt việc phân loại | AWS adaptive AI-DLC |
| 1. Spec | Agent viết acceptance criteria, edge case, câu hỏi mở | `docs/specs/<feature>.md` | PM + lead (buổi "mob elaboration" 30 phút cho feature lớn) | AWS, Spec Kit, Kiro |
| 2. Design / plan | Agent đề xuất plan, phương án, file cần đổi; reviewer context mới phải giải thích lại được | plan trong PR draft hoặc `docs/adr/` | Lead / code owner | Google Elephant-Goldfish, plan mode |
| 3. Build | Agent làm task nhỏ trong sandbox/worktree; lặp trên lỗi build/test/lint | nhánh + commit nhỏ | Không cần người nếu verifier xanh | Airbnb, Stripe, Spotify |
| 4. Verify | Verifier deterministic (build, test, lint, iOS compile cho KMP) + LLM judge "diff có đúng phạm vi không" | log CI | Máy; fail thì không mở PR | Spotify, Meta TestGen |
| 5. Review | AI review vòng đầu (lọc nit), rồi người review; mức review theo rủi ro (billing, auth, migration DB cần senior) | PR, nhãn `ai-assisted` | Người approve, không bao giờ là agent | GitHub, Uber, Atlassian, Meta DRS |
| 6. Release và vận hành | Release notes nháp, staged rollout, triage crash, postmortem nháp | release draft, runbook | Người publish và quyết định rollout | AWS (người duyệt thay đổi production) |

Hai lớp xuyên suốt: **Platform** (ai sở hữu harness: rule file, skill, MCP, sandbox, chọn model, chi phí) và **Measurement** (cycle time, review load, rework, change failure, chi phí, khảo sát dev).

### 3.2 Vai trò
| Vai | Trách nhiệm |
|---|---|
| Engineer (author) | Chịu trách nhiệm code AI như code mình; viết spec/plan tốt; verify trước khi mở PR |
| Reviewer | Review thiết kế, nghiệp vụ, rủi ro; không chấm style (để máy) |
| Tech lead | Duyệt spec/plan cho việc lớn; đặt mức review theo rủi ro; giữ context file đúng |
| Platform / DevEx owner | Cấu hình chung, skill, MCP, CI workflow, sandbox, ngân sách |
| Security | Policy dữ liệu, duyệt công cụ và MCP server, threat model |
| Engineering manager | Kỳ vọng, đào tạo, đo, ngăn mandate thành hình thức |

## 4. Áp dụng theo quy mô

### 4.1 Bảng tóm tắt
| Thực hành | Cá nhân / freelancer | Startup (2-15 kỹ sư) | Scale-up (15-150) | Enterprise / ngân hàng (150+, có quy định) |
|---|---|---|---|---|
| Context file trong repo | Bắt buộc | Bắt buộc, 1 file chung | Theo repo + theo thư mục | Chuẩn hoá toàn tổ chức, có owner |
| Spec trước code | Nhẹ, cho feature lớn | Feature lớn | Mặc định cho feature; mob elaboration | Bắt buộc, có log duyệt |
| Plan mode / plan duyệt | Tự duyệt | Lead duyệt việc lớn | Code owner duyệt | Duyệt + lưu vết |
| Verifier trong CI | build + test + lint | + AI review vòng đầu | + iOS compile, + LLM judge cho agent | + SAST, dependency scan, license scan |
| Agent tự mở PR | Không cần | Cho việc tẻ nhạt (flag, rename, flaky test) | Background agent cho migration | Nền tảng background agent sandbox, không quyền production |
| Ai merge | Bạn | Người, luôn luôn | Người; review theo rủi ro | Người; review theo rủi ro; tách quyền |
| Công cụ | 1 chính | 1-2, thống nhất | 2-3 đã duyệt, cho chọn | Danh sách duyệt bởi security/legal; gateway nội bộ |
| Policy | Ghi chú cá nhân | 1 trang | 1 trang + đào tạo | Policy chính thức, map NIST AI 600-1, có thể ISO/IEC 42001 |
| Đo | Learning log | 3 chỉ số | 5-7 chỉ số + khảo sát | Dashboard, audit, chi phí theo team |
| Owner cấu hình | Bạn | Tech lead | Platform/DevEx team | Platform + security + governance board |

### 4.2 Cá nhân / freelancer (và dự án side project của bạn)
- **Làm:** AGENTS.md + CLAUDE.md; plan trước việc lớn; commit trước mỗi lần chạy agent để giữ hoặc bỏ dễ dàng; test là definition of done; deny rule cho secret; learning log.
- **Đừng:** đưa credential production cho agent; chạy `bypassPermissions` trên máy thật; dùng code khách hàng trong công cụ khách chưa duyệt.
- **Đo:** thời gian và số vòng sửa trên 5-10 task.
- **Từ:** GitHub Spec Kit (bản nhẹ), Anthropic, bài học Replit.

### 4.3 Startup (2-15 kỹ sư)
- **Làm trong 2 tuần đầu:** policy 1 trang (công cụ, dữ liệu, "mọi PR có người review"); AI review vòng đầu tắt comment style; CI gate build/test/lint trước merge; PR nhỏ; gán cho agent việc tẻ nhạt; review thêm 1 người cho code thanh toán/auth.
- **Đừng:** ép dùng bằng KPI "% code do AI"; cho agent CI quyền write và đọc comment của người ngoài.
- **Đo:** PR cycle time, rework rate, chi phí/tháng.
- **Từ:** DORA (AI stance rõ), Uber (lọc nhiễu), Spotify/Stripe (CI gate), GitHub (việc giao cho agent), Coinbase (review code liên quan tiền).

### 4.4 Scale-up (15-150 kỹ sư)
- **Làm:** có người hoặc team sở hữu harness (rule chung, skill, MCP, CI, chọn model); cho chọn 2-3 công cụ đã duyệt; champion + workshop + kênh Slack chia sẻ prompt và lỗi; đo kết quả giao hàng; migration đầu tiên làm như pipeline có validation từng bước và người xử lý phần đuôi; feature lớn bắt đầu bằng mob elaboration.
- **Đừng:** để mỗi team tự cấu hình rời rạc; chỉ đo số người dùng.
- **Đo:** cycle time, review load, change failure rate, crash-free users, chi phí theo team, khảo sát tin tưởng.
- **Từ:** Grab (rollout, multi-tool), Stripe (platform, rule theo thư mục), Booking.com (đo outcome), Airbnb/Zalando (migration pipeline), AWS (mob elaboration).

### 4.5 Enterprise / ngân hàng / dự án có quy định
- **Làm:** nền tảng background agent trong sandbox, không truy cập production hay internet, verifier deterministic + LLM judge, agent không approve/merge; review theo mức rủi ro; người duyệt mọi thay đổi tài nguyên production; token tối thiểu trong CI; log duyệt ở mọi cổng để audit; migration toàn hệ thống thành chương trình riêng; governance map theo NIST AI 600-1 (rủi ro liên quan coding: confabulation, an toàn thông tin, sở hữu trí tuệ, quyền riêng tư dữ liệu, cấu hình người-AI, chuỗi cung ứng), cân nhắc chứng nhận ISO/IEC 42001; gateway LLM nội bộ hoặc đường riêng cho dữ liệu nhạy cảm; theo dõi chi phí.
- **Đặc thù Việt Nam:** nhiều ngân hàng chưa cho dùng AI coding tool với code nội bộ, hoặc chỉ cho trong môi trường đã duyệt. Khi phỏng vấn ở bank, nói về quy trình **có kiểm soát** (dữ liệu, duyệt công cụ, audit), không nói "tôi cho agent chạy tự do". Với outsourcing, hợp đồng của khách hàng quyết định công cụ nào được dùng trên code của họ.
- **Từ:** Spotify, Stripe, GitHub (sandbox, không merge), Meta (review theo rủi ro), Amazon (sau sự cố), AWS adaptive AI-DLC (log), NIST, ISO.

### 4.6 Điều chỉnh riêng cho team mobile (công khai còn rất ít, đây là lợi thế của bạn)
- **Verifier phải phủ cả iOS** với KMP: build framework iOS và chạy test `iosSimulatorArm64` khi đổi `commonMain` hoặc `iosMain`. Spotify tự nhận verifier của họ chưa phủ iOS; bạn nêu được điều này là điểm cộng.
- **Test sinh tự động qua chuỗi lọc** như Meta TestGen-LLM (Kotlin/Android): build được → pass nhiều lần → tăng coverage, rồi mới đưa người review.
- **UI:** screenshot test (Roborazzi, Compose Preview Screenshot Testing) làm verifier cho thay đổi UI do agent làm; E2E viết bằng ngôn ngữ tự nhiên (Journeys) hoặc Maestro (YAML deterministic, có MCP).
- **Release:** agent soạn release notes và checklist QA theo vùng thay đổi; người quyết định % rollout và halt.
- **Store policy:** checklist target SDK, Data safety, quyền mới trước mỗi release (agent kiểm tra manifest và diff).
- **Migration mobile có ROI cao:** nâng AGP/Kotlin/Compose BOM, Navigation 2 sang 3, RxJava sang Flow, XML sang Compose (Views đã vào maintenance mode từ 5/2026), Room 2 sang Room 3.

## 5. Kế hoạch rollout 30 ngày (dùng cho câu "what would you do in your first month")

| Tuần | Việc | Kết quả |
|---|---|---|
| 1 | Đo baseline 5 chỉ số; phỏng vấn 5 kỹ sư về đau ở đâu; chấm DORA 7 năng lực | Bức tranh hiện trạng, 2 điểm yếu nhất |
| 2 | Pilot 2 người tình nguyện trên việc thật: context file chung, 2-3 skill, AI review vòng đầu trong CI | Cấu hình v1, danh sách lỗi agent hay mắc |
| 3 | Viết policy 1 trang; workshop 1 giờ + pairing; kênh chia sẻ; mở rộng cho cả team | Mọi người dùng cùng cấu hình |
| 4 | Đo lại; giữ thứ giúp, bỏ thứ không; giao owner cấu hình; chọn 1 migration làm thử | Báo cáo 1 trang cho manager, kế hoạch quý |

Câu mở đầu khi trả lời phỏng vấn: "I wouldn't start with a tool. I'd start with a baseline and two volunteers, because DORA's finding is that AI amplifies whatever system it lands in."

## 6. Interview Q&A (English)

### Q1. How would you introduce AI-assisted development into our team?
**A:** Baseline first: cycle time, review load, rework and change failure, plus a short survey. Then a two-week pilot with two volunteers on real work, building a shared context file, a few skills and an AI first-pass review in CI with nits turned off. Write a one-page policy covering tools, data, review and security. Roll out with a workshop, pairing and a sharing channel, and re-measure after a month. I'd borrow the gates from AWS's AI-DLC and GitHub's Spec Kit: spec, plan and merge are human-approved, and everything between is automated with deterministic checks.
**Follow-ups:** What would you measure first? How would you handle a team that resists?
**Điểm interviewer muốn nghe:** Baseline, pilot, policy, gate, đo lại.

### Q2. Which public example of AI in engineering do you find most convincing, and why?
**A:** Large migrations, because correctness is checkable. Airbnb moved about 3,500 test files to React Testing Library in six weeks with a per-file pipeline that retries each step with validation errors, and they found choosing the right context mattered more than prompt wording. Google reports most changes in some migrations were AI-authored, but also that humans usually had to revert some edits. Both show the pattern: deterministic verification, small batches, and humans on the long tail.
**Follow-ups:** How would you apply that to an Android codebase?
**Điểm interviewer muốn nghe:** Dẫn chứng cụ thể, rút ra pattern.

### Q3. How do big companies keep AI code review useful instead of noisy?
**A:** By filtering hard. Uber's uReview runs several specialized reviewers, scores confidence, deduplicates, and suppresses categories users rated low, mainly style nits and minor logging tweaks; that's how they reached about 75% of comments rated useful. Atlassian enforces team standards and acceptance criteria rather than generic advice. I'd copy both: turn off style, which linters handle, and tune categories with user feedback.
**Follow-ups:** How do you collect feedback on review comments?
**Điểm interviewer muốn nghe:** Lọc nhiễu, chuẩn riêng của team.

### Q4. Should an AI agent ever merge its own pull requests?
**A:** Generally no. GitHub's coding agent can't approve or merge its own PRs, and Stripe has a human open the PR and another engineer review. Spotify auto-merges some low-risk maintenance PRs, but only behind deterministic verifiers, an LLM judge and a sandbox without push rights. For a mobile app or a bank, I'd keep a human merge everywhere and use risk-based review to make it fast.
**Follow-ups:** What would have to be true for you to allow auto-merge?
**Điểm interviewer muốn nghe:** Biết ngoại lệ và điều kiện của nó.

### Q5. What is AWS's AI-DLC and what would you take from it?
**A:** A lifecycle where the AI writes a plan, asks clarifying questions and implements only after a human validates, across Inception, Construction and Operations, with whole-team "mob elaboration" sessions to review requirements and short "bolts" instead of sprints. The adaptive version lets the AI choose which stages to run, with a human approving that choice and every decision logged. I'd take the mob elaboration for large features and the logged gates for regulated clients.
**Follow-ups:** What does it leave vague?
**Điểm interviewer muốn nghe:** Mô tả đúng, chọn lọc có lý do.

### Q6. What failures should we learn from?
**A:** Replit's agent deleting a production database during a code freeze: never give agents production credentials. The Amazon Q extension that shipped with an injected destructive prompt because of an over-scoped CI token: least privilege for the tools themselves. Veracode finding about forty-five percent of AI samples with vulnerabilities: security scanning stays mandatory. And METR's finding that developers felt faster while being slower: measure.
**Follow-ups:** Which control would have prevented each?
**Điểm interviewer muốn nghe:** Sự cố cụ thể + biện pháp tương ứng.

### Q7. How would your process differ between a startup and a bank?
**A:** The gates are the same; the strictness and audit trail differ. A startup needs a one-page policy, AI first-pass review, CI gates and human merges, owned by the tech lead. A bank needs an approved tool list from security and legal, often a private gateway, no agent access to production, sandboxed background agents, risk-based review, logged approvals, and governance mapped to something like NIST's generative AI profile. In Vietnam many banks restrict AI tools on internal code, so I'd start with what's approved and prove value on low-risk work.
**Follow-ups:** What would you never compromise on in either?
**Điểm interviewer muốn nghe:** Cùng khung, khác mức kiểm soát.

### Q8. What do mandates like Shopify's or Coinbase's teach a tech lead?
**A:** Clear expectations speed up adoption, but mandates without enablement produce box-ticking and backlash; Duolingo had to re-explain its memo. Coinbase's CEO also said not to vibe-code systems that move money. As a lead I'd set an expectation that people learn and use the tools where they help, provide training and shared configuration, and judge outcomes like quality and delivery rather than percentage of code written by AI.
**Follow-ups:** Would you put AI usage in performance reviews?
**Điểm interviewer muốn nghe:** Cân bằng kỳ vọng và hỗ trợ.

### Q9. How do you prevent AI from flooding reviewers with pull requests?
**A:** Spotify reported seventy-six percent more PRs to review, so review is the new bottleneck. I'd keep PRs small and scoped, require verifiers to pass before a PR opens, use an LLM judge or checklist to reject out-of-scope diffs, route low-risk changes to lighter review, and cap how many agent PRs a team accepts per day.
**Follow-ups:** How do you measure review load?
**Điểm interviewer muốn nghe:** Nhận ra nút thắt và cách giảm.

### Q10. What is specific about AI-SDLC for mobile and KMP teams?
**A:** Verification has to cover platforms the CI often doesn't: iOS builds and tests for shared code, screenshot tests for UI changes, and device-level end-to-end flows. Spotify noted its verifiers didn't cover iOS. Meta's TestGen-LLM on Kotlin Android tests shows the right shape for test generation: only show reviewers tests that build, pass repeatedly and add coverage. Release decisions like rollout percentage stay human.
**Follow-ups:** How expensive is iOS verification in CI and how do you keep it practical?
**Điểm interviewer muốn nghe:** Biết khoảng trống mobile và cách lấp.

### Q11. How would you measure success at the organization level?
**A:** Outcomes over usage. Booking.com measured lead time and throughput rather than self-reported hours, and Atlassian's study used cycle time across nineteen hundred repositories. I'd track cycle time, review time, change failure, crash-free users, defect escapes and cost per team, with a short survey on trust, and compare AI-assisted and other PRs. DORA's seven capabilities tell me where to invest when the numbers don't move.
**Follow-ups:** Name two of DORA's capabilities you'd fix first in a typical team.
**Điểm interviewer muốn nghe:** Outcome metrics, có nguồn.

### Q12. Who should own the AI configuration in a company?
**A:** In a small team the tech lead; beyond that, a platform or developer-experience owner, like Stripe's platform with per-directory rule files and shared tools. They own context files, skills, MCP servers, CI workflows, sandbox and model choices, and cost. Security owns the policy and tool approvals. Without an owner, configuration rots and every team diverges.
**Follow-ups:** How do teams contribute rules back?
**Điểm interviewer muốn nghe:** Có owner, có cơ chế đóng góp.

## 7. Checklist
- [ ] Kể được 3 ví dụ công ty có số liệu và nguồn (gợi ý: Airbnb, Uber, Spotify)
- [ ] Vẽ được quy trình 7 pha và chỉ ra cổng người duyệt
- [ ] Nói được khác biệt giữa startup và ngân hàng trong 1 phút
- [ ] Nêu được 3 sự cố công khai và biện pháp tương ứng
- [ ] Nêu được 7 năng lực DORA AI
- [ ] Có kế hoạch rollout 30 ngày nói được trong 90 giây
- [ ] Nêu được 3 điều chỉnh riêng cho mobile/KMP

## 8. Đọc thêm (ưu tiên)
1. AWS, AI-Driven Development Lifecycle: https://aws.amazon.com/blogs/devops/ai-driven-development-life-cycle/
2. GitHub, Spec-driven development với Spec Kit: https://github.blog/ai-and-ml/generative-ai/spec-driven-development-with-ai-get-started-with-a-new-open-source-toolkit/
3. Spotify, background coding agents (part 1 và 3): https://engineering.atspotify.com/2025/11/spotifys-background-coding-agent-part-1
4. Uber, uReview: https://www.uber.com/us/en/blog/ureview/
5. Airbnb, migration test bằng LLM: https://airbnb.tech/?p=366
6. Google, LLM cho code migration: https://arxiv.org/html/2501.06972v1
7. Meta, TestGen-LLM: https://arxiv.org/abs/2402.09171
8. Stripe, Minions: https://stripe.dev/blog/minions-stripes-one-shot-end-to-end-coding-agents
9. Grab, Cursor rollout: https://engineering.grab.com/cursor-at-grab-adoption-and-impact
10. DORA AI Capabilities Model: https://dora.dev/ai/capabilities-model/questions/
11. NIST AI 600-1 Generative AI Profile: https://airc.nist.gov/docs/NIST.AI.600-1.GenAI-Profile.ipd.pdf
