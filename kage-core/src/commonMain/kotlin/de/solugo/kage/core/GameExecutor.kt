package de.solugo.kage.core

expect object GameExecutor {
    fun execute(block: suspend () -> Unit)
}