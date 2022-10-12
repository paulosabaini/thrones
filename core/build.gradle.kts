plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.ktLint)
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
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    implementation(libs.bundles.common)
    implementation(libs.kotlinSerialization)
    implementation(libs.kotlinSerializationConverter)
    implementation(libs.lifecycleViewModel)
    implementation(libs.okHttpLoggingInterceptor)
    implementation(libs.retrofit)

    kapt(libs.hiltCompiler)

    detektPlugins(libs.detektTwitterCompose)
}