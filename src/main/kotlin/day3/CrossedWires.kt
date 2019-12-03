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

    println(CrossedWires().doPartOne(firstWireString, secondWireString))
    println(CrossedWires().doPartTwo(firstWireString, secondWireString))
}

class CrossedWires {
    private fun findIntersectionsWithoutOrigin(
        firstPoints: HashMap<Pair<Int, Int>, Int>,
        secondPoints: HashMap<Pair<Int, Int>, Int>
    ): MutableSet<Pair<Int, Int>> {
        val intersections = firstPoints.keys.intersect(secondPoints.keys).toMutableSet()

        intersections.remove(Pair(0, 0))

        return intersections
    }

    fun doPartOne(wireOne: List<String>, wireTwo: List<String>): Int {
        val firstPoints = getPoints(wireOne)
        val secondPoints = getPoints(wireTwo)

        val intersection = findIntersectionsWithoutOrigin(firstPoints, secondPoints)

        return intersection.map { manhattan(it.first, it.second) }.min()!!
    }

    fun doPartTwo(wireOne: List<String>, wireTwo: List<String>): Int {
        val firstPoints = getPoints(wireOne)
        val secondPoints = getPoints(wireTwo)

        val intersection = findIntersectionsWithoutOrigin(firstPoints, secondPoints)

        return intersection.map {
            val firstPointCount: Int = firstPoints[it]!!
            val secondPointCount: Int = secondPoints[it]!!

            firstPointCount + secondPointCount
        }.min()!!
    }

    private fun manhattan(num1: Int, num2: Int) = abs(num1) + abs(num2)

    private fun getPoints(inputString: List<String>) = processWire(inputString)

    private fun processWire(inputString: List<String>): HashMap<Pair<Int, Int>, Int> {
        val wire = getWire(inputString)

        val points = mutableListOf<Pair<Int, Int>>()
        val things = HashMap<Pair<Int, Int>, Int>()

        points.add(Pair(0, 0))
        things[Pair(0, 0)] = 0

        var length = 0

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
                length++
                currentPoint = Pair(currentPoint.first + xOffset, currentPoint.second + yOffset)
                points.add(currentPoint)
                things[currentPoint] = length
            }
        }

        return things
    }

    private fun getWire(inputString: List<String>) = inputString.map { it[0] to it.substring(1).toInt() }
}

