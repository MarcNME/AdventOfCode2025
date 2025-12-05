package days

import Solution
import isPalindrome
import readInputAsString


class Day02 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day02_example.txt", "day02.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val input = readInputAsString(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }


    private fun part1(input: String): Long {
        var sum = 0L
        val ranges = input.split(",")
        ranges.forEach { range ->
            val (start, end) = range.split('-')
            for (l in start.toLong()..end.toLong()) {
                if (l.toString().isPalindrome()) sum += l
            }
        }

        return sum
    }

    private fun part2(input: String): Long {
        var sum = 0L
        val ranges = input.split(",")
        ranges.forEach { range ->
            val (start, end) = range.split('-').map { it.toLong() }
            for (l in start..end) {
                if (l.hasRepeatedPart()) sum += l
            }
        }

        return sum
    }

    private fun Long.hasRepeatedPart(): Boolean {
        val s = this.toString()

        for (i in (1..s.length / 2).reversed()) {
            val parts = s.splitIntoEqualParts(i)
            val partsSet = parts.toSet()
            if (partsSet.size == 1) {
                return true
            }
        }
        return false
    }

    private fun String.splitIntoEqualParts(partLength: Int): List<String> {
        require(partLength > 0) { "partLength must be > 0" }
        if (this.length % partLength != 0) {
            return emptyList()
        }

        val parts = mutableListOf<String>()
        var i = 0
        while (i + partLength <= this.length) {
            parts += this.substring(i, i + partLength)
            i += partLength
        }
        return parts
    }
}
