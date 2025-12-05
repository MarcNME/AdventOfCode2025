fun main() {
    val fileNames = listOf("day02_example.txt", "day02.txt")
    println("Day 2\n")
    for (fileName in fileNames) {
        val input = readInputAsString(fileName)
        println("Running part 1 for $fileName:")
        part1(input)
        println()
        println("Running part 2 for $fileName")
        part2(input)
        println()
    }
}

private fun part1(input: String) {
    var sum = 0L
    val ranges = input.split(",")
    ranges.forEach { range ->
        val (start, end) = range.split('-')
        for (l in start.toLong()..end.toLong()) {
            if (l.toString().isPalindrome()) sum += l
        }
    }

    println(sum)
}

private fun part2(input: String) {
    var sum = 0L
    val ranges = input.split(",")
    ranges.forEach { range ->
        val (start, end) = range.split('-').map { it.toLong() }
        for (l in start..end) {
            if (l.hasRepeatedPart()) sum += l
        }
    }

    println(sum)
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
