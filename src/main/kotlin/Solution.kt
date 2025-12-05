import de.m3y.kformat.Table
import de.m3y.kformat.table
import java.util.*

class Solution(
    val day: String,
    val inputFile: String,
    val solutionPart1: Number,
    val solutionPart2: Number
)

fun List<Solution>.toTable(): String = table {
    header("Day", "Input File", "Part 1", "Part 2")
    forEach { solution ->
        row(
            solution.day,
            solution.inputFile,
            String.format(Locale.GERMANY, "%,d", solution.solutionPart1),
            String.format(Locale.GERMANY, "%,d", solution.solutionPart2),
        )
    }
    hints {
        borderStyle = Table.BorderStyle.SINGLE_LINE
        alignment("Input File", Table.Hints.Alignment.LEFT)
        alignment("Part 1", Table.Hints.Alignment.RIGHT)
        alignment("Part 2", Table.Hints.Alignment.RIGHT)
    }
}.render().toString()
