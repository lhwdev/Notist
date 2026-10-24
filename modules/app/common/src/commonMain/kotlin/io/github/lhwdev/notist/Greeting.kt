package io.github.lhwdev.notist

class Greeting {
	private val platform = getPlatform()
	
	fun greet(): String {
		return sayHello(platform.name)
	}
}
