import java.io.File


fun main() {
    val lines = File("input.txt").useLines { it.toList() }
    // GameId: Int -> (Red: Int, Green: Int, Blue: Int)
    val games = LinkedHashMap<Int, Triple<Int, Int, Int>>()
    lines.forEach { line ->
        val split = line.split(": ")
        val gameId = split[0].split(" ")[1].toInt()

        var red = 0
        var green = 0
        var blue = 0

        split[1].split("; ").forEach { set ->
            set.split(", ").forEach { color ->
                val number = color.split(" ")[0].toInt()

                if (color.endsWith("red")) {
                    red = red.coerceAtLeast(number)
                } else if (color.endsWith("green")) {
                    green = green.coerceAtLeast(number)
                } else if (color.endsWith("blue")) {
                    blue = blue.coerceAtLeast(number)
                }
            }
        }
        games[gameId] = Triple(red, green, blue);
    }
    val maxRed = 12
    val maxGreen = 13
    val maxBlue = 14
    var partOneSum = 0
    var partTwoSum = 0
    games.forEach { (id, game) ->
        val (red, green, blue) = game
        if (red <= maxRed && green <= maxGreen && blue <= maxBlue)
            partOneSum += id
        partTwoSum += red * green * blue
    }
    println("Total part one -> $partOneSum part two -> $partTwoSum")
}
