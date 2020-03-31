package de.solugo.kage.core.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.internal.MainDispatcherFactory
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

private const val threadName = "Game"
private val executor = Executors.newSingleThreadScheduledExecutor() {
    Thread(it, threadName).also {
        it.isDaemon = true
    }
}

@Suppress("unused")
@InternalCoroutinesApi
val Dispatchers.Game: GameDispatcher
    get() = Game

@InternalCoroutinesApi
sealed class GameDispatcher : MainCoroutineDispatcher(), Delay {
    override fun dispatch(context: CoroutineContext, block: Runnable) = executor.execute(block)

    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        val scheduled = executor.schedule(Callable {
            with(continuation) { resumeUndispatched(Unit) }
        }, timeMillis, TimeUnit.MILLISECONDS)

        continuation.invokeOnCancellation { scheduled.cancel(false) }
    }

    override fun invokeOnTimeout(timeMillis: Long, block: Runnable): DisposableHandle {
        val scheduled = executor.schedule(block, timeMillis, TimeUnit.MILLISECONDS)

        return object : DisposableHandle {
            override fun dispose() {
                scheduled.cancel(false)
            }
        }
    }

}

@InternalCoroutinesApi
internal class GameDispatcherFactory : MainDispatcherFactory {
    override val loadPriority: Int = 0

    override fun createDispatcher(allFactories: List<MainDispatcherFactory>): MainCoroutineDispatcher = Game
}

@InternalCoroutinesApi
private object ImmediateGameDispatcher : GameDispatcher() {
    override val immediate: MainCoroutineDispatcher
        get() = this

    override fun isDispatchNeeded(context: CoroutineContext): Boolean = Thread.currentThread().name != threadName

    override fun toString() = "Game [immediate]"
}

@InternalCoroutinesApi
internal object Game : GameDispatcher() {
    override val immediate: MainCoroutineDispatcher
        get() = ImmediateGameDispatcher

    override fun toString() = "Game"
}