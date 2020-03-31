package de.solugo.kage.core.engine

expect object EngineManager {
    val engines: List<Engine<EngineConfiguration>>
}