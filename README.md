# Thrones

Android project based on [Android Kotlin starter project - 2022 edition](https://github.com/krzdabrowski/android-starter-2022) with the purpose of learning good practices using Kotlin features and latest Android libraries from Jetpack


### **Description**
Application connects to [Game of Thrones Character Api](https://thronesapi.com) to get a list of Game of Thrones characters.

Data always comes from the local persistence (offline-first approach) and updates when necessary.

Clicking on each item show a screen with more information about the character.

Use swipe-down gesture to refresh downloaded data.

Supports light/dark mode theming automatically.


### **Libraries/concepts used**

* Gradle modularised project by features
* The Clean Architecture with MVI pattern in presentation layer
* Jetpack Compose with Material3 design - for UI layer
* Kotlin Coroutines & Kotlin Flow - for concurrency & reactive approach
* Kotlin Serialization converter - for JSON parsing
* Retrofit - for networking
* Hilt - for Dependency Injection pattern implementation
* Room - for local database
* Coil - for image loading
* Version Catalog - for dependency management
* Timber - for logging
* JUnit5, Turbine and MockK - for unit tests
* Jetpack Compose test dependencies and Hilt - for UI tests
* GitHub Actions - for CI/CD
* KtLint and Detekt - for code linting
