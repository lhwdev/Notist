package io.github.lhwdev.notist.core.ast.inline

import kotlinx.serialization.Serializable
import kotlin.reflect.KClass


object FormattingMark {
	@Serializable
	class Bold : SingletonMark() {
		companion object Definition : MarkDefinition<Bold> {
			override val kClass: KClass<Bold>
				get() = Bold::class
		}
		
		override val definition: Definition
			get() = Definition
		
		override fun copy(): Bold = this
	}
	
	class BoldType(override val key: String = "bold") : MarkType<Bold> {
		override val definition: MarkDefinition<Bold>
			get() = Bold.Definition
	}
	
	@Serializable
	class Italic : SingletonMark() {
		companion object Definition : MarkDefinition<Italic> {
			override val kClass: KClass<Italic>
				get() = Italic::class
		}
		
		override val definition: Definition
			get() = Definition
		
		override fun copy(): Italic = this
	}
	
	class ItalicType(override val key: String = "italic") : MarkType<Italic> {
		override val definition: MarkDefinition<Italic>
			get() = Italic.Definition
	}
	
	@Serializable
	class Underline : SingletonMark() {
		companion object Definition : MarkDefinition<Underline> {
			override val kClass: KClass<Underline>
				get() = Underline::class
		}
		
		override val definition: Definition
			get() = Definition
		
		override fun copy(): Underline = this
	}
	
	class UnderlineType(override val key: String = "underlined") : MarkType<Underline> {
		override val definition: MarkDefinition<Underline>
			get() = Underline.Definition
	}
	
	@Serializable
	class Strikethrough : SingletonMark() {
		companion object Definition : MarkDefinition<Strikethrough> {
			override val kClass: KClass<Strikethrough>
				get() = Strikethrough::class
		}
		
		override val definition: Definition
			get() = Definition
		
		override fun copy(): Strikethrough = this
	}
	
	class StrikethroughType(override val key: String = "strikethrough") : MarkType<Strikethrough> {
		override val definition: MarkDefinition<Strikethrough>
			get() = Strikethrough.Definition
	}
	
	@Serializable
	class Highlight(val color: FormatColor) : MarkBase() {
		companion object Definition : MarkDefinition<Highlight> {
			override val kClass: KClass<Highlight>
				get() = Highlight::class
		}
		
		override val definition: Definition
			get() = Definition
		
		fun copy(color: FormatColor = this.color): Highlight =
			withCopyScope { Highlight(color) }
		
		override fun copy(): Highlight = copy(color)
		
		override fun equals(other: Any?): Boolean =
			this === other || other is Highlight && equalsBase(other) && color == other.color
		
		override fun hashCode(): Int =
			31 * hashCodeBase() + color.hashCode()
	}
	
	class HighlightType(override val key: String = "highlight") : MarkType<Highlight> {
		override val definition: MarkDefinition<Highlight>
			get() = Highlight.Definition
	}
	
	@Serializable
	class TextColor(val color: FormatColor) : MarkBase() {
		companion object Definition : MarkDefinition<TextColor> {
			override val kClass: KClass<TextColor>
				get() = TextColor::class
		}
		
		override val definition: Definition
			get() = Definition
		
		fun copy(color: FormatColor = this.color): TextColor =
			withCopyScope { TextColor(color) }
		
		override fun copy(): TextColor = copy(color)
		
		override fun equals(other: Any?): Boolean =
			this === other || other is TextColor && equalsBase(other) && color == other.color
		
		override fun hashCode(): Int =
			31 * hashCodeBase() + color.hashCode()
	}
	
	class TextColorType(override val key: String = "textColor") : MarkType<TextColor> {
		override val definition: MarkDefinition<TextColor>
			get() = TextColor.Definition
	}
	
	@Serializable
	class Link(val url: String) : MarkBase() {
		companion object Definition : MarkDefinition<Link> {
			override val kClass: KClass<Link>
				get() = Link::class
		}
		
		override val definition: Definition
			get() = Definition
		
		fun copy(url: String = this.url): Link =
			withCopyScope { Link(url) }
		
		override fun copy(): Link = copy(url)
		
		override fun equals(other: Any?): Boolean =
			this === other || other is Link && equalsBase(other) && url == other.url
		
		override fun hashCode(): Int =
			31 * hashCodeBase() + url.hashCode()
	}
	
	class LinkType(override val key: String = "link") : MarkType<Link> {
		override val definition: MarkDefinition<Link>
			get() = Link.Definition
	}
	
	@Serializable
	class Code(val language: String? = null) : MarkBase() {
		companion object Definition : MarkDefinition<Code> {
			override val kClass: KClass<Code>
				get() = Code::class
		}
		
		override val definition: Definition
			get() = Definition
		
		fun copy(language: String? = this.language): Code =
			withCopyScope { Code(language) }
		
		override fun copy(): Code = copy(language)
		
		override fun equals(other: Any?): Boolean =
			this === other || other is Code && equalsBase(other) && language == other.language
		
		override fun hashCode(): Int =
			31 * hashCodeBase() + language.hashCode()
	}
	
	class CodeType(override val key: String = "code") : MarkType<Code> {
		override val definition: MarkDefinition<Code>
			get() = Code.Definition
	}
}
