package day10

import util.FileReader
import kotlin.math.atan2
import kotlin.math.hypot

typealias CoOrd = Pair<Int, Int>

inline val CoOrd.x: Int get() = first
inline val CoOrd.y: Int get() = second

operator fun CoOrd.plus(other: CoOrd) = (x + other.x) to (y + other.y)
operator fun CoOrd.minus(other: CoOrd) = (x - other.x) to (y - other.y)

fun main() {
    val file = FileReader().getFile("day10/input.txt")

    val input = mutableListOf<List<Char>>()

    file.forEachLine { input.add(it.toList()) }

    println(MonitoringStation().doPartOne(input))
    println(MonitoringStation().doPartTwo(input))
}

class MonitoringStation {
    fun doPartOne(input: MutableList<List<Char>>): Int {
        val asteroids = createAsteroidMap(input)

        return asteroids.map { countVisibleAsteroids(it, asteroids) }.max()!!
    }

    fun doPartTwo(input: MutableList<List<Char>>): Int {
        val asteroids = createAsteroidMap(input)

        val station = asteroids.maxBy { countVisibleAsteroids(it, asteroids) }!!

        val remainingAsteroids = asteroids.filter { it != station }.toMutableList()

        var angle = -Math.PI / 2 // not sure why!?

        var firstTarget = true

        (1..200).forEach {i ->
            val asteroidsByAngle =
                remainingAsteroids.groupBy { atan2((it - station).y.toDouble(), (it - station).x.toDouble()) }

            val nextAngleTargets =
                asteroidsByAngle.filter { if (firstTarget) it.key >= angle else it.key > angle }
                    .minBy { it.key }
                    ?: asteroidsByAngle.minBy { it.key }!!

            angle = nextAngleTargets.key
            firstTarget = false

            val target = nextAngleTargets.value.minBy { hypot((it - station).x.toDouble(), (it - station).y.toDouble()) }!!
            remainingAsteroids.remove(target)

            if (i == 200) {
                return target.x * 100 + target.y
            }
        }

        return  -1
    }

    private fun countVisibleAsteroids(root: CoOrd, asteroids: List<Pair<Int, Int>>): Int {
        return asteroids.filter { it != root }
            .map { target ->
                val resultPair = (target - root)

                atan2(resultPair.y.toDouble(), resultPair.x.toDouble())
            }
            .distinct()
            .count()
    }

    private fun createAsteroidMap(input: MutableList<List<Char>>): List<Pair<Int, Int>> {
        return input.withIndex().flatMap { (y, row) ->
            row.withIndex().filter { it.value == '#' }.map { it.index to y }
        }
    }
}