plugins {
	id("notist.common")
}

commonDependencies {
	implementation(projects.core.ast)
	implementation(projects.core.storage)
	implementation(projects.core.plugin)
}
