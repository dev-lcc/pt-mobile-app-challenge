import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    id("kotlin-parcelize")
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xexpect-actual-classes",
            )
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            // Compose Preview
            implementation(libs.compose.ui.util)
            implementation(libs.compose.ui.tooling)
            implementation(libs.compose.ui.tooling.preview)

            // Local Database
            implementation(libs.sqlDelight.driver.android)
            implementation(libs.sqlDelight.driver.sqlite)
        }

        iosMain.dependencies {
            // Local Database
            implementation(libs.sqlDelight.driver.native)
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            /*implementation(compose.materialIconsExtended)*/
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            // Koin
            implementation(libs.koin.core)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Network Frameworks
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.engine.cio)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.contentNegotiation)

            // Local Database
            implementation(libs.sqlDelight.runtime)
            implementation(libs.sqlDelight.coroutines.extensions)

            // Store
            implementation(libs.store)

        }
        commonTest.dependencies {
            implementation(libs.koin.test)
            implementation(libs.kotlinx.coroutines.test)
            implementation(libs.ktor.client.mock)
        }

    }
}

android {
    namespace = "io.github.devlcc.ptmobileappchallenge.common"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    /*sourceSets["main"].res.srcDirs("src/androidMain/res")*/
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidXComposeCompiler.get()
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        implementation(libs.compose.ui.util)
        implementation(libs.compose.ui.tooling)
        implementation(libs.compose.ui.tooling.preview)

        // https://github.com/xerial/sqlite-jdbc?tab=readme-ov-file
        testImplementation("org.xerial:sqlite-jdbc") {
            // Override the version of sqlite used by sqlite-driver to match Android API 24
            version { strictly("3.45.2.0") }
        }
        testDebugImplementation(libs.robolectric)
    }
}

sqldelight {
    databases {
        create("PTDatabase") {
            packageName.set("io.github.devlcc.ptmobileappchallenge")
            dialect(libs.sqlDelight.dialect.sqlite338)
        }
    }
}