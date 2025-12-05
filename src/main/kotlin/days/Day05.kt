package days

import Solution
import readInputAsLines
import java.util.*

class Day05 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day05_example.txt", "day05.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val lines = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(lines), part2(lines)))
        }

        return solutions
    }

    private fun part1(lines: List<String>) : Int{
        val freshIngredientsRanges = getFreshIngredientsRanges(lines)
        val freshIngredients = getFreshIngredients(lines, freshIngredientsRanges)
        return freshIngredients.size
    }

    private fun part2(lines: List<String>): Long {
        val freshIngredientsRanges = getFreshIngredientsRanges(lines)
        val rangesWithoutOverlapping = mergeRanges(freshIngredientsRanges.toList())
        val size = rangesWithoutOverlapping.sumOf { it.last - it.first + 1 }
        return size
    }

    private fun getFreshIngredientsRanges(lines: List<String>): Set<LongRange> {
        val ingredientRanges = mutableSetOf<LongRange>()
        for (line in lines) {
            if (line.isEmpty()) break
            val x = line.split('-').map { it.toLong() }

            if (x.size != 2) RuntimeException("Invalid input")

            ingredientRanges.add(x.first()..x.last())
        }

        return ingredientRanges
    }

    private fun getFreshIngredients(
        lines: List<String>,
        freshIngredientsRanges: Set<LongRange>,
    ): List<Long> {
        val ingredientList = lines.subList(lines.indexOfFirst { it.isEmpty() } + 1, lines.size).map { it.toLong() }
        return ingredientList.filter { ingredient -> freshIngredientsRanges.any { range -> ingredient in range } }
    }

    private fun mergeRanges(ranges: List<LongRange>): List<LongRange> {
        if (ranges.isEmpty()) return emptyList()

        val sorted = ranges.sortedBy { it.first }
        val merged = mutableListOf<LongRange>()
        var current = sorted.first()

        for (next in sorted.drop(1)) {
            if (next.first <= current.last + 1) {
                current = current.first..maxOf(current.last, next.last)
            } else {
                merged += current
                current = next
            }
        }

        merged += current
        return merged
    }
}