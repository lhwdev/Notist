package io.github.lhwdev.notist.core.model

import kotlinx.serialization.Serializable


@Serializable
sealed interface NoteMention {
	val name: String
	
	enum class EntityType {
		User;
	}
	
	@Serializable
	data class Entity(val type: EntityType, override val name: String, val id: String) : NoteMention
	
	@Serializable
	data class Page(val icon: NoteIcon, override val name: String, val id: String) : NoteMention
	
	@Serializable
	data class Link(override val name: String, val url: String) : NoteMention
	
}

