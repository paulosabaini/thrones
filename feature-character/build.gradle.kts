plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktlint)
}

android {
    compileSdk = 33
    namespace = "org.sabaini.thrones.feature.character"

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi"
        )
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    sourceSets {
        getByName("test") {
            java.srcDir(project(":core").file("src/test/java"))
        }
    }
}

dependencies {
    implementation(project(":core"))

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.common)
    implementation(libs.accompanist.swipe.refresh)
    implementation(libs.coil)
    implementation(libs.navigation.hilt)
    implementation(libs.kotlin.serialization)
    implementation(libs.retrofit)
    implementation(libs.room)
    implementation(libs.lifecycle.runtime.compose)
    testImplementation(libs.bundles.common.test)
    androidTestImplementation(libs.test.android.compose)
    androidTestImplementation(platform(libs.compose.bom))
    debugImplementation(libs.debug.compose.manifest)

    kapt(libs.hilt.compiler)

    detektPlugins(libs.detekt.twitter.compose)
}