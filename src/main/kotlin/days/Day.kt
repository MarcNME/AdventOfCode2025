package days

import Solution

interface Day {
    fun runDay(): List<Solution>

    companion object {
        fun getAllDays() = listOf(Day01(), Day02(), Day03(), Day04(), Day05(), Day06())
    }
}
