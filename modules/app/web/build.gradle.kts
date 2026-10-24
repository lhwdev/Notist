plugins {
	id("notist.commonUi")
}

kotlin {
	js {
		binaries.executable()
	}
	wasmJs {
		binaries.executable()
	}
}

commonDependencies {
	implementation(projects.app.common)
}
