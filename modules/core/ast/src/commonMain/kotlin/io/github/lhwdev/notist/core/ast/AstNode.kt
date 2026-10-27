@file:OptIn(ExperimentalAtomicApi::class)

package io.github.lhwdev.notist.core.ast

import kotlin.concurrent.atomics.ExperimentalAtomicApi
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract


interface AstNode<Self : Any, Type : Any> {
	val type: Type
	
	
	fun copy(): Self
	
	
	@Suppress("PropertyName")
	@Deprecated(message = "do not implement directly", level = DeprecationLevel.HIDDEN)
	val __doNotImplementDirectly: Nothing
}


abstract class AstNodeBase<Self : Any, Type : Any> : AstNode<Self, Type> {
	override val type: Type = resolveType(schema = currentSchema())
	
	protected inline fun <R> withCopyScope(block: () -> R): R =
		currentSchema.with(provideCopyScopeSchema(), block)
	
	
	@OptIn(ExperimentalContracts::class)
	protected fun equalsBase(other: Any?): Boolean {
		contract { returns(true) implies (other is AstNodeBase<*, *>) }
		
		return when {
			this === other -> true
			other !is AstNodeBase<*, *> -> false
			// this::class != other::class -> false // implied in `type == other.type` for sound implementation
			else -> type == other.type
		}
	}
	
	protected fun hashCodeBase(): Int =
		31 * this::class.hashCode() + type.hashCode()
	
	@Deprecated("do not implement directly", level = DeprecationLevel.HIDDEN)
	final override val __doNotImplementDirectly: Nothing
		get() = error("?")
	
	
	protected abstract fun resolveType(schema: Schema): Type
	
	protected abstract fun provideCopyScopeSchema(): Schema
}


inline fun <reified T : AstNode<*, *>> T.copy(): T =
	copy() as T
