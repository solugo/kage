package de.solugo.kage.core

import kotlinx.coroutines.runBlocking

actual object GameExecutor {
    actual fun execute(block: suspend () -> Unit) {
        runBlocking {
            block()
        }
    }
}