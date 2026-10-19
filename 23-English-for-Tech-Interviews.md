# 23. English for Tech Interviews: nói rõ, có cấu trúc, đúng từ

> Vì sao file này tồn tại: trong `01`, mọi job lương cao nhất (Grab, GeoComply, ANDPAD, NAB, Timo, Axon) đều yêu cầu nói tiếng Anh; GeoComply ghi "Fluent in English" ngay trong title; Grab phỏng vấn bằng tiếng Anh với kỹ sư Singapore. Mục tiêu không phải "hay", mà là **rõ, có cấu trúc, không làm interviewer phải đoán**. 10 phút mỗi ngày trong 12 tuần là đủ để khác biệt rõ rệt.

## 1. Mục tiêu và cách đo

| Mục tiêu | Đo bằng |
|---|---|
| Trả lời câu kỹ thuật trong 60-120 giây, có cấu trúc answer-first | Ghi âm, bấm giờ, chấm rubric mục 8 |
| Không dừng quá 3 giây khi nghĩ (dùng câu giữ chỗ) | Nghe lại, đếm khoảng lặng |
| Phát âm đúng 60 từ kỹ thuật hay sai (mục 5) | Youglish so sánh |
| Hỏi lại được khi không hiểu câu hỏi | Có ít nhất 1 lần trong mỗi mock |
| Giải thích được diagram bằng lời | Mock system design |

## 2. Ba cấu trúc câu trả lời (học thuộc)

### 2.1 Answer-first (câu kỹ thuật ngắn)
1. **Answer**: 1 câu kết luận.
2. **Why / How**: cơ chế, 2-3 câu.
3. **Trade-off or example**: 1-2 câu.
4. **Stop.** Hỏi lại: "Do you want me to go deeper into X?"

Ví dụ: "Q: StateFlow vs SharedFlow?" → "StateFlow is a SharedFlow specialised for state: it always has a current value, replays exactly one, and conflates. I use StateFlow for UI state and SharedFlow for one-off events like navigation or snackbars, because replaying an event on rotation would re-trigger it. The trade-off is that StateFlow drops identical consecutive values, so for events you need SharedFlow with a buffer. Want me to go into how I collect them safely with lifecycle?"

### 2.2 PREP (câu quan điểm: "which would you choose", "do you agree")
Point → Reason → Example → Point again. 60-90 giây.

### 2.3 STAR (behavioral), chi tiết trong `12`
Situation 2 câu → Task 1 câu → Action 4-5 câu, "I" không phải "we" → Result có số → 1 câu bài học. 2 phút.

## 3. Tự giới thiệu

### 60 giây (dùng bản trong `02` mục 5.1, luyện cho thuộc)
Cấu trúc: hiện tại làm gì (1 câu) → thành tựu đại diện có số (2 câu) → điểm mạnh liên quan vị trí (2 câu) → vì sao vị trí này (1 câu).

### 2 phút (hiring manager)
Thêm: 1 quyết định kiến trúc khó, 1 lần dẫn dắt người khác, 1 lý do cụ thể với công ty.

### Câu mở đầu để không bị cứng
- "Thanks for having me. Let me give you the short version first, and you can stop me anywhere."
- "I'll start with what I do now, then two things I'm proud of, then why I'm here."

## 4. 150 cụm từ theo vòng

### 4.1 Làm rõ câu hỏi (dùng ở mọi vòng, đặc biệt system design)
1. "Just to make sure I understand, you're asking about X, not Y, correct?"
2. "Can I clarify the scope first? Are we designing the client only, or the API as well?"
3. "What scale should I assume: thousands or millions of users?"
4. "Is offline support a hard requirement here?"
5. "Should I optimise for latency, battery, or data usage? Which matters most to you?"
6. "Let me restate the problem in my own words."
7. "Before I dive in, a quick question: do we have an existing backend contract, or can I define it?"
8. "Would you like a high-level answer first, or should I go straight into details?"
9. "I'm going to assume X for now; tell me if that's wrong."
10. "Can you give me an example of the kind of failure you're worried about?"

### 4.2 Câu giữ chỗ khi suy nghĩ (không quá 5 giây)
11. "Let me think about that for a second."
12. "There are two ways to approach this. Let me walk through the one I'd pick."
13. "Good question. The short answer is yes, and here's the nuance."
14. "Let me break that into two parts: the mechanism, and the trade-off."
15. "I'll start from first principles and then get concrete."
16. "The way I think about it is..."
17. "So, stepping back for a moment..."
18. "That depends on one thing, which is..."
19. "I haven't hit that exact case, but a similar one was..."
20. "Let me reason out loud, and feel free to redirect me."

### 4.3 Nêu trade-off và quyết định
21. "The trade-off here is A versus B."
22. "I'd lean towards X because Y, but I'd revisit that if Z."
23. "This buys us A at the cost of B."
24. "That's fine at this scale; it breaks down when..."
25. "The simplest thing that could work is X; the more robust option is Y."
26. "I'd rather pay that cost up front than debug it in production."
27. "It's a reversible decision, so I'd ship the simple version and measure."
28. "The risk I'd watch is..."
29. "If I had to pick one, I'd pick X, and here's the one-line reason."
30. "I'd avoid X here; it looks cheaper but it hides a failure mode."

### 4.4 Khi code (live coding)
31. "Let me clarify the constraints: input size, value range, and whether it can be empty."
32. "I'll start with a brute force to get something correct, then optimise."
33. "The brute force is O(n squared); I think we can get to O(n) with a hash map."
34. "I'll write the signature first so we agree on the contract."
35. "Let me name this clearly so the intent is obvious."
36. "I'm going to use a deque here because I need O(1) at both ends."
37. "Let me trace through a small example to check the logic."
38. "Edge cases I want to handle: empty input, a single element, and duplicates."
39. "Time complexity is O(n log n) because of the sort; space is O(n) for the map."
40. "I'd add a test for the boundary case before calling this done."
41. "I realise there's a bug on this line; let me fix it."
42. "In Kotlin I'd use an IntArray here to avoid boxing."
43. "Let me refactor this into a helper so the main loop reads cleanly."
44. "I'd stop here for an interview, but in production I'd also..."
45. "Can I use the standard library's sort, or do you want me to implement it?"

### 4.5 Giải thích diagram (system design)
46. "On the left is the client; on the right is the backend; the arrows are requests."
47. "Data flows from the UI down to the ViewModel, then the repository, then either local storage or the network."
48. "This box is the sync engine; it owns the queue of pending operations."
49. "The dotted line means an asynchronous path, like push."
50. "Let me zoom into this component, because that's where the complexity is."
51. "The single source of truth is the local database; the network only updates it."
52. "I've drawn two caches: memory for hot items and disk for everything else."
53. "The contract between these two boxes is a cursor-based pagination API."
54. "If this component fails, the user still sees cached data; that's the point of the design."
55. "Let me label the non-functional requirements I'm designing for: offline, battery, and security."

### 4.6 Không biết hoặc không chắc
56. "I don't know that off the top of my head, but here's how I'd find out."
57. "I haven't used that API in production; I know the concept and I'd read the docs before relying on it."
58. "I'm not certain about the exact version; I'd check the release notes."
59. "Let me give you my best understanding and flag where I'm unsure."
60. "That's outside what I've done, but the closest thing I've built is..."
61. "I'd rather say I'm not sure than guess on something that matters in production."

### 4.7 Không đồng ý lịch sự
62. "I see it differently, and here's why."
63. "That works, though I'd be cautious about one thing."
64. "I agree with the goal; I'd get there a different way."
65. "Can I push back a little on that assumption?"
66. "In my experience that approach caused X; have you seen the same?"
67. "Fair point. Let me adjust my answer."

### 4.8 Leadership và behavioral
68. "The situation was..." / "My responsibility was..." / "What I did was..." / "The result was..."
69. "I took ownership of..."
70. "I pushed for X because Y."
71. "I set up a weekly one-on-one with..."
72. "I gave direct feedback: here's what I said and how it landed."
73. "I made the call to..., and I communicated it to stakeholders by..."
74. "We measured it by..."
75. "In hindsight, I would have..."
76. "What I learned, and still apply, is..."
77. "I disagreed with my manager, so I wrote a one-page proposal with the data and we discussed it."
78. "I mentored two engineers; one of them now leads the iOS side."
79. "I translated the technical risk into a business impact: two weeks of delay versus a security incident."
80. "I de-risked it by shipping behind a feature flag with a staged rollout."
81. "When the incident happened, the first thing I did was..."
82. "I ran a blameless postmortem and we changed two things."
83. "I estimate by breaking work into two-day chunks and adding a risk buffer."
84. "I protect the team's focus by..."
85. "I say no to scope by offering a trade: this now, that next sprint."

### 4.9 Về AI (câu hỏi thời đại)
86. "I use AI coding agents daily, with a review process: nothing merges that I can't explain."
87. "The agent wrote the first version; I rewrote the concurrency part by hand because it leaked a scope."
88. "I treat AI output like a junior's PR: fast, useful, and reviewed line by line."
89. "We keep rules in the repo so the agent follows our architecture, not its defaults."
90. "The risk I manage is skill erosion, so we schedule AI-off work."
91. "On-device models win on privacy and offline; cloud wins on quality; hybrid picks per request."
92. "I measure AI impact with cycle time and defect escape rate, not lines of code."

### 4.10 Câu hỏi ngược cho interviewer
93. "What does success look like for this role in the first six months?"
94. "How does the mobile team decide between native and cross-platform today?"
95. "What's the release cadence, and what blocks releases most often?"
96. "How is AI tooling used on the team, and is there a review policy?"
97. "What's the biggest technical debt the team is carrying?"
98. "How do mobile engineers work with backend and design here?"
99. "What would you change about the team if you could?"
100. "Who would I report to, and how are performance and growth evaluated?"

### 4.11 Kết thúc câu trả lời và kết thúc buổi
101. "That's my answer; happy to go deeper on any part."
102. "To summarise: X, because Y, with the caveat Z."
103. "Does that answer your question, or did you want a different angle?"
104. "Thanks, I enjoyed this. What are the next steps?"
105. "Is there anything in my background you'd like me to clarify?"

### 4.12 Kỹ thuật, câu nền hay dùng
106. "It runs on the main thread, so it must be cheap."
107. "This is cancelled when the scope is cancelled."
108. "We cache it with a time-to-live of..."
109. "The request is idempotent, so a retry is safe."
110. "We pin the certificate's public key, not the leaf certificate, so rotation doesn't break the app."
111. "Recomposition is skipped when the inputs are stable and unchanged."
112. "Modularisation gives us build parallelism and enforced boundaries."
113. "The baseline profile pre-compiles the startup path."
114. "We shrink and obfuscate with R8 and keep the mapping file for crash symbolication."
115. "The app targets API 36 because Play requires it from August 2026."
116. "Expect/actual lets the common code call a platform implementation."
117. "Compose Multiplatform renders through Skia on iOS."
118. "The ViewModel exposes a StateFlow; the screen collects it with lifecycle awareness."
119. "We handle process death by saving the minimal state and rebuilding the rest."
120. "A WorkManager job retries the upload with backoff when the network returns."

### 4.13 Chuyển ý và nối câu (để nghe trôi)
121. "First... second... finally..."
122. "On the client side... on the server side..."
123. "In the happy path... in the failure path..."
124. "Short term... long term..."
125. "The upside is... the downside is..."
126. "Concretely, ..."
127. "For example, in my last project..."
128. "Which brings me to..."
129. "Having said that, ..."
130. "To put a number on it, ..."

### 4.14 Khi không nghe rõ
131. "Sorry, could you repeat the last part?"
132. "The audio cut out for a second; could you say that again?"
133. "Did you say 'cache' or 'catch'?"
134. "Let me check I heard correctly: you asked about..."
135. "Could you type the question in the chat as well? I want to be precise."

### 4.15 Đàm phán lương (xem `14`)
136. "Based on the scope of the role and the market, I'm targeting X."
137. "I'm flexible on base if there's a strong bonus or equity component."
138. "Can you share the band for this level?"
139. "I have another process at a later stage; could we align timelines?"
140. "I'd like to think it over and come back by Friday."

### 4.16 Câu về bản thân (HR screen)
141. "I'm looking for a Staff or lead role where I own mobile architecture."
142. "I left because the mobile team was being downsized and I wanted a product-focused company."
143. "My notice period is X weeks."
144. "I'm open to hybrid; fully on-site is possible for the right role."
145. "My strongest areas are Kotlin Multiplatform, performance, and engineering process."
146. "One thing I'm improving is backend depth; I'm building a Ktor service to close that gap."
147. "I've led by influence rather than title: reviews, playbooks, and mentoring."
148. "I'm comfortable working with a Singapore or Japan-based team across time zones."
149. "I use English daily for documentation and with AI tools; speaking is what I'm practising most."
150. "I can start on..."

## 5. 60 từ kỹ thuật người Việt hay phát âm sai (nghe trên Youglish, nói 5 lần mỗi từ)

| Từ | Lỗi hay gặp | Gợi ý |
|---|---|---|
| cache | nói như "catch" | /kæʃ/, giống "cash" |
| queue | nói đủ các chữ | /kjuː/, như chữ Q |
| suite (test suite) | "suit" | /swiːt/, như "sweet" |
| schema | "s-chema" | /ˈskiːmə/ |
| thread | thiếu âm "th" | /θred/ |
| throughput | thiếu "th" | /ˈθruːpʊt/ |
| asynchronous | nhấn sai | /eɪˈsɪŋkrənəs/, nhấn "sin" |
| synchronous | | /ˈsɪŋkrənəs/ |
| coroutine | "co-rou-tine" tách | /ˌkoʊruːˈtiːn/ |
| mutable / immutable | nhấn sai | /ˈmjuːtəbl/, /ɪˈmjuːtəbl/ |
| hierarchy | | /ˈhaɪərɑːrki/ |
| architecture | nhấn sai | /ˈɑːrkɪtektʃər/ |
| variable | | /ˈveriəbl/ |
| parameter | nhấn sai | /pəˈræmɪtər/, nhấn "ram" |
| debug | | /diːˈbʌɡ/ |
| execute | | /ˈeksɪkjuːt/ |
| genuine | | /ˈdʒenjuɪn/ |
| determine | | /dɪˈtɜːrmɪn/, không "mine" |
| idempotent | | /ˌaɪdəmˈpoʊtənt/ |
| heuristic | | /hjʊˈrɪstɪk/ |
| integer | "in-te-gơ" | /ˈɪntɪdʒər/ |
| nullable | | /ˈnʌləbl/ |
| deprecated | | /ˈdeprəkeɪtɪd/ |
| latency | | /ˈleɪtənsi/ |
| allocate | | /ˈæləkeɪt/ |
| jank | | /dʒæŋk/ |
| ephemeral | | /ɪˈfemərəl/ |
| paradigm | | /ˈpærədaɪm/ |
| pseudo | | /ˈsuːdoʊ/ |
| privilege | | /ˈprɪvəlɪdʒ/ |
| resume (tiếp tục) vs résumé | | /rɪˈzuːm/ vs /ˈrezəmeɪ/ |
| repository | | /rɪˈpɑːzətɔːri/ |
| analytics | | /ˌænəˈlɪtɪks/ |
| certificate | | /sərˈtɪfɪkət/ |
| authenticate | | /ɔːˈθentɪkeɪt/ |
| vulnerability | | /ˌvʌlnərəˈbɪləti/ |
| obfuscate | | /ˈɑːbfəskeɪt/ |
| mitigate | | /ˈmɪtɪɡeɪt/ |
| orchestration | | /ˌɔːrkɪˈstreɪʃn/ |
| gradle | | /ˈɡreɪdl/ |
| Kotlin | "cốt-lin" | /ˈkɒtlɪn/ |
| Jetpack | | /ˈdʒetpæk/ |
| Koin | | /kɔɪn/ như "coin" |
| Ktor | | /keɪˈtɔːr/ |
| Retrofit | | /ˈretroʊfɪt/ |
| OkHttp | | "okay h-t-t-p" |
| JSON | | /ˈdʒeɪsən/ |
| SQL | | "sequel" hoặc "S-Q-L" đều được |
| GUI | | "gooey" hoặc "G-U-I" |
| cron | | /krɒn/ |
| daemon | | /ˈdiːmən/ |
| nginx | | "engine-x" |
| Kubernetes | | /ˌkuːbərˈnetiːz/ |
| OAuth | | "oh-auth" |
| JWT | | "J-W-T" hoặc "jot" |
| GitHub | | /ˈɡɪthʌb/ |
| iOS | | "i-O-S" |
| Xcode | | "X-code" |
| Swift | | /swɪft/ |
| Flutter | | /ˈflʌtər/ |

Ngữ pháp hay sai: "the datas" (data không đếm được), "informations", "I have 10 years experience" (thiếu "of"), "discuss about" (bỏ about), "he/she" lẫn lộn, thì quá khứ trong STAR ("I lead" → "I led"), thiếu mạo từ ("the ViewModel", "a cache").

## 6. Lịch luyện 10 phút/ngày (12 tuần)

| Tuần | Việc |
|---|---|
| 1-2 | Shadowing: 1 video 3-5 phút về Android/Kotlin (Android Developers YouTube), nghe 1 câu, dừng, nói y hệt. Ghi âm pitch 60s/2 phút. |
| 3-4 | Mỗi ngày trả lời 2 Q&A trong file tuần bằng EN, ghi âm, nghe lại. Bắt đầu bảng từ mục 5 (10 từ/ngày). |
| 5-6 | Thêm "giải thích diagram": vẽ 1 diagram từ `06`/`07`, nói 2 phút. |
| 7-8 | Mock với AI voice (mục 7) 2 lần/tuần, 15 phút. |
| 9-10 | STAR: kể 1 story/ngày bằng EN, bấm giờ 2 phút. |
| 11-12 | Full mock EN. Luyện câu hỏi ngược và đàm phán lương. |

## 7. Luyện với AI voice làm interviewer

Dùng Gemini Live (app Gemini) hoặc ChatGPT voice. Prompt mở đầu:

"You are a Staff Android interviewer at a Singapore product company. Interview me in English for 15 minutes on <topic>. Ask one question at a time, wait for my answer, then ask one follow-up before moving on. Do not explain the answers. At the end, give me feedback on content, structure and English clarity, and list 5 phrases I should practise."

Chủ đề theo tuần: coroutines (1), Compose (2), testing (3), CI/CD (4), network (5), security (6), system design (7-8), AI (9), KMP (10), leadership (11), mixed (12).

Sau mỗi buổi: ghi 3 câu bạn bị vấp vào `PROGRESS.md`, tạo thẻ Anki cho từ mới.

## 8. Rubric tự chấm bản ghi âm (0-4 mỗi dòng)

| Tiêu chí | 4 | 2 | 0 |
|---|---|---|---|
| Answer first | Câu đầu là kết luận | Kết luận ở giữa | Không có kết luận |
| Cấu trúc | Rõ 3 phần, có từ nối | Có ý nhưng lộn xộn | Lan man |
| Độ dài | 60-120 giây đúng chỗ | Quá dài hoặc quá ngắn | Không kiểm soát |
| Khoảng lặng | < 3 giây, có câu giữ chỗ | 3-6 giây | > 6 giây, im lặng |
| Phát âm từ kỹ thuật | Không lỗi | 1-2 lỗi | Nhiều lỗi gây hiểu nhầm |
| Ngữ pháp cơ bản | Thì và mạo từ đúng | Vài lỗi | Lỗi làm khó hiểu |
| Hỏi lại khi cần | Có, tự nhiên | Có nhưng gượng | Không |

Mục tiêu tuần 12: trung bình ≥ 3.

## 9. Checklist
- [ ] Thuộc 3 cấu trúc (answer-first, PREP, STAR) và dùng được không cần nghĩ
- [ ] Pitch 60s và 2 phút nói trôi, ghi âm bản cuối
- [ ] 60 từ trong mục 5 phát âm đúng (tự kiểm tra bằng Youglish)
- [ ] ≥ 20 buổi mock với AI voice trong 12 tuần
- [ ] 100/150 cụm từ dùng được tự nhiên (đánh dấu cụm đã dùng trong mock)
- [ ] Rubric trung bình ≥ 3 ở tuần 12
