object Versions {
    const val toolsBuild = "8.1.0"
    const val toolsKotlin = "1.9.20"
    const val dagger = "2.48.1"
    const val material = "1.10.0"
    const val kotlin = "1.8.0-RC"
    const val core = "1.2.0"
    const val compose = "1.2.0"
    const val room = "2.6.1"
    const val gson = "2.10.1"
    const val junit = "4.13.2"
    const val mockk = "1.13.7"
    const val truth = "1.1.4"
}

object BuildPlugins {
    const val TOOLS_BUILD_GRADLE = "com.android.tools.build:gradle:${Versions.toolsBuild}"
    const val TOOLS_KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.toolsKotlin}"
    const val TOOLS_DAGGER_HILT = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
    const val ANDROID_APPLICATION_PLUGIN = "com.android.application"
    const val ANDROID_LIBRARY_PLUGIN = "com.android.library"
    const val KOTLIN_ANDROID_PLUGIN = "kotlin-android"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val DAGGER_HILT = "dagger.hilt.android.plugin"
}

object Di {
    const val hilt = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:1.1.0"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.1.0"
}

object Google {
    private const val MATERIAL_DESIGN = "com.google.android.material:material:${Versions.material}"
    val list = listOf(MATERIAL_DESIGN)
}

object Kotlin {
    private const val KT_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    private const val KTX_CORE = "androidx.core:core-ktx:${Versions.core}"
    private const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin}"
    private const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin}"

    val list = listOf(KT_STD, KTX_CORE, COROUTINES, COROUTINES_ANDROID)
}

object Compose {
    private const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.compose}"
    private const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.compose}"
    private const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.compose}"
    private const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    private const val COMPOSE_DEBUG_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    private const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:2.5.3"
    private const val COMPOSE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0"


    val list = listOf(
        COMPOSE_UI,
        ACTIVITY_COMPOSE,
        COMPOSE_MATERIAL,
        COMPOSE_TOOLING,
        COMPOSE_DEBUG_TOOLING,
        COMPOSE_NAVIGATION,
        COMPOSE_RUNTIME,
    )
}

object ThirdParty {
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

object Test {
     const val JUNIT = "junit:junit:${Versions.junit}"
     const val MOCKK = "io.mockk:mockk:${Versions.mockk}"
     const val TRUTH = "com.google.truth:truth:${Versions.truth}"

    val list = listOf(
        JUNIT,
        MOCKK,
        TRUTH
    )
}