import java.io.File

fun readInputAsLines(fileName: String) = File("inputs/$fileName").readLines()

fun readInputAsString(fileName: String) = File("inputs/$fileName").toString()
