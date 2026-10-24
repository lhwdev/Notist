package io.github.lhwdev.notist

interface Platform {
	val name: String
}

expect fun getPlatform(): Platform
