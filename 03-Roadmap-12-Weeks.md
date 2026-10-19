# 03. Roadmap 12 tuần: làm gì, đọc gì, nộp gì, mỗi tuần

> Bắt đầu: thứ Hai 19/10/2026. Kết thúc: Chủ nhật 10/1/2027. Mỗi tuần có: trọng tâm, file cần đọc, bài tập, DSA, side project, mock, deliverable, và checklist để tick. Cuối tuần cập nhật `PROGRESS.md`. Nếu có lời mời phỏng vấn sớm, nhảy sang mục 14 (Fast-track 4 tuần).

## 0. Khung thời gian mỗi ngày

| Khung | Trong tuần (2-3h) | Cuối tuần (4-5h/ngày) |
|---|---|---|
| 1 | 45-60 phút đọc lý thuyết (file chủ đề + sách chương được gán) | 2h side project hoặc hands-on của tuần |
| 2 | 45-60 phút practice: 1-2 bài DSA hoặc 1 bài tập trong file | 1.5h mock / ghi âm / drill own-your-code |
| 3 | 20-30 phút tiếng Anh: shadowing 10 phút + trả lời 2 Q&A bằng EN có ghi âm | 30 phút Anki + review tuần (Chủ nhật) |
| Anki | 15 phút, bất kỳ lúc nào (xếp hàng, đi xe) | |

Quy ước xuyên suốt: DSA theo lịch trong `13`, tiếng Anh theo `23`, drill own-your-code theo `02` mục 7 và `18`, side project theo `16`.

## 1. Tuần 1 (19/10 - 25/10): Biết mình ở đâu, Kotlin và Coroutines

**Trọng tâm:** gap analysis, CV, Kotlin 2.4, Coroutines/Flow, bắt đầu drill và tiếng Anh.

**Đọc:** `01` (1 lần, 1h), `02` (làm, không chỉ đọc), `04` mục 1-3, `18`, `23` mục 1-3. Sách: Moskała Part 1 (Understanding coroutines) + Part 3 (Flow) chọn lọc; Kotlin in Action chương coroutines.

**Bài tập:**
- Chấm 3 bảng skill matrix trong `02`, đánh dấu gap đỏ.
- Viết lại CV theo `02` mục 6; LinkedIn headline + about.
- Tự viết (AI-off) 3 đoạn code: một `Flow` pipeline có `stateIn(WhileSubscribed(5000))`, một `callbackFlow` bọc listener, một hàm `suspend` có retry + exponential backoff; viết test cho 1 trong 3 bằng `runTest` (xem `19` mục 5 nếu kẹt).
- Giải thích to bằng EN: structured concurrency, vì sao không `catch (e: Exception)` nuốt `CancellationException`, khác nhau `Dispatchers.Main` và `Main.immediate`.

**DSA:** 6 bài Arrays & Hashing (`13` tuần 1).
**Side project (`16`):** tạo repo, skeleton KMP từ template của bạn, viết `AGENTS.md`/`CLAUDE.md`, 1 màn hình hello.
**Drill:** own-your-code #1: `core/common`, `core/model`, `core/datasource` của kmp-template-impl (`02` mục 7.1 câu 1-3), ghi âm, chấm bằng P1 trong `18`.
**Tiếng Anh:** ghi âm giới thiệu 60s và 2 phút; shadowing bắt đầu.

**Deliverable:**
- [ ] Skill matrix đã chấm, gap đỏ liệt kê trong `PROGRESS.md`
- [ ] CV mới ≤ 2 trang, LinkedIn cập nhật
- [ ] 3 đoạn code AI-off + 1 test chạy pass
- [ ] 6 bài DSA
- [ ] Side project skeleton build được trên Android (iOS nếu có máy)
- [ ] Bản ghi âm drill #1 và pitch EN, đã chấm bằng `18` P1/P4

## 2. Tuần 2 (26/10 - 1/11): Compose internals, Nav3, Architecture, DI, adaptive

**Đọc:** `04` mục 4-7; `21` mục adaptive + Material 3 + design tokens (1 ngày). Sách: Castillo (compiler, runtime, snapshot, smart recomposition). Docs: Compose performance, side effects, Navigation 3.

**Bài tập:**
- Bật Compose compiler metrics trên side project, tìm 1 composable unstable, sửa, giải thích vì sao.
- Viết 1 màn Nav3 (`NavDisplay`, `NavKey`, back stack tự quản) AI-off.
- Viết 1 ADR (`11` mẫu) cho quyết định "Koin thay Hilt trong KMP template" dựa trên repo thật.
- Thêm `WindowSizeClass` + list-detail cho 1 màn side project (`21`).

**DSA:** 8 bài Two Pointers + Sliding Window.
**Drill:** #2: `build-logic` convention plugins + Koin modules (`02` mục 7.1 câu 2, 7.2 câu 3-4).
**Deliverable:**
- [ ] Màn Nav3 AI-off chạy được
- [ ] ADR #1 viết xong (≤ 1 trang)
- [ ] Compose compiler report đọc được, 1 fix stability
- [ ] 8 bài DSA, side project adaptive 1 màn
- [ ] Ghi âm drill #2 đã chấm

## 3. Tuần 3 (2/11 - 8/11): Testing từ zero, Git

**Đọc:** `19` toàn bộ (đây là tuần nặng đọc); `20` phần A (Git). Pro Git ch. 1-3, ch. 7 (Git tools: stash, rewriting history, bisect).

**Bài tập (trên repo của bạn, không phải ví dụ):**
- Viết test cho 2 module trong `kmp-template-impl`: 1 ViewModel với Turbine (state loading/success/error), 1 repository với fake data source; 1 Compose UI test cho 1 màn hình (`testTag`, click, assert). Chạy Kover, ghi số coverage vào `PROGRESS.md`.
- Git: trong repo scratch, luyện 10 trong 20 tình huống "lỡ tay" của `20` (reflog, rebase -i, bisect, cherry-pick, stash, revert), mỗi tình huống 1 lần không nhìn đáp án.
- Side project: thêm branch strategy (trunk-based + release tag), PR template, CODEOWNERS, conventional commits.

**DSA:** 8 bài Stack + Binary Search.
**Drill:** #3: giải thích test strategy hiện tại của repo (có gì, thiếu gì) bằng EN.
**Deliverable:**
- [ ] ≥ 15 test mới pass trên repo của bạn, coverage có số
- [ ] 10 tình huống Git luyện xong, ghi lệnh vào sổ
- [ ] Side project có PR template + branch protection
- [ ] 8 bài DSA

## 4. Tuần 4 (9/11 - 15/11): CI/CD, Performance, Build/R8, Android 16/17; Mock 1

**Đọc:** `20` phần B (GitHub Actions, CircleCI, release); `04` mục 8-12 (build/R8, behavior changes, performance, migration, release). Docs: GitHub Actions quickstart + workflow syntax; CircleCI config intro + Android orb; Baseline Profiles; Macrobenchmark.

**Bài tập:**
- Side project: pipeline GitHub Actions chạy thật: detekt + unit test + build AAB + upload internal track (hoặc artifact nếu chưa có Play listing). Viết `.circleci/config.yml` tương đương, validate.
- Thêm Baseline Profile + Macrobenchmark startup cho side project, ghi số startup trước/sau.
- Bật R8 full mode, cố tình gây crash, dùng `mapping.txt` retrace.
- Viết 1 trang "Android 16/17 impact checklist" cho app của bạn (edge-to-edge, predictive back, 16 KB, target 36 deadline).

**DSA:** 8 bài Linked List + Trees 1.
**Mock 1 (cuối tuần):** Android deep-dive 45 phút bằng EN với Claude Code (`18` P2 trên `04`, 8-10 câu), ghi âm, chấm bằng P4. Ghi điểm vào `PROGRESS.md`.
**Deliverable:**
- [ ] CI xanh trên side project, badge trong README
- [ ] CircleCI config validate pass
- [ ] Baseline Profile có số startup
- [ ] Retrace được 1 crash sau R8
- [ ] Mock 1 có điểm và 3 điểm yếu

## 5. Tuần 5 (16/11 - 22/11): Network từ zero

**Đọc:** `07` toàn bộ. hpbn.co ch. 1-4 (latency, TCP, UDP, TLS), ch. 9-12 (HTTP/1.1, HTTP/2), ch. 13 (mobile). OkHttp docs (interceptors, caching, events).

**Bài tập:**
- Bắt traffic app của bạn bằng Proxyman/Charles, đọc 1 HAR, chỉ ra TLS version, HTTP version, cache header.
- Tự viết (AI-off) 1 OkHttp interceptor retry với exponential backoff + jitter + idempotency key; viết test.
- Vẽ tay TLS 1.3 handshake và HTTP/2 multiplexing, giải thích bằng EN có ghi âm.
- Side project: Ktor client có timeout, retry, logging, `NetworkCallback` offline banner.

**DSA:** 7 bài Trees 2 + Heap.
**Deliverable:**
- [ ] 45 Q&A trong `07` tự trả lời (che đáp án), đánh dấu câu yếu
- [ ] Interceptor + test pass
- [ ] HAR đọc được, ghi 5 nhận xét
- [ ] 7 bài DSA

## 6. Tuần 6 (23/11 - 29/11): Security, bank focus

**Đọc:** `08` toàn bộ. MASVS (hết), MASTG phần Android: Data Storage, Network, Platform Interaction, Code Quality, Anti-Reversing. Play Integrity docs. Android Keystore docs.

**Bài tập:**
- Side project: OAuth2 PKCE flow (có thể dùng Supabase Auth/Firebase Auth), token trong Keystore-backed storage, certificate pinning qua Network Security Config, Play Integrity verdict kiểm tra server-side (mock server được), R8 full mode + kiểm tra string lộ bằng `strings`/`apktool`.
- Chạy MobSF trên APK của bạn, sửa 3 finding.
- Viết threat model 1 trang cho app bank giả định (asset, attacker, entry point, mitigation).
- Chấm lại skill matrix lần 2 (`02`).

**DSA:** 7 bài Graphs.
**Deliverable:**
- [ ] PKCE + pinning + Integrity + R8 trên side project
- [ ] MobSF report trước/sau
- [ ] Threat model 1 trang
- [ ] 45 Q&A `08` tự trả lời, bộ câu hỏi bank trả lời được bằng EN
- [ ] Skill matrix lần 2

## 7. Tuần 7 (30/11 - 6/12): System Design 1; Mock 2

**Đọc:** `06` mục 1-3 và bài 4.1-4.5. Vivo ch. framework + bài feed, chat. weeeBox README.

**Bài tập:** mỗi ngày 1 bài (feed, chat, offline sync, image loader, downloader): 30 phút vẽ tay + 10 phút nói EN có ghi âm, chấm bằng `18` P5. Không đọc lời giải trước khi tự làm.

**DSA:** 7 bài Backtracking + Intervals.
**Mock 2 (cuối tuần):** 1 bài system design chưa làm, 45 phút, không gợi ý, ghi âm, chấm bằng rubric trong `06`.
**Deliverable:**
- [ ] 5 bài design tự vẽ + ghi âm + điểm
- [ ] Mock 2 có điểm
- [ ] 7 bài DSA

## 8. Tuần 8 (7/12 - 13/12): System Design 2, Backend mini, bắt đầu apply

**Đọc:** `06` bài 4.6-4.11; `09` toàn bộ; `15` toàn bộ. Vivo các bài còn lại. Alex Xu ch. rate limiter, notification (tuỳ chọn).

**Bài tập:**
- 6 bài design còn lại (ride-hailing, payment, push, analytics SDK, design system platform, AI feature), mỗi bài 30+10 phút.
- Backend 3 ngày: Ktor server (hoặc Supabase) cho side project: 3 endpoint REST có pagination cursor, idempotency key cho POST, JWT verify, Postgres (1 bảng có index), Docker compose chạy local, deploy 1 nơi miễn phí nếu tiện.
- Apply: chọn 5 JD từ `01` mục 9 và `15`, gửi; liên hệ 2 headhunter; xin 1 referral.

**DSA:** 7 bài 1-D DP.
**Deliverable:**
- [ ] 11/11 bài design đã làm
- [ ] Backend chạy local, side project gọi được
- [ ] 5 đơn apply, 2 headhunter, bảng tracking trong `15`
- [ ] 7 bài DSA

## 9. Tuần 9 (14/12 - 20/12): AI cho mobile lead; Mock 3

**Đọc:** `10` toàn bộ; `18` ôn lại. Chip Huyen ch. 1-6. ML Kit GenAI docs, Firebase AI Logic (hybrid inference), Android Studio Gemini features.

**Bài tập:**
- Side project: 1 AI feature hybrid (ví dụ tóm tắt/gợi ý trả lời): ML Kit GenAI Prompt API on-device nếu device hỗ trợ, fallback Firebase AI Logic cloud; streaming vào Compose; cancel theo lifecycle; App Check; log cost/latency; 10 test case eval tay.
- Viết "AI-SDLC policy" 1 trang cho team giả định (rule, review checklist, metric, AI-off).
- Dùng Android Studio Agent Mode + Journeys cho 1 E2E test trên side project; ghi lại tốt/xấu.

**DSA:** 6 bài 2-D DP + Greedy.
**Mock 3 (cuối tuần):** 30 phút AI questions (`10` Q&A) + 30 phút culture fit/behavioral AI-era, EN, ghi âm.
**Deliverable:**
- [ ] AI feature demo video 1 phút
- [ ] AI-SDLC policy 1 trang
- [ ] Mock 3 có điểm
- [ ] 6 bài DSA

## 10. Tuần 10 (21/12 - 27/12): Cross-platform: KMP học lại, iOS, Flutter, RN; a11y + i18n

**Đọc:** `05` toàn bộ (KMP phần sâu nhất); `21` mục accessibility + i18n. KMP docs (iOS integration, Swift interop, expect/actual), CMP docs; Swift tour + SwiftUI tutorial 1 ngày; Flutter "Get started" + "Flutter for Android developers".

**Bài tập:**
- 3 ngày KMP: drill giải thích toàn bộ iOS integration của `kmp-template-impl` (framework, Koin init iOS, Room driver, lifecycle, resource, build) AI-off; tự tay thêm 1 expect/actual nhỏ và 1 UIKitView interop.
- 2 ngày iOS: đọc và review 1 file SwiftUI thật (từ app iOS bạn đã port), viết 10 dòng nhận xét; giải thích ARC, Keychain, SPM, async/await ở mức so sánh với Android.
- 1.5 ngày Flutter: build mini app 2 màn (list + detail, gọi API, state bằng Riverpod hoặc BLoC) bằng AI, rồi tự giải thích widget tree, build method, platform channel.
- 0.5 ngày RN: đọc New Architecture, viết 1 đoạn so sánh.
- Tối: side project a11y audit (Accessibility Scanner, TalkBack 10 phút), pseudo-locale, RTL check.

**DSA:** Mixed set 1: 6 bài Medium bấm giờ (2 bài/60 phút).
**Deliverable:**
- [ ] Bài trả lời "why KMP vs Flutter vs RN" 2 phút EN ghi âm
- [ ] Mini Flutter app chạy, 10 dòng tự giải thích
- [ ] a11y audit pass các lỗi critical
- [ ] 6 bài DSA bấm giờ, ghi thời gian

## 11. Tuần 11 (28/12 - 3/1): Leadership, STAR, metrics/compliance; Mock 4

**Đọc:** `11`, `12`, `22`, `15` phần company playbook. Fournier ch. 2-5. Reilly Part 1 (tuỳ chọn).

**Bài tập:**
- Điền 20 story STAR trong `12` từ kinh nghiệm thật, mỗi story chấm bằng `18` P6.
- Viết 1 ADR + 1 RFC mẫu (`11`) cho side project (ví dụ RFC "adopt KMP for feature X").
- Viết plan "first 90 days as Tech Lead" 1 trang.
- `22`: định nghĩa 5 metric cho side project (crash-free, startup, retention, funnel, AI feature usage), setup Firebase Analytics event taxonomy 10 event.
- Chọn 3 công ty mục tiêu, viết playbook riêng (họ hỏi gì, domain, câu hỏi ngược).

**DSA:** Mixed set 2: 6 bài + 1 bộ MCQ Android (`13`).
**Mock 4 (cuối tuần):** 60 phút leadership/behavioral EN với Claude Code (`18` P2 trên `11` + `12`), ghi âm, chấm.
**Deliverable:**
- [ ] 20 story đã điền và chấm
- [ ] ADR + RFC + 90-day plan
- [ ] 10 event analytics trên side project
- [ ] Mock 4 có điểm
- [ ] 3 playbook công ty

## 12. Tuần 12 (4/1 - 10/1): Full mock loop, đàm phán, polish

**Đọc:** `14` toàn bộ; ôn checklist cuối mỗi file; Anki.

**Bài tập:**
- Full mock loop trong 1 ngày (thứ Bảy): HR screen 20' → coding 45' (2 bài EN) → Android deep-dive 45' → system design 45' → leadership 45' → culture fit 20'. Ghi âm, chấm, ghi điểm.
- Thứ 2-6: mỗi ngày ôn 1 điểm yếu lớn nhất từ các mock.
- Side project: README (kiến trúc, decision, cách chạy, demo GIF), tag v1.0, Play internal link nếu có.
- Đàm phán lương: viết sẵn số sàn/mục tiêu/trần theo `01` mục 8 và `14`.
- Backend đọc thêm nếu tuần 8 chưa kịp: DB index, Redis, Docker khái niệm.
- Chấm skill matrix lần 3.

**Deliverable:**
- [ ] Full mock loop có điểm từng vòng
- [ ] Side project v1.0 có README + demo
- [ ] Bảng lương sàn/mục tiêu/trần
- [ ] Skill matrix lần 3: không ô nào < 2
- [ ] Kết luận trong `PROGRESS.md`: sẵn sàng onsite

## 13. Nghi thức review cuối tuần (30 phút, Chủ nhật)
1. Mở `PROGRESS.md`, tick việc đã xong, ghi điểm mock, ghi 3 điểm yếu.
2. Chạy `python3 -I tools/make_anki.py` nếu có Q&A mới, import vào Anki.
3. Mở session Claude Code mới trong thư mục này, nói "review tuần N" (prompt P7 trong `18`). Nhận 3 ưu tiên tuần sau.
4. Nếu đi phỏng vấn thật trong tuần, ghi câu hỏi thật vào bảng trong `PROGRESS.md` ngay, và nhờ Claude Code bổ sung Q&A vào file liên quan.

## 14. Fast-track 4 tuần (khi có lời mời phỏng vấn sớm)

Thứ tự ưu tiên tối thiểu để đi thi. Mỗi tuần vẫn giữ DSA 5 bài và tiếng Anh 10 phút/ngày.

| Tuần | Làm | Bỏ qua tạm |
|---|---|---|
| 1 | `02` (CV, pitch, 5 câu chuyện), `04` mục 2-5 (coroutines, Compose, architecture) + 30 Q&A đầu, drill own-your-code #1-2 | Build/R8 chi tiết, migration |
| 2 | `06` framework + 3 bài (feed, chat, offline sync), `07` mục 3-5 + 20 Q&A, `08` mục 2-5 + 20 Q&A (bank nếu apply bank) | Backend, Flutter |
| 3 | `12` 10 story STAR, `11` mục Tech Lead + first 90 days + 20 Q&A, `10` mục AI workflow + 15 Q&A, `19` mục 4-6 (MockK, runTest, Turbine) để trả lời được | CI/CD sâu, a11y/i18n |
| 4 | `14` mock loop rút gọn (coding, deep-dive, design, leadership), `15` playbook công ty đó, `23` luyện phrases | Side project (chỉ cần repo sạch có README) |

Sau khi phỏng vấn xong, quay lại roadmap 12 tuần từ tuần đang dở.

## 15. Nếu chậm tiến độ
- Chậm 1 tuần: bỏ Flutter mini app (giữ bài so sánh), rút backend còn 2 ngày.
- Chậm 2 tuần: gộp tuần 10 vào tuần 11, bỏ a11y hands-on (giữ lý thuyết), giảm DSA còn 4 bài/tuần.
- Không bao giờ bỏ: own-your-code drill, Mock 1-4, STAR 20 story, tiếng Anh hằng ngày.
