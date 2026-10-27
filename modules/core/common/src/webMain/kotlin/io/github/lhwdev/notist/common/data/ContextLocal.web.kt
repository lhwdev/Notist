package io.github.lhwdev.notist.common.data


private val DefaultValuePlaceholder = Any()


actual class PlatformContextLocal<T> actual constructor(private val defaultValue: () -> T) {
	@PublishedApi
	internal var _current: Any? = try {
		defaultValue()
	} catch(e: Throwable) {
		null
	}
	
	actual val current: T
		get() = _current.let {
			if(it == DefaultValuePlaceholder) {
				defaultValue()
			} else {
				@Suppress("UNCHECKED_CAST")
				it as T
			}
		}
	
	actual fun set(new: T) {
		_current = new
	}
	
	actual suspend inline fun <R> withSuspend(new: T, crossinline block: suspend () -> R): R {
		val previous = _current
		return try {
			_current = new
			block()
		} finally {
			_current = previous
		}
	}
}
