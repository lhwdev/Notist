package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.core.model.NoteMention
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


@Serializable
data class MathNode(val expression: MathExpression) : InlineNode {
	companion object Type : InlineNodeType<MathNode> {
		override val key: String
			get() = "math"
		override val kClass: KClass<MathNode>
			get() = MathNode::class
	}
	
	override val type: Type
		get() = Type
}

@Serializable
data class MentionNode(val mention: NoteMention) : InlineNode {
	companion object Type : InlineNodeType<MentionNode> {
		override val key: String
			get() = "mention"
		override val kClass: KClass<MentionNode>
			get() = MentionNode::class
	}
	
	override val type: Type
		get() = Type
}
