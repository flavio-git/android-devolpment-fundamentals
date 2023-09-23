import java.io.File

fun main() {
    // Variables

    // Mutable variables (var)
    var name: String = "Flavio Vicentini"
    var grade: Number = 9.85
    var isEnrolled: Boolean = true
    var gender: Char = 'M'

    name = "Linus Linux"

    println("$name, $grade, $isEnrolled, $gender")

    // Immutable variables (val)
    val foodName: String = "Hamburguer Vegetariano"
    var price: Number = 25.75
    var isVegan: Boolean = true
    var ingredients: String = "Feijão legumes temperos"

    println("$foodName, $price, $isVegan, $ingredients")

    // Arrays
    val programmingLanguages: Array<String> = arrayOf()
    programmingLanguages[0] = "Java"
    programmingLanguages[1] = "C"
    programmingLanguages[2] = "C++"
    programmingLanguages[3] = "Kotlin"

    val operatingSystem = arrayListOf("Linux", "Windows", "macOS", "BDS", "Android")
    operatingSystem[2] = "Unix"

    // Read a file
    val brazilianFoods: List<BrazilianFood> = generateBrazilianFoodsList()

    // Working with list
    println("Working with list")

    // Size
    println("Quantity of foods: ${brazilianFoods.size}")

    // Filtering foods with price under 20.00
    val foodsUnder20 = brazilianFoods.filter { it.price < 20.00f }
    println("Price < 20: ${foodsUnder20.map { it.name }}")

    // Getting just foods name
    val foodsName = brazilianFoods.map { it.name }
    println("Foods name: $foodsName")

    // Checking if all foods are vegan
    val allFoodsAreVegan: Boolean = brazilianFoods.all { it.isVegan }
    println("All foods vegan: $allFoodsAreVegan")

    // Checking if all foods price are greater than 2.00
    val foodsPriceGreater2 = brazilianFoods.all { it.price > 2.00f }
    println("All foods price greater than 2.00: $foodsPriceGreater2")

    // Find the first food with weight under 50.00
    val firstFoodUnder50 = brazilianFoods.find { it.weight < 50 }
    println("First food with weight under 50: $firstFoodUnder50")

    // Exist any food with name "frango"
    val foodWithChicken = brazilianFoods.any { it.name.lowercase().contains("frango") }
    println("There's chicken: $foodWithChicken")

    // Count number of foods with price greater than 20.oo
    val countFoodGreater20 = brazilianFoods.count { it.price > 20 }
    println("Count foods with price > 20: $countFoodGreater20")

    // Concatenate foods code in one line
    val foodCodes = brazilianFoods.joinToString(separator = " / ") { it.code.toString() }
    println("All foods code: $foodCodes")

    // Sorting foods by price
    val foodSortedByPrice = brazilianFoods.sortedBy { it.price }
    println("Foods sorted by price")
    for (food in foodSortedByPrice){
        println("${food.name} R$ ${food.price}")
    }

}

// Read a txt file and return a mutable list of BrazilianFood
fun generateBrazilianFoodsList(): List<BrazilianFood> {
    val foods: MutableList<BrazilianFood> = mutableListOf()

    val fileName = "foods.txt"
    val fr = File(fileName).bufferedReader()

    var line = fr.readLine()
    while (line != null) {
        val data = line.split(",")
        val food = BrazilianFood(
            data[0],
            data[1].toFloat(),
            data[2].toInt(),
            data[3].toFloat(),
            data[4].toBoolean(),
            data[5],
            )
        foods.add(food)

        line = fr.readLine()
    }

    fr.close()
    return foods
}