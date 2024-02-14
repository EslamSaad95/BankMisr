plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.parcelize) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.gradle.versions) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.secrets.gradle) apply false
  alias(libs.plugins.hilt.android) apply false
  alias(libs.plugins.kotlin.kapt) apply false
  alias(libs.plugins.ksp) apply false
}

allprojects {
  apply(plugin = "com.github.ben-manes.versions")
}