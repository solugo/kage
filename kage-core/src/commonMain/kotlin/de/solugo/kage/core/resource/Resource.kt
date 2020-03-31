package de.solugo.kage.core.resource

class Resource(
    val name: String,
    private val provider : () -> ByteArray
) {
    fun read() = provider()
}