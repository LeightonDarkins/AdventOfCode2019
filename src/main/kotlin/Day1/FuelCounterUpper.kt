package Day1

import java.io.File

fun main() {
    val fuelCounterUpper = FuelCounterUpper()
    val input = File("src/main/resources/Day1/input.txt")

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