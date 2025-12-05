fun main() {
    val testInput = readInputAsLines("day01.txt")
    println("Day 1\n")
    part1(testInput)
    part2(testInput)
    println()
}

private fun part1(input: List<String>) {
    var dialPosition = 50
    var zeroCounter = 0

    input.forEach {
        if (it.startsWith("L")) {
            val steps = it.substring(1).toInt()
            dialPosition = moveLeft(dialPosition, steps)
        } else if (it.startsWith("R")) {
            val steps = it.substring(1).toInt()
            dialPosition = moveRight(dialPosition, steps)
        }

        if (dialPosition == 0) {
            zeroCounter++
        }
    }

    println("The dial hit position 0 a total of $zeroCounter times")
}

private fun part2(input: List<String>) {
    var dialPosition = 50
    var zeroCounter = 0

    input.forEach {
        val steps = it.substring(1).toInt()

        print("Moving $it: from $dialPosition to ")
        for (i in 1..steps) {
            if (it.startsWith("R")) {
                if (dialPosition == 99) {
                    dialPosition = 0
                } else {
                    dialPosition++
                }
            } else if (it.startsWith("L")) {
                if (dialPosition == 0) {
                    dialPosition = 99
                } else {
                    dialPosition--
                }
            }
            if (dialPosition == 0) {
                zeroCounter++
            }
        }

        println(dialPosition)
        println("Current zeroCounter: $zeroCounter")
    }

    println("The dial hit position 0 a total of $zeroCounter times")
}

private fun moveLeft(
    dialPosition: Int,
    steps: Int,
): Int {
    var position = dialPosition
    for (i in 1..steps) {
        if (position == 0) {
            position = 99
        } else {
            position--
        }
    }

    return position
}

private fun moveRight(
    dialPosition: Int,
    steps: Int,
): Int {
    var position = dialPosition
    for (i in 1..steps) {
        if (position == 99) {
            position = 0
        } else {
            position++
        }
    }

    return position
}
