package de.solugo.kage.core.tree

import de.solugo.kage.core.data.Vector3

class Node {
    var translation = Vector3.Zero
    var visible = true
    var children = arrayListOf<Node>()
}