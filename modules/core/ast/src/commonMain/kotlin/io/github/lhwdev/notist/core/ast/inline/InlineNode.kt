package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.utils.serialization.BasePolymorphicRegistry
import io.github.lhwdev.notist.utils.serialization.PolymorphicRegistry
import io.github.lhwdev.notist.utils.serialization.RegistryPolymorphicSerializer
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
	companion object : BasePolymorphicRegistry<InlineNode, InlineNodeType<InlineNode>>()
	
	val type: InlineNodeType<*>
	
	
	val marks: List<Mark>
		get() = emptyList()
	
	val isText: Boolean
		get() = false
	
	fun isMarkAllowed(mark: Mark): Boolean = false
}

object InlineNodeSerializer :
	RegistryPolymorphicSerializer<InlineNode>(baseClass = InlineNode::class, registry = InlineNode)

