plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {

    android()

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "feature_core"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {

                api(project(":shared_ui_components"))

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.koin)
                implementation(libs.voyager.transition)

                // Koin
                implementation("io.insert-koin:koin-core:3.4.0")
                implementation("io.insert-koin:koin-test:3.2.0")

                implementation("com.russhwolf:multiplatform-settings:1.0.0")
                implementation("com.russhwolf:multiplatform-settings-no-arg:1.0.0")

                implementation("io.ktor:ktor-client-content-negotiation:2.3.0")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")
                implementation("io.ktor:ktor-client-core:2.3.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.2")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")

                implementation("io.ktor:ktor-client-cio:2.3.0")
                implementation("io.ktor:ktor-client-okhttp:2.3.1")

                implementation("com.github.chuckerteam.chucker:library:3.5.2")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
                implementation("io.insert-koin:koin-androidx-compose:3.4.5")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.3.1")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }

}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.eyedea.shared_core"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}