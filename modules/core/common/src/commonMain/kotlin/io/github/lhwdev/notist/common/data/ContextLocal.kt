@file:Suppress("NOTHING_TO_INLINE")

package io.github.lhwdev.notist.common.data

import kotlin.jvm.JvmInline


@JvmInline
value class ContextLocal<T> private constructor(@PublishedApi internal val local: PlatformContextLocal<T>) {
	constructor(defaultValue: () -> T) : this(local = PlatformContextLocal(defaultValue))
	
	inline val current: T
		get() = local.current
	
	inline operator fun invoke(): T = current
	
	inline fun <R> with(new: T, block: () -> R): R =
		local.with(new, block)
	
	suspend inline fun <R> withSuspend(new: T, crossinline block: suspend () -> R): R =
		local.withSuspend(new, block)
	
	
	companion object {
		operator fun <T> invoke(): ContextLocal<T?> =
			ContextLocal(defaultValue = { null })
	}
}


expect class PlatformContextLocal<T>(defaultValue: () -> T) {
	val current: T
	
	inline fun <R> with(new: T, block: () -> R): R
	
	suspend inline fun <R> withSuspend(new: T, crossinline block: suspend () -> R): R
}
