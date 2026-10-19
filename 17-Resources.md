# 17. Resources: sách, link, và reading plan theo tuần

> Nguyên tắc: official docs trước (miễn phí, mới nhất), sách chỉ đọc chương được gán, blog chỉ khi docs không giải thích đủ "vì sao". Mọi link ở đây là trang gốc (root) để ít chết; link sâu hơn nằm trong mục "Đọc thêm" của từng file chủ đề. Nếu link chết, tìm lại bằng tên tài liệu.

## 1. Sách: 6 cuốn phải đọc (theo chương), 5 cuốn tuỳ chọn

### Phải đọc

| # | Sách | Vì sao cần | Đọc chương nào | Tuần | Mua ở đâu |
|---|---|---|---|---|---|
| 1 | **Mobile System Design Interview: An Insider's Guide**, Manuel Vivo (ByteByteGo, 2025) | Sách duy nhất chuyên vòng mobile system design; 5-step framework và 7 bài thật | Toàn bộ (ngắn, ~300 trang) | 7-8 | https://blog.bytebytego.com/p/our-new-book-mobile-system-design |
| 2 | **Kotlin Coroutines: Deep Dive**, Marcin Moskała (Kt. Academy / Leanpub, cập nhật 6/2026) | Coroutines/Flow là câu hỏi deep-dive chắc chắn gặp | Part 1 Understanding Kotlin Coroutines; Part 2 Kotlin Coroutines library (cancellation, exception handling, dispatchers, testing); Part 3 Channel & Flow (Flow building, lifecycle, SharedFlow/StateFlow, testing Flow) | 1 | https://leanpub.com/coroutines |
| 3 | **Kotlin in Action, 2nd ed.**, Elizarov, Isakova, Aigner, Jemerov (Manning, 2024) | Kotlin nền tảng, chương coroutines do chính tác giả coroutines viết | Ch. generics, ch. DSL, các chương coroutines & Flow (phần 3 của sách), ch. Java interop | 1-2 | https://www.manning.com/books/kotlin-in-action-second-edition |
| 4 | **Jetpack Compose Internals**, Jorge Castillo (Leanpub, trả phí) | Hiểu compiler/runtime/slot table để trả lời performance Compose ở mức Staff | Ch. The Compose compiler; Ch. The Compose runtime; Ch. State snapshot system; Ch. Smart recomposition | 2 | https://leanpub.com/composeinternals |
| 5 | **The Manager's Path**, Camille Fournier (O'Reilly, 2017) | Chương Tech Lead là chuẩn mực mọi interviewer lead dùng | Ch. 3 Tech Lead; Ch. 4 Managing People; Ch. 5 Managing a Team; lướt Ch. 2 Mentoring | 11 | https://www.oreilly.com/library/view/the-managers-path/9781491973882/ |
| 6 | **AI Engineering: Building Applications with Foundation Models**, Chip Huyen (O'Reilly, 2025) | Cuốn tốt nhất về xây ứng dụng LLM: eval, prompt, RAG, agents, cost | Ch. 1-2 (foundation models), Ch. 3-4 (evaluation), Ch. 5 (prompt engineering), Ch. 6 (RAG & agents), Ch. 9 (inference optimization, đọc lướt) | 9 | https://huyenchip.com/books/ |

### Tuỳ chọn (đọc nếu còn thời gian hoặc nhắm vị trí cụ thể)

| Sách | Khi nào | Đọc gì |
|---|---|---|
| **The Staff Engineer's Path**, Tanya Reilly (O'Reilly, 2022) | Nhắm Staff IC ở công ty nước ngoài | Part 1 (Big picture thinking), Ch. "Leading big projects" |
| **Designing Data-Intensive Applications, 2nd ed.**, Kleppmann & Riccomini (O'Reilly, 2-3/2026) | Muốn nói chuyện backend/data ngang hàng | Ch. 1-5 (reliability, data models, storage, encoding, replication) |
| **System Design Interview, vol. 1**, Alex Xu (2020) | Vòng system design có phần backend | Ch. 1-4 (scale from zero, back-of-envelope, framework), ch. rate limiter, notification system, news feed, chat |
| **Effective Kotlin, 2nd ed.**, Marcin Moskała | Làm code review tốt hơn | Items về readability, safety, efficiency |
| **Accelerate**, Forsgren, Humble, Kim (2018) | Nói về CI/CD và DORA metrics ở vai lead | Ch. 1-4 |

### Nguồn miễn phí thay sách

| Nguồn | Thay cho | Link |
|---|---|---|
| High Performance Browser Networking, Ilya Grigorik (đọc free) | Sách network | https://hpbn.co |
| OWASP MASVS + MASTG | Sách security mobile | https://mas.owasp.org |
| Kotlin docs + KMP docs | Sách KMP | https://kotlinlang.org/docs/home.html và https://kotlinlang.org/docs/multiplatform/ |
| Android Developers docs + blog | Hầu hết sách Android | https://developer.android.com/docs và https://android-developers.googleblog.com |
| Now in Android (sample app chuẩn Google) | Sách architecture | https://github.com/android/nowinandroid |
| weeeBox Mobile System Design | Sách system design (bản rút gọn) | https://github.com/weeeBox/mobile-system-design |
| Pro Git (free online) | Sách Git | https://git-scm.com/book/en/v2 |

Sách KMP nếu vẫn muốn: *Kotlin Multiplatform by Tutorials* (Kodeco) hoặc *Master Kotlin Multiplatform* (Leanpub, cập nhật 8/2026). Docs JetBrains đủ và mới hơn.

## 2. Reading plan theo tuần

| Tuần | Đọc | Thời lượng gợi ý |
|---|---|---|
| 1 | Moskała Part 1-3 (chọn lọc); Kotlin in Action ch. coroutines; `04` mục 1-3 | 6-8h |
| 2 | Castillo 4 chương; Compose docs (performance, side effects, Nav3); `04` mục 4-7; `21` mục adaptive | 6-8h |
| 3 | `19` toàn bộ; Pro Git ch. 1-3, 7 (tools); `20` phần A | 6-8h |
| 4 | `20` phần B; GitHub Actions docs (Quickstart, Workflow syntax); CircleCI docs (Config intro, Android orb); `04` mục 8-12 | 6-8h |
| 5 | hpbn.co ch. 1-4 (TCP, UDP, TLS), ch. 9-12 (HTTP/1.1, 2), ch. 13 (optimizing mobile); `07` toàn bộ | 8h |
| 6 | MASVS (đọc hết, ngắn); MASTG phần Android (Data Storage, Network, Platform, Code Quality, Anti-Reversing); Play Integrity docs; `08` | 8h |
| 7 | Vivo ch. 1-4 (framework) + bài feed, chat; weeeBox README; `06` bài 4.1-4.5 | 8h |
| 8 | Vivo các bài còn lại; Alex Xu ch. rate limiter + notification (tuỳ chọn); `06` bài 4.6-4.11; `09` | 8h |
| 9 | Chip Huyen ch. 1-6; ML Kit GenAI docs; Firebase AI Logic docs; Android Studio Gemini features; `10` | 8h |
| 10 | KMP docs (Get started, iOS integration, Swift interop); CMP docs; Swift tour + SwiftUI tutorial (Apple); Flutter "Get started" + "Flutter for Android developers"; `05` | 8h |
| 11 | Fournier ch. 2-5; Reilly Part 1 (tuỳ chọn); `11`, `12`, `22` | 6-8h |
| 12 | Không đọc mới; ôn lại checklist cuối mỗi file, Anki, `14` | 4h |

## 3. Link theo chủ đề (ưu tiên từ trên xuống)

### Android, Kotlin, Compose
- https://developer.android.com/docs
- https://developer.android.com/about/versions/17 (behavior changes 17) và https://developer.android.com/about/versions/16
- https://developer.android.com/google/play/requirements/target-sdk
- https://developer.android.com/guide/practices/page-sizes (16 KB)
- https://developer.android.com/topic/architecture
- https://developer.android.com/develop/ui/compose (docs Compose: performance, side effects, state, navigation)
- https://developer.android.com/jetpack/androidx/releases/compose (release notes)
- https://developer.android.com/topic/performance (startup, Baseline Profiles, Macrobenchmark, jank)
- https://developer.android.com/studio (Android Studio) và https://developer.android.com/studio/gemini/features
- https://kotlinlang.org/docs/home.html, https://kotlinlang.org/docs/coroutines-guide.html, https://kotlinlang.org/docs/releases.html
- https://blog.jetbrains.com/kotlin (what's new mỗi bản)
- https://github.com/Kotlin/kotlinx.coroutines
- https://github.com/android/nowinandroid, https://github.com/android/compose-samples, https://github.com/android/architecture-samples
- https://insert-koin.io, https://dagger.dev/hilt
- https://docs.gradle.org, https://developer.android.com/build/releases/gradle-plugin

### KMP, Compose Multiplatform, iOS, Flutter, RN
- https://kotlinlang.org/docs/multiplatform/ (Get started, Share code, iOS integration, Swift interop)
- https://www.jetbrains.com/compose-multiplatform/ và https://github.com/JetBrains/compose-multiplatform
- https://kmp.jetbrains.com/ (project wizard)
- https://developer.apple.com/swift/ , https://developer.apple.com/tutorials/swiftui , https://developer.apple.com/documentation/
- https://docs.flutter.dev (Get started, "Flutter for Android developers", Architecture overview)
- https://reactnative.dev/docs/getting-started (The New Architecture)

### Network
- https://hpbn.co (free)
- https://developer.mozilla.org/en-US/docs/Web/HTTP
- https://www.cloudflare.com/learning/ (TLS, QUIC, DNS giải thích dễ)
- https://www.rfc-editor.org/rfc/rfc9110 (HTTP semantics), https://www.rfc-editor.org/rfc/rfc8446 (TLS 1.3), https://www.rfc-editor.org/rfc/rfc9000 (QUIC)
- https://github.com/square/okhttp , https://github.com/square/retrofit , https://ktor.io/docs/
- https://developer.android.com/develop/connectivity (Network Security Config, pinning, ConnectivityManager)

### Security
- https://mas.owasp.org (MASVS, MASTG, checklist)
- https://developer.android.com/security
- https://developer.android.com/google/play/integrity
- https://source.android.com/docs/security
- https://github.com/MobSF/Mobile-Security-Framework-MobSF (tự scan APK của mình)
- https://frida.re (hiểu attacker dùng gì)

### Backend, fullstack, cloud
- https://ktor.io/docs/ (server), https://spring.io/guides (nếu công ty dùng Java/Spring)
- https://supabase.com/docs, https://firebase.google.com/docs
- https://www.postgresql.org/docs/ , https://redis.io/docs/
- https://docs.docker.com/get-started/
- https://roadmap.sh/backend (bản đồ kiến thức)
- https://github.com/donnemartin/system-design-primer
- https://aws.amazon.com/getting-started/ (API Gateway, Lambda, S3 khái niệm)

### AI cho mobile và cho workflow
- https://developer.android.com/ai (Gemini Nano, ML Kit GenAI, LiteRT trên Android)
- https://developers.google.com/ml-kit/genai
- https://firebase.google.com/docs/ai-logic
- https://ai.google.dev (Gemini API docs)
- https://docs.claude.com (Claude API, Claude Code docs)
- https://modelcontextprotocol.io (MCP)
- https://www.promptingguide.ai
- https://huyenchip.com (blog và sách AI Engineering)
- https://stackoverflow.blog/2026/10/06/the-results-of-the-2026-developer-survey-are-here/ (số liệu để trích)

### Mobile system design
- https://github.com/weeeBox/mobile-system-design
- https://blog.bytebytego.com (ByteByteGo newsletter, có bài mobile)
- https://proandroiddev.com (tìm "mobile system design interview")
- https://www.educative.io/courses/mobile-system-design

### Thuật toán
- https://neetcode.io/roadmap (14 pattern, 150 bài)
- https://leetcode.com (chọn Kotlin khi làm)
- https://app.codility.com/programmers/lessons/ (luyện kiểu online test)
- https://www.hackerrank.com/domains/algorithms

### Testing
- https://developer.android.com/training/testing
- https://developer.android.com/develop/ui/compose/testing
- https://mockk.io
- https://github.com/cashapp/turbine
- https://github.com/Kotlin/kotlinx.coroutines/tree/master/kotlinx-coroutines-test
- https://github.com/takahirom/roborazzi , https://github.com/cashapp/paparazzi
- https://developer.android.com/studio/preview/compose-screenshot-testing

### Git, CI/CD, release
- https://git-scm.com/book/en/v2 (Pro Git), https://learngitbranching.js.org (luyện tương tác), https://ohshitgit.com (sửa lỗi nhanh)
- https://docs.github.com/en/actions
- https://circleci.com/docs
- https://github.com/gradle/actions (setup-gradle)
- https://docs.fastlane.tools
- https://developer.android.com/studio/publish (signing, Play App Signing)
- https://support.google.com/googleplay/android-developer/ (Play Console help: tracks, staged rollout)

### UI/UX, design system, adaptive, accessibility
- https://m3.material.io (Material 3, tokens, M3 Expressive)
- https://developer.android.com/develop/ui/compose/layouts/adaptive
- https://developer.android.com/guide/topics/large-screens
- https://developer.android.com/guide/topics/ui/accessibility
- https://www.w3.org/WAI/WCAG22/quickref/
- https://help.figma.com (Dev Mode)
- https://lawsofux.com , https://www.nngroup.com/articles/ten-usability-heuristics/

### Product metrics, compliance
- https://developer.android.com/topic/performance/vitals
- https://play.google/developer-content-policy/ (Play policy)
- https://firebase.google.com/docs/remote-config , https://firebase.google.com/docs/ab-testing
- https://vanban.chinhphu.vn (tìm "13/2023/NĐ-CP" bảo vệ dữ liệu cá nhân; và Luật Bảo vệ dữ liệu cá nhân 2025, hiệu lực 2026, cần kiểm tra bản mới nhất)
- https://gdpr.eu (GDPR tóm tắt dễ đọc)

### Leadership, Staff, EM
- https://leaddev.com
- https://staffeng.com (Will Larson, free)
- https://lethain.com (Will Larson blog)
- https://charity.wtf (Charity Majors)
- https://github.com/kaushikb9/em-interviews
- https://github.com/jesselpalmer/the-engineering-managers-booklist

### Question banks và kinh nghiệm phỏng vấn
- https://github.com/amitshekhariitbhu/android-interview-questions
- https://github.com/Kirchhoff-/Android-Interview-Questions
- https://www.glassdoor.com (Interviews tab theo công ty: Grab, Agoda, GeoComply)
- https://www.teamblind.com
- https://levels.fyi (lương công ty nước ngoài)
- https://viblo.asia (tìm "kinh nghiệm phỏng vấn Grab", "phỏng vấn Android")
- https://itviec.com/blog , https://topdev.vn/blog

### Tiếng Anh phỏng vấn
- https://youglish.com (nghe phát âm từ kỹ thuật trong ngữ cảnh thật)
- Gemini Live hoặc ChatGPT voice làm mock interviewer (hướng dẫn trong `23`)
- YouTube: tìm "mobile system design interview mock" và "tech lead interview questions" để shadowing

## 4. Cách dùng tài liệu này
- Mỗi tuần mở mục 2, đọc đúng phần được gán, không đọc lan.
- Gặp link chết: tìm lại bằng tên, rồi sửa link trong file này (bạn là chủ tài liệu).
- Thấy nguồn hay hơn: thêm vào đúng mục, ghi 1 dòng vì sao hay.
