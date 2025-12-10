package days

import Solution
import readInputAsLines
import squareArea

class Day09 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day09_example.txt", "day09.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val input = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }

    private fun part1(input: List<String>): Long {
        val tilePositions = parseTilePositions(input)

        var biggestArea = Long.MIN_VALUE

        tilePositions.forEachIndexed { i, tile1 ->
            tilePositions.drop(i + 1).forEach { tile2 ->
                val area = squareArea(tile1, tile2)
                if (area > biggestArea) {
                    biggestArea = area
                }
            }
        }

        return biggestArea
    }

    private fun part2(input: List<String>): Long {
        val tilePositions = parseTilePositions(input)

        var biggestArea = Long.MIN_VALUE

        tilePositions.forEachIndexed { i, tile1 ->
            tilePositions.drop(i + 1).forEach { tile2 ->
                val area = squareArea(tile1, tile2)
                if (area > biggestArea && tilePositions.isRectangleInside(tile1, tile2)) {
                    biggestArea = area
                    println("$tile1 -> $tile2 -> $area")
                }
            }
        }

        return biggestArea
    }

    private fun parseTilePositions(input: List<String>): List<Pair<Long, Long>> =
        input.map { line ->
            line
                .split(",")
                .zipWithNext { a, b ->
                    a.toLong() to
                        b.toLong()
                }.first()
        }

    fun List<Pair<Long, Long>>.isRectangleInside(
        p1: Pair<Long, Long>,
        p2: Pair<Long, Long>,
    ): Boolean {
        val minX = minOf(p1.first, p2.first)
        val maxX = maxOf(p1.first, p2.first)
        val minY = minOf(p1.second, p2.second)
        val maxY = maxOf(p1.second, p2.second)

        val rectPoints =
            listOf(
                minX to minY,
                minX to maxY,
                maxX to minY,
                maxX to maxY,
            )

        return rectPoints.all { corner -> this.isPointInPolygon(corner) }
    }
}

fun List<Pair<Long, Long>>.isPointInPolygon(p: Pair<Long, Long>): Boolean {
    if (p in this) return true

    for (i in 0 until this.lastIndex) {
        val maxX = maxOf(this[i].first, this[i + 1].first)
        val minX = minOf(this[i].first, this[i + 1].first)
        val minY = minOf(this[i].second, this[i + 1].second)
        val maxY = maxOf(this[i].second, this[i + 1].second)

        if (p.first in minX..maxX && p.second in minY..maxY) return true
    }
    return false
}
