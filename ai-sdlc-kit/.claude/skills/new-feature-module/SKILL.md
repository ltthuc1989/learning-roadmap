---
name: new-feature-module
description: Scaffold a new KMP feature module (feature/<name>) following this repo's module rules, convention plugins, Koin module, navigation entry and a first ViewModel test. Use when the user asks to add a new feature, screen group or module.
argument-hint: <feature-name>
---
Create feature module `feature/$ARGUMENTS`.

1. Read `settings.gradle.kts`, one existing `feature/*/build.gradle.kts`, and the convention plugins in `build-logic` to copy the exact plugin ids. Do not invent plugin ids.
2. Create `build.gradle.kts` and, under `src/commonMain/kotlin/.../$ARGUMENTS/`: `<Name>Screen.kt`, `<Name>ViewModel.kt` (StateFlow UiState), `di/<Name>Module.kt` (Koin), and a navigation entry following the existing pattern.
3. Register the module in `settings.gradle.kts` and wire the Koin module where the others are wired.
4. Add `src/commonTest/.../<Name>ViewModelTest.kt` with one passing test (initial state).
5. Run the module's tests and `./gradlew detekt`. Fix until green.
6. Report files created and the real command output. Do not touch unrelated modules.
