package days

interface Day {
    fun runDay()

    companion object {
        fun getAllDays() = listOf(Day01(), Day02(), Day03(), Day04(), Day05())
    }
}
