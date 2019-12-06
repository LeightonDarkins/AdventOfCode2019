package day5

import util.InputReader
import util.OutputPrinter
import java.io.File

fun main() {
    val printer = OutputPrinter()
    val reader = InputReader()
    val asteroids = Asteroids(printer, reader)

    var intCode = asteroids.readProgramInput()

    asteroids.executeIntCodeProgram(intCode)
}

class Asteroids(
    private val printer: OutputPrinter,
    private val reader: InputReader
) {
    var opCodeIndex = 0

    fun readProgramInput(): MutableList<Int> {
        val input = File("src/main/resources/day5/input.txt")

        var intCode: MutableList<Int> = mutableListOf()

        input.forEachLine { it ->
            val listOfStrings = it.split(",")
            val listOfInts = listOfStrings.map { string -> string.toInt() }
            intCode = listOfInts.toMutableList()
        }

        return intCode
    }

    fun executeIntCodeProgram(intCode: MutableList<Int>): Int {
        while (intCode[opCodeIndex] != 99) {
            val instruction = processInstruction(intCode[opCodeIndex])

            println("ME: ${intCode[opCodeIndex]} 1$instruction")

            var increment = when (instruction.opCode) {
                1 -> handleOriginalInstructions(intCode, instruction)
                2 -> handleOriginalInstructions(intCode, instruction)
                3 -> handleInputOpCode(intCode)
                4 -> handlePrintOpCode(intCode)
                5 -> jumpIfTrue(intCode, instruction)
                6 -> jumpIfFalse(intCode, instruction)
                7 -> lessThan(intCode, instruction)
                8 -> equal(intCode, instruction)
                else -> throw Exception()
            }

            opCodeIndex += increment
        }

        return intCode[0]
    }

    fun processInstruction(instruction: Int): Instruction {
        var instructionCopy = instruction

        val opCode = instructionCopy % 100
        instructionCopy /= 100

        val firstParamMode = instructionCopy % 10
        instructionCopy /= 10

        val secondParamMode = instructionCopy % 10
        instructionCopy /= 10

        val thirdParamMode = instructionCopy % 10

        return Instruction(opCode, firstParamMode, secondParamMode, thirdParamMode)
    }

    private fun handleInputOpCode(intCode: MutableList<Int>): Int {
        val int = reader.readInt()
        val resultIndex = intCode[opCodeIndex + 1]

        intCode[resultIndex] = int

        return 2
    }

    private fun handlePrintOpCode(intCode: MutableList<Int>): Int {
        val index = intCode[opCodeIndex + 1]

        printer.print(intCode[index].toString())

        return 2
    }

    private fun lessThan(intCode: MutableList<Int>, instruction: Instruction): Int {
        val firstParam = intCode[opCodeIndex + 1]
        val secondParam = intCode[opCodeIndex + 2]
        val thirdParam = intCode[opCodeIndex + 3]

        val firstOperand = if (instruction.firstParamMode == 0) intCode[firstParam] else firstParam
        val secondOperand = if (instruction.secondParamMode == 0) intCode[secondParam] else secondParam

        if (firstOperand < secondOperand) {
            intCode[thirdParam] = 1
        } else {
            intCode[thirdParam] = 0
        }

        return 4
    }

    private fun equal(intCode: MutableList<Int>, instruction: Instruction): Int {
        val firstParam = intCode[opCodeIndex + 1]
        val secondParam = intCode[opCodeIndex + 2]
        val thirdParam = intCode[opCodeIndex + 3]

        val firstOperand = if (instruction.firstParamMode == 0) intCode[firstParam] else firstParam
        val secondOperand = if (instruction.secondParamMode == 0) intCode[secondParam] else secondParam

        if (firstOperand == secondOperand) {
            intCode[thirdParam] = 1
        } else {
            intCode[thirdParam] = 0
        }

        return 4
    }

    private fun jumpIfTrue(intCode: MutableList<Int>, instruction: Instruction): Int {
        val firstParam = intCode[opCodeIndex + 1]
        val secondParam = intCode[opCodeIndex + 2]

        val firstOperand = if (instruction.firstParamMode == 0) intCode[firstParam] else firstParam
        val secondOperand = if (instruction.secondParamMode == 0) intCode[secondParam] else secondParam

        if (firstOperand != 0) {
            opCodeIndex = secondOperand
            return 0
        }

        return 3
    }

    private fun jumpIfFalse(intCode: MutableList<Int>, instruction: Instruction): Int {
        val firstParam = intCode[opCodeIndex + 1]
        val secondParam = intCode[opCodeIndex + 2]

        val firstOperand = if (instruction.firstParamMode == 0) intCode[firstParam] else firstParam
        val secondOperand = if (instruction.secondParamMode == 0) intCode[secondParam] else secondParam

        if (firstOperand == 0) {
            opCodeIndex = secondOperand
            return 0
        }

        return 3
    }

    private fun handleOriginalInstructions(intCode: MutableList<Int>, instruction: Instruction): Int {
        val firstParam = intCode[opCodeIndex + 1]
        val secondParam = intCode[opCodeIndex + 2]
        val resultIndex = intCode[opCodeIndex + 3]

        val firstOperand = if (instruction.firstParamMode == 0) intCode[firstParam] else firstParam
        val secondOperand = if (instruction.secondParamMode == 0) intCode[secondParam] else secondParam

        when (instruction.opCode) {
            1 -> intCode[resultIndex] = firstOperand + secondOperand
            2 -> intCode[resultIndex] = firstOperand * secondOperand
        }

        return 4
    }
}