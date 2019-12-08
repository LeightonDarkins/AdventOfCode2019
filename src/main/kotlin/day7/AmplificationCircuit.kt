package day7

import day5.Instruction

import java.io.File

fun main() {
    val ampCircuit = AmplificationCircuit(name = "MASTER")

    var intCode = ampCircuit.readProgramInput()

    println("Part One: ${ampCircuit.doPartOne(intCode)}")
}

fun permutations(list: List<Int>): List<List<Int>> {
    if (list.size == 1) return listOf(list)

    val perms = mutableListOf<List<Int>>()

    val toInsert = list[0]

    for (perm in permutations(list.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }

    return perms
}

class AmplificationCircuit(
    private val inputs: MutableList<Int> = mutableListOf(),
    private val outputs: MutableList<Int> = mutableListOf(),
    private val name: String
) {
    var opCodeIndex = 0

    fun doPartOne(intCode: MutableList<Int>): Int {
        val phaseSettings = listOf(0,1,2,3,4)

        val phasePermutations = permutations(phaseSettings)
        val results = mutableListOf<Int>()

        phasePermutations.forEach { permutation ->
            var inputSignal = 0

            permutation.forEachIndexed { index, phaseSetting ->
                val ampCircuit = AmplificationCircuit(inputs = mutableListOf(phaseSetting, inputSignal), name = "AMP")

                ampCircuit.executeIntCodeProgram(intCode)
                inputSignal = ampCircuit.outputs.removeAt(0)

                if (index == permutation.size - 1) {
                    results.add(inputSignal)
                }
            }

        }

        return results.max()!!
    }

    fun doPartTwo(intCode: MutableList<Int>): Int {
        val phaseSettings = listOf(5,6,7,8,9)

        val phasePermutations = permutations(phaseSettings)
        val results = mutableListOf<Int>()

        phasePermutations.forEach { permutation ->
            val amplifiers = mutableListOf<AmplificationCircuit>()

            var outputSignal = 0

            permutation.forEachIndexed { index, it ->
                amplifiers.add(AmplificationCircuit(inputs = mutableListOf(it), name = "AMP+$index"))
            }

            amplifiers.forEach {
                it.inputs.add(outputSignal)
                it.executeIntCodeProgram(intCode)
                outputSignal = it.outputs.removeAt(0)
            }

            results.add(outputSignal)
        }

        return 0
    }

    fun readProgramInput(): MutableList<Int> {
        val input = File("src/main/resources/day7/input.txt")

        var intCode: MutableList<Int> = mutableListOf()

        input.forEachLine { it ->
            val listOfStrings = it.split(",")
            val listOfInts = listOfStrings.map { string -> string.toInt() }
            intCode = listOfInts.toMutableList()
        }

        return intCode
    }

    private fun executeIntCodeProgram(intCode: MutableList<Int>): Int {
        while (intCode[opCodeIndex] != 99) {
            val instruction = processInstruction(intCode[opCodeIndex])

            println("$name: ${intCode[opCodeIndex]} 1$instruction")

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

    private fun handleInputOpCode(intCode: MutableList<Int>): Int {
        val int = inputs.removeAt(0)

        val resultIndex = intCode[opCodeIndex + 1]

        intCode[resultIndex] = int

        return 2
    }

    private fun handlePrintOpCode(intCode: MutableList<Int>): Int {
        val index = intCode[opCodeIndex + 1]

        outputs.add(intCode[index])
        println("$name: Printing: ${intCode[index]}")

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