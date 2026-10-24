import gradle.kotlin.dsl.accessors._47744de74c0441e971000dbe81cc2b2d.commonMain
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByName
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun Project.commonDependencies(block: KotlinDependencyHandler.() -> Unit) {
	extensions.getByName<KotlinMultiplatformExtension>("kotlin").sourceSets.commonMain.configure {
		dependencies(block)
	}
}
