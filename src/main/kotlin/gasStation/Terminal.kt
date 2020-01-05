package gasStation

class Terminal(
    val capacity: Int,
    val gasType: GasType,
    var fill: Int = capacity
) {
    val minFill: Int = capacity / 10

    fun service(car: Car): Boolean {

        if(car.request > fill) {
            return false
        }

        fill -= car.request

        return true
    }
}