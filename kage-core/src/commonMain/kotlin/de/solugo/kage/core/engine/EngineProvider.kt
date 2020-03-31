package de.solugo.kage.core.engine

interface EngineProvider<T: Engine<*>> {
    val instance: T
}