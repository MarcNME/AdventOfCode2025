package days

import Solution
import multiply
import readInputAsLines
import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.math.pow
import kotlin.math.sqrt

class Day08 : Day {
    override fun runDay(): List<Solution> {
        val fileNames = listOf("day08.txt")
        val solutions = mutableListOf<Solution>()
        for (fileName in fileNames) {
            val input = readInputAsLines(fileName)
            solutions.add(Solution(this.javaClass.simpleName, fileName, part1(input), part2(input)))
        }

        return solutions
    }

    private fun part1(input: List<String>): Long {
        val junctionBoxes = getJunctionBoxes(input)
        val connections = mutableListOf<Pair<JunctionBox, JunctionBox>>()
        val circuits = mutableListOf<MutableList<Pair<JunctionBox, JunctionBox>>>()

        while (circuits.sumOf { it.size } < 1001) {
            val connection = getShortestPossibleConnection(junctionBoxes, connections)
            connections.add(connection)
            val circuit = circuits.firstOrNull { circuit -> circuit.any { connection.first == it.first || connection.second == it.second } }
            if (circuit != null) {
                if (circuit.none { connection.first == it.first } || circuit.none { connection.second == it.second }) {
                    circuit.add(connection)
                }
            } else {
                circuits.add(mutableListOf(connection))
            }
        }

        circuits.forEach { circuit -> println(circuit) }
        println()
        val biggestCircuits = circuits.sortedByDescending { it.size }.take(3)
        biggestCircuits.forEach { circuit -> println(circuit) }
        val x = biggestCircuits.map { circuit -> circuit.flatMap { listOf(it.first, it.second) }.toSet() }
        return multiply(x.map { it.size })
    }

    private fun part2(input: List<String>): Int = 0

    private class JunctionBox(
        val x: Int,
        val y: Int,
        val z: Int,
    ) {
        override fun toString(): String = "[$x,$y,$z]"
    }

    private fun getJunctionBoxes(input: List<String>): List<JunctionBox> =
        input.map {
            val values = it.split(',')
            JunctionBox(values[0].toInt(), values[1].toInt(), values[2].toInt())
        }

    private fun getShortestPossibleConnection(
        boxes: List<JunctionBox>,
        connections: List<Pair<JunctionBox, JunctionBox>>,
    ): Pair<JunctionBox, JunctionBox> {
        var shortestPossibleConnection: Pair<JunctionBox, JunctionBox>? = null
        var shortestDistance = POSITIVE_INFINITY

        boxes.forEachIndexed { i, box1 ->
            boxes.drop(i + 1).forEach { box2 ->
                val distance = getDistance(box1, box2)
                if (distance < shortestDistance && box1 to box2 !in connections) {
                    shortestDistance = distance
                    shortestPossibleConnection = box1 to box2
                }
            }
        }

        if (shortestPossibleConnection == null) {
            throw RuntimeException("No connection found")
        }
        return shortestPossibleConnection
    }

    private fun getDistance(
        box1: JunctionBox,
        box2: JunctionBox,
    ): Double =
        sqrt(
            ((box2.x - box1.x).toDouble()).pow(2.0) + ((box2.y - box1.y).toDouble()).pow(2.0) +
                ((box2.z - box1.z).toDouble()).pow(2.0),
        )
}
