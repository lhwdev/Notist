package io.github.lhwdev.notist.common.serialization

import io.github.lhwdev.notist.common.data.Binary
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule


val DefaultJson = Json {
	serializersModule = SerializersModule {
		contextual(Binary::class, BinarySerializer.AsBase64())
	}
}
