package io.github.lhwdev.notist.core.ast.inline

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
	class Color(val hue: Float, val prominence: Float = 0.4f) : FormatColor
}
