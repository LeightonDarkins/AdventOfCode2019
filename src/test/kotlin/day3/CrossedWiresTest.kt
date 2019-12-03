package day3

import org.junit.Assert.assertEquals
import org.junit.Test

class CrossedWiresTest {
    @Test
    fun example1() {
        val firstLine = listOf("R75","D30","R83","U83","L12","D49","R71","U7","L72")
        val secondLine = listOf("U62","R66","U55","R34","D71","R55","D58","R83")

        val partOneResult = CrossedWires().doPartOne(firstLine, secondLine)
        val partTwoResult = CrossedWires().doPartTwo(firstLine, secondLine)

        assertEquals(159, partOneResult)
        assertEquals(610, partTwoResult)
    }

    @Test
    fun example2() {
        val firstLine = listOf("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51")
        val secondLine = listOf("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7")

        val partOneResult = CrossedWires().doPartOne(firstLine, secondLine)
        val partTwoResult = CrossedWires().doPartTwo(firstLine, secondLine)

        assertEquals(135, partOneResult)
        assertEquals(410, partTwoResult)
    }

    @Test
    fun handRolledExample1() {
        val firstLine = listOf("R2","U2")
        val secondLine = listOf("U2","R2")

        val partOneResult = CrossedWires().doPartOne(firstLine, secondLine)
        val partTwoResult = CrossedWires().doPartTwo(firstLine, secondLine)

        assertEquals(4, partOneResult)
        assertEquals(8, partTwoResult)
    }

    @Test
    fun handRolledExample2() {
        val firstLine = listOf("R3","U2","L5","U2","R4")
        val secondLine = listOf("U5")

        val partOneResult = CrossedWires().doPartOne(firstLine, secondLine)
        val partTwoResult = CrossedWires().doPartTwo(firstLine, secondLine)

        assertEquals(2, partOneResult)
        assertEquals(10, partTwoResult)
    }
}