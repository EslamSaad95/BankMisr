@file:Suppress("UnstableApiUsage")

import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.parcelize)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.secrets.gradle)
  alias(libs.plugins.hilt.android)
  alias(libs.plugins.kotlin.kapt)
  alias(libs.plugins.ksp)
}

val prop = Properties().apply {
  load(FileInputStream(File(rootProject.rootDir, "secrets.properties")))
}
val baseUrl: String = prop.getProperty("BASE_URL")


android {
  namespace = libs.versions.applicationId.get()
  buildToolsVersion = libs.versions.buildToolsVersion.get()
  compileSdk = libs.versions.targetSdk.get().toInt()

  defaultConfig {
    applicationId = libs.versions.applicationId.get()
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = libs.versions.versionCode.get().toInt()
    versionName = libs.versions.versionName.get()
    resourceConfigurations.addAll(listOf("ar", "en")) // Remove unused alternative resources
    vectorDrawables.useSupportLibrary = true
  }

  applicationVariants.all {
    outputs.all {
      val date = Calendar.getInstance().time
      val timestamp = SimpleDateFormat("dd-MM-yyyy").format(date)
      val fileName = "bank_misr-v${versionName}(${versionCode})_${timestamp}"
      setProperty("archivesBaseName", fileName)
    }
  }

  buildTypes {
    getByName("debug") {
      isDebuggable = true
      isMinifyEnabled = false // Shrink code by removing unused methods
      isShrinkResources = false // Remove all the resources that are not used in the project
      buildConfigField("String", "BASE_URL", baseUrl)
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }

    getByName("release") {
      isDebuggable = false
      isMinifyEnabled = true // Shrink code by removing unused methods
      isShrinkResources = true // Remove all the resources that are not used in the project
      buildConfigField("String", "BASE_URL", baseUrl)
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = "17"
    javaParameters = true

    freeCompilerArgs = listOf(
      // Don’t generate not-null assertions for arguments of platform types
      "-Xno-call-assertions",
      // Don’t generate not-null assertion for extension receiver arguments of platform types
      "-Xno-receiver-assertions",
      // Don’t generate not-null assertions on parameters of methods accessible from Java
      "-Xno-param-assertions",

      "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
    )
  }

  kapt.correctErrorTypes = true
  composeOptions.kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()

  buildFeatures {
    buildConfig = true
    compose = true
  }

  bundle {
    language.enableSplit = false
  }

  packagingOptions.resources {
    // Multiple dependency bring these files in.
    // Exclude them to enable our test APK to build (has no effect on our AARs)
    excludes += listOf(
      "/META-INF/{AL2.0,LGPL2.1}",
      "META-INF/DEPENDENCIES",
      "META-INF/LICENSE",
      "META-INF/LICENSE.txt",
      "META-INF/license.txt",
      "META-INF/NOTICE",
      "META-INF/NOTICE.txt",
      "META-INF/notice.txt",
      "META-INF/ASL2.0",
      "META-INF/*.kotlin_module"
    )
  }
}

secrets {
  // Optionally specify a different file name containing your secrets.
  // The plugin defaults to "local.properties"
  propertiesFileName = "secrets.properties"
  // A properties file containing default secret values. This file can be checked in version control.
  defaultPropertiesFileName = "secrets.properties"
}

dependencies {
  debugImplementation(libs.compose.ui.tooling)

  implementation(platform(libs.compose.bom))
  implementation(libs.bundles.dependencies)
  implementation("com.google.android.gms:play-services-location:21.0.1")
  implementation("io.coil-kt:coil-compose:1.3.2")


  kapt(libs.bundles.compilers)
  ksp(libs.ksp)
}