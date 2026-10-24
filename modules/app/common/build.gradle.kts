plugins {
	id("notist.commonUi")
}

compose.resources {
	packageOfResClass = "notist.core.generated.resources"
}

commonDependencies {
	implementation(projects.core.ast)
	implementation(projects.core.storage)
	implementation(projects.core.sync)
	implementation(projects.core.scripting)
	implementation(projects.core.plugin)
	implementation(projects.core.editor)
	implementation(projects.plugins.git)
	implementation(projects.plugins.learningAssistant)
}
