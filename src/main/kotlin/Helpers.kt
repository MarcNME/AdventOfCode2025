import java.io.File
import kotlin.math.max
import kotlin.math.min

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

fun squareArea(
    p1: Pair<Long, Long>,
    p2: Pair<Long, Long>,
): Long {
    val dx = max(p1.first, p2.first) - min(p1.first, p2.first) + 1
    val dy = max(p1.second, p2.second) - min(p1.second, p2.second) + 1
    return dx * dy
}
