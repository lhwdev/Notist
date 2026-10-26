plugins {
	id("notist.common")
}

commonDependencies {
	implementation(projects.core.common)
	implementation(projects.core.model)
}
