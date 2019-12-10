package day9

import util.FileReader
import java.math.BigInteger

fun main() {
    val file = FileReader().getFile("day9/input.txt")

    var input = listOf<String>()

    file.forEachLine {
        input = it.split(",")
    }

    SensorBoost().doPartOne(input.map { it.toBigInteger() }.toTypedArray())
    SensorBoost().doPartTwo(input.map { it.toBigInteger() }.toTypedArray())
}

class SensorBoost{
    fun doPartOne(input: Array<BigInteger>) {
        val computer = Computer(input)

        val output = computer.calculate(1)

        println(output)
    }

    fun doPartTwo(input: Array<BigInteger>) {
        val computer = Computer(input)

        val output = computer.calculate(2)

        println(output)
    }
}

class Computer(program: Array<BigInteger> ) {
    private val data: Array<BigInteger> = Array(3000) { BigInteger.ZERO }

    init {
        program.forEachIndexed { index, value ->
            data[index] = value
        }
    }

    var index = 0
    var done = false
    var output = mutableListOf<BigInteger>()
    var input = mutableListOf<Int>()
    private var relativeBase: BigInteger = BigInteger.ZERO

    private fun getParam(mode: Int, increment: Int): Int {
        return when (mode) {
            0 -> (data[index + increment]).toInt()
            1 -> index + increment
            2 -> (relativeBase + data[index + increment]).toInt()
            else -> throw Exception()
        }
    }

    private fun processInstruction(instruction: Int): Instruction {
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

    private fun add(instruction: Instruction) {
        val param1 = getParam(instruction.firstParamMode, 1)
        val param2 = getParam(instruction.secondParamMode, 2)
        val param3 = getParam(instruction.thirdParamMode, 3)

        data[param3] = data[param1] + data[param2]

        index += 4
    }

    private fun multiply(instruction: Instruction) {
        val param1 = getParam(instruction.firstParamMode, 1)
        val param2 = getParam(instruction.secondParamMode, 2)
        val param3 = getParam(instruction.thirdParamMode, 3)

        data[param3] = data[param1] * data[param2]

        index += 4
    }

    private fun readInput(instruction: Instruction) {
        val param = getParam(instruction.firstParamMode, 1)

        data[param] = input.removeAt(0).toBigInteger()

        index += 2
    }

    private fun writeOutput(instruction: Instruction): MutableList<BigInteger> {
        val param = getParam(instruction.firstParamMode, 1)

        output.add(data[param])

        index += 2

        return output
    }

    private fun jumpIfTrue(instruction: Instruction) {
        val param1 = getParam(instruction.firstParamMode, 1)
        val param2 = getParam(instruction.secondParamMode, 2)

        index = if (data[param1] != BigInteger.ZERO) data[param2].toInt() else index + 3
    }

    private fun jumpIfFalse(instruction: Instruction) {
        val param1 = getParam(instruction.firstParamMode, 1)
        val param2 = getParam(instruction.secondParamMode, 2)

        index = if (data[param1] == BigInteger.ZERO) data[param2].toInt() else index + 3
    }

    private fun lessThan(instruction: Instruction) {
        val param1 = getParam(instruction.firstParamMode, 1)
        val param2 = getParam(instruction.secondParamMode, 2)
        val param3 = getParam(instruction.thirdParamMode, 3)

        data[param3] = if (data[param1] < data[param2]) BigInteger.ONE else BigInteger.ZERO

        index += 4
    }

    private fun equal(instruction: Instruction) {
        val param1 = getParam(instruction.firstParamMode, 1)
        val param2 = getParam(instruction.secondParamMode, 2)
        val param3 = getParam(instruction.thirdParamMode, 3)

        data[param3] = if (data[param1] == data[param2]) BigInteger.ONE else BigInteger.ZERO

        index += 4
    }

    private fun relativeOffset(instruction: Instruction) {
        val param = getParam(instruction.firstParamMode, 1)

        relativeBase += data[param]

        index += 2
    }

    fun calculate(inputValue: Int): MutableList<BigInteger> {
        input.add(inputValue)

        while (true) {
            val instruction = processInstruction(data[index].toInt())

            when (instruction.opCode) {
                1 -> add(instruction)
                2 -> multiply(instruction)
                3 -> readInput(instruction)
                4 -> writeOutput(instruction)
                5 -> jumpIfTrue(instruction)
                6 -> jumpIfFalse(instruction)
                7 -> lessThan(instruction)
                8 -> equal(instruction)
                9 -> relativeOffset(instruction)
                99 -> {
                    done = true
                    return output
                }
            }
        }
    }
}

data class Instruction(
    val opCode: Int,
    val firstParamMode: Int,
    val secondParamMode: Int,
    val thirdParamMode: Int
)