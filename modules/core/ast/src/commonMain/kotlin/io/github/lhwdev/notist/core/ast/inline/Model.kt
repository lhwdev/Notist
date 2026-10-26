package io.github.lhwdev.notist.core.ast.inline

import io.github.lhwdev.notist.common.similarTo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed interface MathExpression {
	@Serializable
	@SerialName("latex")
	class Latex(val latex: String) : MathExpression
}


@Serializable
sealed interface FormatColor {
	@Serializable
	@SerialName("palette")
	enum class Palette : FormatColor {
		Brown, Gray, Red, Orange, Yellow, Green, Blue, Purple,
		Pink, Cyan, Black;
	}
	
	@Serializable
	data class Color(val hue: Float, val prominence: Float = 0.4f) : FormatColor {
		override fun equals(other: Any?): Boolean = when {
			this === other -> true
			other !is Color -> false
			else -> hue similarTo other.hue && prominence similarTo other.prominence
		}
	}
}
