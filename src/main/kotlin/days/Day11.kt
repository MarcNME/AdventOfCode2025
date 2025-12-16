package days

import Solution
import readInputAsLines

class Day11 : Day {
    override fun runDay(): List<Solution> {
        val devicesPart1Example = parseInput(readInputAsLines("day11_example_part1.txt"))
        val devicesPart2Example = parseInput(readInputAsLines("day11_example_part2.txt"))
        val input = readInputAsLines("day11.txt")
        val devices = parseInput(input)
        return listOf(
            Solution(this.javaClass.simpleName, "day11_example.txt", part1(devicesPart1Example), part2(devicesPart2Example)),
            Solution(this.javaClass.simpleName, "day11.txt", part1(devices), part2(devices)),
        )
    }

    private fun part1(devices: Map<String, List<String>>): Long = devices.countPaths("you")

    private fun part2(devices: Map<String, List<String>>): Int = 0

    private fun parseInput(input: List<String>): Map<String, List<String>> {
        val devices = mutableMapOf<String, List<String>>()
        input.forEach { line ->
            devices[line.substringBefore(':')] = line.substringAfter(": ").split(" ")
        }

        return devices
    }

    private fun Map<String, List<String>>.countPaths(deviceName: String): Long {
        if (deviceName == "out") return 1
        return this[deviceName]?.sumOf { this.countPaths(it) } ?: throw RuntimeException("Illegal device")
    }
}
