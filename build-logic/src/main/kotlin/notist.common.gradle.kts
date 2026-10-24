import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
	id("org.jetbrains.kotlin.multiplatform")
	id("org.jetbrains.kotlin.plugin.serialization")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

configure<KotlinMultiplatformExtension> {
	jvm()
	
	js {
		browser()
	}
	
	@OptIn(ExperimentalWasmDsl::class)
	wasmJs {
		browser()
	}
	
	sourceSets.apply {
		commonMain.dependencies {
			implementation(libs.findLibrary("kotlinx-coroutines-core").get())
			implementation(libs.findLibrary("kotlinx-serialization-json").get())
		}
		commonTest.dependencies {
			implementation(libs.findLibrary("kotlin-test").get())
		}
		jsMain.dependencies {
			implementation(libs.findLibrary("wrappers-browser").get())
		}
	}
}
