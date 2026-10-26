package io.github.lhwdev.notist.common.serialization

import io.github.lhwdev.notist.common.data.Binary
import kotlinx.serialization.ContextualSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.io.encoding.Base64

@OptIn(ExperimentalSerializationApi::class)
object BinarySerializer : KSerializer<Binary> by ContextualSerializer(
	Binary::class,
	fallbackSerializer = AsByteArray,
	typeArgumentsSerializers = emptyArray()
) {
	object AsByteArray : KSerializer<Binary> {
		private val delegate = ByteArraySerializer()
		override val descriptor: SerialDescriptor = delegate.descriptor
		
		override fun serialize(encoder: Encoder, value: Binary) {
			delegate.serialize(encoder, value.data)
		}
		
		override fun deserialize(decoder: Decoder): Binary =
			Binary(data = delegate.deserialize(decoder))
	}
	
	class AsBase64(val instance: Base64 = Base64) : KSerializer<Binary> {
		override val descriptor: SerialDescriptor
			get() = String.serializer().descriptor
		
		override fun serialize(encoder: Encoder, value: Binary) {
			val value = instance.encode(value.data)
			encoder.encodeString(value)
		}
		
		override fun deserialize(decoder: Decoder): Binary {
			val value = decoder.decodeString()
			return Binary(data = instance.decode(value))
		}
	}
}
