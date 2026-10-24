@file:Suppress("ERROR_SUPPRESSION", "INVISIBLE_REFERENCE")

package io.github.lhwdev.notist.core.util.serialization

import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlin.reflect.KClass

@Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
@OptIn(InternalSerializationApi::class)
open class RegistryPolymorphicSerializer<T : Any>(
	override val baseClass: KClass<T>,
	val registry: PolymorphicRegistry<T>,
) : AbstractPolymorphicSerializer<T>() {
	// private var _annotations: List<Annotation> = emptyList()
	
	@OptIn(ExperimentalSerializationApi::class)
	override val descriptor: SerialDescriptor by lazy(LazyThreadSafetyMode.PUBLICATION) {
		buildSerialDescriptor(
			"io.github.lhwdev.notist.core.util.serialization.RegistryPolymorphic",
			PolymorphicKind.OPEN
		) {
			element("type", String.serializer().descriptor)
			element(
				"value",
				buildSerialDescriptor(
					"io.github.lhwdev.notist.core.util.serialization.RegistryPolymorphic<${baseClass.simpleName}>",
					SerialKind.CONTEXTUAL
				) {
					registry.registryItems.forEach { item ->
						element(item.key, item.serializer().descriptor)
					}
				}
			)
			// annotations = _annotations
		}.withContext(baseClass)
	}
	
	@InternalSerializationApi
	override fun findPolymorphicSerializerOrNull(
		decoder: CompositeDecoder,
		klassName: String?,
	): DeserializationStrategy<T>? = registry.getRegistryItem(key = klassName)?.serializer()
		?: super.findPolymorphicSerializerOrNull(decoder, klassName)
	
	
	@InternalSerializationApi
	override fun findPolymorphicSerializerOrNull(
		encoder: Encoder,
		value: T,
	): SerializationStrategy<T>? =
		registry.getRegistryItem(type = value::class)?.let { registrySerializer(it) }
			?: super.findPolymorphicSerializerOrNull(encoder, value)
	
	private fun registrySerializer(item: PolymorphicRegistry.RegistryItem<T>): KSerializer<T> {
		val serializer = item.serializer()
		return object : KSerializer<T> by serializer {
			@OptIn(SealedSerializationApi::class)
			override val descriptor: SerialDescriptor = object : SerialDescriptor by serializer.descriptor {
				override val serialName: String
					get() = item.key
			}
		}
	}
	
	
	override fun toString(): String =
		"io.github.lhwdev.notist.core.util.serialization.RegistryPolymorphic(baseClass: $baseClass)"
}
