package day10

import org.junit.Assert
import org.junit.Test

class MonitoringStationTest {
    @Test
    fun `Example 1`() {
        val stringInput = ".#..#\n.....\n#####\n....#\n...##".split("\n")

        val input = mutableListOf<List<Char>>()

        stringInput.forEach {
            input.add(it.toList())
        }

        Assert.assertEquals(8, MonitoringStation().doPartOne(input))
    }

    @Test
    fun `Example 2`() {
        val stringInput = ("......#.#.\n" +
                "#..#.#....\n" +
                "..#######.\n" +
                ".#.#.###..\n" +
                ".#..#.....\n" +
                "..#....#.#\n" +
                "#..#....#.\n" +
                ".##.#..###\n" +
                "##...#..#.\n" +
                ".#....####").split("\n")

        val input = mutableListOf<List<Char>>()

        stringInput.forEach {
            input.add(it.toList())
        }

        Assert.assertEquals(33, MonitoringStation().doPartOne(input))
    }

    @Test
    fun `Example 3`() {
        val stringInput = ("#.#...#.#.\n" +
                ".###....#.\n" +
                ".#....#...\n" +
                "##.#.#.#.#\n" +
                "....#.#.#.\n" +
                ".##..###.#\n" +
                "..#...##..\n" +
                "..##....##\n" +
                "......#...\n" +
                ".####.###.").split("\n")

        val input = mutableListOf<List<Char>>()

        stringInput.forEach {
            input.add(it.toList())
        }

        Assert.assertEquals(35, MonitoringStation().doPartOne(input))
    }

    @Test
    fun `Example 4`() {
        val stringInput = (".#..#..###\n" +
                "####.###.#\n" +
                "....###.#.\n" +
                "..###.##.#\n" +
                "##.##.#.#.\n" +
                "....###..#\n" +
                "..#.#..#.#\n" +
                "#..#.#.###\n" +
                ".##...##.#\n" +
                ".....#.#..").split("\n")

        val input = mutableListOf<List<Char>>()

        stringInput.forEach {
            input.add(it.toList())
        }

        Assert.assertEquals(41, MonitoringStation().doPartOne(input))
    }

    @Test
    fun `Example 5`() {
        val stringInput = (".#..##.###...#######\n" +
                "##.############..##.\n" +
                ".#.######.########.#\n" +
                ".###.#######.####.#.\n" +
                "#####.##.#.##.###.##\n" +
                "..#####..#.#########\n" +
                "####################\n" +
                "#.####....###.#.#.##\n" +
                "##.#################\n" +
                "#####.##.###..####..\n" +
                "..######..##.#######\n" +
                "####.##.####...##..#\n" +
                ".#####..#.######.###\n" +
                "##...#.##########...\n" +
                "#.##########.#######\n" +
                ".####.#.###.###.#.##\n" +
                "....##.##.###..#####\n" +
                ".#.#.###########.###\n" +
                "#.#.#.#####.####.###\n" +
                "###.##.####.##.#..##").split("\n")

        val input = mutableListOf<List<Char>>()

        stringInput.forEach {
            input.add(it.toList())
        }

        Assert.assertEquals(210, MonitoringStation().doPartOne(input))
    }
}