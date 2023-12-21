import kotlin.time.measureTime

fun main() {

    data class Location(val mapRow: Int, val mapCol: Int, val row: Int, val col: Int)

    var inputSize: Int = 0
    var rowLength : Int = 0
    fun addToSet(
        mapRow: Int,
        mapCol: Int,
        row: Int,
        col: Int,
        set: MutableSet<Location>,
        map: Set<Pair<Int, Int>>
    ) {
        val adjustedRow: Int
        val newMapRow: Int
        if (row < 0) {
            newMapRow = mapRow - 1
            adjustedRow = inputSize - 1
        } else if (row > inputSize - 1) {
            newMapRow = mapRow + 1
            adjustedRow = 0
        } else {
            newMapRow = mapRow
            adjustedRow = row
        }

        val newMapCol: Int
        val adjustedCol: Int
        if (col < 0) {
            newMapCol = mapCol - 1
            adjustedCol = rowLength - 1
        } else if (col > rowLength - 1) {
            newMapCol = mapCol + 1
            adjustedCol = 0
        } else {
            newMapCol = mapCol
            adjustedCol = col
        }


        if (!map.contains(Pair(adjustedRow, adjustedCol)))
            set.add(Location(newMapRow, newMapCol, adjustedRow, adjustedCol))

    }

    fun part1(input: List<String>, totalStep: Int): Int {
        var set = mutableSetOf<Location>() // row , line
        inputSize = input.size
        rowLength = input[0].length
        // find S
        for ((row, s) in input.withIndex()) {
            if (s.contains('S')) {
                set.add(Location(0, 0, row, s.indexOf('S')))
            }
        }
//        println("found s " + set)

        // generate map
        var map = mutableSetOf<Pair<Int, Int>>()
        for ((row, s) in input.withIndex()) {
            for (i in s.indices) {
                if (s[i] == '#')
                    map.add(Pair(row, i))
            }
        }

        // iterate through the steps
        for (i in 1..totalStep) {
            var newSet = mutableSetOf<Location>()
            println("step $i")
            for (p in set) {
                addToSet(p.mapRow, p.mapCol, p.row + 1, p.col, newSet, map)
                addToSet(p.mapRow, p.mapCol, p.row - 1, p.col, newSet, map)
                addToSet(p.mapRow, p.mapCol, p.row, p.col + 1, newSet, map)
                addToSet(p.mapRow, p.mapCol, p.row, p.col - 1, newSet, map)
                set = newSet
//                println(set)
            }
        }
        println(set)
        return set.size // add in doc
    }

    fun assessTest(testInput: List<String>, stepCount: Int, expectedOutput: Int) {
        var testResult = 0

        val time = measureTime {
            testResult = part1(testInput, stepCount)
        }
        println("Result test for $stepCount: $testResult, ${time}")
        check(testResult == expectedOutput)
    }


    fun part2(input: List<String>): Int {
        return input.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day21_test")
    assessTest(testInput, 6, 16)
    assessTest(testInput, 10, 50)
    assessTest(testInput, 50, 1594)
    assessTest(testInput, 5000, 16733044)
//    assessTest(testInput, 16, 6)
//    assessTest(testInput, 16, 6)
//    assessTest(testInput, 16, 6)


    val input = readInput("Day21")
//    println("Result part1: ${part1(input, 64)}")
//    println("Result part2: ${part2(input)}")
}


//20.47