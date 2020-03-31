package de.solugo.kage.core.engine

interface Engine<C: EngineConfiguration> {

    suspend fun start(configuration: C.() -> Unit)

}