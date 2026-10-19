# 01. Market Research: thị trường mobile Việt Nam, tháng 10/2026

> Mục đích: biết chính xác JD đang đòi gì để ôn đúng thứ được hỏi, không ôn theo cảm giác. Toàn bộ số liệu bên dưới lấy từ đọc trực tiếp JD trên ITviec, VietnamWorks, TopDev, Joboko, ITnavi, career page công ty, và report lương ITviec 2025-2026, ngày 19/10/2026. Số liệu không kiểm chứng được sẽ ghi rõ.

## 0. Kết luận trong 10 dòng

1. Thị trường **rất hẹp**: ITviec có 15 job gắn tag Android nhưng chỉ ~7 là app dev; VietnamWorks có 1 job Android và 4 job Mobile; TopDev gần như trống.
2. **Không còn JD "Android Lead / Mobile Tech Lead / Mobile EM" đang mở** (TymeX, GoTymeX Mobile Tech Lead, NAB EM, Techcombank EM, Backbase Principal đều đã đóng).
3. Vị trí senior được **đẩy lên Staff / Principal / Architect**, yêu cầu 8-14+ năm và thường cover **cả iOS lẫn Android**.
4. Cross-platform (Flutter/React Native) xuất hiện trong 8/15 JD nhưng chỉ **bắt buộc ở 3 JD Flutter-first**. KMP là "plus", chưa JD nào bắt buộc.
5. Ba thứ được hỏi nhiều nhất ở mọi JD: **REST/API, performance, security**. Sau đó là testing, CI/CD, architecture.
6. JD cấp Lead/Architect **không liệt kê thư viện** (Compose, Hilt, Room ít được nhắc), họ hỏi architecture, performance, security, cross-platform breadth, mentoring, stakeholder.
7. **AI trong JD là AI coding agent** (Claude Code, Copilot, Cursor, Codex), đã thành yêu cầu cứng ở một số nơi; "AI Native Engineer" là title thật. AI trong sản phẩm (Gemini Nano, LLM API) chỉ ở Ahamove, Angle AI.
8. **Bank và fintech chiếm đa số** JD senior trở lên: GoTymeX/Tyme, Timo, Payoo, OCB, BIDV Securities, One Mount, NAB, Techcombank, KSS.
9. Job lương cao nhất ở **công ty nước ngoài** (Grab, GeoComply, ANDPAD, Axon, NAB) và đều cần **nói tiếng Anh**.
10. Lương median ITviec: Mobile dev >8 năm 45.7 triệu, Tech Lead >8 năm 68.45 triệu. Chênh lệch đó chính là lý do phải nhắm Lead/Staff.

## 1. Nguồn và giới hạn

| Nguồn | Kết quả |
|---|---|
| ITviec | Đọc đầy đủ 15 JD live (đăng 3-35 ngày trước). Lương ẩn sau login. Nhiều URL cũ trả 410 Gone (job đã gỡ). |
| VietnamWorks | Trang listing chạy JavaScript nên chỉ lấy được số đếm: 1 job Android Developer, 4 job Mobile Developer (19/10/2026). |
| TopDev | Search chạy JavaScript; mọi JD Android tìm được đều "Đã hết hạn". Report mới nhất của TopDev là 09/2024. |
| LinkedIn | Không fetch được. Snippet nói "361 Senior Android Developer jobs in Vietnam", không kiểm chứng. |
| Khác | Joboko, ITnavi, career page Payoo, Timo, Tyme, Axon (Greenhouse bị chặn), VNG (lỗi 500). |
| Report | ITviec "Vietnam IT Salary and Recruitment Market 2025-2026" (1.839 người trả lời, khảo sát 5-7/2025 và 10-11/2025). |

## 2. JD đã đọc đầy đủ

| # | Công ty (loại) | Vị trí | Năm KN | Ghi chú | Link |
|---|---|---|---|---|---|
| A | Grab (foreign product, Singapore) | Senior Android Engineer | 5+ | HCMC, on-site; Java + Kotlin; mentor mid-level; sprint planning | https://itviec.com/it-jobs/senior-android-engineer-grab-vietnam-ltd-1601 |
| B | GeoComply (foreign product, Canada, anti-fraud) | Staff Mobile Engineer, Android/iOS, Fluent English | 10+ | Hybrid; device attestation, anti-fraud; define architecture patterns; mentor senior | https://itviec.com/it-jobs/staff-mobile-engineer-android-ios-fluent-in-english-geocomply-geotech-hub-vietnam-3835 |
| C | ANDPAD (foreign product, Nhật, construction SaaS) | Principal Android Native Engineer (Kotlin) | 8+ | Hybrid; Compose; GraphQL; AWS/GCP; "design and implement APIs"; code review bắt buộc; đã làm TL full lifecycle | https://itviec.com/it-jobs/principal-android-native-engineer-kotlin-andpad-vietnam-co-ltd-4502 |
| D | UPLIVE VN (product, music/game) | Android Developer (Kotlin, Compose, MVVM) | 2-5 | Media3, GLSL, AdMob; "AI Coding Agents (Claude Code, Copilot)" bắt buộc; Supabase/Ktor nice | https://itviec.com/it-jobs/android-developer-kotlin-jetpack-compose-mvvm-uplive-vn-3827 |
| E | GoTymeX / Tyme (digital bank) | Mobile Community, Design System Architect (Android/iOS) | 14+ | Cross-platform lead; design system; AWS, API gateway, Datadog; "AI native approach"; 40-50% hands-on | https://itviec.com/it-jobs/mobile-community-design-system-architect-android-ios-gotymex-0521 |
| F | GoTymeX (digital bank) | (Senior) Android Developer (Kotlin) | 3+ | DexGuard/ProGuard/DexProtector; LaunchDarkly; "leading voice in architecture road-map"; "translate technical talk to the business"; KMP big plus | https://itviec.com/it-jobs/android-developer-kotlin-gotymex-0455 |
| G | One Mount Group (Techcombank ecosystem) | Senior Mobile Engineer Collaborator (iOS/Android) | 5+ | JVM/ART memory internals; C/C++ nice; Flutter/RN nice | https://itviec.com/it-jobs/senior-mobile-engineer-collaborator-ios-android-one-mount-group-5554 |
| H | Digital bank Singapore (qua ITviec Recruitment) | Mobile Architect / Design System Lead | 10 (3 năm architect/TL) | Hybrid; cross-platform; AWS, API gateway; giải thích cho non-technical | https://itviec.com/it-jobs/mobile-architect-itviec-recruitment-consulting-0943 |
| I | DatVietVAC (media product) | Senior AI Native Engineer (React, Next.js, Flutter) | 4+ | Cursor/Copilot/Claude Code hằng ngày; thiết kế prompt/rules/workflow; review code AI; mentor dùng AI; gRPC | https://itviec.com/it-jobs/senior-ai-native-engineer-react-next-js-flutter-datvietvac-5500 |
| J | Digital Unicorn (outsourcing, Pháp) | Senior Full-stack Mobile (Flutter, React) | 3-8+ | "At least 2 years" với AI coding tools; Supabase | https://itviec.com/it-jobs/senior-full-stack-mobile-developer-flutter-react-digital-unicorn-0728 |
| K | Payoo (fintech) | Senior Android Developer | 5+ | "Lead architectural and design discussions"; "monitor individual and team performance" | https://payoo.vn/tuyen-dung/senior-android-developer-2.html |
| L | Timo (digital bank, Kredivo) | Senior Software Engineer, Android | 5+ | English hằng ngày với regional team; modularization; làm việc với Security team | https://vn.joboko.com/viec-lam-senior-software-engineer-android-xvi6539591 |
| M | KS Securities | Senior Mobile Developer (Flutter, Android, iOS) | 2 Flutter + 1 native | Hà Nội | https://itnavi.com.vn/job-detail/senior-mobile-developer-flutter-android-ios |
| N | OCB (bank) | Middle/Senior iOS (Swift) | 4+ | Certificate pinning, Keychain, biometrics, obfuscation; "AI-SDLC"; eKYC/OCR nice; modularization lớn | https://itviec.com/it-jobs/middle-senior-ios-developer-swift-ngan-hang-tmcp-phuong-dong-ocb-4406 |
| O | BIDV Securities (BSC) | Mobile Developer (Flutter + backend) | 1-5+ | Gần như fullstack: .NET/Java, Oracle/Postgres, Kafka, Redis, Docker | https://itviec.com/it-jobs/mobile-developer-flutter-reactjs-angular-c-java-bidv-securities-company-bsc-5714 |
| P | G-Group | Mobile Security Researcher, up to 100M | 3+ | Frida/JADX/Ghidra, anti-tamper, root detection | https://itviec.com/it-jobs/mobile-security-researcher-up-to-100m-g-group-5100 |

**JD đã đóng nhưng còn snippet (để biết Lead/EM họ đòi gì):**

| Công ty | Vị trí | Yêu cầu đáng nhớ |
|---|---|---|
| Tyme | Mobile Technical Lead (3/2026) | 6+ năm mobile, **2+ năm lead team**, Kotlin hoặc Swift (biết cả hai là plus), vẫn hands-on, mentor, thiết kế kiến trúc sản phẩm mới, KMP là bonus |
| Techcombank | Engineering Manager Mobile + Angular (Hà Nội) | 5+ năm front-end web + mobile, **quản lý team 10+ người**, Angular/TypeScript, Swift + Kotlin, AWS, English |
| NAB Innovation Centre | Engineering Manager Frontend/Mobile | React + RN, BFF/microservices, **lead Sev1/Sev2 incident**, observability, CI/CD, release automation, mentor |
| NAB | Lead Android Engineer | 7+ năm, Kotlin, Clean Architecture, MVVM |
| Axon | Engineering Manager Mobile App (HCMC, on-site) | Team IoT mobile, **5-8 direct reports** |
| Ahamove | Senior Android (Kotlin, Gen AI) | Gemini API + Claude API, Gemini Nano, local vs cloud trade-off, prompt engineering, token management; chia việc **40% app / 20% GenAI / 20% quality & AI workflow / 20% cross-functional** |
| Angle AI | Middle Android | Compose, Coroutines, MVVM/MVI, Hilt/Koin, modularization, Play rules (target SDK, 16KB, Data safety), MockK/Turbine/Compose UI Test, ktlint/detekt; nice: LiteRT/MediaPipe/ML Kit, Gemini Nano |
| VietnamWorks | Mobile Tech Lead (React Native) | 7+ năm, lead và mentor, code review |
| Bankaool | Senior Flutter | Clean Architecture, BLoC, platform channels Swift/Kotlin, biometrics, mentoring |

## 3. Tần suất kỹ năng trong 15 JD ITviec

| Kỹ năng | Số JD | Nhận xét |
|---|---|---|
| REST API integration / API design | 10 | Gần như mọi JD. ANDPAD còn yêu cầu "design and implement APIs" |
| Performance (profiling, memory, battery, APM) | 10 | Được hỏi nhiều nhất cùng REST |
| Testing (unit/UI/automation) | 9 | JUnit, MockK, Espresso, Compose UI Test; DatVietVAC: Jest/Playwright |
| Kotlin | 8 | Thêm 1 JD chỉ gắn tag |
| Architecture (MVVM/MVI/Clean/SOLID) | 8 | |
| Flutter | 8 | Bắt buộc chỉ ở DatVietVAC, Digital Unicorn, KSS; còn lại là plus |
| iOS/Swift kèm Android | 8 | Staff/Principal/Architect đều muốn cover iOS |
| Mobile security | 8 | pinning, Keystore/Keychain, biometrics, DexGuard/R8, attestation, anti-fraud |
| CI/CD | 7 | |
| React Native | 6 | |
| Agile/Scrum | 6 | |
| Firebase (Crashlytics, Remote Config, Analytics) | 6 | |
| Java (Android) | 5 | Grab, GeoComply, One Mount, G-Group, Motorist: legacy vẫn sống |
| Design system | 4 | GoTymeX, Mobile Architect, DatVietVAC |
| Store release / Play policy | 4 | |
| Jetpack Compose | 3 | Ít hơn tưởng tượng; JD Lead không nhắc |
| Coroutines/Flow | 3 | |
| C/C++ | 3 | |
| KMP/KMM | 2 | "big plus" ở GoTymeX, nice ở OCB |
| Hilt/Dagger | 2 | |
| Room/SQLite/DataStore | 2 | |
| Retrofit/OkHttp/Ktor | 2 | |
| AWS/cloud | 2 | |
| Modularization | 1 | OCB "large-scale modularization" |
| GraphQL | 1 | ANDPAD |
| gRPC | 1 | DatVietVAC |
| Feature flags (LaunchDarkly) | 1 | GoTymeX |
| WebSocket | 0 | Grab có chat/VoIP nhưng không nêu protocol |
| Payment/banking SDK cụ thể | 0 | Banking là domain của 6 JD, không nêu SDK |

Bảng VietnamWorks/TopDev/Joboko (14 JD core) cho kết quả tương tự: Performance 12, Kotlin 10, CI/CD 9, Testing 9, Architecture 8, REST 8, Security 8, Flutter 8, RN 7, domain payments/banking 7, Agile 7, English 7, iOS/Swift 6, Compose 4, Firebase 4.

## 4. Yêu cầu leadership trong JD Staff/Principal/Architect/EM

| Yêu cầu | Xuất hiện ở |
|---|---|
| Mentoring / coaching | Grab (mentor mid-level), GeoComply (mentor senior), GoTymeX x2, Mobile Architect, OCB, Timo, Payoo (nice), ANDPAD (nice), Tyme MTL, VietnamWorks RN Lead |
| Code review / design review | Grab, GeoComply, ANDPAD (bắt buộc), ITviec Architect, OCB ("deep code reviews"), UPLIVE, Digital Unicorn, Timo |
| Quyết định kiến trúc, đặt standard | GeoComply (patterns, interface specs, data flows), GoTymeX Architect, Mobile Architect, OCB, GoTymeX Android ("custodian of best practices"), Payoo, Timo, Tyme MTL |
| Roadmap | GoTymeX Android: "leading voice in the evolving mobile architecture framework and road-map" |
| Planning / estimation | ANDPAD, Grab (sprint planning, estimation) |
| Hiring / talent | GoTymeX ("enhance our talent pool"), Architect roles (grow the community), Axon ("lead and grow the team") |
| Stakeholder | GoTymeX ("translate technical talk to the business", "convincing presentations"), GeoComply (technical và non-technical audiences), DatVietVAC (communicate trade-offs), Mobile Architect |
| Quản lý người | ANDPAD (nice-to-have), Techcombank EM (10+ người), Axon EM (5-8 direct reports), Payoo ("monitor individual and team performance") |
| Vận hành | NAB EM: lead Sev1/Sev2 incident, observability, release automation |
| Tỷ lệ hands-on | Mobile Architect: 40-50% hands-on ban đầu rồi dịch sang leadership; Tyme MTL: vẫn hands-on |
| Kinh nghiệm lead cần có | Tyme MTL: 2+ năm lead; Mobile Architect: 3 năm architect/TL |

## 5. AI trong JD

**Dạng 1, AI coding agent trong workflow (hầu hết):**
- UPLIVE: "Highly comfortable leveraging AI Coding Agents (e.g., Claude Code, GitHub Copilot) as daily development companions". Benefit: "AI tool provided".
- DatVietVAC "Senior AI Native Engineer": hands-on với Cursor, Copilot, Claude Code; "design effective prompts, context, rules, and development workflows"; "identify security, performance, maintainability, and architectural risks in AI-generated code"; "mentor team members on the effective and responsible use of coding agents".
- Digital Unicorn: "At least 2 years of hands-on experience using premium AI coding tools (Claude Pro/Max, ChatGPT Plus/Pro, Codex, Cursor)"; "prompt, review, validate, and improve AI-generated code".
- OCB: "AI tools in product development activities (AI-SDLC)".
- GoTymeX Architect, Mobile Architect: "AI native approach", design system "adopts AI techniques and tools".

**Dạng 2, AI trong sản phẩm (ít, nhưng là điểm khác biệt):**
- Ahamove: Gemini API (AI Studio/Vertex) và Claude API, Gemini Nano, local vs cloud trade-off, prompt engineering, token management. Chia việc 40/20/20/20.
- Angle AI (nice-to-have): LiteRT, MediaPipe, ML Kit, quantization, GPU/NNAPI delegate, ML Kit GenAI / Gemini Nano.
- OCB (nice-to-have): chatbot, OCR/eKYC.

**Hàm ý:** phải có bằng chứng dùng Claude Code/Cursor thành thạo và có quy trình review code AI (xem `10-AI-for-Mobile-Lead.md`, `18-AI-Study-Workflow.md`). Có thêm 1 AI feature thật trong side project là lợi thế hiếm.

## 6. Network, security, backend, fullstack trong JD

| Mảng | JD nói gì |
|---|---|
| API | REST gần như mọi JD. GraphQL (ANDPAD). gRPC (DatVietVAC). API Gateway + AWS + Datadog/CloudWatch (2 Architect). BFF + microservices (NAB). |
| Security | Generic "secure coding" ở đa số. Cụ thể: device attestation + anti-fraud (GeoComply); DexGuard/ProGuard/DexProtector (GoTymeX); R8 (UPLIVE); certificate pinning, Keychain/Keystore, biometrics, obfuscation (OCB); làm việc với Security team (Timo). SSL pinning/MASVS không ghi trong JD dev nhưng chắc chắn bị hỏi ở vòng kỹ thuật bank. |
| Backend | ANDPAD "design and implement APIs"; BSC gần như fullstack (.NET/Java/Python, Oracle/Postgres/MySQL, Kafka/RabbitMQ, Redis, Docker); Supabase/Ktor nice-to-have (UPLIVE, Digital Unicorn); DatVietVAC và Digital Unicorn gộp React/Next.js với Flutter. |
| Cloud | AWS (ANDPAD, GoTymeX, Mobile Architect), GCP (ANDPAD). Docker/K8s không xuất hiện trong JD mobile. |

## 7. Tiếng Anh

| Mức | Công ty |
|---|---|
| Fluent, ghi ngay trong title | GeoComply |
| Giao tiếp tốt / làm việc hằng ngày | ANDPAD, Digital Unicorn, Timo (regional Kredivo team), Motorist (HQ Singapore), NAB, Techcombank EM, Opus |
| Đọc tài liệu | G-Group, DatVietVAC |
| Không ghi | Grab, UPLIVE (chỉ tag), OCB, BSC, One Mount, Payoo, GoTymeX Android |
| Tiếng Nhật | NTT DATA Mobile Technical Leader: N3+ |

Quy luật: công ty nước ngoài và outsourcing cần nói; bank Việt ít ghi. Không JD nào mô tả ngôn ngữ phỏng vấn, nhưng theo kinh nghiệm cộng đồng (viblo 2025) Grab phỏng vấn bằng tiếng Anh với kỹ sư Singapore.

## 8. Lương

**Report ITviec 2025-2026, median triệu VND/tháng** (https://itviec.com/report/vietnam-it-salary-and-recruitment-market):

| Vị trí | Chung | <1 năm | 1-2 | 3-4 | 5-8 | >8 |
|---|---|---|---|---|---|---|
| Mobile Developer | 36.85 | 14.15 | 28.8 | 29.05 | 37.35 | **45.7** |
| Tech Lead | 51.8 | | 45.3 | 53.85 | | **68.45** |
| IT Manager | 50.25 | | | | | |
| PM | 50.1 | | | | | |
| CTO/CIO/VPoE | ~101 | | | | | |

Report không tách Android/iOS, không có dòng Engineering Manager. Nhận xét của report: "optimistic signals of the IT hiring demand" nhưng người đi làm "delaying job-change decisions amid salary stagnation".

**Số lẻ nhìn thấy trên JD:** G-Group Mobile Security Researcher "up to 100M"; XTech AI Android 2 năm "up to 45M"; ANDPAD Senior Android ~$3.500 (snippet, không kiểm chứng); Tinh Vân Trưởng nhóm Android 18-22M (2+ năm); BKACAD Flutter 20-30M.

**Ước tính vendor (không nguồn, dùng làm trần khi đàm phán):** TopDev blog 2025: Mobile Senior 40-65M. Second Talent 9/2026: Android Senior 50-75M, Lead/Architect 75-120M, Compose +20-35%, English +20-35%.

**Cách dùng khi đàm phán:** mốc ITviec median là sàn (Senior >8 năm ~46M, Lead ~68M); công ty nước ngoài tại VN trả trên median rõ rệt; remote/global tính bằng USD. Chi tiết trong `14-Mock-Interview-Scripts.md`.

## 9. Công ty đang tuyển (19/10/2026)

| # | Công ty | Vị trí | Loại |
|---|---|---|---|
| 1 | Grab | Senior Android Engineer | foreign product |
| 2 | GeoComply | Staff Mobile Engineer | foreign product |
| 3 | ANDPAD | Principal Android, Principal iOS | foreign product |
| 4 | GoTymeX / Tyme | Design System Architect; Android Engineer | digital bank |
| 5 | Digital bank Singapore (ITviec Recruitment) | Mobile Architect | digital bank |
| 6 | One Mount | Senior Mobile Engineer Collaborator | VN conglomerate |
| 7 | UPLIVE VN | Android Developer | product |
| 8 | DatVietVAC | Senior AI Native Engineer (Flutter) | product |
| 9 | Digital Unicorn | Senior Full-stack Mobile | outsourcing |
| 10 | KSS Securities | Senior Mobile (Flutter) | securities |
| 11 | Payoo | Senior Android | fintech |
| 12 | Timo | Senior Android (deadline 5/10, có thể mở lại) | digital bank |
| 13 | OCB | Middle/Senior iOS | bank |
| 14 | G-Group | Mobile Security Researcher | security |
| 15 | Axon | Engineering Manager Mobile (chưa xác nhận còn mở) | foreign product |
| 16 | Bosch Global Software | Senior Telematics Linux/Android | foreign |

## 10. Bối cảnh AI và thị trường lao động (để trả lời "bạn nghĩ gì về AI")

- Stack Overflow 2026 (6/10/2026, 30k+ người): 73% người dùng AI dùng hằng ngày, 66% dùng coding agent, **Claude Code 66%, Copilot 59%**, 79% muốn học AI skills, 93% cần source attribution mới tin output. https://stackoverflow.blog/2026/10/06/the-results-of-the-2026-developer-survey-are-here/
- Indeed Hiring Lab 7/2026 (qua nguồn thứ cấp): postings software dev Mỹ +15% từ 2/2025, 71% tăng ở senior, entry-level giảm.
- Stanford "Canaries in the Coal Mine": dev 22-25 tuổi giảm ~20% từ cuối 2022; mất việc tập trung ở nơi AI *thay thế*, không phải nơi AI *tăng cường*.
- LinkedIn Skills on the Rise 2026: posting cần AI literacy +70% YoY; leadership và stakeholder communication cũng tăng.
- ITviec Q1-2026: AI/Data/ML postings +96.6% YoY.
- Meta (10/2025) và Canva (6/2025) cho dùng AI trong vòng coding.

**Cách kể:** "Nhu cầu đang dịch sang senior biết thẩm định output của AI, lead được team dùng AI, và vẫn hiểu hệ thống tới tận cùng. Đó là vị trí tôi nhắm."

## 11. Ecosystem Android 10/2026 (để tài liệu và câu trả lời không lỗi thời)

| Mục | Hiện trạng |
|---|---|
| Android OS | Android 17 (API 37), stable 16/6/2026. Target 37: lock-free MessageQueue, không sửa `static final` qua reflection/JNI, bỏ opt-out orientation trên large screen. Mọi app: per-app memory limit, Keystore key caps, background audio restrictions. |
| Android 16 (API 36) | Edge-to-edge bắt buộc; predictive back mặc định; sw600dp+ bỏ qua orientation/resizability; local network permission opt-in. |
| Google Play | Target 36 bắt buộc từ 31/8/2026 (gia hạn 1/11/2026). 16 KB page size: AGP ≥8.5.1, NDK r28+; chặn update không hỗ trợ từ 1/2/2027 (theo trang chính thức). Developer verification từ 30/9/2026 ở BR/ID/SG/TH, global 2027. |
| Kotlin | 2.4.21 (8/10/2026); 2.4.0 stable context parameters, explicit backing fields, UUID. K2 mặc định. |
| Compose | 1.12.1, Material3 1.4.0; "Compose First" (5/2026): Views/Fragments/RecyclerView maintenance mode. Navigation 3 stable (11/2025). |
| KMP/CMP | CMP iOS stable từ 1.8 (5/2025); hiện 1.12.2; Hot Reload stable. |
| Android Studio | Rabbit 1 (2026.2.1). Gemini Agent Mode, MCP, AGENTS.md, Journeys, Android CLI 1.0. |
| Build | AGP 9.4.0, Gradle 9.6, JDK 17. Room 3.0 (`androidx.room3`, KSP bắt buộc). Coroutines 1.11.0. |
| AI on-device | ML Kit GenAI APIs (Gemini Nano): Summarization, Proofreading, Rewriting, Image description, Prompt API; Gemma 4 là base của Nano 4. LiteRT. Firebase AI Logic + hybrid inference. |
| Cross-platform | Flutter 3.47 (8/2026), Impeller mặc định. React Native 0.88, New Architecture only. |

Nguồn: developer.android.com (behavior-changes-17, behavior-changes-16, page-sizes, target-sdk, jetpack releases, studio), kotlinlang.org, blog.jetbrains.com/kotlin, developers.google.com/ml-kit/genai, firebase.google.com/docs/ai-logic, docs.flutter.dev, reactnative.dev.

## 12. Interview patterns ghi nhận được

- **Grab VN** (phỏng vấn bởi kỹ sư Singapore, nguồn viblo 2025): recruiter call ~30 phút → vòng thuật toán 2 LeetCode Medium → system design không gợi ý (ứng viên nói khó nhất) → culture fit ~30 phút có thảo luận design miệng.
- **Online test** (HackerRank/Codility, thường ở GeoComply/Shopee/Agoda/Tyme): Android MCQ + 2 bài thuật toán.
- **Agoda**: reverse linked list, rotate array, build BST; có vòng "fix & refactor this codebase".
- **Bank VN**: Techcombank CV → group interview → cá nhân (quy trình chung của bank). VPBank NEO Senior Android làm việc với Solution Architect, review + performance. MoMo có vòng test. Zalo nhấn performance.
- **Vòng điển hình toàn cầu cho Senior/Lead mobile**: HR screen → tech screen/online test → DSA → Android deep-dive → mobile system design → behavioral/leadership → (take-home hoặc refactor round).
- Không tìm được bài viết phỏng vấn Android cụ thể cho Techcombank/MB/ZaloPay/VNPay/Shopee/LINE/Bosch; Glassdoor VN và Blind là nơi tìm tiếp. Bạn hãy ghi câu hỏi thật vào `PROGRESS.md` để bổ sung.

## 13. Hàm ý cho kế hoạch ôn

1. Định vị là **Staff-level mobile generalist**: Android sâu, đọc/review được iOS, biết so sánh Flutter/RN/KMP ở mức architect.
2. Ôn sâu nhất 3 mảng được hỏi nhiều nhất: **REST/API + network** (`07`), **performance** (`04`), **security** (`08`).
3. Chuẩn bị **bank domain** riêng: pinning, Keystore, biometrics, DexGuard, Play Integrity, eKYC (`08`, `06` bài 4.7).
4. **AI coding agent** là yêu cầu cứng: có quy trình, có policy, có bằng chứng (`10`, `18`).
5. **English speaking** quyết định job lương cao nhất (`23`).
6. Thị trường trong nước hẹp nên **mở rộng remote/global** và dùng headhunter (`15`).
7. Lead/Architect cần kể được: mentoring, review, architecture decision, roadmap, stakeholder, incident (`11`, `12`).
