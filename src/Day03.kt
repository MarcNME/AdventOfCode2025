fun main() {
    println("Day 3")
    val fileNames = listOf("day03_example.txt", "day03.txt")
    for (fileName in fileNames) {
        val input = readInputAsLines(fileName)
        println("Running part 1 for $fileName:")
        part1(input)
        println()
        println("Running part 2 for $fileName")
        part2(input)
        println()
    }
}

private fun part1(lines: List<String>) {
    var joltageSum = 0
    lines.forEach { line ->
        val lpj = getLargestPossibleJoltage(line)
        joltageSum += lpj
        // println("$lpj for line $line")
    }

    println()
    println("Joltage sum: $joltageSum")
}

private fun part2(lines: List<String>) {
    var joltageSum = 0L
    lines.map { line -> line.map { c -> c.digitToInt() } }.forEach { line ->
        val lineLength = line.size
        val digits = 12
        var joltage = ""
        var index = 0
        for (i in 0 until digits) {
            // println("Find biggest in ${line.subList(index, lineLength - digits + i + 1)}")
            val biggestWithIndex = getBiggestNumber(line, index, lineLength - digits + i + 1)
            joltage += biggestWithIndex.first
            index = biggestWithIndex.second + 1
            // println("Biggest $joltage at index $index")
        }
        joltageSum += joltage.toLong()
        println("Joltage for $line: $joltage")
    }

    println("Joltage sum: $joltageSum")
}

private fun getLargestPossibleJoltage(str: String): Int {
    val digitList = str.toCharArray().map { it.digitToInt() }
    var index = -1
    var highestDigit = -1

    for (i in 0 until digitList.lastIndex) {
        if (digitList[i] > highestDigit) {
            highestDigit = digitList[i]
            index = i
        }
    }
    var lpj = highestDigit.toString()
    highestDigit = -1

    for (i in index + 1..digitList.lastIndex) {
        if (digitList[i] > highestDigit) {
            highestDigit = digitList[i]
        }
    }
    lpj += highestDigit.toString()

    return lpj.toInt()
}

private fun getBiggestNumber(
    numbers: List<Int>,
    startIndex: Int,
    endIndex: Int,
): Pair<Int, Int> {
    var biggest = -1
    var index = startIndex
    for (i in startIndex until endIndex) {
        if (numbers[i] > biggest) {
            biggest = numbers[i]
            index = i
        }
    }

    return biggest to index
}
