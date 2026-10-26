package io.github.lhwdev.notist.core.model

import io.github.lhwdev.notist.common.data.Binary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
sealed interface NoteMention {
	val name: String
	
	enum class EntityKind {
		User;
	}
	
	@Serializable
	data class Entity(val kind: EntityKind, override val name: String, val id: String) : NoteMention
	
	@Serializable
	data class Page(override val name: String, val id: String, val icon: NoteIcon? = null) : NoteMention
	
	@Serializable
	data class Link(override val name: String, val url: String, val icon: LinkIcon? = null) : NoteMention
	
}


@Serializable
sealed class LinkIcon {
	@Serializable
	@SerialName("embed")
	class Embed(val mimeType: String, val data: Binary) : LinkIcon()
}

