package io.github.lhwdev.notist.core.ast.inline

import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
class TextNode(val text: String, override val marks: List<Mark>) : InlineNode {
	companion object Type : InlineNodeType<TextNode> {
		override val key: String
			get() = "text"
		override val kClass: KClass<TextNode>
			get() = TextNode::class
	}
	
	override val type: Type
		get() = Type
	
	override val isText: Boolean
		get() = true
	
	override fun isMarkAllowed(mark: Mark): Boolean = true
}
