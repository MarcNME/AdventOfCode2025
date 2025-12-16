package days

import Solution
import readInputAsLines

class Day10 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day10_example.txt", "day10.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val input = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }

    private fun part1(input: List<String>): Int {
        val machines = getMachines(input)
        println(machines)
        return 0
    }

    private fun part2(input: List<String>): Int = 0

    private class Machine(
        val lights: List<Boolean>,
        val buttons: List<List<Int>>,
        val joltages: List<Int> = emptyList(),
    ) {
        override fun toString(): String = "${lights.joinToString(",")} ${buttons.joinToString(",")} ${joltages.joinToString(",")}"
    }

    private fun getMachines(input: List<String>): List<Machine> {
        val machineList = mutableListOf<Machine>()
        input.forEach { line ->
            val splitInput = line.split(" ")
            val lights =
                splitInput
                    .first()
                    .replace("[", "")
                    .replace("]", "")
                    .map { it != '.' }
            val buttons =
                splitInput
                    .filter { it.contains('(') }
                    .map {
                        it
                            .replace("(", "")
                            .replace(")", "")
                            .split(',')
                            .map { buttonNumber -> buttonNumber.toInt() }
                    }
            val joltages =
                splitInput
                    .last()
                    .replace("{", "")
                    .replace("}", "")
                    .split(",")
                    .map { it.toInt() }
            machineList.add(Machine(lights, buttons, joltages))
        }

        return machineList
    }
}
