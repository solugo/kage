package de.solugo.kage.core.engine

import java.util.*

actual object EngineManager {

    @Suppress("unchecked_cast")
    actual val engines: List<Engine<EngineConfiguration>>
        get() = ServiceLoader.load(EngineProvider::class.java).map { it.instance as Engine<EngineConfiguration> }
}