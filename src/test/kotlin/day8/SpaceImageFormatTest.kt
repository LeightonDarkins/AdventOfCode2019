package day8

import org.junit.Assert.assertEquals
import org.junit.Test

class SpaceImageFormatTest {
    @Test
    fun example1() {
        var underTest = SpaceImageFormat(2, 3)

        val result = underTest.doPartOne("123456789012")

        assertEquals(1, result)
    }

    @Test
    fun example2() {
        var underTest = SpaceImageFormat(2, 3)

        val result = underTest.doPartOne("000112")

        assertEquals(2, result)
    }

    @Test
    fun example3() {
        var underTest = SpaceImageFormat(2, 3)

        val result = underTest.doPartOne("000112011122")

        assertEquals(6, result)
    }

    @Test
    fun example4() {
        var underTest = SpaceImageFormat(2, 3)

        val result = underTest.doPartOne("000112001122022211")

        assertEquals(6, result)
    }

    @Test
    fun `Part Two - Example 1`() {
        var underTest = SpaceImageFormat(2, 2)

        underTest.doPartTwo("0222112222120000")
    }

    @Test
    fun `Part Two - Example 2`() {
        var underTest = SpaceImageFormat(4, 4)

        underTest.doPartTwo("0022220022110011")
    }
}