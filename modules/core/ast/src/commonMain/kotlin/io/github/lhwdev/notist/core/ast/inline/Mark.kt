package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.common.serialization.PolymorphicRegistry
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


interface MarkType<T : Mark> : PolymorphicRegistry.RegistryItem<T> {
	override val key: String
	
	override val kClass: KClass<T>
	
	override fun serializer(): KSerializer<T>
}


@Serializable(with = MarkSerializer::class)
interface Mark {
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
}

interface SingletonMark<T : Mark> : Mark, MarkType<T> {
	override val type: MarkType<T>
		get() = this
	
	override val kClass: KClass<T>
		get() = @Suppress("UNCHECKED_CAST") (this::class as KClass<T>)
}


@OptIn(ExperimentalSerializationApi::class)
object MarkSerializer : KSerializer<Mark> by ContextualSerializer(Mark::class)
