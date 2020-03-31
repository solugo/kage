package de.solugo.kage.core.node

open class Node {
    val children = arrayListOf<Node>()

    open fun attach() = Unit
    open fun detach() = Unit
}