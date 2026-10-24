package io.github.lhwdev.notist.core.ast.inline

import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


object FormattingMark {
	@Serializable
	data object Bold : SingletonMark<Bold> {
		override val key: String = "bold"
	}
	
	@Serializable
	data object Italic : SingletonMark<Italic> {
		override val key: String = "italic"
	}
	
	@Serializable
	data object Underlined : SingletonMark<Underlined> {
		override val key: String = "underlined"
	}
	
	@Serializable
	data object Strikethrough : SingletonMark<Strikethrough> {
		override val key: String = "strikethrough"
	}
	
	@Serializable
	data class Highlight(val color: FormatColor) : Mark {
		companion object Type : MarkType<Highlight> {
			override val key: String
				get() = "highlight"
			override val kClass: KClass<Highlight>
				get() = Highlight::class
		}
		
		override val type: Type
			get() = Type
	}
	
	@Serializable
	data class TextColor(val color: FormatColor) : Mark {
		companion object Type : MarkType<TextColor> {
			override val key: String
				get() = "textColor"
			override val kClass: KClass<TextColor>
				get() = TextColor::class
		}
		
		override val type: Type
			get() = Type
	}
	
	@Serializable
	data class Link(val url: String) : Mark {
		companion object Type : MarkType<Link> {
			override val key: String
				get() = "link"
			override val kClass: KClass<Link>
				get() = Link::class
		}
		
		override val type: Type
			get() = Type
	}
	
	@Serializable
	data class Code(val language: String? = null) : Mark {
		companion object Type : MarkType<Code> {
			override val key: String
				get() = "code"
			override val kClass: KClass<Code>
				get() = Code::class
		}
		
		override val type: Type
			get() = Type
	}
}
