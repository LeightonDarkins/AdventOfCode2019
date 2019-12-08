package day8

import util.FileReader

fun main() {
    val file = FileReader().getFile("day8/input.txt")

    file.forEachLine {
        println(SpaceImageFormat(6, 25).doPartOne(it))
        SpaceImageFormat(6, 25).doPartTwo(it)
    }
}

class SpaceImageFormat(private val imageHeight: Int, private val imageWidth: Int) {
    private val layerSize = imageHeight * imageWidth

    fun doPartOne(input: String): Int {
        val fewestZeros = getLayers(input).minBy { it.numberOfZeros() }!!

        return fewestZeros.numberOfOnes() * fewestZeros.numberOfTwos()
    }

    fun doPartTwo(input: String) {
        val layers = getLayerStrings(input)

        val grid = IntArray(25 * 6) { -1 }

        layers.forEach { layer ->
            layer.toCharArray().forEachIndexed { index, c ->
                if (grid[index] in listOf(-1, 2)) {
                    grid[index] = "$c".toInt()
                }
            }
        }

        val result = grid.map { if (it == 1) 'X' else ' ' }.chunked(25).joinToString("\n") { it.joinToString("") }

        println(result)
    }

    private fun getLayers(input:String): List<Layer> {
        return input.chunked(layerSize).map { Layer(it, imageHeight, imageWidth) }
    }

    private fun getLayerStrings(input: String): List<String> = input.chunked(layerSize)
}

data class Layer(val contents: String, val height: Int, val width: Int) {
    fun numberOfZeros() = contents.count { it == '0' }
    fun numberOfOnes() = contents.count { it == '1' }
    fun numberOfTwos() = contents.count { it == '2' }
}