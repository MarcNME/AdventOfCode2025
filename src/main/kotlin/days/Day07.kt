package days

import Solution
import readInputAsLines

class Day07 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day07_example.txt", "day07.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val input = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }

    private fun part1(input: List<String>): Int {
        val tachyonManifold = input.map { it.toMutableList() }
        var splitCounter = 0
        for (i in 1 until tachyonManifold.size) {
            for (j in 0 until tachyonManifold[i].size) {
                if (tachyonManifold[i - 1][j] == 'S' || tachyonManifold[i - 1][j] == '|') {
                    if (tachyonManifold[i][j] == '.') tachyonManifold[i][j] = '|'
                    else if (tachyonManifold[i][j] == '^') {
                        splitCounter++
                        tachyonManifold[i][j - 1] = '|'
                        tachyonManifold[i][j + 1] = '|'
                    }
                }
            }
        }

        return splitCounter
    }

    private fun part2(input: List<String>): Long {
        val tachyonManifold = input.map { it.map { c -> if (c == '.') "0" else c.toString() }.toMutableList() }
        tachyonManifold[0][tachyonManifold.first().indexOf("S")] = "1"
        for (i in 1 until tachyonManifold.size) {
            for (j in 0 until tachyonManifold[i].size) {
                if (tachyonManifold[i][j] == "^") {
                    tachyonManifold[i][j - 1] =
                        if (tachyonManifold[i][j - 1] == "0") tachyonManifold[i - 1][j] else (tachyonManifold[i - 1][j].toLong() + tachyonManifold[i][j - 1].toLong()).toString()
                    tachyonManifold[i][j + 1] =
                        if (tachyonManifold[i][j + 1] == "0") tachyonManifold[i - 1][j] else (tachyonManifold[i - 1][j].toLong() + tachyonManifold[i][j + 1].toLong()).toString()
                }
            }
            for (j in 0 until tachyonManifold[i].size) {
                if ((tachyonManifold[i][j].toLongOrNull() != null) && tachyonManifold[i - 1][j] != "^") {
                    tachyonManifold[i][j] =
                        (tachyonManifold[i][j].toLong() + tachyonManifold[i - 1][j].toLong()).toString()
                }
            }
        }

        return tachyonManifold.last().sumOf { it.toLong() }
    }

    private fun nextStep(tachyonManifold: List<MutableList<Char>>, pos: Pair<Int, Int>): Long {
        return if (pos.first == tachyonManifold.lastIndex) 1L
        else {
            var newPos = pos
            while (tachyonManifold[newPos.first][newPos.second] == '.') {
                if (newPos.first + 1 >= tachyonManifold.lastIndex) return 1L
                newPos = newPos.first + 1 to newPos.second

            }
            return nextStep((tachyonManifold), newPos.first + 1 to newPos.second - 1) +
                    nextStep((tachyonManifold), newPos.first + 1 to newPos.second + 1)
        }
    }
}
