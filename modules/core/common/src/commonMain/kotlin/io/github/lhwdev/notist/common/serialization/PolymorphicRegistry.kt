package io.github.lhwdev.notist.common.serialization

import kotlinx.serialization.KSerializer
import kotlin.reflect.KClass


interface PolymorphicRegistry<T : Any> {
	interface RegistryItem<T : Any> {
		val key: String
		val kClass: KClass<T>
		fun serializer(): KSerializer<T>
	}
	
	
	val registryItems: Collection<RegistryItem<T>>
	
	fun getRegistryItem(key: String?): RegistryItem<T>?
	fun getRegistryItem(type: KClass<*>): RegistryItem<T>?
}


open class BasePolymorphicRegistry<T : Any, Item : PolymorphicRegistry.RegistryItem<T>> : PolymorphicRegistry<T> {
	protected val byKey: MutableMap<String, Item> = mutableMapOf()
	protected val byType: MutableMap<KClass<T>, Item> = mutableMapOf()
	
	override val registryItems: Collection<PolymorphicRegistry.RegistryItem<T>>
		get() = byKey.values
	
	override fun getRegistryItem(key: String?): PolymorphicRegistry.RegistryItem<T>? =
		byKey[key]
	
	override fun getRegistryItem(type: KClass<*>): PolymorphicRegistry.RegistryItem<T>? =
		byType[type]
}

class PolymorphicRegistryImpl<T : Any> : BasePolymorphicRegistry<T, PolymorphicRegistryImpl.SimpleItem<T>>() {
	class SimpleItem<T : Any>(
		override val key: String,
		override val kClass: KClass<T>,
		val serializer: KSerializer<T>,
	) : PolymorphicRegistry.RegistryItem<T> {
		override fun serializer(): KSerializer<T> = serializer
	}
	
	fun register(key: String, type: KClass<T>, serializer: KSerializer<T>) {
		val item = SimpleItem(key, type, serializer)
		byKey[key] = item
		byType[type] = item
	}
}
