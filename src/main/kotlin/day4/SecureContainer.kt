package day4

import util.FileReader


fun main() {
    val file = FileReader().getFile("Day4/input.txt")

    val lines = file.readLines()

    val rangeItems = lines[0].split("-")
    val lowerBound: Int = rangeItems[0].toInt()
    val upperBound: Int = rangeItems[1].toInt()

    var possiblePartOnePasswords = 0

    for (i in lowerBound..upperBound) {
        if (SecureContainer().isPartOnePassword(i.toString())) possiblePartOnePasswords++
    }

    println(possiblePartOnePasswords)
}

class SecureContainer {
    fun isPartOnePassword(input: String): Boolean {
        if (input.length != 6) return false

        // at least two adjacent digits are the same
        if (!hasAdjacentDigits(input)) return false

        // no decreasing digits
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

    private fun hasDecreasingDigits(input: String): Boolean {
        for (i in input.indices) {
            if (i == input.lastIndex) continue
            if (input[i] > input[i+1]) return true
        }

        return false
    }
}