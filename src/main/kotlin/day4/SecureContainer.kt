package day4

import util.FileReader


fun main() {
    val file = FileReader().getFile("Day4/input.txt")

    val lines = file.readLines()

    val rangeItems = lines[0].split("-")
    val lowerBound: Int = rangeItems[0].toInt()
    val upperBound: Int = rangeItems[1].toInt()

    var possiblePartOnePasswords = 0
    var possiblePartTwoPasswords = 0

    for (i in lowerBound..upperBound) {
        if (SecureContainer().isPartOnePassword(i.toString())) possiblePartOnePasswords++
        if (SecureContainer().isPartTwoPassword(i.toString())) possiblePartTwoPasswords++
    }

    println(possiblePartOnePasswords)
    println(possiblePartTwoPasswords)
}

class SecureContainer {
    fun isPartOnePassword(input: String): Boolean {
        if (input.length != 6) return false
        if (!hasAdjacentDigits(input)) return false
        if (hasDecreasingDigits(input)) return false

        return true
    }

    fun isPartTwoPassword(input:String): Boolean {
        if (input.length != 6) return false
        if (!hasAdjacentDigitsPartTwo(input)) return false
        if (hasDecreasingDigits(input)) return false

        return true
    }

    private fun hasAdjacentDigits(input: String): Boolean {
        for (i in input.indices) {
            if (i == input.lastIndex) continue
            if (input[i] == input[i+1]) return true
        }

        return false
    }

    private fun hasAdjacentDigitsPartTwo(input: String): Boolean {
        val groups = HashMap<Char, Int>()

        for (index in input.indices) {
            val currentNumber = input[index]

            if (!groups.containsKey(currentNumber)) groups[currentNumber] = 1

            if (index == input.lastIndex) continue

            val nextNumber = input[index+1]

            if (currentNumber == nextNumber) {
                val currentCount = groups[currentNumber]!!
                groups[currentNumber] = currentCount + 1
            }
        }

        if (groups.values.contains(2)) return true

        return false
    }

    private fun hasDecreasingDigits(input: String): Boolean {
        for (i in input.indices) {
            if (i == input.lastIndex) continue
            if (input[i] > input[i+1]) return true
        }

        return false
    }
}