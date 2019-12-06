package day5

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import util.InputReader
import util.OutputPrinter

class ProgramAlarmTest {
    private lateinit var reader: InputReader
    private lateinit var printer: OutputPrinter
    private lateinit var underTest: Asteroids

    @Before
    fun setUp() {
        reader = Mockito.mock(InputReader::class.java)
        printer = Mockito.spy(OutputPrinter())
        underTest = Asteroids(printer, reader)
    }

    @Test
    fun example1() {
        val intCode = mutableListOf(1,0,0,0,99)

        val result = underTest.executeIntCodeProgram(intCode)

        assertEquals(2, result)
    }

    @Test
    fun example2() {
        val intCode = mutableListOf(2,3,0,3,99)

        underTest.executeIntCodeProgram(intCode)

        assertEquals(6, intCode[3])
    }

    @Test
    fun example3() {
        val intCode = mutableListOf(2,4,4,5,99,0)

        underTest.executeIntCodeProgram(intCode)

        assertEquals(9801, intCode[5])
    }

    @Test
    fun example4() {
        val result = underTest.executeIntCodeProgram(mutableListOf(1,1,1,4,99,5,6,0,99))

        assertEquals(30, result)
    }

    @Test
    fun example5() {
        Mockito.`when`(reader.readInt()).thenReturn(2)

        underTest.executeIntCodeProgram(mutableListOf(3,0,4,0,99))

        Mockito.verify(printer).print("2")
    }

    @Test
    fun example6() {
        val program = mutableListOf(1002,4,3,4,33)

        underTest.executeIntCodeProgram(program)

        assertEquals(99, program[4])
    }

    @Test
    fun instructionProcessing1002() {
        val result = underTest.processInstruction(1002)

        assertEquals(2, result.opCode)
        assertEquals(0, result.firstParamMode)
        assertEquals(1, result.secondParamMode)
        assertEquals(0, result.thirdParamMode)
    }

    @Test
    fun instructionProcessing2() {
        val result = underTest.processInstruction(2)

        assertEquals(2, result.opCode)
        assertEquals(0, result.firstParamMode)
        assertEquals(0, result.secondParamMode)
        assertEquals(0, result.thirdParamMode)
    }

    @Test
    fun instructionProcessing99() {
        val result = underTest.processInstruction(99)

        assertEquals(99, result.opCode)
        assertEquals(0, result.firstParamMode)
        assertEquals(0, result.secondParamMode)
        assertEquals(0, result.thirdParamMode)
    }

    @Test
    fun jumpTest1() {
        Mockito.`when`(reader.readInt()).thenReturn(0)

        underTest.executeIntCodeProgram(mutableListOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9))

        Mockito.verify(printer).print("0")
    }

    @Test
    fun jumpTest1_2() {
        Mockito.`when`(reader.readInt()).thenReturn(1)

        underTest.executeIntCodeProgram(mutableListOf(3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9))

        Mockito.verify(printer).print("1")
    }

    @Test
    fun jumpTest2() {
        Mockito.`when`(reader.readInt()).thenReturn(0)

        underTest.executeIntCodeProgram(mutableListOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1))

        Mockito.verify(printer).print("0")
    }

    @Test
    fun jumpTest2_2() {
        Mockito.`when`(reader.readInt()).thenReturn(1)

        underTest.executeIntCodeProgram(mutableListOf(3,3,1105,-1,9,1101,0,0,12,4,12,99,1))

        Mockito.verify(printer).print("1")
    }

    @Test
    fun equalTest1() {
        Mockito.`when`(reader.readInt()).thenReturn(8)

        underTest.executeIntCodeProgram(mutableListOf(3,9,8,9,10,9,4,9,99,-1,8))

        Mockito.verify(printer).print("1")
    }

    @Test
    fun equalTest1_2() {
        Mockito.`when`(reader.readInt()).thenReturn(7)

        underTest.executeIntCodeProgram(mutableListOf(3,9,8,9,10,9,4,9,99,-1,8))

        Mockito.verify(printer).print("0")
    }

    @Test
    fun equalTest2() {
        Mockito.`when`(reader.readInt()).thenReturn(8)

        underTest.executeIntCodeProgram(mutableListOf(3,3,1108,-1,8,3,4,3,99))

        Mockito.verify(printer).print("1")
    }

    @Test
    fun equalTest2_2() {
        Mockito.`when`(reader.readInt()).thenReturn(7)

        underTest.executeIntCodeProgram(mutableListOf(3,3,1108,-1,8,3,4,3,99))

        Mockito.verify(printer).print("0")
    }

    @Test
    fun lessThanTest1() {
        Mockito.`when`(reader.readInt()).thenReturn(7)

        underTest.executeIntCodeProgram(mutableListOf(3,9,7,9,10,9,4,9,99,-1,8))

        Mockito.verify(printer).print("1")
    }

    @Test
    fun lessThanTest1_2() {
        Mockito.`when`(reader.readInt()).thenReturn(8)

        underTest.executeIntCodeProgram(mutableListOf(3,9,7,9,10,9,4,9,99,-1,8))

        Mockito.verify(printer).print("0")
    }

    @Test
    fun lessThanTest2() {
        Mockito.`when`(reader.readInt()).thenReturn(7)

        underTest.executeIntCodeProgram(mutableListOf(3,3,1107,-1,8,3,4,3,99))

        Mockito.verify(printer).print("1")
    }

    @Test
    fun lessThanTest2_2() {
        Mockito.`when`(reader.readInt()).thenReturn(8)

        underTest.executeIntCodeProgram(mutableListOf(3,3,1107,-1,8,3,4,3,99))

        Mockito.verify(printer).print("0")
    }

    @Test
    fun finalExampleTest() {
        Mockito.`when`(reader.readInt()).thenReturn(7)

        underTest.executeIntCodeProgram(mutableListOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
            1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
            999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99))

        Mockito.verify(printer).print("999")
    }

    @Test
    fun finalExampleTest_1() {
        Mockito.`when`(reader.readInt()).thenReturn(8)

        underTest.executeIntCodeProgram(mutableListOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
            1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
            999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99))

        Mockito.verify(printer).print("1000")
    }

    @Test
    fun finalExampleTest_2() {
        Mockito.`when`(reader.readInt()).thenReturn(9)

        underTest.executeIntCodeProgram(mutableListOf(3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,
            1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,
            999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99))

        Mockito.verify(printer).print("1001")
    }
}