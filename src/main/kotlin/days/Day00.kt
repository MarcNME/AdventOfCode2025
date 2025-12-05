package days

import Solution
import readInputAsLines

//TODO: Change class name
class Day00 : Day {
    override fun runDay(): List<Solution> {
        // TODO: Change used input files
        val fileNames = listOf("day00_example.txt", "day00.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val input = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }

    private fun part1(input: List<String>): Int {
        TODO("Template for part1")
    }

    private fun part2(input: List<String>): Int {
        TODO("Template for part2")
    }
}