package io.github.lhwdev.notist.core.ast

import io.github.lhwdev.notist.common.data.ContextLocal
import io.github.lhwdev.notist.core.ast.inline.InlineNodeType
import io.github.lhwdev.notist.core.ast.inline.Mark
import io.github.lhwdev.notist.core.ast.inline.MarkDefinition
import io.github.lhwdev.notist.core.ast.inline.MarkType


val currentSchema: ContextLocal<Schema> = ContextLocal { error("schema expected") }


interface Schema {
	val marks: List<MarkType<*>>
	
	fun <T : Mark> findMark(definition: MarkDefinition<T>): MarkType<T>
	
	
	val inlineNodes: List<InlineNodeType<*>>
	
	// fun <T : Mark> findInlineNode(definition: MarkDefinition<T>): T
}


object EmptySchema : Schema {
	override val marks: List<MarkType<*>>
		get() = emptyList()
	
	override fun <T : Mark> findMark(definition: MarkDefinition<T>): MarkType<T> =
		error("empty")
	
	override val inlineNodes: List<InlineNodeType<*>>
		get() = emptyList()
}
