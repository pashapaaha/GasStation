package gasStation

class Terminal(
    val capacity: Int,
    val gasType: GasType,
    var fill: Int = capacity
) {
    val minFill: Int = capacity / 10

    fun service(car: Car): Boolean {



        return true
    }
}