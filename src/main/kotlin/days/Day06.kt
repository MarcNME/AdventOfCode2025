package days

import Solution
import multiply
import readInputAsLines
import readInputAsString

class Day06 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day06_example.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            solutions.add(
                Solution(
                    this.javaClass.simpleName,
                    fileName,
                    part1(readInputAsLines(fileName)),
                    part2(readInputAsString(fileName))
                )
            )
        }

        return solutions
    }

    private fun part1(input: List<String>): Long {
        val x = input.map { splitWithMultipleSpaces(it) }
        var sum = 0L

        for (j in x.first().indices) {
            val numbers = mutableListOf<Int>()
            for (i in x.indices) {
                if (i == x.size - 1) {
                    val result = if (x[i][j] == "+") numbers.sum().toLong()
                    else multiply(numbers)
                    sum += result
                } else {
                    numbers.add(x[i][j].toInt())
                }
            }
        }

        return sum
    }

    private fun part2(input: String): Long {
        return calcCephalopodMath(input).sum()
    }

    private fun splitWithMultipleSpaces(line: String): List<String> {
        val numbers = mutableListOf<String>()
        var tmp = ""
        for (c in line) {
            if (c.isWhitespace()) {
                if (tmp.isEmpty()) continue
                numbers.add(tmp)
                tmp = ""
            } else tmp += c
        }

        if (tmp.isNotEmpty()) numbers.add(tmp)
        return numbers
    }

    private fun calcCephalopodMath(input: String): List<Long> {
        val problems = mutableListOf<List<List<Char>>>()
        var problem = mutableListOf<List<Char>>()
        for (j in input) {
            val column = mutableListOf<Char>()
            for (i in input.indices) {
                column.add(input[i])
            }
            if (column.all {it.isWhitespace()}) {
                problems.add(problem)
                problem = mutableListOf()
            } else {
                problem.add(column)
            }
        }
        return emptyList()
    }
}