import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.composeMultiplatform)
	alias(libs.plugins.composeCompiler)
}

dependencies {
	implementation(projects.app.common)
	
	implementation(compose.desktop.currentOs)
	implementation(libs.kotlinx.coroutines.swing)
	implementation(libs.compose.uiToolingPreview)
}

compose.desktop {
	application {
		mainClass = "io.github.lhwdev.notist.MainKt"
		
		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "io.github.lhwdev.notist"
			packageVersion = "1.0.0"
		}
	}
}
