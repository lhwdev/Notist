package io.github.lhwdev.notist.common.data

import io.github.lhwdev.notist.common.serialization.BinarySerializer
import kotlinx.serialization.Serializable


@Serializable(with = BinarySerializer::class)
class Binary(val data: ByteArray)
