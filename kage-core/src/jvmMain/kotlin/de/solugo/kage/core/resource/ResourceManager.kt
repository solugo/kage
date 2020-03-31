package de.solugo.kage.core.resource

actual object ResourceManager {
    actual fun find(
        location: String
    ) = Thread.currentThread().contextClassLoader.getResources(location).toList().map {
        Resource(it.path, it::readBytes)
    }
}