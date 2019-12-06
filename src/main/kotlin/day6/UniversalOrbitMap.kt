package day6

import util.FileReader

data class OrbitPair(val inOrbit: String, val around: String)

data class OrbitNode(val name: String, val children: MutableList<OrbitNode> = mutableListOf()) {
    fun addChild(child: OrbitPair) {
        children.add(OrbitNode(child.inOrbit, mutableListOf()))
    }
}

fun main() {
    val input = FileReader().getFile("/Day6/input.txt")

    val orbitPairs = mutableListOf<OrbitPair>()

    input.forEachLine {
        val orbitItems = it.split(')')

        orbitPairs.add(OrbitPair(orbitItems[1], orbitItems[0]))
    }

    println(UniversalOrbitMap().doIt(orbitPairs))
}

class UniversalOrbitMap {
    fun doIt(orbitPairs: MutableList<OrbitPair>): Int {
        val rootOrbit = findRootOrbit(orbitPairs)

        val rootNode = OrbitNode(rootOrbit.around)

        findAllOrbitsOf(rootNode, orbitPairs)

        return countAllOrbits(rootNode)
    }

    private fun findRootOrbit(orbitPairs: MutableList<OrbitPair>): OrbitPair {
        return orbitPairs.first { it.around == "COM" }
    }

    private fun findAllOrbitsOf(node: OrbitNode, orbitPairs: MutableList<OrbitPair>) {
        orbitPairs
                .filter { it.around == node.name }
                .forEach { node.addChild(it) }

        node.children.forEach { findAllOrbitsOf(it, orbitPairs) }
    }

    private fun countAllOrbits(node: OrbitNode): Int {
        var thisOrbitCount = countOrbits(node)

        node.children.forEach { thisOrbitCount += countAllOrbits(it) }

        return thisOrbitCount
    }

    private fun countOrbits(node: OrbitNode, currentCount: Int = 0): Int {
        var orbitCount = currentCount

        val directOrbits = node.children.size

        orbitCount += directOrbits

        node.children.forEach { orbitCount += countOrbits(it, 0) }

        return orbitCount
    }
}