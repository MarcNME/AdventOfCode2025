package days

import Solution
import readInputAsLines

class Day04 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day04_example.txt", "day04.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val grid = readInputAsLines(fileName).map { it.toCharArray() }
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(grid), part2(grid)))
        }

        return solutions
    }

    private fun part1(grid: List<CharArray>): Int {
        var moveableRolls = 0
        for (i in grid.indices) {
            for (j in grid.first().indices) {
                if (grid[i][j] == '@') {
                    var adjacentRolles = 0
                    // Vertically left to right ➡️
                    if (j + 1 in grid[i].indices && grid[i][j + 1] == '@') {
                        adjacentRolles++
                    }

                    // Vertically right to left ⬅️
                    if (j - 1 in grid[i].indices && grid[i][j - 1] == '@') {
                        adjacentRolles++
                    }

                    // Horizontally top to bottom ⬇️
                    if (i + 1 in grid.indices && grid[i + 1][j] == '@') {
                        adjacentRolles++
                    }

                    // Horizontally bottom to top ⬆️
                    if (i - 1 in grid.indices && grid[i - 1][j] == '@') {
                        adjacentRolles++
                    }

                    // Diagonally top left to bottom right ↘️
                    if (i + 1 in grid.indices && j + 1 in grid[i].indices && grid[i + 1][j + 1] == '@') {
                        adjacentRolles++
                    }

                    // Diagonally top right to bottom left ↙️
                    if (i + 1 in grid.indices && j - 1 in grid[i].indices && grid[i + 1][j - 1] == '@') {
                        adjacentRolles++
                    }

                    // Diagonally bottom left to top right ↗️
                    if (i - 1 in grid.indices && j + 1 in grid[i].indices && grid[i - 1][j + 1] == '@') {
                        adjacentRolles++
                    }

                    // Diagonally bottom right to top left↖️
                    if (i - 1 in grid.indices && j - 1 in grid[i].indices && grid[i - 1][j - 1] == '@') {
                        adjacentRolles++
                    }

                    if (adjacentRolles < 4) {
                        moveableRolls++
                    }
                }
            }
        }

        return moveableRolls
    }

    private fun part2(grid: List<CharArray>): Int {
        var moveableRollsTotal = 0
        var movableRollsRound: Int
        val gridMutable = grid.map { it.toMutableList() }.toMutableList()
        do {
            movableRollsRound = 0
            for (i in gridMutable.indices) {
                for (j in gridMutable.first().indices) {
                    if (gridMutable[i][j] == '@') {
                        var adjacentRolles = 0
                        // Vertically left to right ➡️
                        if (j + 1 in gridMutable[i].indices && (gridMutable[i][j + 1] == '@' || gridMutable[i][j + 1] == '#')) {
                            adjacentRolles++
                        }

                        // Vertically right to left ⬅️
                        if (j - 1 in gridMutable[i].indices && (gridMutable[i][j - 1] == '@' || gridMutable[i][j - 1] == '#')) {
                            adjacentRolles++
                        }

                        // Horizontally top to bottom ⬇️
                        if (i + 1 in gridMutable.indices && (gridMutable[i + 1][j] == '@' || gridMutable[i + 1][j] == '#')) {
                            adjacentRolles++
                        }

                        // Horizontally bottom to top ⬆️
                        if (i - 1 in gridMutable.indices && (gridMutable[i - 1][j] == '@' || gridMutable[i - 1][j] == '#')) {
                            adjacentRolles++
                        }

                        // Diagonally top left to bottom right ↘️
                        if (i + 1 in gridMutable.indices && j + 1 in gridMutable[i].indices && (gridMutable[i + 1][j + 1] == '@' || gridMutable[i + 1][j + 1] == '#')) {
                            adjacentRolles++
                        }

                        // Diagonally top right to bottom left ↙️
                        if (i + 1 in gridMutable.indices && j - 1 in gridMutable[i].indices && (gridMutable[i + 1][j - 1] == '@' || gridMutable[i + 1][j - 1] == '#')) {
                            adjacentRolles++
                        }

                        // Diagonally bottom left to top right ↗️
                        if (i - 1 in gridMutable.indices && j + 1 in gridMutable[i].indices && (gridMutable[i - 1][j + 1] == '@' || gridMutable[i - 1][j + 1] == '#')) {
                            adjacentRolles++
                        }

                        // Diagonally bottom right to top left↖️
                        if (i - 1 in gridMutable.indices && j - 1 in gridMutable[i].indices && (gridMutable[i - 1][j - 1] == '@' || gridMutable[i - 1][j - 1] == '#')) {
                            adjacentRolles++
                        }

                        if (adjacentRolles < 4) {
                            movableRollsRound++
                            gridMutable[i][j] = '#'
                        }
                    }
                }
            }

            for (i in gridMutable.indices) {
                for (j in gridMutable.first().indices) {
                    if (gridMutable[i][j] == '#') {
                        gridMutable[i][j] = 'x'
                    }
                }
            }

            moveableRollsTotal += movableRollsRound
        } while (movableRollsRound > 0)

        return moveableRollsTotal
    }
}
