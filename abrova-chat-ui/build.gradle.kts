plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

val sdkVersion = "0.2.0"

android {
    namespace = "ir.abrova.chat.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

mavenPublishing {
    publishToMavenCentral(
        com.vanniktech.maven.publish.SonatypeHost.CENTRAL_PORTAL,
        automaticRelease = true
    )
    signAllPublications()

    coordinates("ir.abrova", "abrova-chat-android-ui", sdkVersion)

    pom {
        name.set("Abrova Chat Android UI")
        description.set("Pre-built Jetpack Compose UI components for AbrovaChat Android SDK.")
        inceptionYear.set("2025")
        url.set("https://abrova.ir/cloud/abrova-chat")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("abrova")
                name.set("Abrova")
                email.set("founder@abrova.ir")
                url.set("https://abrova.ir")
            }
        }

        scm {
            url.set("https://github.com/Abrova-co/abrova_chat_android_sdk")
            connection.set("scm:git:git://github.com/Abrova-co/abrova_chat_android_sdk.git")
            developerConnection.set("scm:git:ssh://git@github.com/Abrova-co/abrova_chat_android_sdk.git")
        }
    }
}

dependencies {
    // AbrovaChat Core SDK
    implementation(project(":abrova-chat"))

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Compose BOM
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)

    // Compose UI
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.foundation:foundation")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.8.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Image loading
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
