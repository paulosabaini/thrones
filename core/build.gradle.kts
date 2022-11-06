plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.ktlint)
}

android {
    compileSdk = 33
    namespace = "org.sabaini.thrones.core"

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        buildConfigField("String", "THRONES_API_URL", "\"https://thronesapi.com/api/v2/\"")
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

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.FlowPreview",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
        )
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.common)
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.serialization.converter)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    testImplementation(libs.bundles.common.test)

    kapt(libs.hilt.compiler)

    detektPlugins(libs.detekt.twitter.compose)
}