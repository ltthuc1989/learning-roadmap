# 02. Gap Analysis và Định vị: bạn đang ở đâu, cần tới đâu, kể mình thế nào

> Mục tiêu: trước khi ôn, chấm trung thực từng kỹ năng theo đúng thứ JD đòi (file `01`), chọn định vị, và viết lại CV/LinkedIn/pitch sao cho interviewer đọc 30 giây là hiểu "đây là Staff mobile + lead được". Làm trong tuần 1, xem lại ở tuần 6 và tuần 12.

## 1. Thang chấm (dùng cho toàn bộ bảng bên dưới)

| Điểm | Nghĩa | Kiểm chứng |
|---|---|---|
| 0 | Chưa biết | Không giải thích được khái niệm |
| 1 | Biết tên, dùng qua AI | Dùng được khi có Claude Code, không tự giải thích |
| 2 | Dùng được, giải thích cơ bản | Trả lời được câu hỏi Senior "là gì, dùng thế nào" |
| 3 | Hiểu cơ chế, có trade-off | Trả lời được "vì sao, khi nào không dùng, lỗi hay gặp", kể được ví dụ thật |
| 4 | Dạy được, quyết định được | Giải thích cho junior, ra quyết định kiến trúc, viết được ADR, biết giới hạn |

Mức cần: **Senior IC** hầu hết 3; **Staff IC** 3-4 ở Android core, performance, security, system design; **Tech Lead** thêm 3-4 ở leadership, 2-3 ở network/backend/AI.

## 2. Skill matrix: chấm trung thực

Cột "Hiện tại" bạn tự điền. Cột "Gợi ý" là ước đoán của tôi từ code và từ việc bạn nói code được build bằng Claude Code; sửa nếu sai.

### 2.1 Android core (được hỏi ở mọi vòng kỹ thuật)

| Kỹ năng | Tần suất JD | Senior cần | Staff/Lead cần | Gợi ý | Hiện tại | File ôn |
|---|---|---|---|---|---|---|
| Kotlin nâng cao (generics, inline, delegation, context parameters, K2) | 8/15 | 3 | 4 | 2 | | 04 |
| Coroutines/Flow internals (structured concurrency, cancellation, StateFlow/SharedFlow, backpressure) | 3/15 nhưng hỏi ở mọi deep-dive | 3 | 4 | 2 | | 04 |
| Compose internals (recomposition, stability, slot table, side effects, Nav3) | 3/15 | 3 | 4 | 2 | | 04 |
| Architecture (MVVM/MVI/Clean, UDF, modularization, convention plugins) | 8/15 | 3 | 4 | 2 (code có, giải thích chưa) | | 04 |
| DI (Koin vs Hilt, scope, testing) | 2/15 | 3 | 3 | 2 | | 04 |
| Data (Room 3, DataStore, Paging, offline-first) | 2/15 | 3 | 3 | 2 | | 04, 06 |
| Build, Gradle, AGP 9, R8 (keep rules, mapping, retrace) | ít ghi, hỏi nhiều | 2 | 4 | 1 | | 04 |
| Android 16/17 behavior changes, Play policies (target 36, 16 KB, dev verification) | 4/15 | 3 | 4 | 1 | | 04 |
| Performance (startup, Baseline Profiles, Macrobenchmark, jank, ANR, memory, ART) | 10/15 | 3 | 4 | 1 | | 04 |
| Concurrency ngoài coroutines, RxJava legacy, migration Java/XML → Kotlin/Compose | ngầm ở bank | 2 | 3 | 1 | | 04 |
| Testing (MockK, Turbine, coroutines test, Compose UI test, screenshot) | 9/15 | 3 | 4 | 1 (bạn tự nhận) | | 19 |
| Git nâng cao (rebase, bisect, reflog, branching strategy) | ngầm | 3 | 4 | 1 (bạn tự nhận) | | 20 |
| CI/CD (GitHub Actions, CircleCI, Fastlane, Play tracks) | 7/15 | 2 | 4 | 1 (bạn tự nhận) | | 20 |

### 2.2 Breadth cho Staff/Lead

| Kỹ năng | Tần suất JD | Senior cần | Staff/Lead cần | Gợi ý | Hiện tại | File ôn |
|---|---|---|---|---|---|---|
| Mobile system design (framework, 11 bài) | mọi vòng Staff | 2 | 4 | 1 | | 06 |
| Network (TCP/TLS/HTTP2-3, REST/gRPC/WebSocket, OkHttp internals, retry, pinning) | REST 10/15 | 2 | 4 | 1 (bạn tự nhận) | | 07 |
| Security (MASVS, Keystore, pinning, PKCE, R8/DexGuard, Play Integrity, bank domain) | 8/15 | 2 | 4 | 1 | | 08 |
| iOS/Swift đọc và review code | 8/15 | 1 | 3 | 1 | | 05 |
| KMP/CMP (expect/actual, Swift interop, iOS integration, CMP rendering) | 2/15 nhưng là câu chuyện của bạn | 2 | 4 | 1-2 (code có, do AI) | | 05 |
| Flutter nền tảng, RN khái niệm, bảng so sánh | 8/15 | 1 | 3 | 0 (bạn tự nhận) | | 05 |
| Backend/fullstack (REST design, Ktor, DB, cache, Docker, cloud) | 3/15 | 1 | 3 | 1 | | 09 |
| UI/UX, design system, adaptive, a11y, i18n | 4/15 design system | 2 | 3 | 2 | | 21 |
| Product metrics, A/B, compliance (Play policy, PDPD, COPPA) | ngầm | 1 | 3 | 2 (COPPA đã làm) | | 22 |

### 2.3 AI và leadership

| Kỹ năng | Tần suất JD | Senior cần | Staff/Lead cần | Gợi ý | Hiện tại | File ôn |
|---|---|---|---|---|---|---|
| AI coding agent có quy trình (prompt/rules/workflow, review code AI, đo impact, mentor) | 5-7 JD, bắt buộc ở 3 | 2 | 4 | 3 (dùng nhiều, chưa hệ thống hoá) | | 10, 18 |
| AI trong sản phẩm (Gemini Nano/ML Kit, LLM API, hybrid, streaming, eval) | 2 JD | 1 | 3 | 1 (đã dùng Gemini sinh audio) | | 10 |
| LLM fundamentals (token, context, RAG, function calling, MCP, eval, injection) | ngầm | 1 | 3 | 1 | | 10 |
| Mentoring, code review culture | 8 JD | 2 | 4 | ? | | 11 |
| Architecture decision, ADR, roadmap, estimation | 5 JD | 2 | 4 | ? | | 11, 22 |
| Stakeholder, "translate tech to business" | 4 JD | 1 | 4 | ? | | 11, 23 |
| Incident, release strategy, hotfix, feature flags | 2 JD | 2 | 4 | 2 | | 11, 20 |
| Hiring, 1:1, performance feedback | 2 JD | 1 | 3 | ? | | 11 |
| Behavioral story bank (STAR) | mọi vòng | 3 | 4 | 1 | | 12 |
| DSA LeetCode Medium bằng Kotlin trong 30 phút | công ty nước ngoài | 3 | 3 | 1-2 | | 13 |
| Tiếng Anh nói trong phỏng vấn | job lương cao | 3 | 4 | ? | | 23 |

### 2.4 Cách đọc kết quả
- Mọi ô "Hiện tại" ≤ 1 ở cột "Staff/Lead cần" = 4 là **gap đỏ**: ưu tiên tuần sớm trong roadmap. Theo gợi ý, gap đỏ của bạn là: Performance, R8/Build, Testing, Git/CI, System design, Network, Security, KMP tự giải thích, STAR.
- Chấm lại ở tuần 6 và tuần 12. Mục tiêu tuần 12: không còn ô nào < 2, các ô Staff/Lead = 4 đạt ≥ 3.

## 3. Định vị: "Staff-level mobile generalist, AI-fluent lead"

**Một câu (EN):** "I am a mobile engineer with 10 years on Android who ships Kotlin Multiplatform apps to Android and iOS, owns architecture, build and release end to end, and leads teams that use AI coding agents with a review process, not blind trust."

**Vì sao định vị này thắng (từ `01`):**
- JD Staff/Architect muốn cover cả iOS và lead cross-platform: KMP production + đọc được Swift đúng chỗ.
- JD nào cũng hỏi performance, security, REST: ôn sâu 3 mảng này là lợi thế trực tiếp.
- AI coding agent là yêu cầu cứng: bạn đã dùng Claude Code để ship app thật, chỉ cần hệ thống hoá thành quy trình kể được.
- Không có JD "Android Lead" nên phải **apply vào JD Senior/Staff và thể hiện lead trong phỏng vấn**, hoặc apply Architect/Lead qua headhunter (`15`).

**Không nên định vị:** "Android dev 10 năm" (bị so với ứng viên rẻ hơn), "fullstack" (không có bằng chứng), "AI engineer" (không có nền ML).

## 4. Năm câu chuyện mạnh từ project thật của bạn

Interviewer sẽ hỏi "tell me about a project". Dưới đây là 5 câu chuyện có sẵn trong repo của bạn; mỗi câu chuyện ghi điểm ở vòng nào và cần bổ sung gì để kể cho chắc.

| # | Câu chuyện | Bằng chứng trong repo | Ghi điểm ở | Cần bổ sung trước khi kể |
|---|---|---|---|---|
| 1 | **Ship app KMP + Compose Multiplatform lên Android và iOS** (ABC Phonics Kids) | `kmp-template-impl`: `composeApp`, `core/*` 9 module, `feature/*` 7 module, Room KMP, Koin, Nav/Lifecycle KMP, target iOS | Android deep-dive, cross-platform, system design | Tự giải thích: vì sao Koin, vì sao chia module như vậy, expect/actual ở đâu, iOS integration thế nào, build time, pain point thật (`05` drill) |
| 2 | **Xây template và publish library tái sử dụng** (`io.github.ltthuc.template:ui/network/datastore/ads/billing/rating/app-update/navigation/settings-common`) | `YammyDelivery` publish lên Maven; `compose-template` consume qua version catalog; convention plugins `build-logic` | Architecture, Lead (platform thinking), design system | Số liệu: bao nhiêu app dùng, giảm bao nhiêu thời gian bootstrap app mới, cách versioning và breaking change |
| 3 | **Monetization end-to-end**: RevenueCat/Play Billing, AdMob + AppLovin, UMP consent, Remove-Ads SKU | `core/billing`, `feature/billing`, `04-BILLING_SETUP.md`, `SecretsModule`, BuildKonfig | Product/metrics, bank/fintech (payment flow), compliance | Số liệu doanh thu/conversion nếu có; cách test billing; lỗi đã gặp |
| 4 | **Quy trình delivery có cổng chặn và playbook chống lỗi**: 11 pha từ plan tới publish, FAILURE_FIRST_PLAYBOOK, CONTENT_PACK_OPERATIONS, skill `/grabee`, command `/scaffold-unit` | `docs/engineering/*`, `.claude/skills`, `.claude/commands`, `CLAUDE.md` | Leadership, AI-SDLC (câu hỏi "how do you lead a team using AI") | Kể thành quy trình: rule nào, vì sao, bug nào sinh ra rule đó, cách review code AI, cách đo |
| 5 | **App cho trẻ em tuân thủ COPPA/Families, content pipeline audio sinh bằng Gemini, CDN Firebase Storage** | `references/coppa.md`, GeminiGenerator audio project, `FIREBASE_STORAGE_BUCKET` | Compliance (`22`), AI trong sản phẩm (`10`), system design (content delivery) | Chi tiết pipeline: cache, versioning content pack, chi phí, chất lượng audio, cách fallback |

Thêm câu chuyện 6 nếu có: port app iOS sang Android bằng skill `ios-app-port` và `swift-compose-mapping.md` (chứng minh đọc Swift được, `05`).

## 5. Elevator pitch

### 5.1 60 giây (EN), dùng cho "tell me about yourself"

"I'm a mobile engineer with ten years on Android, currently shipping Kotlin Multiplatform apps to both Android and iOS. My most recent product is a children's phonics app built on a KMP and Compose Multiplatform template I designed: nine core modules, seven feature modules, Room and Koin shared across platforms, with billing, ads and a content delivery pipeline on Firebase. Before that I built and published a set of reusable Android libraries, so new apps in our family bootstrap in days instead of weeks. I care a lot about engineering process: I run an eleven-phase delivery workflow with quality gates, and I use AI coding agents daily with a review process I wrote myself. I'm looking for a Staff or Tech Lead role where I can own mobile architecture and help a team ship faster without losing quality."

Điền số thật vào: số module, số app dùng template, số ngày bootstrap, số user nếu có.

### 5.2 2 phút (EN), dùng cho vòng hiring manager
Thêm vào bản 60 giây 3 đoạn: (a) một quyết định kiến trúc khó và trade-off (ví dụ chọn Koin thay Hilt cho KMP, hoặc chia content pack tải về thay vì bundle), (b) một lần dẫn người khác (review, mentor, hoặc viết playbook để team làm đúng), (c) vì sao bạn muốn vị trí này và công ty này (lấy từ `15`).

### 5.3 Bản tiếng Việt (cho bank/công ty Việt)
Dịch ý, không dịch từng chữ: "Mình làm Android 10 năm, hiện ship app KMP lên cả Android và iOS, tự thiết kế template và bộ thư viện dùng chung, chịu trách nhiệm từ kiến trúc tới release. Mình quan tâm tới quy trình: có delivery workflow 11 pha, có playbook chống lỗi, và dùng AI coding agent hằng ngày nhưng có quy trình review rõ. Mình tìm vị trí Staff hoặc Tech Lead để chịu trách nhiệm kiến trúc mobile và giúp team ship nhanh mà vẫn chắc."

## 6. CV cho Staff/Lead: viết lại trong tuần 1

### 6.1 Cấu trúc 1-2 trang
1. **Header**: tên, title mục tiêu ("Senior/Staff Android Engineer | Kotlin Multiplatform | Tech Lead"), email, LinkedIn, GitHub/Play links, thành phố, "open to remote".
2. **Summary 3-4 dòng**: bản 60 giây rút gọn, có số.
3. **Core skills** (1 khối, nhóm theo chủ đề, dùng đúng từ khoá JD): Kotlin, Jetpack Compose, Compose Multiplatform/KMP, Coroutines/Flow, Clean Architecture/MVI, Modularization, Koin/Hilt, Room, Ktor/Retrofit/OkHttp, Performance (Baseline Profiles, Macrobenchmark), Security (Keystore, pinning, R8/DexGuard, Play Integrity), Testing (JUnit, MockK, Turbine, Compose UI Test), CI/CD (GitHub Actions, CircleCI, Fastlane), Play Console release, Firebase, Billing/RevenueCat, AI coding agents (Claude Code, Cursor, Gemini in Android Studio), Swift/SwiftUI (reading, porting).
4. **Experience**: mỗi vị trí 4-7 bullet theo công thức **Action + Scope + Metric + How**. Ưu tiên bullet có số.
5. **Selected projects / Open source**: template libs, KMP app, port iOS.
6. **Education, certificates** (ngắn).

### 6.2 Công thức bullet và ví dụ (EN)
- Sai: "Developed Android app using Kotlin and MVVM."
- Đúng: "Designed a Kotlin Multiplatform template (9 core + 7 feature modules, Koin, Room KMP) and shipped a children's phonics app to Android and iOS from one codebase; iOS share of UI code ~95%."
- "Built and published 9 reusable Android libraries (ui, network, datastore, ads, billing, rating, app-update, navigation, settings) consumed by N apps; cut new-app bootstrap from ~2 weeks to ~2 days."
- "Owned release engineering: signing, Play tracks, staged rollout, Crashlytics triage; kept crash-free users above X% across N releases." (điền số thật hoặc bỏ)
- "Defined an 11-phase delivery workflow with quality gates and a failure-first playbook; adopted by the team as the default process."
- "Introduced AI coding agents (Claude Code) with repo-level rules (CLAUDE.md), skills and a review checklist for AI-generated code; measured by PR cycle time and defect rate." (điền số nếu có)
- "Integrated RevenueCat/Play Billing, AdMob and AppLovin with UMP consent; handled COPPA constraints for a kids app."
- "Ported a SwiftUI iOS app to Jetpack Compose using a documented SwiftUI-to-Compose mapping; delivered feature parity in N weeks."

### 6.3 Từ khoá phải có (ATS và mắt người)
Staff, Principal, Tech Lead, Kotlin Multiplatform, Compose Multiplatform, iOS, Swift, architecture, modularization, performance, security, CI/CD, GitHub Actions, unit testing, mentoring, code review, cross-functional, stakeholder, AI coding agents, Claude Code, Copilot, Cursor, Gemini, banking/fintech (nếu có), English.

### 6.4 Bỏ đi
Java/XML kỹ năng cũ liệt kê dài dòng, "familiar with" mơ hồ, bullet không có kết quả, ảnh, địa chỉ nhà, "references available". Không ghi "10 năm Android" như điểm bán chính; ghi phạm vi trách nhiệm.

### 6.5 LinkedIn
- Headline: "Senior/Staff Android Engineer · Kotlin Multiplatform · Shipping to Android + iOS · AI-assisted engineering"
- About: bản 60 giây + 3 bullet thành tựu + "Open to Staff / Tech Lead roles, Vietnam or remote".
- Featured: link Play Store app, GitHub template, 1 bài viết kỹ thuật (viết 1 bài về KMP template hoặc AI review process trong tuần 3-4, 800 từ, đăng Medium/viblo/LinkedIn; đây là bằng chứng rẻ nhất).
- Bật "Open to work" cho recruiter, chọn title Staff Android, Mobile Lead, Mobile Architect, Senior Android.

## 7. Drill "Own your own code": bộ câu hỏi về repo của bạn

Mỗi tuần chọn 1 nhóm, trả lời to bằng tiếng Anh, ghi âm 10-15 phút, không mở AI. Sau đó dùng prompt trong `18` để Claude Code chấm.

### 7.1 kmp-template-impl (ABC Phonics Kids)
1. Vẽ dependency graph giữa `composeApp`, `core/*` (common, ui, datasource, repository, resource, model, billing, audio, content) và `feature/*` (home, setting, billing, learningpath, onboarding, review, download). Module nào được phép phụ thuộc module nào, rule đó nằm ở đâu (convention plugin nào)?
2. Vì sao chọn Koin thay vì Hilt? Koin module được khai báo ở đâu, scope thế nào, iOS khởi tạo Koin ra sao?
3. Room KMP: driver trên iOS là gì, migration xử lý thế nào, vì sao `androidxSqlite` ghim 2.6.1 (comment trong `libs.versions.toml` nói về iosX64)?
4. Navigation và lifecycle KMP: back stack quản lý ở đâu, deep link xử lý thế nào, state giữ qua rotation/process death ra sao?
5. Content pack tải về từ Firebase Storage: cache ở đâu, versioning thế nào, mất mạng giữa chừng thì sao, cost?
6. BuildKonfig và secrets: giá trị đi từ `local.properties` tới code qua đường nào, release build thiếu key thì fail ở đâu?
7. Billing qua RevenueCat: flow mua, restore, verify, sandbox test; vì sao `assembleBilling` vẫn FAKE billing?
8. Audio pipeline: Media3/AVAudio trên 2 nền tảng, expect/actual ở đâu, lỗi đã gặp?
9. COPPA: rule nào ảnh hưởng tới code (voice, parental gate, ads), bạn kiểm tra thế nào?
10. Nếu phải thêm 1 feature module mới hôm nay, bạn làm những bước gì (và `/scaffold-unit` làm gì hộ bạn)?

### 7.2 compose-template và YammyDelivery (bộ thư viện)
1. Vì sao tách thành 9 artifact thay vì 1? Artifact nào phụ thuộc artifact nào?
2. `publishToMavenLocal` và version catalog: thêm 1 alias mới cần sửa những gì, breaking change xử lý thế nào?
3. Convention plugin `composetemplate.android.application` làm gì? Nếu AGP lên major mới, chỗ nào vỡ trước?
4. `RemoveAdsController` được "auto-provided" bằng cơ chế gì? Nếu app muốn override thì sao?
5. Ads: app-open, interstitial, banner, UMP consent: thứ tự khởi tạo, lỗi "ad shown before consent" tránh thế nào?
6. Dark mode và theme: `ComposeTemplateTheme` quản lý system bar ra sao, vì sao có rule riêng về `themeMode`?
7. Rule "rules flow upstream": kể 1 bug thật sinh ra 1 rule trong CLAUDE.md.

### 7.3 Câu hỏi interviewer sẽ hỏi khi nghe "tôi build bằng Claude Code"
- "What did the agent get wrong, and how did you catch it?"
- "Which parts did you write or rewrite by hand, and why?"
- "How do you make sure you understand code you did not type?"
- "What is your review checklist for AI-generated code?"
Chuẩn bị 2 ví dụ thật cho mỗi câu (`10`, `18`).

## 8. Checklist tuần 1
- [ ] Chấm xong 3 bảng skill matrix, đánh dấu gap đỏ
- [ ] Chọn và viết ra định vị 1 câu (EN + VI)
- [ ] Điền số thật vào 5 câu chuyện mục 4
- [ ] Ghi âm pitch 60 giây và 2 phút (EN), nghe lại, sửa 1 lần
- [ ] CV mới ≤ 2 trang theo mục 6, nhờ 1 người đọc 30 giây và nói lại bạn là ai
- [ ] LinkedIn headline + about + featured cập nhật, bật Open to work
- [ ] Làm drill 7.1 câu 1-3, ghi âm
- [ ] Lên lịch viết 1 bài kỹ thuật 800 từ vào tuần 3-4
