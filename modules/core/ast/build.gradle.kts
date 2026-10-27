plugins {
	id("notist.common")
}

commonDependencies {
	api(projects.core.common)
	api(projects.core.model)
}
