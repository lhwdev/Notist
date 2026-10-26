package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.common.serialization.PolymorphicRegistry
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


interface InlineNodeType<T : InlineNode> : PolymorphicRegistry.RegistryItem<T> {
	override val key: String
	override val kClass: KClass<T>
	override fun serializer(): KSerializer<T>
}

@Serializable(with = InlineNodeSerializer::class)
interface InlineNode {
	val type: InlineNodeType<*>
	
	
	val marks: List<Mark>
		get() = emptyList()
	
	val isText: Boolean
		get() = false
	
	fun isMarkAllowed(mark: Mark): Boolean = false
}

@OptIn(ExperimentalSerializationApi::class)
object InlineNodeSerializer : KSerializer<InlineNode> by ContextualSerializer(InlineNode::class)

