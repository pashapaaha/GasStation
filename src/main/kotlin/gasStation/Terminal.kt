package gasStation

class Terminal(
    private val capacity: Int,
    val gasType: GasType,
    var state: TerminalState = TerminalState.WORKING,
    var fill: Int = capacity
) {
    private val minFill: Int = capacity / 10

    fun service(car: Car): Boolean {
        println("Terminal.service(): Терминал $gasType начал обслуживание машины $car")
        return when(state) {
            TerminalState.WORKING -> workingHandler(car)
            TerminalState.REFUELING -> refuelingHandler()
            TerminalState.DAMAGE -> damageHandler()
        }
    }

    private fun workingHandler(car: Car): Boolean {
        if (car.request > fill) return false
        fill -= car.request
        if (fill <= minFill) {
            state = TerminalState.REFUELING
        }
        return true
    }

    private fun refuelingHandler(): Boolean {
        fill = capacity
        return false
    }

    private fun damageHandler(): Boolean {
        return false
    }
}