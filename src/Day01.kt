fun startWith(s: String): String? {
    if (s[0] in '0'..'9')
        return s[0].toString()
    val list =
        listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    for ((i, value) in list.withIndex()) {
        if (s.startsWith(value)) {
            return (i + 1).toString();
        }
    }
    return null;

}

fun endWith(s: String): String? {
    if (s[s.length-1] in '0'..'9')
        return s[s.length-1].toString()
    val list =
        listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    for ((i, value) in list.withIndex()) {
        if (s.endsWith(value)) {
            return (i + 1).toString();
        }
    }
    return null;

}

fun getNb(s: String): String {

    var nb = "";
    for (i in s.indices) {
        //                println(s[i])
        val oui = startWith(s.substring(i))
        if (oui != null) {
            nb += oui
            break
        }
    }
    for (i in s.length downTo 0) {
        //                println(s[i])
        val oui = endWith(s.substring(0, i))
        if (oui != null) {
            nb += oui
            break
        }
    }
    println(nb)
    return nb
}


fun main() {


    fun part1(input: List<String>): Int {
        var out = 0;
        for (s in input) {

            var nb = getNb(s)
            out += nb.toInt()
        }
        return out
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    val testResult = part1(testInput)
    println("Result test: $testResult")
    check(testResult == 281)

    val input = readInput("Day01")
    println("Result part1: ${part1(input)}")
    println("Result part2: ${part2(input)}")
}
