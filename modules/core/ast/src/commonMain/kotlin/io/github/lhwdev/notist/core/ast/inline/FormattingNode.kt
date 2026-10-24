package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.core.model.NoteMention
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


object FormattingNode {
	@Serializable
	data class Math(val expression: MathExpression) : InlineNode {
		companion object Type : InlineNodeType<Math> {
			override val key: String
				get() = "math"
			override val kClass: KClass<Math>
				get() = Math::class
		}
		
		override val type: Type
			get() = Type
	}
	
	@Serializable
	data class Mention(val mention: NoteMention) : InlineNode {
		companion object Type : InlineNodeType<Mention> {
			override val key: String
				get() = "mention"
			override val kClass: KClass<Mention>
				get() = Mention::class
		}
		
		override val type: Type
			get() = Type
	}
}
