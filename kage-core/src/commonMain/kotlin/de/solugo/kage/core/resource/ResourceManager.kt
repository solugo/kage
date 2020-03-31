package de.solugo.kage.core.resource

expect object ResourceManager {
    fun find(location: String): List<Resource>
}