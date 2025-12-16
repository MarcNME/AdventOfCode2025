package days

import Solution
import readInputAsLines

class Day12 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day12_example.txt", "day12.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val input = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }

    private fun part1(input: List<String>): Int {
        val shapes = getPresentShapes(input)
        val regions = getRegions(input)

        return regions.count { region ->
            val regionArea = region.x * region.y
            val usedCells =
                region.presentsNeeded
                    .map { (presentIndex, count) ->
                        count * shapes[presentIndex].shape.sumOf { it.count { it } }
                    }.sum()

            regionArea >= usedCells
        }
    }

    private fun part2(input: List<String>): Int = 0

    private fun getPresentShapes(input: List<String>): List<PresentShape> {
        val presentShapes = mutableListOf<PresentShape>()
        val tmpShape = mutableListOf<List<Boolean>>()
        for (line in input) {
            if (line.contains('x')) break

            if (line.startsWith('.') || line.startsWith('#')) {
                tmpShape.add(line.map { it == '#' })
                continue
            }

            if (line.isBlank()) {
                presentShapes.add(PresentShape(tmpShape.toList()))
                tmpShape.clear()
            }
        }

        return presentShapes
    }

    private fun getRegions(input: List<String>): List<Region> =
        input.filter { it.contains('x') }.map { line ->

            val x = line.substringBefore('x').toInt()
            val y = line.substring(line.indexOf('x') + 1, line.indexOf(':')).toInt()
            val s = line.substringAfter(": ").split(' ')
            val presentsNeeded = mutableMapOf<Int, Int>()
            for (i in 0..s.lastIndex) {
                val count = s[i].toInt()
                if (count > 0) {
                    presentsNeeded[i] = count
                }
            }

            Region(x, y, presentsNeeded)
        }

    private data class PresentShape(
        val shape: List<List<Boolean>> = mutableListOf(),
    )

    private data class Region(
        val x: Int,
        val y: Int,
        val presentsNeeded: Map<Int, Int>,
    )
}
