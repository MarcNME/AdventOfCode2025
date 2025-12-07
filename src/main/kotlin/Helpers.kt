import java.io.File

fun readInputAsLines(fileName: String) = File("inputs/$fileName").readLines()

fun readInputAsString(fileName: String) = File("inputs/$fileName").readText()

fun String.isPalindrome(): Boolean {
    val mid = this.length / 2

    val firstHalf = this.take(mid)
    val secondHalf = this.substring(mid)

    return firstHalf == secondHalf
}

fun multiply(numbers: List<Int>): Long {
    if (numbers.isEmpty()) return 0
    if (numbers.size == 1) return numbers.first().toLong()
    var product = numbers.first().toLong()
    numbers.drop(1).forEach { product *= it }
    return product
}
