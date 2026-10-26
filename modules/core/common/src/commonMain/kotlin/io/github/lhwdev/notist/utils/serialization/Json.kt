package io.github.lhwdev.notist.utils.serialization

import io.github.lhwdev.notist.utils.data.Binary
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule


val DefaultJson = Json {
	serializersModule = SerializersModule {
		contextual(Binary::class, BinarySerializer.AsBase64())
	}
}
