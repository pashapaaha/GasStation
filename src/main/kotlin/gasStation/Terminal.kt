package gasStation

import gasStation.TerminalState.*

class Terminal(
    private val capacity: Int,
    val gasType: GasType,
    private var fill: Int = capacity
) {
    private val minFill: Int = capacity / 20

    private var timer = 0
    private var state: TerminalState = WORKING
        set(value) {
            when (value) {
                REFUELING -> timer = 2
                DAMAGE -> timer = 4
            }
            field = value
        }

    fun service(car: Car): Boolean {
        println("Terminal.service(): Терминал $gasType начал обслуживание машины $car")
        return when (state) {
            WORKING -> workingHandler(car)
            REFUELING -> refuelingHandler()
            DAMAGE -> damageHandler()
        }
    }

    private fun workingHandler(car: Car): Boolean {
        if (car.request > fill) return false
        fill -= car.request
        if (fill <= minFill) {
            state = REFUELING
        }
        return true
    }

    private fun refuelingHandler(newState: TerminalState = WORKING): Boolean {
        if (timer == 0) {
            fill = capacity
            state = newState
        }
        timer--
        return false
    }

    private fun damageHandler(): Boolean {
        return refuelingHandler(REFUELING)
    }
}