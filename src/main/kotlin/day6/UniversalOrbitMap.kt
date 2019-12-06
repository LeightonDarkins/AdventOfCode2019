package day6

import util.FileReader

data class OrbitPair(val inOrbit: String, val around: String)

data class OrbitNode(val name: String, val parent: String?, val children: MutableList<OrbitNode> = mutableListOf()) {
    fun addChild(child: OrbitPair) {
        children.add(OrbitNode(child.inOrbit, name, mutableListOf()))
    }
}

fun main() {
    val input = FileReader().getFile("/Day6/input.txt")

    val orbitPairs = mutableListOf<OrbitPair>()

    input.forEachLine {
        val orbitItems = it.split(')')

        orbitPairs.add(OrbitPair(orbitItems[1], orbitItems[0]))
    }

    println(UniversalOrbitMap().doPartOne(orbitPairs))
    println(UniversalOrbitMap().doPartTwo(orbitPairs))
}

class UniversalOrbitMap {
    fun doPartOne(orbitPairs: MutableList<OrbitPair>): Int {
        val rootNode = setUpMap(orbitPairs)

        return countAllOrbits(rootNode)
    }

    fun doPartTwo(orbitPairs: MutableList<OrbitPair>): Int {
        val pathToComFromYou = mutableListOf("YOU")
        val pathToComFromSan = mutableListOf("SAN")

        addParentToPath(orbitPairs, pathToComFromYou, "YOU")
        addParentToPath(orbitPairs, pathToComFromSan, "SAN")

        val sharedParent = pathToComFromSan.intersect(pathToComFromYou).first()

        val youToParent = pathToComFromYou.indexOf(sharedParent) - 1
        val sanToParent = pathToComFromSan.indexOf(sharedParent) - 1

        return youToParent + sanToParent
    }

    private fun addParentToPath(
        orbitPairs: MutableList<OrbitPair>,
        pathToCom: MutableList<String>,
        startingNode: String
    ) {
        val currentParent = orbitPairs.first { it.inOrbit == startingNode }!!.around

        pathToCom.add(currentParent)

        if (currentParent != "COM") {
            addParentToPath(orbitPairs, pathToCom, currentParent)
        }
    }

    private fun setUpMap(orbitPairs: MutableList<OrbitPair>): OrbitNode {
        val rootOrbit = findRootOrbit(orbitPairs)

        val rootNode = OrbitNode(rootOrbit.around, null)

        findAllOrbitsOf(rootNode, orbitPairs)

        return rootNode
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