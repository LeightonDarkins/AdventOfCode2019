package day3

import util.FileReader
import kotlin.math.abs

fun main() {
    val fileReader = FileReader()
    val input = fileReader.getFile("Day3/input.txt")

    var firstWireString = listOf<String>()
    var secondWireString = listOf<String>()

    input.forEachLine {
        val stringArray = it.split(",")

        if (firstWireString.isEmpty()) {
            firstWireString = stringArray
        } else {
            secondWireString = stringArray
        }
    }

    println(CrossedWires().doIt(firstWireString, secondWireString))
}

class CrossedWires {
    fun doIt(wireOne: List<String>, wireTwo: List<String>): Int {
        val firstWire = getWire(wireOne)
        val secondWire = getWire(wireTwo)

        val firstPoints = processWire(firstWire)
        val secondPoints = processWire(secondWire)

        val intersection = firstPoints.intersect(secondPoints).toMutableSet()

        intersection.remove(Pair(0,0))

        return intersection.map { manhattan(it.first, it.second) }.min()!!
    }

    private fun manhattan(num1: Int, num2: Int) = abs(num1) + abs(num2)

    private fun getWire(inputString: List<String>) = inputString.map { it[0] to it.substring(1).toInt() }

    private fun processWire(wire: List<Pair<Char, Int>>): MutableList<Pair<Int, Int>> {
        val points = mutableListOf<Pair<Int, Int>>()

        points.add(Pair(0,0))

        wire.forEach {
            var currentPoint = points.last()

            var xOffset = 0
            var yOffset = 0

            when (it.first) {
                'U' -> yOffset = 1
                'D' -> yOffset = -1
                'R' -> xOffset = 1
                'L' -> xOffset = -1
            }

            for (i in 0 until it.second) {
                currentPoint = Pair(currentPoint.first + xOffset, currentPoint.second + yOffset)
                points.add(currentPoint)
            }
        }

        return points
    }
}

