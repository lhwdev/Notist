import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

plugins {
	id("notist.common")
	id("org.jetbrains.compose")
	id("org.jetbrains.kotlin.plugin.compose")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
	sourceSets.apply {
		commonMain.dependencies {
			implementation(libs.findLibrary("compose-runtime").get())
			implementation(libs.findLibrary("compose-foundation").get())
			implementation(libs.findLibrary("compose-material3").get())
			implementation(libs.findLibrary("compose-ui").get())
			implementation(libs.findLibrary("compose-components-resources").get())
			implementation(libs.findLibrary("compose-uiToolingPreview").get())
			implementation(libs.findLibrary("androidx-lifecycle-viewmodelCompose").get())
			implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
		}
	}
}
