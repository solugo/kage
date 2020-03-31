package de.solugo.kage.core.jwgl

import de.solugo.kage.core.engine.Engine
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

object Jlwgl :
    Engine<JlwglConfiguration> {

    override suspend fun start(block: JlwglConfiguration.() -> Unit) {
        val configuration = JlwglConfiguration().apply(block)

        println("Started game")

        val title = configuration.title
        val width = configuration.windowWidth
        val height = configuration.windowHeight

        GLFW.glfwInit()
        val window = GLFW.glfwCreateWindow(width, height, title, 0, 0)
        val monitor = GLFW.glfwGetPrimaryMonitor()
        val mode = GLFW.glfwGetVideoMode(monitor) ?: throw RuntimeException("Could not get monitor info")
        val monitorX = BufferUtils.createIntBuffer(1)
        val monitorY = BufferUtils.createIntBuffer(1)

        GLFW.glfwGetMonitorPos(monitor, monitorX, monitorY)
        GLFW.glfwSetWindowPos(
            window,
            monitorX[0] + (mode.width() - width) / 2,
            monitorY[0] + (mode.height() - height) / 2
        )
        GLFW.glfwShowWindow(window)
        GLFW.glfwMakeContextCurrent(window)

        GL.createCapabilities()

runBlocking {  }
        while (!GLFW.glfwWindowShouldClose(window)) {
            val start = System.currentTimeMillis()
            GL11.glClearColor(0f, 0f, 0f, 1f)
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
            GLFW.glfwSwapBuffers(window)
            GLFW.glfwPollEvents()
            delay(1000 / configuration.maxFps - System.currentTimeMillis() - start)
        }
    }
}