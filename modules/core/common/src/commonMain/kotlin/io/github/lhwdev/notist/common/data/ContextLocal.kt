@file:Suppress("NOTHING_TO_INLINE")

package io.github.lhwdev.notist.common.data

import kotlin.jvm.JvmInline


@JvmInline
value class ContextLocal<T> private constructor(@PublishedApi internal val local: PlatformContextLocal<T>) {
	constructor(defaultValue: () -> T) : this(local = PlatformContextLocal(defaultValue))
	
	inline val current: T
		get() = local.current
	
	inline operator fun invoke(): T = current
	
	inline fun <R> with(new: T, block: () -> R): R {
		val previous = local.current
		local.set(new)
		return try {
			block()
		} finally {
			local.set(previous)
		}
	}
	
	suspend inline fun <R> withSuspend(new: T, crossinline block: suspend () -> R): R =
		local.withSuspend(new, block)
	
	@Deprecated("Unsafe API", level = DeprecationLevel.ERROR)
	fun set(new: T) {
		local.set(new)
	}
	
	
	companion object {
		operator fun <T> invoke(): ContextLocal<T?> =
			ContextLocal(defaultValue = { null })
	}
}


expect class PlatformContextLocal<T>(defaultValue: () -> T) {
	val current: T
	
	fun set(new: T)
	
	suspend inline fun <R> withSuspend(new: T, crossinline block: suspend () -> R): R
}
