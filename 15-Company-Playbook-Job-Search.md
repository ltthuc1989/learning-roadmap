# 15. Company Playbook và Job Search: ai hỏi gì, và tìm job ở đâu khi thị trường hẹp

> Phần A: playbook theo loại công ty và theo từng công ty (từ JD thật trong `01` + kinh nghiệm cộng đồng; mục nào là suy luận sẽ ghi "suy luận"). Phần B: job search ops, vì thị trường trong nước chỉ có vài chục JD mobile, bạn phải mở rộng kênh. Ghi câu hỏi thật bạn gặp vào `PROGRESS.md` để làm giàu file này.

# Phần A. Company playbook

## A1. Theo loại công ty

| Loại | Ví dụ | Họ hỏi gì | Ngôn ngữ | Ôn thêm |
|---|---|---|---|---|
| **Digital bank / fintech** | GoTymeX/Tyme, Timo, Payoo, OCB, One Mount, NAB, Techcombank, MoMo, ZaloPay, VNPay | Security sâu (pinning, Keystore, biometrics, obfuscation, Play Integrity, eKYC), performance, modularization lớn, release quy trình, compliance; hỏi về incident và on-call; design system ở Tyme | VN bank: tiếng Việt, đôi khi EN với Solution Architect; Tyme/Timo/NAB: EN | `08` toàn bộ, `06` bài payment, `22` compliance, `11` incident |
| **Foreign product company** | Grab, GeoComply, ANDPAD, Axon, Agoda, LINE, Rakuten, Bosch | DSA 2 Medium, mobile system design không gợi ý, Android deep-dive (concurrency, architecture, lifecycle), behavioral theo value công ty, culture fit; GeoComply thêm anti-fraud/attestation | EN toàn bộ | `13`, `06`, `04`, `12`, `23` |
| **Outsourcing / software house** | Digital Unicorn, FPT Software, KMS, NashTech, Axon Active, Rikkeisoft, Opus | Breadth (Flutter/RN), tốc độ, giao tiếp khách hàng, estimation, AI tool thành thạo (Digital Unicorn: 2+ năm), đôi khi test online | EN giao tiếp với khách | `05`, `11` estimation, `10` workflow |
| **VN product company** | UPLIVE, DatVietVAC, Zalo/VNG, Tiki, Be, Ahamove, Sendo | Thư viện cụ thể (Compose, Hilt, Room, Media3), performance (Zalo), AI coding agent bắt buộc (UPLIVE, DatVietVAC), AI feature (Ahamove) | Tiếng Việt, đọc EN | `04`, `10`, `18` |
| **Remote / global** | Công ty Singapore, Úc, Nhật, EU thuê VN remote | Giống foreign product + async communication, viết rõ, timezone | EN | `23`, `11` remote |

## A2. Playbook từng công ty

Mỗi mục: nguồn, quy trình (nếu biết), hỏi gì, domain cần ôn, câu hỏi ngược.

### Grab (Senior Android Engineer, HCMC)
- **Nguồn:** JD ITviec 10/2026; kinh nghiệm viblo 2025 (ứng viên VN, interviewer Singapore).
- **Quy trình:** recruiter call ~30 phút → vòng thuật toán (2 LeetCode Medium) → system design (không gợi ý, khó nhất) → culture fit ~30 phút có thảo luận design miệng. Có thể có hiring manager round (suy luận).
- **Hỏi gì:** Java + Kotlin, Jetpack, JUnit/Espresso/Mockito, performance, mentoring mid-level, sprint planning/estimation; chat/VoIP real-time (từ JD).
- **Ôn:** `13` Medium set, `06` bài chat + ride-hailing + feed, `04` concurrency, `12` với "Grab principles" (đọc trên trang careers).
- **Hỏi ngược:** "How does the Vietnam team split ownership with Singapore?", "What's the release cadence for the passenger app?".

### GeoComply (Staff Mobile Engineer, Android/iOS, hybrid)
- **Nguồn:** JD ITviec 10/2026.
- **Hỏi gì (từ JD):** define architecture patterns, interface specs, data flows; mentor senior; device attestation, anti-fraud, geolocation; network handling và battery; giao tiếp với non-technical; Fluent English.
- **Ôn:** `08` hardening + attestation + anti-fraud (device fingerprinting, root/emulator/hook detection, Play Integrity), `07` network + battery, `05` iOS đọc được, `11` architecture decision và stakeholder.
- **Hỏi ngược:** "How do you balance fraud detection accuracy with false positives on legitimate devices?".

### ANDPAD (Principal Android, Nhật, hybrid)
- **Nguồn:** JD ITviec 10/2026.
- **Hỏi gì:** Compose, GraphQL, AWS/GCP, "design and implement APIs", code review bắt buộc, planning và deadline, camera module/drawing, đã làm TL full lifecycle; "Good at English".
- **Ôn:** `07` GraphQL vs REST, `09` API design, `11` planning/estimation, `04` Compose + camera/Media3 cơ bản.
- **Hỏi ngược:** "How are product decisions shared between Japan and Vietnam?".

### GoTymeX / Tyme (Design System Architect; Android Engineer; từng có Mobile Tech Lead)
- **Nguồn:** 3 JD (2 live, 1 snippet 3/2026).
- **Hỏi gì:** design system (tokens, component library, governance, adoption), cross-platform lead (Android/iOS/Flutter/RN), AWS/API gateway/Datadog, "AI native approach", DexGuard/ProGuard/DexProtector, LaunchDarkly feature flags, "translate technical talk to the business", roadmap; Mobile Tech Lead: 6+ năm, 2+ năm lead, vẫn hands-on, KMP là bonus.
- **Ôn:** `21` design system architecture, `06` bài 4.10 (design system platform), `08` hardening, `11` roadmap + stakeholder, `10` AI native.
- **Hỏi ngược:** "How many app teams consume the design system, and how is adoption measured?".

### Timo (Senior Android, Kredivo Group)
- **Nguồn:** Joboko 10/2026.
- **Hỏi gì:** 5+ năm, modularization, làm việc với Security team, English hằng ngày với Lead Android và regional team.
- **Ôn:** `04` modularization, `08`, `23`.

### Payoo (Senior Android, fintech)
- **Nguồn:** career page 6/2026.
- **Hỏi gì:** "lead architectural and design discussions", "monitor individual and team performance" (đây là senior có vai lead), payment domain.
- **Ôn:** `06` bài payment, `08` PCI/3DS khái niệm, `11` performance feedback.

### OCB (bank, JD iOS nhưng phản ánh cách bank hỏi mobile)
- **Nguồn:** JD ITviec 10/2026.
- **Hỏi gì:** certificate pinning, Keychain/Keystore, biometrics, obfuscation, token-based auth, large-scale modularization, "deep code reviews", "drives architectural decisions", AI-SDLC, eKYC/OCR nice.
- **Ôn:** `08` bộ câu hỏi bank, `04` modularization, `10` AI-SDLC.

### One Mount (Senior Mobile Collaborator, hệ sinh thái Techcombank)
- **Nguồn:** JD ITviec 10/2026.
- **Hỏi gì:** JVM/ART memory internals, C/C++ nice, Flutter/RN nice, Java + Kotlin.
- **Ôn:** `04` ART/GC/memory, Java interop.

### NAB Innovation Centre (Lead Android; EM Frontend/Mobile; đã đóng, sẽ mở lại)
- **Nguồn:** snippet.
- **Hỏi gì:** 7+ năm, Clean Architecture, MVVM; EM: BFF/microservices, Sev1/Sev2 incident lead, observability, CI/CD, release automation, mentoring; fluent English.
- **Ôn:** `11` incident + release, `20` CI/CD, `07` BFF.

### Techcombank (EM Mobile + Angular, Hà Nội; đã đóng)
- **Nguồn:** snippet; JobOKO về quy trình bank.
- **Quy trình (bank chung):** CV → group interview → cá nhân.
- **Hỏi gì:** quản lý team 10+, web + mobile, AWS, English.
- **Ôn:** `11` quản lý người, `09` web cơ bản.

### Axon (Engineering Manager Mobile, HCMC, on-site)
- **Nguồn:** Greenhouse (bị chặn), snippet.
- **Hỏi gì:** 5-8 direct reports, IoT mobile apps.
- **Ôn:** `11` toàn bộ nếu nhắm EM.

### MoMo, ZaloPay, VNPay, Zalo (VN fintech/product)
- **Nguồn:** JD snippet, career page; không có bài viết phỏng vấn Android cụ thể (suy luận từ JD).
- **Hỏi gì (suy luận):** MoMo có vòng test; Compose, Coroutines, Retrofit, Room, Hilt/Dagger (ZaloPay); performance nặng (Zalo); security thanh toán; scale super-app, modularization, dynamic feature.
- **Ôn:** `04`, `08`, `06` bài payment + feed.

### DatVietVAC (Senior AI Native Engineer, Flutter + React)
- **Nguồn:** JD ITviec 10/2026.
- **Hỏi gì:** Cursor/Copilot/Claude Code hằng ngày, thiết kế prompt/rules/workflow, review code AI về security/perf, mentor dùng AI, gRPC, Flutter + Next.js.
- **Ôn:** `10`, `18`, `05` Flutter nền tảng. Chỉ apply nếu chấp nhận Flutter-first.

### UPLIVE VN (Android, Kotlin + Compose)
- **Nguồn:** JD ITviec 10/2026.
- **Hỏi gì:** Compose state hoisting + Material 3, Coroutines/Flow, Hilt, Room, Retrofit, KSP, R8, Media3/ExoPlayer, GLSL, AdMob; "AI Coding Agents (Claude Code, Copilot)" bắt buộc; Supabase/Ktor nice.
- **Ôn:** `04`, `10` workflow; bạn có lợi thế Ads/Billing.

### Ahamove (Senior Android, Kotlin + Gen AI; đã đóng, có thể mở lại)
- **Nguồn:** snippet.
- **Hỏi gì:** Gemini API + Claude API, Gemini Nano, local vs cloud trade-off, prompt engineering, token management; 40% app / 20% GenAI / 20% quality & AI workflow / 20% cross-functional.
- **Ôn:** `10` toàn bộ, `06` bài 4.11.

### Agoda (Senior/Lead Android, Bangkok hoặc remote)
- **Nguồn:** Glassdoor.
- **Hỏi gì:** reverse linked list, rotate array, build BST; vòng "fix & refactor this codebase"; English.
- **Ôn:** `13`, `14` review round.

## A3. Bảng theo dõi playbook của bạn (điền khi apply)

| Công ty | Vị trí | Nguồn JD | Vòng | Ngôn ngữ | AI tool được dùng? | Câu hỏi thật đã gặp | File cần ôn |
|---|---|---|---|---|---|---|---|
| | | | | | | | |

# Phần B. Job search ops

## B1. Thực tế: thị trường trong nước hẹp, nên 4 kênh song song

| Kênh | Tỷ lệ nỗ lực | Vì sao |
|---|---|---|
| 1. JD công khai (ITviec, VietnamWorks, TopDev, LinkedIn Jobs, career page) | 30% | Ít nhưng là nơi dễ bắt đầu; apply ngay khi JD mới (≤ 7 ngày) |
| 2. Headhunter và recruitment consulting | 25% | Vị trí Lead/Architect ở bank và công ty nước ngoài thường đi qua headhunter (ví dụ ITviec Recruitment Consulting đang tuyển Mobile Architect cho digital bank) |
| 3. Referral qua network | 25% | Tỷ lệ được gọi cao nhất; cần danh sách 20 người |
| 4. Remote / global | 20% | Thị trường rộng hơn nhiều lần, trả USD, yêu cầu EN và async |

## B2. Danh sách nơi tìm

**Trong nước:** itviec.com (bật job alert "Android", "Mobile", "Kotlin", "Staff", "Architect"), vietnamworks.com, topdev.vn, linkedin.com/jobs (filter Vietnam + Remote), career page: grab.careers, geocomply.com/careers, andpad.co.jp (Vietnam), tyme (vietnam.tyme.com/recruitment), timo.vn/careers, payoo.vn/tuyen-dung, momo.vn/careers (tuyendung.momo.vn), zalopay.vn careers, careers.vng.com.vn, onemount.com careers, nab.com.au/careers (NAB Innovation Centre Vietnam), axon.com/careers, agoda careers, line (linecorp.com/vn), rakuten, bosch.com.vn careers, fpt-software careers, kms-technology.com/careers, nashtechglobal.com/careers, axonactive.com/careers, rikkeisoft.com/careers.

**Headhunter (liên hệ bằng LinkedIn + email, kèm CV + 3 dòng định vị):** ITviec Recruitment Consulting, Navigos Search, Robert Walters Vietnam, Michael Page Vietnam, Adecco Vietnam, ManpowerGroup Vietnam, HR2B, Talentnet, First Alliances, Glints (remote/SEA), NodeFlair (SEA tech), Persol Kelly. Nói rõ: "Staff Android / Mobile Lead / Mobile Architect, Kotlin Multiplatform, open to remote and relocation to Singapore".

**Remote / global:** LinkedIn Jobs (Remote, "Android", "Kotlin Multiplatform"), wellfound.com, arc.dev, toptal.com, turing.com, remoteok.com, weworkremotely.com, remotive.com, himalayas.app, jobs của công ty SEA tuyển VN remote (Singapore, Malaysia, Indonesia fintech), Nhật (Mercari, LINE, Rakuten, ANDPAD, Money Forward tuyển EN), Úc (NAB, Atlassian thường cần visa; hỏi remote). Lưu ý thuế và hợp đồng (contractor vs employee), thanh toán (Payoneer/Wise), bảo hiểm.

## B3. Referral: xây danh sách 20 người trong tuần 8

| Nhóm | Ai | Cách tiếp cận |
|---|---|---|
| Đồng nghiệp cũ đang ở công ty mục tiêu | liệt kê | Nhắn LinkedIn: hỏi thăm + "team mobile có đang tuyển không, mình đang tìm vai Staff/Lead" |
| Người từng phỏng vấn bạn | liệt kê | Cảm ơn lại + cập nhật |
| Cộng đồng: Android Vietnam, Kotlin Vietnam, GDG, viblo | tham gia | Đăng bài kỹ thuật (`02` mục 6.5), comment chất lượng 2 lần/tuần |
| Headhunter | 5 người | Mục B2 |

**Template nhắn referral (EN, cho công ty nước ngoài):**
"Hi <Name>, hope you're well. I saw <Company> is hiring a <Role>. I've spent the last few years shipping Kotlin Multiplatform apps to Android and iOS and leading mobile architecture, and the role looks like a strong match. Would you be open to referring me, or to a 15-minute chat about the team first? Happy to send my CV and a short summary. Thanks either way."

**Template tiếng Việt:** "Chào anh/chị <Tên>, em thấy <Công ty> đang tuyển <Vị trí>. Em làm Android 10 năm, gần đây ship app KMP lên cả Android và iOS và phụ trách kiến trúc mobile. Nếu tiện, anh/chị có thể refer giúp em hoặc cho em 15 phút hỏi về team không? Em gửi CV ngay. Cảm ơn anh/chị."

**Template nhắn headhunter (EN):**
"Hi <Name>, I'm a Senior/Staff Android engineer in Ho Chi Minh City with 10 years on Android, currently shipping Kotlin Multiplatform apps to Android and iOS, with strong experience in performance, security and release engineering, and daily use of AI coding agents with a review process. I'm targeting Staff Android, Mobile Tech Lead or Mobile Architect roles, in Vietnam, remote, or Singapore. CV attached. Are you working on any roles like this?"

## B4. Bảng tracking đơn apply (copy vào `PROGRESS.md` hoặc Google Sheet)

| Ngày | Công ty | Vị trí | Kênh | Liên hệ | Trạng thái | Vòng tiếp | Ngày follow-up | Ghi chú |
|---|---|---|---|---|---|---|---|---|
| | | | | | applied / screening / tech / design / final / offer / rejected | | | |

Quy tắc: follow-up sau 7 ngày im lặng, 1 lần, lịch sự. Mỗi tuần ≥ 5 đơn + 3 tin nhắn network từ tuần 8. Ghi câu hỏi thật ngay sau mỗi vòng.

## B5. Timing và xử lý nhiều offer
- Pipeline trung bình 3-6 tuần; bank có thể 8 tuần. Apply từ tuần 8 để có offer quanh tuần 12-14.
- Khi có offer sớm: xin 1 tuần để "hoàn tất quy trình khác"; dùng offer làm đòn bẩy lịch sự: "I have an offer at X; your role is my preference if we can align on Y."
- So sánh offer theo 5 cột: lương gộp, bonus/equity, học được gì trong 2 năm, sếp trực tiếp, rủi ro (layoff, outsourcing contract).
- Đàm phán chi tiết trong `14`; mốc số trong `01` mục 8.

## B6. Checklist
- [ ] Job alert bật ở ITviec, LinkedIn, VietnamWorks
- [ ] Danh sách 10 headhunter, đã nhắn 5
- [ ] Danh sách 20 người referral, đã nhắn 10
- [ ] 3 playbook công ty mục tiêu viết riêng (mục A3)
- [ ] Bảng tracking có ≥ 15 dòng sau tuần 10
- [ ] Hồ sơ remote: CV EN, LinkedIn EN, GitHub README, timezone và contractor setup rõ
