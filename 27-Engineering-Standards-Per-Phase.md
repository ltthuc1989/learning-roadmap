# 27. Chuẩn kỹ thuật từng pha: đọc đúng mục, rule, pattern, ví dụ, và rubric để chấm skill/agent

> **Vì sao có file này.** Muốn viết một skill tạo PR, một agent viết unit test, hay một reviewer agent, trước hết bạn phải **tự làm tốt việc đó bằng tay** và biết thế nào là tốt. Không có kiến thức đó thì không viết được skill, và cũng không đánh giá được một skill/command (của mình, của người khác, hoặc do AI sinh ra) là tốt hay tệ. File này là phần kiến thức đó. Mọi rule đều lấy từ **nguồn chính chủ**, có link tới đúng mục để bạn đọc tận gốc.
> **Cách đọc mỗi chủ đề (7 phần).** (1) Đọc gì: link tới đúng mục + đọc để biết gì; (2) Rule: đánh số, ghi nguồn. **Q** = trích nguyên văn, **P** = diễn giải sát; (3) Pattern có tên; (4) Ví dụ xấu → tốt; (5) Checklist khi tự làm bằng tay; (6) **Rubric chấm skill/command/agent** của chủ đề đó, mỗi dòng trỏ về rule; (7) Bài tập.
> **Liên hệ.** `26-AI-SDLC-Step-by-Step.md` cho biết thứ tự các pha và cách tối ưu artifact bằng eval. File này cho biết **nội dung** cần đưa vào artifact và **tiêu chí** để chấm nó. Các link đã được fetch và kiểm anchor ngày 18/11/2026.

## 0. Bản đồ

| # | Chủ đề | Pha ở `26` | Artifact AI dùng chuẩn này | Kiến thức nền đã có trong repo |
|---|---|---|---|---|
| 1 | Spec và acceptance criteria | Pha 2 | Skill viết spec | `11` (RFC/ADR), `22` |
| 2 | Design doc, plan, ADR | Pha 3 | Prompt plan, skill ADR | `11` |
| 3 | Viết code Kotlin/Android/KMP | Pha 4 | AGENTS.md, hook luật cứng | `04`, `05` |
| 4 | Unit test | Pha 5 | Subagent test-writer | `19` (tooling, MockK, coroutines, Turbine) |
| 5 | Commit message | Pha 6 | Skill commit / phần commit của skill PR | `20` §6.2 |
| 6 | Pull request | Pha 6 | Skill tạo PR | `20` §6.1, §7.4 |
| 7 | Code review | Pha 7 | Subagent reviewer | `11` (review culture), `samples/review-exercise/` |
| 8 | CI pipeline | Pha 8 | Workflow CI, workflow AI review | `20` Phần B |
| 9 | Release: version, release notes, rollout | Pha 9 | Skill release notes | `20` §6.4, §7.6 |
| 10 | Vận hành: crash, vitals, postmortem | Pha 9-10 | Script triage, skill fix-crash, skill postmortem | `22` |

Thứ tự học đề xuất cho tuần này: **4 → 5 → 6 → 7** (unit test, commit, PR, review). Bốn chủ đề này dùng hằng ngày, và cũng là bốn artifact bạn sẽ viết đầu tiên.

---

## 1. Spec và acceptance criteria

### 1.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://cucumber.io/docs/gherkin/reference/#rule | `Rule` là "one business rule", gom các ví dụ | 5' |
| https://cucumber.io/docs/gherkin/reference/#example và #steps (#given, #when, #then) | Given là ngữ cảnh, When là sự kiện, Then là kết quả **quan sát được** | 15' |
| https://cucumber.io/docs/gherkin/reference/#tips-for-using-background | Background ngắn, dưới 4 dòng | 5' |
| https://cucumber.io/docs/bdd/better-gherkin/#describe-behaviour và #consider-a-more-declarative-style | Viết "what, not how", phong cách declarative | 15' |
| *Specification by Example* (Adzic, Manning 2011), Ch7 "Illustrating using examples", Ch8 "Refining the specification" | Ví dụ cụ thể thay cho câu chung chung | 1-2h |
| *User Story Mapping* (Patton, O'Reilly 2014), Ch6 "The Real Story About Stories" | Card, Conversation, Confirmation | 30' |

### 1.2 Rule
1. **Q** Cucumber: "we recommend 3-5 steps per example."
2. **Q** Cucumber (Rule): một `Rule` đại diện "one business rule that should be implemented". Mỗi rule có các ví dụ đi kèm.
3. **Q** Cucumber (Given): "Avoid talking about user interaction in Given's." Given mô tả trạng thái, không mô tả thao tác bấm.
4. **Q** Cucumber (Then): Then nói về "an observable output", không phải "a record in a database". Với app, observable là thứ người dùng thấy (màn hình, thông báo) hoặc thứ module khác nhận được (giá trị trả về, event).
5. **Q** Cucumber: "Implementation details should be hidden in the step definitions." **Q** Better Gherkin: "describe what, not how". Câu tự kiểm: "Will this wording need to change if the implementation does?" Nếu có thì đang viết "how".
6. **P** Cucumber (Background): dưới 4 dòng; dùng tên sinh động ("Bé Na", "Phụ huynh Lan") thay vì "User A"; không dùng Background cho trạng thái phức tạp.
7. **P** Adzic: mỗi acceptance criterion là một **ví dụ cụ thể có dữ liệu thật**, không phải câu kiểu "hệ thống phải nhanh". Câu nào không viết được thành ví dụ thì chưa đủ rõ để code.
8. **P** Patton: "Confirmation" trong 3C chính là acceptance criteria. Story chưa có confirmation thì chưa sẵn sàng.

### 1.3 Pattern
- **Rule → Examples.** Mỗi business rule có 2-4 ví dụ: một happy path, một biên, một lỗi.
- **Bảng thất bại (failure table).** Đây là bổ sung cho mobile, không phải chuẩn Gherkin: một bảng liệt kê offline, process death, quyền bị từ chối, ngôn ngữ en/vi, a11y, màn nhỏ, iOS. Mỗi dòng ghi hành vi mong đợi. Nguồn cho các trường hợp biên: Android *What to test* (#edge-cases, xem chủ đề 4).
- **Out of scope.** Liệt kê thứ cố ý không làm, để agent không tự làm thêm.

### 1.4 Ví dụ xấu → tốt

Xấu (imperative, nói "how", Then không quan sát được):
```gherkin
Scenario: favorite
  Given user opens app and taps Lessons tab
  When user taps heart icon on lesson 3
  Then a row is inserted into favorites table
```

Tốt (declarative, Then quan sát được, có rule và biên):
```gherkin
Feature: Yêu thích bài học

  Rule: Bài đã yêu thích hiện trong danh sách Yêu thích, kể cả khi offline
    Example: Thêm một bài vào yêu thích
      Given "Bé Na" chưa yêu thích bài nào
      When "Bé Na" yêu thích bài "Animals 1"
      Then danh sách Yêu thích có đúng 1 bài là "Animals 1"

    Example: Mở danh sách khi không có mạng
      Given "Bé Na" đã yêu thích "Animals 1" và "Colors 2"
      And thiết bị đang offline
      When "Bé Na" mở danh sách Yêu thích
      Then thấy "Animals 1" và "Colors 2" theo thứ tự yêu thích gần nhất trước

  Rule: Bỏ yêu thích có thể hoàn tác trong 5 giây
    Example: Hoàn tác
      Given "Bé Na" đã yêu thích "Animals 1"
      When "Bé Na" bỏ yêu thích "Animals 1" rồi bấm "Hoàn tác" trong 5 giây
      Then "Animals 1" vẫn ở trong danh sách Yêu thích
```

### 1.5 Checklist khi tự viết spec
- [ ] Mỗi rule là một business rule, có 2-4 example
- [ ] Mỗi example 3-5 bước, có dữ liệu cụ thể (tên, số, thời gian)
- [ ] Given không có thao tác bấm; Then là thứ quan sát được
- [ ] Đổi implementation (Room sang SQLDelight) thì spec không phải sửa
- [ ] Có bảng thất bại: offline, process death, quyền, en/vi, a11y, iOS
- [ ] Có mục ngoài phạm vi và câu hỏi mở

### 1.6 Rubric chấm một skill/command viết spec
| Dấu hiệu skill **tốt** | Dấu hiệu skill **tệ** | Rule |
|---|---|---|
| Output có Rule + Example, 3-5 bước mỗi example | Một danh sách gạch đầu dòng chung chung ("app phải hỗ trợ offline") | 1, 2, 7 |
| Then mô tả thứ người dùng thấy | Then nhắc tên bảng, tên class, "gọi API" | 4, 5 |
| Tự đưa ra bảng thất bại và hỏi câu mở | Không có case lỗi nào, hoặc tự trả lời câu hỏi nghiệp vụ thay PM | 1.3, 8 |
| Dừng và chờ duyệt | Viết spec xong thì tự chuyển sang code | `26` pha 2 |
| Spec ngắn (≤ 1 trang cho làn M) | Spec 5 trang, lặp lại, không ai đọc hết | `26` pha 2 (Böckeler) |

### 1.7 Bài tập
Viết lại spec của 2 feature cũ trong repo bạn theo checklist 1.5. Đưa từng câu Then qua câu tự kiểm ở rule 5. Sau đó chạy kit `spec-to-tasks` (hoặc skill của bạn) trên cùng yêu cầu và chấm output bằng rubric 1.6.

---

## 2. Design doc, plan, ADR

### 2.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://www.industrialempathy.com/posts/design-docs-at-google/#anatomy-of-a-design-doc | Design doc gồm những phần nào | 10' |
| …/design-docs-at-google/#goals-and-non-goals | Non-goal đúng nghĩa là gì | 5' |
| …/design-docs-at-google/#alternatives-considered | Vì sao đây là phần quan trọng nhất | 5' |
| …/design-docs-at-google/#when-not-to-write-a-design-doc. (id có dấu chấm ở cuối) | Khi nào **không** cần viết | 5' |
| https://developer.android.com/topic/architecture#common-principles (#separation-of-concerns, #drive-ui-from-model, #single-source-of-truth, #unidirectional-data-flow) | 4 nguyên tắc kiến trúc Android | 15' |
| https://developer.android.com/topic/architecture#best-practices | Thực hành tốt nhất (không lưu data trong app component…) | 5' |
| https://cognitect.com/blog/2011/11/15/documenting-architecture-decisions | ADR gốc của Nygard | 10' |
| https://adr.github.io/madr/#full-template | Template MADR 4.0 (Considered Options, Decision Outcome, Consequences) | 10' |
| https://github.com/joelparkerhenderson/architecture-decision-record#suggestions-for-writing-good-adrs, #what-justifies-raising-an-adr, #what-justifies-not-raising-an-adr | Khi nào viết ADR, viết thế nào cho tốt | 10' |
| *A Philosophy of Software Design* 2e (Ousterhout), Ch4 "Modules Should Be Deep", Ch11 "Design it Twice" | Deep module, luôn thiết kế hai lần | 1h |

### 2.2 Rule
**Design doc**
1. **Q** Ubl: "Rule #1 is: Write them in whatever form makes the most sense for the particular project."
2. **P** Ubl (#context-and-scope): phần context là "objective background facts", "not a requirements doc".
3. **Q** Ubl: non-goal "aren't negated goals like 'The system shouldn't crash'". Non-goal là thứ **hợp lý có thể là goal** nhưng cố ý loại ra (ví dụ của Ubl: "ACID compliance").
4. **P** Ubl: phần design tập trung vào **trade-off**. Không dán toàn bộ API/schema; design doc "rarely contain code".
5. **Q** Ubl: "Alternatives considered" là "one of the most important" sections.
6. **P** Ubl (#when-not-to-write-a-design-doc.): giải pháp không mơ hồ, hoặc doc chỉ là "implementation manual" không có trade-off, thì viết code luôn. Doc ngắn 1-3 trang là bình thường.
7. **P** Ousterhout Ch11: trước khi chọn, phác ít nhất hai phương án **khác nhau về cơ bản** rồi so sánh. Ch4: module tốt là module sâu, tức interface đơn giản che nhiều chức năng.

**Kiến trúc Android (dùng làm tiêu chí trong plan)**
8. **P** Android architecture: viết mọi thứ trong Activity là "a common mistake". Có tối thiểu hai lớp UI và data, domain là tuỳ chọn. Mỗi loại dữ liệu có một single source of truth, expose dữ liệu bất biến cùng hàm/event để thay đổi. Dữ liệu chảy một chiều (UDF).
9. **P** Android #best-practices: "Don't store data in app components"; "Reduce dependencies on Android classes"; "Expose as little as possible from each module".

**ADR**
10. **P** Nygard: một ADR gồm Title, Status, Context, Decision ("We will…"), Consequences.
11. **P** joelparkerhenderson: ADR tốt có lý do (rationale), cụ thể (**một quyết định mỗi ADR**), có thời điểm, và bất biến: muốn đổi thì "supersede the ADR by creating a new ADR".
12. **P** joelparkerhenderson: không cần ADR khi quyết định nhỏ, chỉ ảnh hưởng một người, đã có chuẩn chung, hoặc là giải pháp tạm (workaround, PoC). Tên file dạng mệnh lệnh: `choose-database.md`.
13. **P** MADR: liệt kê Considered Options; Decision Outcome ghi "Chosen option: …, because …"; Consequences ghi Good/Bad kèm "because".

### 2.3 Pattern
- **Mini design doc** (1-3 trang) cho làn M ở `26` pha 1: Context and scope → Goals / Non-goals → Design (trade-off) → Alternatives considered → Cross-cutting concerns (bảo mật, privacy, offline, iOS).
- **Plan cho agent** = mini design doc + danh sách file thật + test cho từng bước (xem `26` pha 3).
- **ADR một quyết định**: dùng MADR khi cần so phương án, dùng Nygard khi chỉ cần ghi lại quyết định.

### 2.4 Ví dụ xấu → tốt

Non-goal xấu → tốt:
| Xấu | Vì sao | Tốt |
|---|---|---|
| "Không crash" | Goal phủ định, ai cũng muốn vậy (rule 3) | "Không đồng bộ yêu thích giữa các thiết bị (để follow-up)" |
| "Hiệu năng tốt" | Không đo được | Đưa vào goal: "Mở tab Yêu thích < 300 ms với 500 bài trên Pixel 6a" |

ADR (MADR rút gọn):
```markdown
# Store favorites in the existing lessons database

Status: accepted · Date: 2026-11-20

## Context and Problem Statement
Favorites must survive offline use and app restarts, on Android and iOS (KMP).

## Considered Options
1. New table in the existing lessons DB (core:datasource)
2. DataStore with a JSON list
3. Server-only, with an in-memory cache

## Decision Outcome
Chosen option: 1, because favorites are queried together with lessons
(sort by favoritedAt, join with lesson titles) and the DB already runs on both platforms.

### Consequences
- Good, because one source of truth for lessons and favorites.
- Bad, because it needs a schema migration (7 → 8) and a migration test.
```

### 2.5 Checklist khi tự viết plan/design
- [ ] Context là sự thật khách quan, không lẫn yêu cầu
- [ ] Non-goal là thứ hợp lý nhưng cố ý không làm
- [ ] Có ≥ 2 phương án khác nhau về cơ bản (làn L), mỗi phương án có trade-off
- [ ] Plan tôn trọng nguyên tắc Android: lớp UI/data rõ, SSOT, UDF, không có logic trong Activity
- [ ] Quyết định khó đảo ngược có ADR, mỗi ADR một quyết định
- [ ] Không viết doc khi chẳng có trade-off nào (rule 6)

### 2.6 Rubric chấm prompt plan / skill ADR
| Tốt | Tệ | Rule |
|---|---|---|
| Plan có alternatives với trade-off thật | Chỉ một phương án, hoặc "phương án 2" là bản tệ hơn của phương án 1 | 5, 7 |
| Non-goal đúng nghĩa | Non-goal kiểu "không có bug" | 3 |
| Plan đặt code đúng lớp, đúng module | Logic trong Composable/Activity, ViewModel giữ Context | 8, 9 |
| ADR một quyết định, có Consequences cả tốt lẫn xấu | ADR gộp 3 quyết định, chỉ có mặt tốt | 11, 13 |
| Nói "không cần design doc" cho việc nhỏ | Sinh design doc 5 trang cho việc đổi màu nút | 6, 12 |

### 2.7 Bài tập
Viết mini design doc cho "Yêu thích bài học" theo pattern 2.3. Sau đó viết ADR chọn nơi lưu (3 phương án). Nhờ một agent context mới đọc doc và tóm tắt lại: nó hiểu đúng phạm vi không?

---

## 3. Viết code Kotlin/Android/KMP

> Phần chuyên sâu (coroutines, Compose, kiến trúc) nằm ở `04`. Mục này gom **các rule kiểm được** từ nguồn chính chủ. Đây là nguồn để viết AGENTS.md và hook luật cứng, và cũng là checklist cho reviewer ở chủ đề 7.

### 3.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://kotlinlang.org/docs/coding-conventions.html#idiomatic-use-of-language-features (#immutability, #default-parameter-values, #named-arguments, #if-versus-when, #functions-vs-properties, #scope-functions-apply-with-run-also-let) | Kotlin idiomatic | 30' |
| https://kotlinlang.org/docs/coding-conventions.html#naming-rules (#names-for-backing-properties, #choose-good-names) | Đặt tên, backing property | 10' |
| https://kotlinlang.org/docs/coding-conventions.html#multiplatform-projects | Tên file KMP theo source set | 5' |
| https://developer.android.com/kotlin/style-guide#formatting, #naming_2 | Style Android: brace, 100 cột, hằng số, Composable | 20' |
| https://developer.android.com/topic/architecture/recommendations#layered-architecture, #ui-layer, #viewmodel, #handle-dependencies, #naming-conventions | Khuyến nghị có mức Strongly/Recommended/Optional | 20' |
| https://developer.android.com/kotlin/coroutines/coroutines-best-practices#inject-dispatchers, #main-safe, #mutable-types, #global-scope, #coroutine-cancellable, #exceptions | 8 rule coroutine dạng DO / DO NOT | 20' |
| https://kotlinlang.org/docs/multiplatform/multiplatform-expect-actual.html#rules-for-expected-and-actual-declarations, #recommendations-on-dependency-injection | Khi nào dùng expect/actual, khi nào dùng interface | 15' |
| https://kotlinlang.org/docs/multiplatform/multiplatform-discover-project.html#source-sets, #intermediate-source-sets | Source set, không có "một target iOS" | 10' |
| https://refactoring.guru/refactoring/smells (#bloaters, #change-preventers, #couplers) | Nhận diện code smell | 30' |
| https://refactoring.com/catalog/ (ví dụ /catalog/extractFunction.html) | Catalog refactoring của Fowler | tra cứu |

### 3.2 Rule
**Kotlin**
1. **Q** Kotlin conventions: "Always declare local variables and properties as val rather than var if they are not modified after initialization." Tham số và kiểu trả về dùng `List`/`Set`/`Map`, không dùng `HashSet`/`ArrayList`.
2. **Q** "Prefer declaring functions with default parameter values to declaring overloaded functions."
3. **P** Dùng named argument khi có nhiều tham số cùng kiểu nguyên thuỷ, hoặc tham số Boolean.
4. **Q** "Prefer using the expression form of try, if, and when." **P** `if` cho hai nhánh, `when` cho từ ba nhánh.
5. **P** Dùng property thay function khi logic không ném exception, rẻ, và trả cùng kết quả nếu state không đổi.
6. **P** Tên class là danh từ, method là động từ; tránh từ vô nghĩa như "Manager", "Wrapper"; `sort` sửa tại chỗ, `sorted` trả bản sao.
7. **P** KMP: file top-level trong source set nền tảng có hậu tố (`Platform.android.kt`, `Platform.ios.kt`), file common thì không.

**Android style**
8. **P** Android style guide: không wildcard import; brace bắt buộc cho `if`/`for`/`when`/`while` (trừ `if` một dòng); thụt 4 khoảng trắng; tối đa 100 cột.
9. **Q** Hằng `UPPER_SNAKE_CASE` chỉ khi bất biến sâu: "If any of an instance's observable state can change, it is not a constant." **P** `@Composable` trả `Unit` đặt tên PascalCase danh từ.

**Kiến trúc (Android architecture recommendations; trong ngoặc là mức khuyến nghị)**
10. **Q** [Strongly] "Create repositories even if they contain only a single data source." UI không đọc data source trực tiếp.
11. **Q** [Strongly] "Do not send events from the ViewModel to the UI." Xử lý event rồi cập nhật state. Thu state bằng `collectAsStateWithLifecycle`.
12. **P** [Strongly] ViewModel không nhận Activity/Context/Resources; ViewModel ở cấp màn hình, UI tái sử dụng thì dùng state holder thường; không dùng `AndroidViewModel`.
13. **P** [Recommended] Expose một `uiState: StateFlow`, dựng bằng `stateIn(…, WhileSubscribed(5000), …)`.
14. **P** [Optional] Đặt tên: `getAuthorStream(): Flow<Author>`; implementation theo cách nó làm (`OfflineFirstNewsRepository`), `Default…` chỉ khi không có tên tốt hơn; fake có tiền tố `Fake`.

**Coroutines (Android coroutines best practices)**
15. **P** Không hard-code `Dispatchers.IO`/`Default`; inject qua constructor (có thể kèm giá trị mặc định).
16. **P** Suspend function phải main-safe: class làm việc chặn thì tự `withContext` ra khỏi main thread.
17. **P** ViewModel tự tạo coroutine (`viewModelScope`) thay vì expose suspend function cho UI.
18. **P** Expose `StateFlow`, không expose `MutableStateFlow` (dùng backing property `_uiState`).
19. **P** Tránh `GlobalScope`. Việc phải sống lâu hơn màn hình thì dùng một external scope được inject.
20. **P** Cancellation là hợp tác: vòng lặp chặn phải gọi `ensureActive()`.
21. **Q** "Don't consume exceptions of type CancellationException." **P** Bắt `IOException` thay vì `Exception`/`Throwable`. Catch bên trong `viewModelScope.launch`.

**KMP**
22. **P** `expect` trong common không có thân, `actual` cùng package ở **mọi** source set nền tảng, compiler kiểm đủ.
23. **Q** "rely on standard language constructs wherever possible instead of using expected and actual declarations". **Q** "using classes in simple cases where interfaces would be sufficient is not recommended". Phụ thuộc nền tảng nên viết thành interface và inject. Expect/actual **class** vẫn là Beta (compiler cảnh báo).
24. **P** API nền tảng không compile trong `commonMain`; không có "một target iOS" (cần `iosArm64` + target simulator).

**Code smell (refactoring.guru)**
25. **P** Long Method ("any method longer than ten lines should make you start asking questions"), Shotgun Surgery (một thay đổi phải sửa nhiều class), Divergent Change (một class đổi vì nhiều lý do không liên quan), Feature Envy (method dùng dữ liệu object khác nhiều hơn của mình), Primitive Obsession (dùng `Int` cho role, `String` cho id).

### 3.3 Pattern
| Pattern | Dùng khi | Nguồn |
|---|---|---|
| Backing property `_uiState` + `uiState` | Expose state từ ViewModel | Kotlin conventions #names-for-backing-properties; coroutines #mutable-types |
| Single `uiState` + `stateIn(WhileSubscribed(5000))` | Màn hình có nhiều nguồn dữ liệu | Architecture recommendations #viewmodel |
| Inject dispatcher có giá trị mặc định | Class gọi `withContext` | Coroutines #inject-dispatchers |
| Interface + DI thay cho `expect class` | Phụ thuộc nền tảng trong KMP | Kotlin expect/actual #recommendations-on-dependency-injection |
| Repository kể cả khi chỉ có một nguồn | Mọi data | Architecture recommendations #layered-architecture |
| Extract Function, Guard Clauses, Replace Primitive with Object | Gỡ smell | refactoring.com/catalog |

### 3.4 Ví dụ xấu → tốt
```kotlin
// Xấu: 5 vi phạm. Context trong ViewModel (12), Dispatchers.IO hard-code (15),
// MutableStateFlow public (18), nuốt CancellationException (21), GlobalScope (19).
class FavoritesViewModel(private val context: Context, private val dao: FavoritesDao) : ViewModel() {
    val state = MutableStateFlow<List<String>>(emptyList())
    fun load() {
        GlobalScope.launch(Dispatchers.IO) {
            try { state.value = dao.all() } catch (e: Exception) { Log.e("Fav", context.getString(R.string.err)) }
        }
    }
}

// Tốt: repository được inject, state bất biến, scope của ViewModel, chỉ bắt lỗi mong đợi.
class FavoritesViewModel(private val repo: FavoritesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(favoriteIds = repo.favorites()) }   // repo tự main-safe
            } catch (e: IOException) {
                _uiState.update { it.copy(error = FavoritesError.Storage) }   // UI tự chọn chuỗi
            }
        }
    }
}

class OfflineFirstFavoritesRepository(
    private val dao: FavoritesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,   // inject được trong test
) : FavoritesRepository {
    override suspend fun favorites(): List<String> = withContext(ioDispatcher) { dao.all() }
}
```

KMP xấu → tốt (rule 23):
```kotlin
// Xấu: expect class cho một việc chỉ cần interface.
expect class Clock() { fun nowMillis(): Long }

// Tốt: interface trong commonMain, implementation nền tảng inject qua DI, test dùng FakeClock.
interface Clock { fun nowMillis(): Long }
```

### 3.5 Checklist khi tự viết code
- [ ] `val` mặc định; collection kiểu interface; default argument thay vì overload
- [ ] ViewModel: không Context; một `uiState: StateFlow`; không expose Mutable; không gửi event một lần sang UI
- [ ] Dispatcher được inject; suspend function main-safe; không `GlobalScope`
- [ ] Không bắt `Exception` chung; không nuốt `CancellationException`
- [ ] Có repository giữa UI và data source
- [ ] KMP: không có API nền tảng trong `commonMain`; phụ thuộc nền tảng qua interface; file nền tảng có hậu tố
- [ ] Không có method > ~10 dòng mà chưa có lý do; không có Primitive Obsession cho id/role

### 3.6 Rubric chấm AGENTS.md / hook luật cứng / code do agent viết
| Tốt | Tệ | Rule |
|---|---|---|
| AGENTS.md ghi rule cụ thể, kiểm được, có lý do ("Inject dispatchers; tests replace them") | "Write clean idiomatic Kotlin" | 15-21 |
| Rule cứng (`GlobalScope`, `MutableStateFlow` public, Room ngoài `core:datasource`) nằm trong hook/detekt, có test | Chỉ nhắc trong AGENTS.md | `26` mục 1 |
| Code agent viết qua được checklist 3.5 | ViewModel có Context, `catch (e: Exception)` | 12, 21 |
| Agent chọn interface + DI cho phụ thuộc nền tảng | Agent rải `expect class` khắp nơi | 23 |
| Hook báo 0 lỗi nhầm trên code hiện có | Hook regex bắt nhầm comment, string | `26` pha 4 |

### 3.7 Bài tập
1. Viết lại đoạn "Xấu" ở 3.4 mà không nhìn đáp án, rồi so sánh.
2. Lấy 3 ViewModel trong repo, chấm theo checklist 3.5 và liệt kê vi phạm kèm số rule.
3. Chọn 3 rule có thể kiểm bằng code (19, 18, 21), viết detekt rule hoặc hook cho chúng, kèm test 0 báo nhầm.

---

## 4. Unit test

> Phần tooling (JUnit, MockK, `runTest`, Turbine, test theo layer) đã có trong `19` §2-7. Mục này bổ sung phần còn thiếu: **thế nào là một test tốt, và vì sao**.

### 4.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://developer.android.com/training/testing/fundamentals/what-to-test#essential | Unit test bắt buộc phải có cho những gì | 10' |
| https://developer.android.com/training/testing/fundamentals/what-to-test#edge-cases và #avoid | Các trường hợp biên phải test; test nên tránh | 10' |
| https://developer.android.com/training/testing/fundamentals#architecture (mục #decoupling) | Kiến trúc thế nào thì test được | 10' |
| https://developer.android.com/training/testing/fundamentals/test-doubles#types | Fake, mock, stub, dummy, spy, shadow; vì sao ưu tiên fake | 10' |
| https://developer.android.com/topic/architecture/recommendations#testing | 3 khuyến nghị "Strongly recommended" về test | 5' |
| https://developer.android.com/training/testing/local-tests#test-class | Một test kiểm "a single aspect"; tên test | 10' |
| https://abseil.io/resources/swe-book/html/ch12.html#test_via_public_apis | Test qua API công khai | 15' |
| …/ch12.html#test_statecomma_not_interactions | Test trạng thái, không test tương tác | 10' |
| …/ch12.html#test_behaviorscomma_not_methods | Mỗi hành vi một test, không phải mỗi method một test | 15' |
| …/ch12.html#donapostrophet_put_logic_in_tests | Không đặt logic trong test | 5' |
| …/ch12.html#tests_and_code_sharing_dampcomma_not_dr | DAMP thay cho DRY trong test | 15' |
| https://abseil.io/resources/swe-book/html/ch13.html#prefer_realism_over_isolation | Real implementation > fake > stub/mock | 15' |
| …/ch13.html#the_dangers_of_overusing_stubbing và #avoid_overspecification | Lạm dụng stub và verify quá chi tiết | 10' |
| https://developer.android.com/kotlin/coroutines/test#testdispatchers, #injecting-test-dispatchers, #setting-main-dispatcher | Test coroutine đúng cách | 20' |
| https://developer.android.com/kotlin/flow/test#stateflows (mục #statein) | Test StateFlow | 10' |
| https://github.com/cashapp/turbine#consuming-all-events, #timeouts | Turbine: event chưa tiêu thụ, timeout thật | 10' |
| *Unit Testing Principles, Practices, and Patterns* (Khorikov, Manning 2020), Ch4 "The four pillars of a good unit test", Ch5 "Mocks and test fragility" | Bốn tiêu chí chấm một test | 2h |

### 4.2 Rule
**Test cái gì**
1. **P** Android *What to test*: unit test bắt buộc cho ViewModel/presenter, repository và data layer, use case, utility. Kiến trúc khuyến nghị (Strongly recommended, "Know what to test"): tối thiểu có test ViewModel gồm cả Flow, test repository và data source.
2. **P** Android *What to test* (#edge-cases): test số âm, số 0, biên; **mọi** lỗi mạng; JSON sai định dạng; bộ nhớ đầy; object bị tạo lại giữa chừng (xoay màn hình, process death).
3. **P** Android (#avoid): không test lại framework hay thư viện; không unit test activity/fragment. **Q** coverage "should not be used as the only metric".

**Test thế nào**
4. **Q** SWE at Google ch12 (TL;DR, P): test qua API công khai ("Use the front door first"); test trạng thái, không test tương tác; mỗi hành vi một test; cấu trúc given/when/then; đặt tên theo hành vi; không đặt logic trong test; failure message rõ ràng; DAMP hơn DRY.
5. **Q** SWE ch12: "interaction tests check how a system arrived at its result, whereas usually you should care only what the result is."
6. **Q** SWE ch12: "rather than writing a test for each method, write a test for each behavior." Phần lớn test chỉ có một "when" và một "then".
7. **Q** SWE ch12: "a test's body should contain all of the information needed to understand it without containing any irrelevant or distracting information." **Q** "A little bit of duplication is OK in tests so long as that duplication makes the test simpler and clearer."
8. **Q** SWE ch12: "Clear tests are trivially correct upon inspection." Không dùng vòng lặp, `if`, nối chuỗi để tính expected.
9. **Q** Android *local tests*: một test method kiểm "a single aspect of the component".

**Test double**
10. **Q** SWE ch13: "A real implementation should be preferred over a test double." **Q** "A fake is often the ideal solution if a real implementation can't be used". **Q** Android *test doubles*: "Fakes don't require a mocking framework and are lightweight. They are preferred." Kiến trúc khuyến nghị: "Prefer fakes to mocks" (Strongly recommended).
11. **Q** SWE ch13: "Overuse of stubbing leads to tests that are unclear and brittle." Chỉ verify tương tác với hàm **làm thay đổi trạng thái** (gửi analytics, ghi DB), và không verify quá chi tiết (#avoid_overspecification).
12. **Q** SWE ch13: một "fake must have its own tests". Fake nên do team sở hữu implementation thật viết.

**Coroutines và Flow**
13. **Q** Android coroutines test: "one invocation of runTest per test, and using an expression body is recommended."
14. **Q** "There should only be one scheduler instance used in a test". Mọi TestDispatcher phải dùng chung scheduler. Dispatcher tạo **sau** `setMain`.
15. **Q** "The Main dispatcher should be replaced by a TestDispatcher in local unit tests" (dùng `MainDispatcherRule` hoặc `setMain`/`resetMain`). Không làm việc này trong instrumented test.
16. **P** `StandardTestDispatcher` xếp hàng coroutine, phải gọi `advanceUntilIdle()`/`runCurrent()`. **Q** "If your test focuses on concurrency, prefer using StandardTestDispatcher". **P** Nếu phải gọi `advanceUntilIdle` để test một API, có thể API đó nên trả về `Job`/`Deferred`.
17. **Q** Android Flow test: "recommend treating StateFlow as a data holder and asserting on its value property". Với `stateIn(WhileSubscribed)`: "there must be at least one collector present during the test".
18. **P** Turbine: event chưa tiêu thụ làm test fail ("Unconsumed events found"); lỗi trong flow thành event, không bị ném ra; timeout 3 giây là **thời gian thật**, không theo thời gian ảo của `runTest`.

**Chấm chất lượng test**
19. **P** Khorikov Ch4: bốn tiêu chí là protection against regressions, resistance to refactoring, fast feedback, maintainability. Test kiểm chi tiết implementation thì mất "resistance to refactoring" (đỏ khi refactor dù hành vi không đổi).
20. **Q** PIT: "Killed means a test caught the mutation", "Survived means the mutation was not detected". Kotlin chỉ được hỗ trợ qua plugin thương mại Arcmutate (https://pitest.org/faq/). Repo Kotlin không mua plugin thì dùng **bug cấy bằng tay** để đo.

### 4.3 Pattern
| Pattern | Dùng khi | Nguồn |
|---|---|---|
| **Given / When / Then** (Arrange / Act / Assert) | Mọi test, một when + một then | SWE ch12 #structure_tests_to_emphasize_behaviors |
| **Tên theo hành vi**: `unit_condition_result` hoặc câu trong backtick | Mọi test | Android local-tests (`emailValidator_CorrectEmailSimple_ReturnsTrue`), SWE ch12 (`divide_byZero_throwsException`) |
| **Fake viết tay** cho repository/data source | Thay dependency có trạng thái | Android test-doubles #example-fake, SWE ch13 |
| **Inject dispatcher** | Class có `withContext(Dispatchers.IO)` | Android coroutines test #injecting-test-dispatchers |
| **MainDispatcherRule** | Test ViewModel dùng `viewModelScope` | Android coroutines test #setting-main-dispatcher |
| **Assert `.value` của StateFlow** | ViewModel expose StateFlow | Android Flow test #stateflows |
| **Turbine `test { awaitItem() }`** | Flow phát nhiều giá trị hoặc event một lần | Turbine README |
| **Test data builder** có giá trị mặc định, test chỉ ghi field quan trọng | Model nhiều field | SWE ch12 #shared_values (DAMP: field quan trọng phải hiện trong test) |

### 4.4 Ví dụ xấu → tốt (Kotlin)

**(a) Test tương tác thay vì trạng thái** (rule 5, 10, 11)
```kotlin
// Xấu: kiểm "đã gọi save chưa". Refactor repository (gộp save + cache) là test đỏ,
// dù hành vi không đổi. Không biết danh sách cuối cùng có đúng không.
@Test fun toggleFavorite() = runTest {
    val repo = mockk<FavoritesRepository>(relaxed = true)
    val vm = FavoritesViewModel(repo)
    vm.toggle("animals-1")
    coVerify { repo.save(any()) }
}

// Tốt: fake có trạng thái thật, assert trên thứ người dùng thấy.
@Test fun `toggle adds lesson to favorites`() = runTest {
    val repo = FakeFavoritesRepository()
    val vm = FavoritesViewModel(repo)

    vm.toggle("animals-1")

    assertEquals(listOf("animals-1"), vm.uiState.value.favoriteIds)
}
```

**(b) Một test cho cả method thay vì mỗi hành vi một test** (rule 6, 9)
```kotlin
// Xấu: tên theo method, 3 hành vi trong 1 test. Fail ở assert thứ 2 thì không biết hành vi nào hỏng.
@Test fun testToggle() = runTest { /* thêm, bỏ, hoàn tác, ... 3 khối assert */ }

// Tốt: 3 test, mỗi test 1 when + 1 then, tên nói hành vi.
@Test fun `toggle adds lesson to favorites`() = runTest { /* ... */ }
@Test fun `toggle twice removes lesson`() = runTest { /* ... */ }
@Test fun `undo within 5 seconds restores lesson`() = runTest { /* ... */ }
```

**(c) Logic trong test** (rule 8)
```kotlin
// Xấu: expected được tính bằng cùng logic với code thật. Code sai thì test cũng sai theo.
val expected = lessons.filter { it.id in favIds }.sortedByDescending { it.favoritedAt }.map { it.id }
assertEquals(expected, vm.uiState.value.favoriteIds)

// Tốt: expected viết thẳng ra, "trivially correct upon inspection".
assertEquals(listOf("colors-2", "animals-1"), vm.uiState.value.favoriteIds)
```

**(d) DRY quá mức che mất input** (rule 7)
```kotlin
// Xấu: không nhìn test thì không biết vì sao kết quả là rỗng.
@Test fun `offline shows cached`() = runTest {
    val vm = createViewModelInStateB()
    assertTrue(vm.uiState.value.favoriteIds.isEmpty())
}

// Tốt: thông tin quan trọng (offline, cache có 2 bài) nằm ngay trong test.
@Test fun `offline shows cached favorites`() = runTest {
    val repo = FakeFavoritesRepository(cached = listOf("animals-1", "colors-2"), online = false)
    val vm = FavoritesViewModel(repo)

    vm.load()

    assertEquals(listOf("animals-1", "colors-2"), vm.uiState.value.favoriteIds)
}
```

**(e) Coroutine: quên cho scheduler chạy** (rule 14, 16; ví dụ gốc ở Android coroutines test #standardtestdispatcher)
```kotlin
// Xấu: launch trên StandardTestDispatcher chỉ được xếp hàng, chưa chạy. Assert chạy trước nên test fail.
@Test fun `load sets items`() = runTest {
    val vm = FavoritesViewModel(FakeFavoritesRepository(cached = listOf("a")), StandardTestDispatcher(testScheduler))
    vm.load()
    assertEquals(listOf("a"), vm.uiState.value.favoriteIds)   // ❌
}

// Tốt: cho scheduler chạy hết việc đang chờ rồi mới assert.
@Test fun `load sets items`() = runTest {
    val vm = FavoritesViewModel(FakeFavoritesRepository(cached = listOf("a")), StandardTestDispatcher(testScheduler))
    vm.load()
    advanceUntilIdle()
    assertEquals(listOf("a"), vm.uiState.value.favoriteIds)   // ✅
}
```

**(f) Test "xanh" nhưng không bảo vệ gì** (rule 19, 20): chạy thử bằng bug cấy
```kotlin
// Cấy bug vào code thật: đổi sortedByDescending thành sortedBy.
// Nếu toàn bộ test vẫn xanh thì không test nào kiểm thứ tự => thiếu test cho rule "gần nhất trước".
```

### 4.5 Checklist khi tự viết một test
- [ ] Tên nói hành vi + điều kiện + kết quả
- [ ] Một when, một then; given ghi đủ dữ liệu quan trọng ngay trong test
- [ ] Gọi qua API công khai; không dùng reflection hay `@VisibleForTesting` để chọc vào bên trong
- [ ] Dependency có trạng thái dùng fake; chỉ verify tương tác cho side effect quan trọng
- [ ] Không có `if`/vòng lặp/tính toán để ra expected
- [ ] Coroutine: `runTest` một lần, dispatcher được inject, Main được thay, cùng một scheduler
- [ ] StateFlow: assert `.value`; Flow nhiều giá trị: Turbine, tiêu thụ hết event
- [ ] Có test cho từng dòng của bảng thất bại trong spec (offline, lỗi mạng, process death)
- [ ] Cấy một bug thì ít nhất một test đỏ

### 4.6 Rubric chấm một test-writer agent (hoặc bộ test do AI viết)
| Dấu hiệu **tốt** | Dấu hiệu **tệ** | Rule |
|---|---|---|
| Mỗi criterion trong spec có ít nhất 1 test, tên nói hành vi | Tên `test1`, `testLoad`, hoặc chỉ test happy path | 1, 2, 6 |
| Dùng fake có trạng thái, assert kết quả | `mockk(relaxed = true)` + `coVerify` ở khắp nơi | 10, 11 |
| Expected viết thẳng ra | Expected tính bằng cùng logic với code | 8 |
| Test fail trước khi implement (có output đỏ) | Không có bằng chứng test từng đỏ | `26` pha 5 |
| Dispatcher inject, `MainDispatcherRule`, không `Thread.sleep` | `runBlocking`, `delay` thật, `Dispatchers.IO` cứng trong code được test | 13-16 |
| Bắt được ≥ 4/5 bug cấy | Toàn bộ xanh khi bug cấy | 19, 20 |
| Gặp bug production thì báo, giữ nguyên test đỏ | Sửa test cho xanh, hoặc sửa code production khi không được giao | `26` pha 5 (Kent Beck) |

### 4.7 Bài tập
1. Lấy 10 test có sẵn trong repo, chấm từng test theo checklist 4.5, ghi lỗi nào gặp nhiều nhất.
2. Viết lại 3 test tệ nhất theo các mẫu (a)-(d).
3. Cho test-writer (kit hoặc của bạn) viết test cho `FavoritesViewModel`, chấm theo rubric 4.6, cấy 5 bug và đếm số bug bị bắt.

---

## 5. Commit message

### 5.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://cbea.ms/git-commit/#seven-rules | 7 rule viết commit message | 15' |
| https://cbea.ms/git-commit/#imperative | Câu tự kiểm "If applied, this commit will…" | 5' |
| https://www.conventionalcommits.org/en/v1.0.0/#summary | `type(scope): description`, ánh xạ sang SemVer | 5' |
| https://www.conventionalcommits.org/en/v1.0.0/#specification | 16 mục của spec (type, scope, breaking change, footer) | 15' |
| https://www.conventionalcommits.org/en/v1.0.0/#examples | 7 ví dụ chuẩn | 5' |
| https://git-scm.com/book/en/v2/Distributed-Git-Contributing-to-a-Project (mục "Commit Guidelines") | `git diff --check`, tách commit bằng `git add --patch` | 10' |

### 5.2 Rule
1. **Q** cbeams, 7 rule: "Separate subject from body with a blank line" · "Limit the subject line to 50 characters" · "Capitalize the subject line" · "Do not end the subject line with a period" · "Use the imperative mood in the subject line" · "Wrap the body at 72 characters" · "Use the body to explain what and why vs. how".
2. **Q** cbeams: "shoot for 50 characters, but consider 72 the hard limit." Câu tự kiểm: "If applied, this commit will *your subject line here*".
3. **P** cbeams: khó tóm tắt commit trong 50 ký tự thường là dấu hiệu commit đang chứa quá nhiều thay đổi; hướng tới commit nguyên tử.
4. **Q** Conventional Commits mục 1: prefix gồm type, scope tuỳ chọn, `!` tuỳ chọn, rồi ": ". Mục 2-3: `feat` là tính năng mới, `fix` là sửa lỗi. Mục 4: scope là danh từ trong ngoặc, ví dụ `fix(parser):`.
5. **Q** mục 12-13: breaking change "MUST consist of the uppercase text BREAKING CHANGE, followed by a colon, space, and description", hoặc dấu "! immediately before the :".
6. **P** Summary: `fix` ứng với PATCH, `feat` với MINOR, BREAKING với MAJOR. Các type khác (`build`, `chore`, `ci`, `docs`, `style`, `refactor`, `perf`, `test`) theo quy ước Angular/commitlint (mục 14 cho phép).
7. **P** Footer theo dạng git trailer, token dùng `-` (ví dụ `Reviewed-by:`, `Refs: #123`).
8. **Lưu ý khi kết hợp hai chuẩn.** Conventional Commits dùng chữ thường sau `type:` (`feat: add…`), còn cbeams rule 3 bảo viết hoa đầu subject. Trong repo dùng Conventional Commits thì áp rule 3 cho phần **sau** dấu hai chấm hoặc bỏ qua. Chọn một kiểu và ghi vào AGENTS.md.

### 5.3 Pattern
- **Một commit = một thay đổi logic build được** (`20` §6.2, Pro Git).
- **Commit cấu trúc tách khỏi commit hành vi** (`refactor:` riêng, `feat:`/`fix:` riêng). Nguồn: Google *Small CLs* #refactoring, Kent Beck *Tidy First?*.
- **Squash "wip" trước khi merge** (`20` tình huống 17).

### 5.4 Ví dụ xấu → tốt
| Xấu | Vì sao | Tốt |
|---|---|---|
| `fix bug` | Không biết bug gì, không có scope | `fix(favorites): keep list visible when offline` |
| `Fixed bug with Y` (ví dụ của cbeams) | Không ở thể mệnh lệnh | `fix(audio): release player when screen stops` |
| `update code.` | Mơ hồ, có dấu chấm cuối | `refactor(favorites): extract FavoritesStore from ViewModel` |
| `feat: add favorites, refactor lessons repo, bump compose` | 3 thay đổi trong 1 commit | 3 commit: `feat(favorites): …`, `refactor(lessons): …`, `build: bump compose BOM to …` |
| `feat(api): remove v1 endpoints` | Breaking nhưng không đánh dấu | `feat(api)!: remove v1 lesson endpoints` + footer `BREAKING CHANGE: clients must call /v2/lessons` |

Một commit đầy đủ:
```
fix(favorites): keep list visible when offline

The favorites screen cleared its list when the network call failed,
because the repository emitted an empty list on IOException. Emit the
cached list instead and expose the error as a separate event, so the
screen can show a snackbar without losing content.

Refs: #142
```

### 5.5 Checklist
- [ ] Subject ≤ 50 ký tự (tối đa 72), mệnh lệnh, không chấm cuối, có type và scope
- [ ] Đọc được thành "If applied, this commit will …"
- [ ] Body nói what và why (không nói how), ngắt dòng 72
- [ ] Breaking change có `!` hoặc `BREAKING CHANGE:`
- [ ] Một thay đổi logic; build được; refactor tách riêng

### 5.6 Rubric chấm một skill/command viết commit
| Tốt | Tệ | Rule |
|---|---|---|
| Đọc `git diff --staged` trước khi viết | Viết theo lời kể trong chat, không theo diff thật | 1 (what/why phải khớp diff) |
| Đề xuất tách khi diff có nhiều thay đổi logic | Gộp mọi thứ vào một commit dài | 3, 5.3 |
| Đúng format, đúng type (`refactor` không bị gọi là `feat`) | Type sai làm release notes và version sai | 4, 6 |
| Body có "why" lấy từ spec/issue | Body liệt kê lại tên file đã đổi | 1 (rule 7) |
| Hỏi trước khi commit (hoặc chỉ in ra) | Tự commit và push | `26` pha 6 |

### 5.7 Bài tập
Chạy `git log --oneline -30` trên repo của bạn, chấm từng commit theo checklist 5.5. Viết lại 5 commit tệ nhất (chỉ trên giấy, không rewrite lịch sử đã push).

---

## 6. Pull request

### 6.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://google.github.io/eng-practices/review/developer/small-cls.html#what-is-small | Thế nào là nhỏ | 5' |
| …/small-cls.html#why | Vì sao nhỏ: review nhanh hơn, kỹ hơn, ít bug hơn, dễ rollback | 5' |
| …/small-cls.html#splitting (#stacking, #splitting-files, #splitting-horizontally, #splitting-vertically, #splitting-grid) | **5 cách tách PR** | 20' |
| …/small-cls.html#refactoring và #test-code | Refactor riêng; test đi cùng code | 5' |
| https://google.github.io/eng-practices/review/developer/cl-descriptions.html#first-line | Dòng đầu tiên | 5' |
| …/cl-descriptions.html#informative | Thân mô tả gồm gì | 5' |
| …/cl-descriptions.html#bad và #good | Ví dụ tệ, ví dụ tốt | 10' |
| …/cl-descriptions.html#review-the-description-before-submitting-the-cl | Đọc lại mô tả trước khi submit | 2' |
| https://docs.github.com/en/pull-requests/concepts/helping-others-review-your-changes#write-small-pull-requests, #provide-context-and-guidance, #review-your-own-pull-request-first, #review-for-security | Góc nhìn GitHub: PR một mục đích, chỉ thứ tự review, tự review trước | 15' |
| https://docs.github.com/en/communities/using-templates-to-encourage-useful-issues-and-pull-requests/creating-a-pull-request-template-for-your-repository | Đặt template ở đâu | 5' |
| https://google.github.io/eng-practices/review/developer/handling-comments.html#code và #think | Xử lý comment của reviewer | 10' |

### 6.2 Rule
**Kích thước**
1. **Q** Google: "the right size for a CL is one self-contained change."
2. **Q** Google: "100 lines is usually a reasonable size… 1000 lines is usually too large". **P** 200 dòng rải trên 50 file thường cũng là quá lớn.
3. **Q** Google: "When in doubt, write CLs that are smaller than you think you need to write." **P** Reviewer có quyền từ chối CL chỉ vì nó quá lớn.
4. **P** Google #refactoring: đổi tên/di chuyển code để CL riêng, tách khỏi feature hoặc bug fix. Dọn nhỏ đi kèm thì được.
5. **Q** Google #test-code: "A CL that adds or changes logic should be accompanied by new or updated tests".
6. **Q** Google: "If you add a new API, you should include a usage of the API in the same CL".
7. **P** GitHub: tách thay đổi lớn thành các PR "that each serve one purpose".

**Mô tả**
8. **Q** Google #first-line: "Short summary of what is being done." · "Complete sentence, written as though it was an order." · "Follow by empty line." · "the first line should stand alone".
9. **P** Google #informative: thân nói vấn đề đang giải, vì sao chọn cách này, hạn chế đã biết, bối cảnh (bug, benchmark, design doc).
10. **P** Google: link ngoài có thể không mở được về sau, nên mô tả phải tự đủ ngữ cảnh.
11. **P** Google: mô tả sinh tự động cũng phải theo đúng các rule trên. **Q** GitHub về PR summary do AI sinh: "review it carefully and add context that only you know."
12. **P** GitHub: chỉ chỗ reviewer cần tập trung; PR nhiều file thì gợi ý thứ tự đọc.

**Trước và sau khi mở**
13. **P** GitHub #review-your-own-pull-request-first: đọc lại diff, tìm thay đổi vô tình, xác nhận đã chạy build/test.
14. **P** GitHub #review-for-security: PR chạm dependency, auth, quyền, workflow hoặc dữ liệu nhạy cảm thì tự review bảo mật và gọi người phù hợp.
15. **P** Google *handling comments*: reviewer không hiểu thì **sửa code cho rõ** trước, thêm comment trong code, sau cùng mới trả lời trong tool. **Q** Tự hỏi trước: "Do I understand what the reviewer is asking for?"
16. **P** GitHub template: đặt `pull_request_template.md` ở root, `docs/` hoặc `.github/`; nhiều template thì đặt trong `.github/PULL_REQUEST_TEMPLATE/`. **Q** "You must create templates on the repository's default branch."

### 6.3 Pattern tách PR (Google #splitting)
| Pattern | Cách làm | Ví dụ mobile |
|---|---|---|
| **Stacking** | PR 2 dựa trên PR 1, review song song | PR 1 thêm `FavoritesStore` + test; PR 2 thêm UI dùng nó |
| **By files** | Nhóm file cần reviewer khác nhau | Thay đổi `.github/workflows` tách cho người lo CI |
| **Horizontal** | Theo lớp, lớp dưới có stub | PR 1 data layer (DAO, repository), PR 2 domain, PR 3 UI |
| **Vertical** | Lát cắt đủ các lớp, nhỏ theo tính năng | PR 1 "thêm yêu thích", PR 2 "hoàn tác", PR 3 "offline" |
| **Grid** | Kết hợp horizontal + vertical | Migration lớn: mỗi module một PR, mỗi PR một bước |
| **Refactor first** | PR refactor không đổi hành vi, rồi PR feature | PR 1 `refactor(lessons): extract repository interface`, PR 2 `feat(favorites): …` |

Lưu ý: Thoughtworks Radar Vol. 35 xếp *stacked PRs* vào Caution, vì có thể dùng để che việc chia nhỏ kém. Mỗi PR trong chồng vẫn phải tự đứng được (rule 1).

### 6.4 Ví dụ xấu → tốt

Xấu (ví dụ tệ lấy từ Google #bad: "Fix bug", "Phase 1.", "Moving code from A to B."):
```
Title: Favorites
Body: Add favorites feature. Fix some bugs. See ticket.
```

Tốt:
```
Title: feat(favorites): show favorite lessons offline

Why
Kids lose their favorites list on the bus because the screen only reads
from the network. Spec: docs/specs/favorites.md (rules 1-2).

What
- data: FavoritesStore backed by the existing lessons DB (core:datasource)
- domain: ToggleFavorite use case with 5 s undo
- ui: Favorites tab + heart toggle on lesson cards; strings en + vi
Out of scope: syncing favorites across devices (follow-up #151).

How tested
- ./gradlew :feature:favorites:testDebugUnitTest  (14 tests, all pass)
- ./gradlew :shared:iosSimulatorArm64Test         (pass)
- Manual: Pixel 7 / Android 16, airplane mode; iPhone 15 simulator

Risk
DB schema change (new table, migration 7→8, migration test added).
Reviewer: please start with core/datasource/…/Migration7to8.kt.

Screenshots: light / dark, en / vi (attached)
Label: ai-assisted
```

### 6.5 Checklist trước khi mở PR
- [ ] Một mục đích; ≤ ~400 dòng hoặc có lý do; refactor tách riêng; test đi cùng code
- [ ] Đã tự đọc hết diff, không có thay đổi vô tình, không có secret
- [ ] Tiêu đề: câu mệnh lệnh tự đứng được (+ type theo Conventional Commits)
- [ ] Mô tả: why, what, out of scope, how tested (lệnh thật + kết quả), risk, chỗ cần đọc trước
- [ ] UI: ảnh light/dark, en/vi; KMP: đã build/test iOS
- [ ] Chạm auth/billing/permission/workflow/dependency: gọi đúng reviewer, tự review bảo mật

### 6.6 Rubric chấm một skill/command tạo PR
| Tốt | Tệ | Rule |
|---|---|---|
| Đo diff, đề xuất **pattern tách cụ thể** (6.3) khi lớn hoặc trộn refactor | Không đo kích thước, gom mọi thứ vào một PR | 1-4, 7 |
| Tiêu đề mệnh lệnh, tự đứng được | "Update", "Fix bug", "Favorites" | 8 |
| Thân có why/what/out-of-scope/test/risk, why lấy từ spec/issue | Liệt kê tên file; "improve code quality" | 9, 12 |
| "How tested" chỉ ghi lệnh **đã chạy thật** trong session | Bịa "all tests pass", hoặc ghi lệnh không tồn tại | 11, 13 |
| Gắn nhãn rủi ro khi chạm auth/billing/DB/workflow | Không phân biệt PR rủi ro với PR thường | 14 |
| Mô tả tự đủ ngữ cảnh | Chỉ có "See ticket" | 10 |
| In nháp hoặc hỏi trước khi push/tạo PR | Tự push, tự tạo PR, tự merge | `26` pha 6, GitHub secure-use (#preventing-github-actions-from-creating-or-approving-pull-requests) |

### 6.7 Bài tập
1. Lấy 3 PR gần nhất của bạn, chấm theo checklist 6.5, viết lại mô tả của PR tệ nhất theo mẫu 6.4.
2. Chọn một PR lớn cũ, viết kế hoạch tách theo 2 pattern khác nhau ở 6.3 và so sánh.
3. Chạy kit `pr_describe.py` (hoặc skill của bạn) trên 5 diff, chấm theo rubric 6.6.

---

## 7. Code review

### 7.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://google.github.io/eng-practices/review/reviewer/standard.html#principles | Khi nào approve; dữ kiện thắng ý kiến; style guide là chuẩn | 10' |
| https://google.github.io/eng-practices/review/reviewer/looking-for.html#design, #functionality, #complexity, #tests, #every-line, #summary | Xem gì, theo thứ tự nào; checklist 12 mục ở #summary | 20' |
| https://google.github.io/eng-practices/review/reviewer/comments.html#courtesy, #why, #label-comment-severity | Viết comment: lịch sự, giải thích lý do, gắn nhãn mức độ | 10' |
| https://conventionalcomments.org/#format, #labels, #decorations | Format `<label> [decorations]: <subject>` | 10' |
| *Software Engineering at Google* Ch9 "Code Review" (https://abseil.io/resources/swe-book/html/ch09.html) | Review ở quy mô lớn: correctness, comprehension, consistency | 30' |
| Bacchelli & Bird, ICSE 2013 (https://www.microsoft.com/en-us/research/publication/expectations-outcomes-and-challenges-of-modern-code-review/) | Review thật ra mang lại gì | 10' (abstract + kết luận) |
| https://developer.android.com/topic/architecture/recommendations (#layered-architecture, #viewmodel, #handle-dependencies) | Chuẩn Android để đối chiếu khi review | 15' |
| https://developer.android.com/kotlin/coroutines/coroutines-best-practices | Chuẩn coroutine để đối chiếu | 15' |

### 7.2 Rule
**Tiêu chuẩn approve**
1. **Q** Google: reviewer "should favor approving a CL once it is in a state where it definitely improves the overall code health… even if the CL isn't perfect." **Q** "there is no such thing as 'perfect' code—there is only better code."
2. **Q** Google: "Technical facts and data overrule opinions and personal preferences." **P** Style guide là chuẩn tuyệt đối về style; thứ không có trong guide là sở thích cá nhân. Design không bao giờ thuần sở thích, nhưng nếu tác giả chứng minh được các cách đều hợp lệ thì chấp nhận cách của tác giả.

**Xem gì**
3. **Q** Google: "The most important thing to cover in a review is the overall design".
4. **Q** Google: "Too complex" thường nghĩa là "can't be understood quickly by code readers." **P** Cảnh giác over-engineering: giải bài toán đang có, không giải bài toán tưởng tượng.
5. **Q** Google: "tests should be added in the same CL as the production code unless the CL is handling an emergency."
6. **P** Google #every-line: đọc mọi dòng do người viết. Khi không hiểu, nhiều khả năng người đọc sau cũng không hiểu, nên yêu cầu làm rõ.
7. **P** Google #summary, checklist 12 mục: design, functionality (cả UI), parallel programming an toàn, complexity, không có tính năng đoán trước, có test, test được thiết kế tốt, naming, comment nói why, docs, style.
8. **P** Google #good-things: khen điểm tốt. **Q** Conventional Comments (praise): "Try to leave at least one of these comments per review. Do not leave false praise".

**Viết comment**
9. **Q** Google: "Be kind." / "Explain your reasoning." **P** Nói về code, không nói về người viết.
10. **Q** Google: nhãn "Nit:", "Optional (or Consider):", "FYI:". Không có nhãn thì "authors may interpret all comments as mandatory".
11. **P** Google #explanations: lời giải thích thường nên dẫn tới việc viết lại code cho rõ. Giải thích chỉ nằm trong tool review thì người đọc code sau này không thấy.
12. **Q** Conventional Comments: format `<label> [decorations]: <subject>`. Labels: praise, nitpick, suggestion, issue, todo, question, thought, chore, note. Decorations: (blocking), (non-blocking), (if-minor). **Q** issue: "strongly recommended to pair this comment with a suggestion."

**Review mang lại gì**
13. **P** Bacchelli & Bird (Microsoft, 2013): review "less about defects than expected". Phần lớn giá trị là chia sẻ hiểu biết, nắm được thay đổi và tìm phương án tốt hơn; khó nhất là **hiểu** thay đổi. Hệ quả cho AI: bot bắt lỗi cơ học, người tập trung vào hiểu và thiết kế.

### 7.3 Pattern
- **Thứ tự đọc**: mô tả + spec → design tổng thể → test (test nói gì về hành vi) → code từng dòng (Google *Navigating a CL*: https://google.github.io/eng-practices/review/reviewer/navigate.html).
- **Review theo rủi ro**: billing, auth, migration DB, quyền Android mới thì cần reviewer senior và đọc kỹ hơn (Thoughtworks Vol. 35, *risk-tiered human oversight*).
- **Checklist mobile** (đối chiếu chuẩn chính chủ, không phải thêm theo cảm tính):

| Kiểm | Chuẩn gốc |
|---|---|
| ViewModel không giữ `Context`, không đọc resource | Android testing fundamentals #decoupling ("don't use Android Contexts in ViewModels"); architecture recommendations #viewmodel |
| Không expose mutable state (`MutableStateFlow` public) | Coroutines best practices ("don't expose mutable types") |
| Dispatcher được inject, suspend function main-safe | Coroutines best practices |
| Không `GlobalScope`; không nuốt `CancellationException` | Coroutines best practices |
| Logic không nằm trong Activity/Fragment/Composable | Android testing fundamentals #decoupling; architecture recommendations #layered-architecture |
| KMP: không có API nền tảng trong `commonMain`; đủ `actual` cho iOS | Kotlin docs, AI for development (acceptance criteria KMP) |
| Logic mới có test trong cùng PR | Google looking-for #tests |

### 7.4 Ví dụ comment xấu → tốt
| Xấu | Tốt |
|---|---|
| "Why did you use threads here when there's obviously no benefit…?" (ví dụ của Google) | "The concurrency model here is adding complexity to the system without any actual performance benefit that I can see. Because there's no performance benefit, it's best for this code to be single-threaded." (Google) |
| "This is not worded correctly." | "suggestion: This is not worded correctly. Can we change this to match the wording of the marketing page?" (Conventional Comments) |
| "Sai rồi, dùng Flow đi." | "issue (blocking): `_state` là `MutableStateFlow` public nên UI có thể ghi đè state. Theo coroutines best practices, expose `StateFlow` qua `asStateFlow()`. suggestion: `val state: StateFlow<UiState> = _state.asStateFlow()`" |
| "Đặt tên lại." | "nitpick (non-blocking): `data2` → `cachedFavorites` sẽ dễ đọc hơn." |
| (không khen gì) | "praise: Test migration 7→8 với dữ liệu thật rất tốt, mình sẽ dùng lại pattern này." |

### 7.5 Checklist khi bạn review
- [ ] Đọc mô tả + spec trước; hiểu PR muốn đạt gì
- [ ] Design: đặt đúng lớp/module? có đơn giản hơn được không?
- [ ] Functionality: biên, lỗi, offline, process death, concurrency
- [ ] Test: có cho logic mới; kiểm hành vi, không kiểm implementation (chủ đề 4)
- [ ] Checklist mobile 7.3
- [ ] Mỗi comment có nhãn + mức độ + lý do (+ đề xuất nếu là issue)
- [ ] Ít nhất một praise thật lòng
- [ ] Approve khi PR làm code health tốt hơn, không chờ hoàn hảo

### 7.6 Rubric chấm một reviewer agent
| Tốt | Tệ | Rule |
|---|---|---|
| Ưu tiên design/functionality/test, có thứ tự | Toàn nhận xét về style và tên biến (việc của linter) | 3, 7 |
| Mỗi phát hiện có file:line, nhãn Conventional Comments, (blocking)/(non-blocking), lý do, đề xuất | "Consider improving error handling" không chỉ chỗ nào | 10, 12 |
| Lý do dẫn chiếu chuẩn (coroutines best practices, architecture recommendations) | Lý do là "best practice" chung chung | 2 |
| Kết luận được "không có lỗi chặn merge" trên diff sạch | Luôn tìm ra lỗi, kể cả khi diff đúng | `26` pha 7 (Claude Code best practices) |
| Bắt lỗi cấy trong checklist mobile 7.3 | Bỏ sót `CancellationException` bị nuốt, `MutableStateFlow` public | 7.3 |
| Chỉ đọc, không sửa file, không approve | Tự sửa code, hoặc ghi "LGTM, approved" | `26` pha 7, GitHub secure-use |
| Số comment có giới hạn, xếp theo mức nghiêm trọng | 40 comment ngang hàng nhau | 10 (nhãn mức độ) |

### 7.7 Bài tập
1. Làm `samples/review-exercise/` (28 lỗi cố ý) bằng tay theo checklist 7.5 và viết comment theo Conventional Comments. So với `ANSWERS.md`.
2. Chạy reviewer agent trên cùng bài, chấm theo rubric 7.6 rồi tính recall (số lỗi bắt / 28) và số báo nhầm.
3. Viết lại 5 comment review cũ của chính bạn theo mẫu 7.4.

---

## 8. CI pipeline

### 8.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://martinfowler.com/articles/continuousIntegration.html#AutomateTheBuild, #MakeTheBuildSelf-testing, #FixBrokenBuildsImmediately, #build-fast, #HideWork-in-progress | Các thực hành CI gốc | 40' |
| https://developer.android.com/training/testing/continuous-integration#typical-example | Pipeline Android điển hình | 5' |
| https://developer.android.com/training/testing/continuous-integration/automation#basic, #instrumented-tests, #performance-regression, #test-coverage | Chạy gì, ở tần suất nào | 15' |
| https://developer.android.com/training/testing/continuous-integration/features#cache, #retry-failed | Cache và bảng xử lý flaky | 10' |
| https://docs.github.com/en/actions/tutorials/build-and-test-code/java-with-gradle#caching-dependencies | Gradle trên GitHub Actions | 10' |
| https://github.com/gradle/actions/blob/main/docs/setup-gradle.md (#caching-build-state-between-jobs, #incompatibility-with-other-caching-mechanisms, #select-which-branches-should-write-to-the-cache, #gradle-wrapper-validation) | Cache Gradle đúng cách | 15' |
| https://docs.github.com/en/actions/reference/workflows-and-actions/dependency-caching#cache-key-matching, #restrictions-for-accessing-a-cache, #best-practices-for-using-caches-securely | Khoá cache, phạm vi, an toàn | 15' |
| https://docs.github.com/en/actions/how-tos/write-workflows/choose-when-workflows-run/control-workflow-concurrency#example-only-cancel-in-progress-jobs-or-runs-for-the-current-workflow | Huỷ run cũ khi có push mới | 5' |
| https://docs.gradle.org/current/userguide/performance.html#inspect_your_build, #enable_build_cache, #enable_configuration_cache, #sec:enable_parallel_execution | Làm build nhanh | 20' |
| https://docs.github.com/en/actions/reference/security/secure-use#good-practices-for-mitigating-script-injection-attacks, #mitigating-the-risks-of-untrusted-code-checkout, #using-third-party-actions, #restricting-permissions-for-tokens | Bảo mật workflow | 20' |
| https://dora.dev/guides/dora-metrics-four-keys/ | 5 chỉ số DORA | 10' |

### 8.2 Rule
**Thực hành CI (Fowler)**
1. **P** Build chạy bằng một lệnh. Lá chắn chính chống bug là "a comprehensive test suite… run before each integration".
2. **Q** "nobody has a higher priority task than fixing the build". **P** Cách sửa thường là revert commit mới nhất về bản tốt gần nhất, chứ không debug trên main.
3. **Q** "the XP guideline of a ten minute build is perfectly within reason". **P** Dựng deployment pipeline: commit build nhanh trước, các stage chậm (instrumented, benchmark) sau.
4. **P** Che việc đang làm dở theo thứ tự: Keystone Interface → Feature Flag (xoá sớm) → Branch By Abstraction cho thay đổi lớn.

**Android CI**
5. **P** Android: thứ tự build → `./gradlew test` → instrumented test trên emulator (Gradle Managed Devices) → báo cáo. Benchmark chạy nightly, không chạy mỗi commit.
6. **P** Android: coverage giảm so với base branch thì fail hoặc cảnh báo, nhưng coverage "shouldn't be the only indicator".
7. **P** Android (#retry-failed): có bảng lỗi flaky → hành động (ví dụ "Emulator failed to boot → Rerun the whole task"). Phải tìm và sửa flaky, không chỉ retry. Cache quá lớn "could be detrimental"; chỉ build module bị ảnh hưởng.

**Gradle và cache**
8. **P** setup-gradle: cache bật mặc định. **Không** cache thêm Gradle User Home bằng `actions/cache` hay `setup-java cache: gradle` (xung đột). Mặc định chỉ default branch được ghi cache, các nhánh khác chỉ đọc. Action tự validate Gradle wrapper.
9. **P** GitHub cache: thứ tự tìm là key khớp đúng → prefix → `restore-keys`, ở nhánh hiện tại rồi default branch. Mục không dùng 7 ngày thì bị xoá; giới hạn mặc định 10 GB/repo. **Q** "Cache contents are not signed or verified", nên không cache secret, và chỉ ghi cache từ trigger tin cậy.
10. **P** Gradle performance: đo trước (Build Scan hoặc profile), đổi một thứ, đo lại. `org.gradle.parallel=true`, `org.gradle.caching=true` (build cache tắt mặc định), configuration cache có giới hạn với plugin. **Q** "The fastest task is one that doesn't run."
11. **P** Concurrency: `group: ${{ github.workflow }}-${{ github.ref }}` + `cancel-in-progress: true`. Thiếu `github.workflow` trong key sẽ huỷ nhầm run của workflow khác. Không huỷ nhánh release.

**Bảo mật workflow (GitHub secure-use)**
12. **Q** "set the default permission for the GITHUB_TOKEN to read access only for repository contents", rồi nâng quyền theo từng job.
13. **P** Giá trị không tin cậy (`github.event.pull_request.title`, body, tên nhánh) truyền qua `env:` hoặc input của action, **không** chèn thẳng `${{ }}` vào `run:`.
14. **Q** "Avoid using the pull_request_target workflow trigger if it's not necessary"; workflow như vậy "must not explicitly check out untrusted code".
15. **Q** Khoá dependency của workflow bằng lockfile ("supersedes SHA pinning"); nếu không có lockfile thì "Pin actions to a full-length commit SHA".
16. **Q** Cho automation tạo hoặc approve PR "could be a security risk".

**Đo**
17. **P** DORA: 5 chỉ số. Throughput gồm change lead time, deployment frequency, failed deployment recovery time. Instability gồm change fail rate, deployment rework rate. Lưu ý "rework rate" của DORA là **deployment** phải làm lại để sửa lỗi production, khác "rework" trong learning log của bạn.

### 8.3 Pattern
- **Hai tầng**: job `check` nhanh (≤ 10 phút: unit test, lint/detekt, build) chạy mỗi push → job chậm (instrumented trên GMD, build iOS khi chạm `commonMain`) chạy khi PR sẵn sàng hoặc theo lịch → benchmark nightly.
- **Job tổng hợp cho required check**: một job `ci-passed` phụ thuộc các job khác, để branch protection chỉ cần trỏ vào một tên.
- **AI review sau tầng tất định**: job AI chỉ chạy khi `check` xanh, chỉ có quyền comment (`26` pha 8).
- **Hide WIP bằng feature flag** thay vì nhánh sống lâu.

### 8.4 Ví dụ xấu → tốt
```yaml
# Xấu: quyền ghi toàn repo, title PR chèn thẳng vào shell (script injection),
# action theo tag trôi, cache Gradle hai lần, không huỷ run cũ.
on: pull_request_target
permissions: write-all
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@main
        with: { ref: "${{ github.event.pull_request.head.sha }}" }
      - uses: actions/setup-java@v6
        with: { distribution: temurin, java-version: 17, cache: gradle }
      - uses: gradle/actions/setup-gradle@v6
      - run: echo "Building ${{ github.event.pull_request.title }}" && ./gradlew build
```
```yaml
# Tốt: trigger pull_request, token chỉ đọc, giá trị không tin cậy qua env,
# một cơ chế cache, huỷ run cũ cùng workflow + ref. Pin action theo SHA (thay <sha>).
on: pull_request
permissions:
  contents: read
concurrency:
  group: ${{ github.workflow }}-${{ github.ref }}
  cancel-in-progress: true
jobs:
  check:
    runs-on: ubuntu-latest
    timeout-minutes: 20
    steps:
      - uses: actions/checkout@<sha>        # v7.0.1
      - uses: actions/setup-java@<sha>      # v6.3.0
        with: { distribution: temurin, java-version: 17 }
      - uses: gradle/actions/setup-gradle@<sha>   # v6.4.1, tự cache + validate wrapper
      - env:
          PR_TITLE: ${{ github.event.pull_request.title }}
        run: |
          echo "Building: $PR_TITLE"
          ./gradlew testDebugUnitTest detekt assembleDebug --build-cache --parallel
```
Version lấy từ tag mới nhất của từng repo ngày 18/11/2026 (`git ls-remote --tags`): checkout v7.0.1, setup-java v6.3.0, gradle/actions v6.4.1. Kiểm lại trước khi dùng, và pin theo SHA của đúng release đó (bảng version và SHA mẫu: `20` mục 10.3).

### 8.5 Checklist
- [ ] Một lệnh build; tầng nhanh ≤ 10 phút; tầng chậm tách riêng
- [ ] Build đỏ trên main: revert trước, debug sau
- [ ] `permissions` tối thiểu; không `pull_request_target` + checkout code PR; giá trị không tin cậy qua env
- [ ] Action pin SHA (hoặc lockfile); có Dependabot cho actions
- [ ] Một cơ chế cache Gradle; chỉ main ghi cache; không cache secret
- [ ] `concurrency` có `github.workflow`; `timeout-minutes` cho mọi job
- [ ] KMP: job iOS chạy khi chạm `commonMain`/`iosMain`
- [ ] Flaky: có quy trình sửa, không chỉ retry

### 8.6 Rubric chấm một workflow CI hoặc workflow AI review (do agent viết)
| Tốt | Tệ | Rule |
|---|---|---|
| `permissions` khai báo hẹp từng job | `write-all` hoặc không khai báo | 12 |
| Giá trị từ event đi qua `env:` | `${{ github.event.* }}` trong `run:` | 13 |
| Không checkout code PR dưới `pull_request_target` | Checkout `head.sha` dưới `pull_request_target` | 14 |
| Action pin SHA, có comment version | `@main`, `@master` | 15 |
| Một cơ chế cache, có `concurrency`, `timeout-minutes` | Cache chồng nhau, run cũ chạy tiếp, job treo 6 giờ | 8, 9, 11 |
| Job AI chạy sau job tất định, chỉ comment | Job AI có quyền push, tự approve hay merge | 16, `26` pha 8 |
| Tầng nhanh ≤ 10 phút | Mọi thứ (instrumented, benchmark) chạy mỗi push | 3, 5 |

### 8.7 Bài tập
Chấm `ci.yml` hiện có của bạn (hoặc Talk Buddies) theo checklist 8.5. Tìm mọi chỗ `${{ github.event` trong `run:` bằng `grep -n 'github.event' .github/workflows/*.yml`. Đo thời gian tầng nhanh, bật `--build-cache` rồi đo lại.

---

## 9. Release: version, release notes, staged rollout

### 9.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://semver.org/#summary và #semantic-versioning-specification-semver | MAJOR.MINOR.PATCH, 11 rule | 15' |
| https://semver.org/#how-should-i-handle-deprecating-functionality | Deprecate thế nào | 5' |
| https://developer.android.com/studio/publish/versioning#appversioning, #versioningsettings | `versionCode` và `versionName` | 10' |
| https://keepachangelog.com/en/1.1.0/#principles, #types, #bad-practices | Changelog cho người đọc | 15' |
| https://support.google.com/googleplay/android-developer/answer/9859348 (mục "Add release notes and manage translations") | Release notes trên Play: thẻ ngôn ngữ, 500 ký tự | 10' |
| https://support.google.com/googleplay/android-developer/answer/6346149 (mục "Set up and maintain a staged rollout", "Tips for live staged rollouts") | Staged rollout, halt, resume | 10' |
| https://developer.android.com/topic/performance/vitals#what_are_the_bad_behavior_thresholds | Ngưỡng vitals ảnh hưởng hiển thị trên Play | 10' |

### 9.2 Rule
**Version**
1. **P** SemVer: PATCH cho sửa lỗi tương thích, MINOR cho tính năng mới tương thích (kể cả đánh dấu deprecate), MAJOR cho thay đổi không tương thích; tăng số lớn thì reset số nhỏ. **Q** Bản đã phát hành "MUST NOT be modified".
2. **P** SemVer FAQ: lỡ phát hành breaking change dưới dạng minor thì phát hành một minor sửa lại, không sửa bản đã phát hành.
3. **P** Android: `versionCode` là số nguyên dương, phải **tăng** ở mọi bản phát hành, không dùng lại, tối đa 2100000000 trên Play, không hiển thị cho người dùng. `versionName` là chuỗi người dùng thấy.

**Release notes**
4. **Q** Keep a Changelog: "Changelogs are for humans, not machines." Mỗi version một mục, bản mới nhất ở trên, có ngày theo dạng ISO.
5. **Q** Nhóm theo loại: Added, Changed, Deprecated, Removed, Fixed, Security. Có mục `Unreleased` ở đầu.
6. **Q** "Using commit log diffs as changelogs is a bad idea". Commit log là nguyên liệu, không phải sản phẩm: phải viết lại theo góc nhìn người dùng. **Q** "list deprecations, removals, and any breaking changes".
7. **Q** Play: "Do not use release notes for promotional purposes or to solicit user actions." **Q** tối đa "500 Unicode characters per language". Mỗi ngôn ngữ đặt giữa thẻ riêng (`<en-US>` … `</en-US>`, `<vi-VN>` … `</vi-VN>`). Release name chỉ dùng nội bộ.

**Rollout**
8. **P** Play: staged rollout chỉ áp dụng cho bản cập nhật, không cho bản phát hành đầu tiên. % "won't increase automatically", bạn phải tự tăng.
9. **P** Halt: "no additional users will receive" bản đó, người đã nhận vẫn giữ. Resume tiếp tục với cùng nhóm người dùng. Đã bắt đầu rollout thì không bỏ bớt quốc gia được. Sửa lỗi bằng một bản mới có bundle đã sửa.
10. **P** Vitals (ngưỡng bad behavior, 28 ngày): user-perceived crash rate 1.09% tổng, 8% theo từng model điện thoại; user-perceived ANR rate 0.47% tổng, 8% theo model. Vượt ngưỡng thì Play có thể giảm hiển thị hoặc gắn cảnh báo trên trang store.

### 9.3 Pattern
- **Release notes hai tầng**: `CHANGELOG.md` theo Keep a Changelog (cho team, đầy đủ) → bản Play "What's new" ≤ 500 ký tự mỗi ngôn ngữ (cho người dùng, chỉ thứ họ thấy).
- **Từ Conventional Commits tới changelog**: `feat` thành Added, `fix` thành Fixed, `!`/`BREAKING CHANGE` thành Changed/Removed. Viết lại câu chữ, không dán log (rule 6).
- **Bậc rollout có cổng**: 1% → 5% → 20% → 50% → 100%. Mỗi bậc chờ đủ dữ liệu, so crash/ANR với bản trước, có ngưỡng halt viết sẵn.

### 9.4 Ví dụ xấu → tốt
| Xấu | Vì sao | Tốt |
|---|---|---|
| "Bug fixes and performance improvements." | Không nói gì với người dùng | "Danh sách Yêu thích giờ mở được cả khi không có mạng." |
| "feat(favorites): FavoritesStore + migration 7→8" | Dán commit log, lộ chi tiết nội bộ | "Mới: bấm tim để lưu bài học yêu thích." |
| "Rate us 5 stars! New awesome features!!!" | Quảng cáo, kêu gọi hành động (vi phạm rule 7) | "Sửa lỗi âm thanh bị tắt khi khoá màn hình." |

Play "What's new":
```
<en-US>
New: tap the heart to save favorite lessons, even offline.
Fixed: audio no longer stops when the screen locks.
</en-US>
<vi-VN>
Mới: bấm tim để lưu bài học yêu thích, dùng được cả khi offline.
Sửa: âm thanh không còn tắt khi khoá màn hình.
</vi-VN>
```

### 9.5 Checklist
- [ ] `versionCode` tăng; `versionName` theo SemVer; breaking change thì tăng MAJOR
- [ ] CHANGELOG có mục cho version này, nhóm theo 6 loại, ngày ISO
- [ ] Play notes ≤ 500 ký tự mỗi ngôn ngữ, đủ en + vi, không quảng cáo, không chi tiết nội bộ
- [ ] Mỗi dòng release notes truy được về một PR/commit
- [ ] Kế hoạch rollout có bậc và ngưỡng halt (so với 1.09% / 0.47% và với bản trước)

### 9.6 Rubric chấm một skill release notes
| Tốt | Tệ | Rule |
|---|---|---|
| Viết lại theo góc nhìn người dùng, nhóm theo loại | Dán commit log | 5, 6 |
| Mỗi dòng truy được về PR; không có dòng bịa | Thêm tính năng không có trong log | `26` pha 9 |
| ≤ 500 ký tự mỗi ngôn ngữ, đúng thẻ ngôn ngữ | Vượt giới hạn, sai thẻ, thiếu tiếng Việt | 7 |
| Không quảng cáo, không chi tiết nội bộ | "Rate us!", tên class, số migration | 6, 7 |
| Nêu rõ breaking change/deprecation (changelog nội bộ) | Giấu thay đổi không tương thích | 6 |
| Tạo draft, người publish | Tự publish lên Play | `26` pha 9 |

### 9.7 Bài tập
Viết CHANGELOG cho 3 bản gần nhất của app từ git log, theo Keep a Changelog. Viết bản Play notes en + vi và đếm ký tự. Viết kế hoạch rollout có ngưỡng halt cho bản tiếp theo.

---

## 10. Vận hành: crash, vitals, sự cố, postmortem

### 10.1 Đọc gì

| Link (đúng mục) | Đọc để biết | Thời gian |
|---|---|---|
| https://developer.android.com/topic/performance/vitals#core-vitals, #what_are_the_bad_behavior_thresholds | Core vitals và ngưỡng | 10' |
| https://firebase.google.com/docs/crashlytics/android/get-deobfuscated-reports#config-r8-proguard-dexguard | Stack trace đọc được (mapping file) | 10' |
| https://firebase.google.com/docs/crashlytics/android/customize-crash-reports#add-keys, #add-logs, #log-excepts | Thêm ngữ cảnh cho crash | 15' |
| https://firebase.google.com/docs/crashlytics/alerts-default#velocity-alerts-trigger | Ngưỡng velocity alert | 5' |
| https://firebase.google.com/docs/crashlytics/troubleshooting#what-is-a-regressed-issue | Thế nào là regressed issue | 5' |
| https://sre.google/sre-book/managing-incidents/#elements-of-incident-management-process, #when-to-declare-an-incident | Quản lý sự cố | 20' |
| https://sre.google/sre-book/postmortem-culture/ | Văn hoá postmortem | 20' |
| https://sre.google/workbook/postmortem-culture/#bad-postmortem, #why-is-this-postmortem-bad, #good-postmortem | Postmortem tệ và tốt, có phân tích | 30' |
| https://sre.google/sre-book/example-postmortem/ | Template đầy đủ | 10' |

### 10.2 Rule
**Vitals và Crashlytics**
1. **P** Vitals: ngưỡng ở 9.2 rule 10; Play đánh giá trên 28 ngày gần nhất. Vitals tính theo người dùng hoạt động hằng ngày, còn Crashlytics tính theo session. Ví dụ của trang vitals: 1 crash trong 3 session hiện 100% ở Vitals nhưng 33% ở Crashlytics. Đừng so trực tiếp hai con số.
2. **P** Crashlytics + R8: plugin Gradle tự upload mapping file. Tắt `mappingFileUploadEnabled` cho build nhanh hơn nhưng stack trace sẽ bị obfuscate. Dùng ProGuard config thì thêm `-keepattributes SourceFile,LineNumberTable`.
3. **P** Custom keys tối đa 64 cặp, mỗi giá trị tối đa 1 kB; log tối đa 64 kB mỗi session (cũ bị bỏ); `recordException` chỉ giữ 8 non-fatal gần nhất; user ID phải là ID/token/giá trị đã hash, không dùng email.
4. **P** Velocity alert mặc định: 1% session và 25 người dùng trong 30 phút, mỗi issue một lần mỗi version. Issue đã đóng chỉ mở lại (regressed) khi crash đến từ version mà Crashlytics chưa biết lúc bạn đóng issue.

**Sự cố**
5. **P** SRE: các vai Incident Command, Operational Work, Communication, Planning. Có một live incident state document. Bàn giao phải nói rõ (ví dụ trong sách: "You're now the incident commander, okay?").
6. **P** SRE (#when-to-declare-an-incident): tuyên bố sự cố khi cần thêm team thứ hai, khi khách hàng thấy được, hoặc sau một giờ tập trung mà chưa giải được. **Q** "Declare an incident early." Thứ tự ưu tiên: "Stop the bleeding, restore service, and preserve the evidence". Với app mobile, "stop the bleeding" thường là halt rollout hoặc tắt feature flag.

**Postmortem**
7. **Q** SRE book: postmortem là "a written record of an incident, its impact, the actions taken to mitigate or resolve it, the root cause(s), and the follow-up actions". **P** Phải viết khi: downtime người dùng thấy được, mất dữ liệu, on-call phải can thiệp (rollback), thời gian xử lý vượt ngưỡng, monitoring không phát hiện. Ngưỡng định nghĩa trước.
8. **Q** "You can't 'fix' people, but you can fix systems and processes." **Q** "Writing a postmortem is not punishment."
9. **P** SRE workbook: postmortem tệ thì thiếu context, thiếu chi tiết chính, action item mơ hồ ("Improve", "Make better"), đổ lỗi, ngôn ngữ cảm xúc ("careless ignorance"), không có owner, ít người đọc, công bố trễ. **Q** "Trying to change human behavior is less reliable than changing automated systems". Tập trung vào "'what' went wrong, not 'who'".
10. **P** SRE workbook: mỗi action item có owner, mã theo dõi, mức ưu tiên, trạng thái kết thúc kiểm được, và có ít nhất một action **phòng ngừa** (không chỉ giảm nhẹ).

### 10.3 Pattern
- **Triage crash theo thứ tự**: mới hoặc regressed trong bản đang rollout → số người dùng bị ảnh hưởng → model/OS tập trung (gần ngưỡng 8% theo model?) → có custom keys/log đủ để tái hiện không → quyết định halt, flag off, hotfix hay để bản sau.
- **Template postmortem (SRE)**: Summary, Impact, Root Causes, Trigger, Resolution, Detection, Action Items (Type: mitigate/prevent/process, Owner, Bug), Lessons Learned (What went well / What went wrong / Where we got lucky), Timeline.

### 10.4 Ví dụ xấu → tốt
| Xấu | Tốt |
|---|---|
| "Dev A quên kiểm tra null nên app crash." | "`LessonRepository.favorites()` trả null khi DB đang migrate; caller không xử lý trường hợp này." |
| Action item: "Cẩn thận hơn khi review." | "Thêm migration test 7→8 chạy trong CI (owner: Thuc, #160, P1, xong khi job `migration-test` là required check)." |
| Action item: "Cải thiện monitoring." | "Bật velocity alert cho issue mới ở bản đang rollout, gửi vào kênh #mobile-oncall (owner: …, #161)." |
| Kết luận root cause từ stack trace chưa deobfuscate | "Stack trace chưa deobfuscate vì thiếu mapping file của 2.4.1; upload lại mapping trước khi kết luận." |

### 10.5 Checklist
- [ ] Mọi build release upload mapping file; custom keys có version, màn hình, trạng thái mạng; user ID đã hash
- [ ] Biết ngưỡng vitals và có alert trước khi chạm ngưỡng
- [ ] Sự cố: ai là incident commander, có live doc, có quyết định halt/flag
- [ ] Postmortem: không đổ lỗi, có timeline, root cause, action item có owner + mã + trạng thái kiểm được + ít nhất một action phòng ngừa
- [ ] Công bố postmortem đúng hạn, cho đúng người đọc

### 10.6 Rubric chấm script triage crash / skill fix-crash / skill postmortem
| Tốt | Tệ | Rule |
|---|---|---|
| Báo "không đủ dữ liệu" khi trace chưa deobfuscate | Kết luận root cause tự tin từ trace obfuscate | 2 |
| Phân biệt số Vitals và số Crashlytics | So 33% với 100% như cùng thước đo | 1 |
| Ưu tiên issue mới/regressed ở bản đang rollout, có số người dùng | Xếp theo số lần crash thuần | 4, 10.3 |
| Postmortem nháp đúng template, ngôn ngữ trung tính | Đổ lỗi cho người, câu cảm xúc | 8, 9 |
| Action item cụ thể, có owner/mã/điều kiện xong | "Improve testing", "Be careful" | 9, 10 |
| Đề xuất "stop the bleeding" trước (halt, flag) | Đề xuất refactor lớn giữa sự cố | 6 |

### 10.7 Bài tập
Lấy 5 crash thật từ Crashlytics, triage theo pattern 10.3 và ghi quyết định. Viết postmortem cho một sự cố cũ theo template SRE, rồi tự chấm theo danh sách "why is this postmortem bad" của SRE workbook.

---

## 11. Dùng file này để chấm một skill/command bất kỳ

Khi gặp một skill, command hay agent (của bạn, của đồng nghiệp, do AI sinh ra, hoặc tải từ mạng), làm 5 bước sau:

1. **Xác định chủ đề**: nó phục vụ chủ đề nào trong 10 chủ đề ở trên?
2. **Đọc rubric của chủ đề đó** (mục x.6). Mỗi dòng là một tiêu chí pass/fail có nguồn.
3. **Đọc skill**: trong skill có câu nào thực thi tiêu chí đó không? Ví dụ skill PR có bước đo kích thước diff không (chủ đề 6, rule 1-4)? Có cấm ghi lệnh test chưa chạy không (rule 11, 13)?
4. **Chạy thử trên golden set** (`26` mục 2.1): đọc output và chấm từng dòng rubric.
5. **Ghi kết quả**: tiêu chí nào fail, sửa ở lớp nào (`26` mục 2.4), và đưa case đó vào golden set.

Nếu bạn không chấm được một dòng rubric vì không chắc thế nào là đúng, quay lại mục x.1 và đọc đúng link đó. Đây chính là lý do file này tồn tại: **không có chuẩn thì không chấm được skill**.

## 12. Thứ tự học đề xuất (khớp `26` mục 14)

| Tuần | Chủ đề trong file này | Làm tay trước | Rồi chấm artifact |
|---|---|---|---|
| 5 | 1 Spec, 2 Design, 4 Unit test | Bài tập 1.7, 2.7, 4.7 | Skill spec (rubric 1.6), test-writer (4.6) |
| 6 | 3 Viết code, 7 Code review | 3.7, 7.7 (`samples/review-exercise`) | AGENTS.md/hook (3.6), reviewer (7.6) |
| 7 | 5 Commit, 6 PR, 8 CI | 5.7, 6.7, 8.7 | Skill commit/PR (5.6, 6.6), workflow (8.6) |
| 8 | 9 Release, 10 Vận hành | 9.7, 10.7 | Skill release notes (9.6), triage/postmortem (10.6) |
