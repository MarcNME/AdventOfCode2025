package days

import Solution
import readInputAsLines

class Day01 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day01_example.txt", "day01.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val input = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }

    private fun part1(input: List<String>): Int {
        var dialPosition = 50
        var zeroCounter = 0

        input.forEach {
            if (it.startsWith("L")) {
                val steps = it.substring(1).toInt()
                dialPosition = moveLeft(dialPosition, steps)
            } else if (it.startsWith("R")) {
                val steps = it.substring(1).toInt()
                dialPosition = moveRight(dialPosition, steps)
            }

            if (dialPosition == 0) {
                zeroCounter++
            }
        }

        return zeroCounter
    }

    private fun part2(input: List<String>): Int {
        var dialPosition = 50
        var zeroCounter = 0

        input.forEach {
            val steps = it.substring(1).toInt()

            for (i in 1..steps) {
                if (it.startsWith("R")) {
                    if (dialPosition == 99) {
                        dialPosition = 0
                    } else {
                        dialPosition++
                    }
                } else if (it.startsWith("L")) {
                    if (dialPosition == 0) {
                        dialPosition = 99
                    } else {
                        dialPosition--
                    }
                }
                if (dialPosition == 0) {
                    zeroCounter++
                }
            }
        }

        return zeroCounter
    }

    private fun moveLeft(
        dialPosition: Int,
        steps: Int,
    ): Int {
        var position = dialPosition
        for (i in 1..steps) {
            if (position == 0) {
                position = 99
            } else {
                position--
            }
        }

        return position
    }

    private fun moveRight(
        dialPosition: Int,
        steps: Int,
    ): Int {
        var position = dialPosition
        for (i in 1..steps) {
            if (position == 99) {
                position = 0
            } else {
                position++
            }
        }

        return position
    }

}