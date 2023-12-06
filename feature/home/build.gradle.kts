plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    namespace = "com.multimodule.feature"
    compileSdk = AppVersions.COMPILE_SDK

    defaultConfig {
        namespace = (AppVersions.APPLICATION_ID)
        minSdk = (AppVersions.MIN_SDK)
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.5"
    }
}

dependencies {

    api(project(":navigator"))
    api(project(":domain"))

    Google.list.forEach(::api)
    Kotlin.list.forEach(::api)
    Compose.list.forEach(::api)

    /*DI*/
    with(Di){
        api(hilt)
        api(hiltNavigationCompose)
        kapt(hiltCompiler)
        kapt(hiltAndroidCompiler)
    }
}