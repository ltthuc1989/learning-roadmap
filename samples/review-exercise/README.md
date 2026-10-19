# review-exercise: "Review and refactor this codebase" in 45 minutes

Bài tập mô phỏng vòng "fix and refactor" kiểu Agoda (xem `14-Mock-Interview-Scripts.md` mục 4). Project là Kotlin/JVM thuần, không có dependency Android, nên build được ở bất kỳ máy nào có JDK 17+ và Gradle (hoặc mở bằng IntelliJ / Android Studio).

Code mô phỏng một "data layer" của app ngân hàng: `TransactionRepository`, `SyncService`, `SessionManager`, một ViewModel-like class dùng `StateFlow`, `HttpClient` + helper `retry`, và `Cache`. Trong code có hơn 15 lỗi cố ý: coroutine, concurrency, security, logic, performance, API design. Code compile và test pass, nghĩa là test hiện tại không bắt được lỗi. Đó cũng là một phần của bài.

## Cách chạy

Không có Gradle wrapper trong thư mục này (tránh commit binary). Dùng Gradle đã cài hoặc IDE.

```bash
# Gradle 8.x hoặc 9.x đã cài sẵn, JDK 17 trở lên
gradle build        # compile + chạy test
gradle run          # chạy Main.kt với backend giả lập, in ra một vài triệu chứng
gradle test --info  # xem log test
```

Hoặc: IntelliJ IDEA / Android Studio, File > Open, chọn thư mục này, chờ Gradle sync, chạy `Main.kt`.

Nếu máy chưa có Gradle: `brew install gradle` (macOS) hoặc tải tại https://gradle.org/install/. Kotlin plugin trong `build.gradle.kts` là 2.3.10, coroutines 1.11.0, JUnit 5.10.2.

## Luật chơi (45 phút, như phỏng vấn thật)

1. Phút 0-5: đọc README này, chạy `gradle run`, đọc output. Đoán có gì lạ.
2. Phút 5-25: đọc code theo thứ tự `Models.kt` > `HttpClient.kt` > `SessionManager.kt` > `TransactionRepository.kt` > `SyncService.kt` > `TransactionsViewModel.kt` > `Cache.kt` > `AppConfig.kt` > test. Ghi mỗi lỗi một dòng: `file:line, loại, mức độ (Critical/High/Medium/Low), vì sao, sửa thế nào`.
3. Phút 25-40: xếp ưu tiên. Sửa 2-3 lỗi Critical ngay trong code (nói to bằng tiếng Anh như đang phỏng vấn).
4. Phút 40-45: tóm tắt bằng tiếng Anh: "Here is what I found, here is what I would fix first and why, here is what I would leave for a follow-up PR."

Chỉ mở `ANSWERS.md` sau khi hết giờ. Tự chấm:

| Kết quả | Mức |
|---|---|
| Tìm được 12 lỗi trở lên, có đủ 4 lỗi Critical | Sẵn sàng cho vòng này ở mức Senior/Staff |
| Tìm được 8-11 lỗi, thiếu 1-2 Critical | Cần làm lại sau 1 tuần |
| Dưới 8 lỗi | Đọc lại `04-Android-Kotlin-Deep-Dive.md` phần Coroutines và `08-Security.md`, rồi làm lại |

## Gợi ý cách narrate (English)

- "Before I change anything, let me map the data flow: ViewModel calls Repository, Repository calls HttpClient through a retry helper, SyncService pushes pending items in a background loop."
- "I will group findings by severity: anything that can lose money or leak user data is Critical, crashes are High, performance and maintainability are Medium or Low."
- "I would not refactor everything in this session. I will fix the top three and leave the rest as TODOs with a short justification."

## Sau khi làm xong

- Ghi điểm và danh sách lỗi bạn bỏ sót vào `PROGRESS.md`.
- Thử lại sau 2 tuần với file khác: mở một module cũ trong `YammyDelivery` và áp dụng checklist 10 điểm trong `14-Mock-Interview-Scripts.md`.
