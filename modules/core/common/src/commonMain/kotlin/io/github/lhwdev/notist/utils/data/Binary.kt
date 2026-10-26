package io.github.lhwdev.notist.utils.data

import io.github.lhwdev.notist.utils.serialization.BinarySerializer
import kotlinx.serialization.Serializable


@Serializable(with = BinarySerializer::class)
class Binary(val data: ByteArray)
