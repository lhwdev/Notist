package io.github.lhwdev.notist.common.data

import kotlinx.coroutines.asContextElement
import kotlinx.coroutines.withContext


actual class PlatformContextLocal<T> actual constructor(defaultValue: () -> T) {
	val threadLocal: ThreadLocal<T> = object : ThreadLocal<T>() {
		override fun initialValue(): T =
			defaultValue()
	}
	
	actual val current: T
		get() = threadLocal.get()
	
	actual inline fun <R> with(new: T, block: () -> R): R {
		val previous = threadLocal.get()
		return try {
			threadLocal.set(new)
			block()
		} finally {
			threadLocal.set(previous)
		}
	}
	
	actual suspend inline fun <R> withSuspend(new: T, crossinline block: suspend () -> R): R =
		withContext(threadLocal.asContextElement(new)) { block() }
}
