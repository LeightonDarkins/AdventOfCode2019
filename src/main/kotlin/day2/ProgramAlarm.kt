package day2

import java.io.File

fun main() {
    val programAlarm = ProgramAlarm()

    var intCode = programAlarm.readProgramInput()

    val resultCode = programAlarm.executeIntCodeProgram(intCode)

    println(resultCode[0])

    intCode = programAlarm.readProgramInput()

    val resultNounAndVerb = programAlarm.findCorrectVerbAndNoun(intCode, 19690720)

    println(resultNounAndVerb)
}

class ProgramAlarm {
    fun readProgramInput(): MutableList<Int> {
        val input = File("src/main/resources/day2/input.txt")

        var intCode: MutableList<Int> = mutableListOf()

        input.forEachLine { it ->
            val listOfStrings = it.split(",")
            val listOfInts = listOfStrings.map { string -> string.toInt() }
            intCode = listOfInts.toMutableList()
        }

        return intCode
    }

    fun executeIntCodeProgram(intCode: MutableList<Int>): MutableList<Int> {
        var opCodeIndex = 0

        while (opCodeIndex <= intCode.size - 5) {
            val actionCode = intCode[opCodeIndex]
            val firstOperandIndex = intCode[opCodeIndex + 1]
            val secondOperandIndex = intCode[opCodeIndex + 2]
            val resultIndex = intCode[opCodeIndex + 3]

            when (actionCode) {
                1 -> intCode[resultIndex] = intCode[firstOperandIndex] + intCode[secondOperandIndex]
                2 -> intCode[resultIndex] = intCode[firstOperandIndex] * intCode[secondOperandIndex]
                99 -> return intCode
            }

            opCodeIndex += 4
        }

        return intCode
    }

    fun findCorrectVerbAndNoun(intCode: MutableList<Int>, targetValue: Int): Pair<Int, Int> {
        var result = Pair(-1,-1)

        for (noun in 0..99) {
            for (verb in 0..99) {
                val modifiedIntCode = intCode.toMutableList()

                modifiedIntCode[1] = noun
                modifiedIntCode[2] = verb

                val resultCode = executeIntCodeProgram(modifiedIntCode)

                if (resultCode[0] == targetValue) result = Pair(noun, verb)
            }
        }

        return result
    }
}