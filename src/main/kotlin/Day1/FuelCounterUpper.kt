package Day1

import java.io.File

fun main() {
    val fuelCounterUpper = FuelCounterUpper()
    val input = File("src/main/resources/Day1/input.txt")

    var total = 0

    input.forEachLine {
        val result = fuelCounterUpper.doIt(it.toInt())
        total += result
    }

    println(total)
}

class FuelCounterUpper {
    fun doIt(mass: Int) = (mass / 3) - 2
}