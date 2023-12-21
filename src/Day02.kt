import kotlin.math.max

fun main() {

    data class Game(var number: Int, val map: Map<String, Int>)

    fun getGames(input: List<String>): MutableList<Game> {
        val games = mutableListOf<Game>()
        for (s in input) {
            val split = s.split(":")
            val number = split[0].split(" ")[1].toInt()
            println(number)
            val hands = split[1].split(";")
            val map = mutableMapOf<String, Int>()
            for (hand in hands) {
                val nbColours = hand.split(",")
                for (oneColour in nbColours) {
                    val split1 = oneColour.trim().split(' ')
                    val nb = split1[0].trim().toInt()
                    val colour = split1[1]
                    map[colour] = max(map[colour] ?: 0, nb)
                }
            }
            games.add(Game(number, map))
        }
        return games
    }

    fun part1(input: List<String>): Int {
        val games = getGames(input)
        val out =
            games.filter {
                (it.map["red"] ?: 0) <= 12 &&
                        (it.map["green"] ?: 0) <= 13 &&
                        (it.map["blue"] ?: 0) <= 14
            }
                .sumOf { it.number }
        return out;
    }

    fun part2(input: List<String>): Int {
        val games = getGames(input)
        val sum = games.map {
            (it.map["red"] ?: 0) * (it.map["green"] ?: 0) *
                    (it.map["blue"] ?: 0)
        }.onEach { it -> println(it) }
            .sum()

        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val testResult = part1(testInput)
    println("Result test: $testResult")
    check(testResult == 8)

    val input = readInput("Day02")
    println("Result part1: ${part1(input)}")
    println("Result part2: ${part2(input)}")
}
