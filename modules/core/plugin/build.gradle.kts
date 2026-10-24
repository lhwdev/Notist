plugins {
	id("notist.commonUi")
}

commonDependencies {
	implementation(projects.core.ast)
	implementation(projects.core.scripting)
}
