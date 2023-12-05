@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
}

android {
    namespace = AppVersions.APPLICATION_ID
    compileSdk = AppVersions.COMPILE_SDK

    defaultConfig {
        applicationId = (AppVersions.APPLICATION_ID)
        minSdk = (AppVersions.MIN_SDK)
        targetSdk = (AppVersions.TARGET_SDK)
        versionCode = AppVersions.versionCode
        versionName = AppVersions.versionName
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    Google.list.forEach(::api)
    Kotlin.list.forEach(::api)

    /*DI*/
    api(Di.hilt)
    api(Di.hiltNavigationCompose)
    kapt(Di.hiltCompiler)
    kapt(Di.hiltAndroidCompiler)
}