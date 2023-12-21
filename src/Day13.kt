fun main() {
    var score = 0;

    fun isLineMirror(v: Int, line: String): Boolean {
        val stack = ArrayDeque<Char>()

        for (i in 0..<v) {
            stack.addLast(line[i])
        }
        var i = v;
        while (stack.isNotEmpty() && i < line.length) {
            val last = stack.removeLast()
            val toCompare = line[i]
            if (toCompare != last)
                return false
            i++
        }
        return true;
    }

    val test = "pollop"
    for (i in 1..<test.length) {
        println(isLineMirror(i, test))
    }


    fun searchVMirror(map: List<String>, v: Int): Boolean {
        for (line in map) {
            if (!isLineMirror(v, line)) {
                return false
            }
        }
        return true;
    }

    fun searchHMirror(map: List<String>, h: Int): Boolean {
        val stack = ArrayDeque<String>()
        for ((i, line) in map.withIndex()) {
            if (i < h) {
                stack.addLast(line)
            } else {
                if (stack.isEmpty()) {
                    return true
                }
                val last = stack.removeLast()
                if (last != line)
                    return false
            }
        }
        return true
    }

    fun findMirror(map: List<String>) {
        for (v in 1..map[0].length) {
            if (searchVMirror(map, v) == true) {
                println("found mirror row $v")

                score += v
            }
        }

        for (h in 1..<map.size) {
            if (searchHMirror(map, h)) {
                println("found mirror line $h")
                score += h * 100
            }
        }

    }

    fun part1(input: List<String>): Int {
        score = 0;
        var map = mutableListOf<String>()
        for ((i, s) in input.withIndex()) {
            if (s.isEmpty() || i == input.size -1) {
                println("check new map")
                findMirror(map)
                map = mutableListOf()
            } else {
                map.add(s)
            }
        }
        return score
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day13_test")
//    val testResult = part1(testInput)
//    println("Result test: $testResult")
//    check(testResult == 405)


//
//    val input = readInput("Day13")
//    println("Result part1: ${part1(input)}")
//    println("Result part2: ${part2(input)}")
}
