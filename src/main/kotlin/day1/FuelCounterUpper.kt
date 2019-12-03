package day1

import util.FileReader

fun main() {
    val fuelCounterUpper = FuelCounterUpper()
    val fileReader = FileReader()
    val input = fileReader.getFile("src/main/resources/Day1/input.txt")

    var total = 0

    input.forEachLine {
        val result = fuelCounterUpper.calculateFuelRequirementRecursively(it.toInt())
        total += result
    }

    println(total)
}

class FuelCounterUpper {
    fun calculateFuelRequirement(mass: Int) = (mass / 3) - 2

    fun calculateFuelRequirementRecursively(mass: Int): Int {
        var fuel = calculateFuelRequirement(mass)

        if (calculateFuelRequirement(fuel) > 0) {
            fuel += calculateFuelRequirementRecursively(fuel)
        }

        return fuel
    }
}