package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.core.util.serialization.BasePolymorphicRegistry
import io.github.lhwdev.notist.core.util.serialization.PolymorphicRegistry
import io.github.lhwdev.notist.core.util.serialization.RegistryPolymorphicSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.reflect.KClass


interface MarkType<T : Mark> : PolymorphicRegistry.RegistryItem<T> {
	override val key: String
	
	override val kClass: KClass<T>
	
	override fun serializer(): KSerializer<T>
}


@Serializable(with = MarkSerializer::class)
interface Mark {
	companion object : BasePolymorphicRegistry<Mark, MarkType<Mark>>()
	
	
	val type: MarkType<*>
	
	
	/**
	 * Determines whether mark is active, when placing cursor at the end of this mark.
	 *
	 * If this is true, inserted text right to this mark will also include this mark.
	 */
	val isEndInclusive: Boolean
		get() = true
	
	/**
	 * Whether this mark can be joined with adjacent [right] mark. This is called when two text can be merged, meaning
	 * there are no marks other than `this` and `right`.
	 * If this returns non-null result, both mark will be replaced with result.
	 */
	fun merge(right: Mark): Mark? =
		takeIf { this == right }
	
	/**
	 * Whether this mark can be visually represented as one chunk, joined with [right].
	 * For example, when two [FormattingMark.Code] is adjacent, they will be shown as one rectangular shape.
	 *
	 * If both `a.mergeVisually(b)` and `b.mergeVisually(c)`, a, b, c can be visually merged.
	 */
	fun mergeVisually(right: Mark): Boolean =
		merge(right) != null
}

interface SingletonMark<T : Mark> : Mark, MarkType<T> {
	override val type: MarkType<T>
		get() = this
	
	override val kClass: KClass<T>
		get() = @Suppress("UNCHECKED_CAST") (this::class as KClass<T>)
}


object MarkSerializer : RegistryPolymorphicSerializer<Mark>(baseClass = Mark::class, registry = Mark)
