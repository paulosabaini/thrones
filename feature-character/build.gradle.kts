plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ktLint)
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
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
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

    implementation(libs.bundles.common)
    implementation(libs.coil)
    implementation(libs.composeAccompanistSwipeRefresh)
    implementation(libs.composeNavigationHilt)
    implementation(libs.kotlinSerialization)
    implementation(libs.retrofit)
    implementation(libs.room)
    testImplementation(libs.bundles.commonTest)
    androidTestImplementation(libs.testAndroidCompose)
    debugImplementation(libs.debugComposeManifest)

    kapt(libs.hiltCompiler)

    detektPlugins(libs.detektTwitterCompose)
}