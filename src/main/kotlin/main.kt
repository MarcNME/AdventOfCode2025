import days.Day

fun main(args: Array<String> = emptyArray()) {
    val begin = System.currentTimeMillis()
    val solutions = if (args.isNotEmpty()) {
        Day.getAllDays().filter { args.contains(it::class.simpleName) }.flatMap(Day::runDay)
    } else {
        Day.getAllDays().flatMap(Day::runDay)
    }
    val end = System.currentTimeMillis()

    println(solutions.toTable())
    println("Total time: ${end - begin}ms")
}