package days

import Solution
import readInputAsLines
class Day03 : Day {
    override fun runDay(): List<Solution> {
        val solutions = mutableListOf<Solution>()
        val fileNames = listOf("day03_example.txt", "day03.txt")
        for (fileName in fileNames) {
            val input = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }

    private fun part1(lines: List<String>): Int {
        var joltageSum = 0
        lines.forEach { line ->
            val lpj = getLargestPossibleJoltage(line)
            joltageSum += lpj
        }

        return joltageSum
    }

    private fun part2(lines: List<String>): Long {
        var joltageSum = 0L
        lines.map { line -> line.map { c -> c.digitToInt() } }.forEach { line ->
            val lineLength = line.size
            val digits = 12
            var joltage = ""
            var index = 0
            for (i in 0 until digits) {
                val biggestWithIndex = getBiggestNumber(line, index, lineLength - digits + i + 1)
                joltage += biggestWithIndex.first
                index = biggestWithIndex.second + 1
            }
            joltageSum += joltage.toLong()
        }

        return joltageSum
    }

    private fun getLargestPossibleJoltage(str: String): Int {
        val digitList = str.toCharArray().map { it.digitToInt() }
        var index = -1
        var highestDigit = -1

        for (i in 0 until digitList.lastIndex) {
            if (digitList[i] > highestDigit) {
                highestDigit = digitList[i]
                index = i
            }
        }
        var lpj = highestDigit.toString()
        highestDigit = -1

        for (i in index + 1..digitList.lastIndex) {
            if (digitList[i] > highestDigit) {
                highestDigit = digitList[i]
            }
        }
        lpj += highestDigit.toString()

        return lpj.toInt()
    }

    private fun getBiggestNumber(
        numbers: List<Int>,
        startIndex: Int,
        endIndex: Int,
    ): Pair<Int, Int> {
        var biggest = -1
        var index = startIndex
        for (i in startIndex until endIndex) {
            if (numbers[i] > biggest) {
                biggest = numbers[i]
                index = i
            }
        }

        return biggest to index
    }
}