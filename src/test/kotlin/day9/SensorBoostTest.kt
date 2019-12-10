package day9

import org.junit.Assert.assertEquals
import org.junit.Test

class SensorBoostTest {
    @Test
    fun `Example 1`() {
        val input = listOf(109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99).map { it.toBigInteger() }.toTypedArray()
    }

    @Test
    fun `Example 2`() {
        val input = listOf(1102,34915192,34915192,7,4,7,99,0).map { it.toBigInteger() }.toTypedArray()

        SensorBoost().doPartOne(input)
    }

    @Test
    fun `Example 3`() {
        val input = listOf(104,1125899906842624,99).map { it.toBigInteger() }.toTypedArray()

        SensorBoost().doPartOne(input)
    }
}