import java.io.File

fun readInputAsLines(fileName: String) = File("inputs/$fileName").readLines()

fun readInputAsString(fileName: String) = File("inputs/$fileName").readText()

fun String.isPalindrome(): Boolean {
    val mid = this.length / 2

    val firstHalf = this.take(mid)
    val secondHalf = this.substring(mid)

    return firstHalf == secondHalf
}

fun callParts(
    inputs: List<String>,
    functions: List<(String) -> Unit>,
) {
    inputs.forEach { input ->
        functions.forEach { function ->
            println("Calling function: $function:")
            function(input)
            println()
        }
    }
}
