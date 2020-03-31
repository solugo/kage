package de.solugo.kage.core

import de.solugo.kage.core.engine.Engine
import de.solugo.kage.core.engine.EngineConfiguration
import de.solugo.kage.core.engine.EngineManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object Game {

    fun setup(block: EngineConfiguration.() -> Unit) =
        Setup(
            EngineManager.engines.first(),
            block
        )

    fun <C : EngineConfiguration> setup(engine: Engine<C>, block: C.() -> Unit) =
        Setup(engine, block)

    fun <C : EngineConfiguration> start(setup: Setup<C>) {
        GameExecutor.execute {
            setup.engine.start(setup.configuration)
        }
    }

    fun <C : EngineConfiguration> start(engine: Engine<C>, block: C.() -> Unit) {
        start(
            setup(
                engine,
                block
            )
        )
    }

    fun start(block: EngineConfiguration.() -> Unit) {
        start(setup(block))
    }

    data class Setup<C : EngineConfiguration>(val engine: Engine<C>, val configuration: C.() -> Unit)
}