package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.core.util.serialization.BasePolymorphicRegistry
import io.github.lhwdev.notist.core.util.serialization.PolymorphicRegistry
import io.github.lhwdev.notist.core.util.serialization.RegistryPolymorphicSerializer
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

@Serializable
class Text(val text: String, override val marks: List<Mark>) : InlineNode {
	companion object Type : InlineNodeType<Text> {
		override val key: String
			get() = "text"
		override val kClass: KClass<Text>
			get() = Text::class
	}
	
	override val type: Type
		get() = Type
	
	override val isText: Boolean
		get() = true
	
	override fun isMarkAllowed(mark: Mark): Boolean = true
}
