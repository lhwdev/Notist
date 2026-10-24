@file:Suppress("UnstableApiUsage")

rootProject.name = "Notist"


enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	includeBuild("build-logic")
	repositories {
		google {
			mavenContent {
				includeGroupAndSubgroups("androidx")
				includeGroupAndSubgroups("com.android")
				includeGroupAndSubgroups("com.google")
			}
		}
		mavenCentral()
		gradlePluginPortal()
	}
}

dependencyResolutionManagement {
	repositories {
		google {
			mavenContent {
				includeGroupAndSubgroups("androidx")
				includeGroupAndSubgroups("com.android")
				includeGroupAndSubgroups("com.google")
			}
		}
		mavenCentral()
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}


/// Projects

fun includeModule(path: String) {
	include(path)
	project(path).projectDir = file("modules${path.replace(':', '/')}")
}

includeModule(":core")
includeModule(":core:model")
includeModule(":core:ast")
includeModule(":core:storage")
includeModule(":core:sync")
includeModule(":core:scripting")
includeModule(":core:plugin")
includeModule(":core:editor")

includeModule(":plugins")
includeModule(":plugins:git")
includeModule(":plugins:learning-assistant")

includeModule(":app")
includeModule(":app:common")
includeModule(":app:desktop")
includeModule(":app:web")
