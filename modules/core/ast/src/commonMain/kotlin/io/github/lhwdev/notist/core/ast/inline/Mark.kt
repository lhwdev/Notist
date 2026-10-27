package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.common.serialization.PolymorphicRegistry
import io.github.lhwdev.notist.core.ast.AstNode
import io.github.lhwdev.notist.core.ast.AstNodeBase
import io.github.lhwdev.notist.core.ast.EmptySchema
import io.github.lhwdev.notist.core.ast.Schema
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


interface MarkType<T : Mark> : MarkDefinition<T>, PolymorphicRegistry.RegistryItem<T> {
	override val key: String
	
	val definition: MarkDefinition<T>
	
	override val kClass: KClass<T>
		get() = definition.kClass
	
	override fun serializer(): KSerializer<T> =
		definition.serializer()
}

interface MarkDefinition<T : Mark> {
	val kClass: KClass<T>
	
	fun serializer(): KSerializer<T>
}


@Serializable(with = MarkSerializer::class)
interface Mark : AstNode<Mark, MarkType<*>> {
	val definition: MarkDefinition<*>
	
	override val type: MarkType<*>
	
	
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
	 * Returned instance will be from same class with `this::class`.
	 * It does not necessarily have separate instance. [SingletonMark.copy] will return `this`.
	 *
	 * All implementation should create instance with same [type], by using [MarkBase.withCopyScope].
	 */
	override fun copy(): Mark
}


abstract class MarkBase : Mark, AstNodeBase<Mark, MarkType<*>>() {
	override fun resolveType(schema: Schema): MarkType<*> = type
	
	override fun provideCopyScopeSchema(): Schema =
		MarkCopyScopeSchema(type)
}

abstract class SingletonMark : MarkBase() {
	override fun copy(): SingletonMark = this
	
	override fun equals(other: Any?): Boolean =
		equalsBase(other)
	
	override fun hashCode(): Int =
		hashCodeBase()
}


@OptIn(ExperimentalSerializationApi::class)
object MarkSerializer : KSerializer<Mark> by ContextualSerializer(Mark::class)

@PublishedApi
internal class MarkCopyScopeSchema(val type: MarkType<*>) : Schema by EmptySchema {
	override val marks: List<MarkType<*>> = listOf(type)
	
	@Suppress("UNCHECKED_CAST")
	override fun <T : Mark> findMark(definition: MarkDefinition<T>): MarkType<T> {
		require(type.definition == definition) { "in withCopyScope, $definition is not found" }
		return type as MarkType<T>
	}
}
