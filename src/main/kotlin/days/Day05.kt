package days

import readInputAsLines
import java.util.*

class Day05 : Day {
    override fun runDay() {
        println("Day 4")
        val fileNames = listOf("day05_example.txt", "day05.txt")
        for (fileName in fileNames) {
            val lines = readInputAsLines(fileName)
            println("Running part 1 for $fileName:")
            part1(lines)
            println()
            println("Running part 2 for $fileName")
            part2(lines)
            println()
        }
    }

    private fun part1(lines: List<String>) {
        val freshIngredientsRanges = getFreshIngredientsRanges(lines)
        val freshIngredients = getFreshIngredients(lines, freshIngredientsRanges)
        println(freshIngredients)
        println(freshIngredients.size)
    }

    private fun part2(lines: List<String>) {
        val freshIngredientsRanges = getFreshIngredientsRanges(lines)
        println(String.format(Locale.GERMANY, "%,d", freshIngredientsRanges.sumOf { it.last - it.first + 1 }))
        val rangesWithoutOverlapping = mergeRanges(freshIngredientsRanges.toList())
        println(rangesWithoutOverlapping)
        val size = rangesWithoutOverlapping.sumOf { it.last - it.first + 1 }
        println(String.format(Locale.GERMANY, "%,d", size))
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