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
        println("Терминал $gasType начал обслуживание машины $car")
        attempt(car)
        return when (state) {
            WORKING -> workingHandler(car)
            REFUELING -> refuelingHandler()
            DAMAGE -> damageHandler()
        }
    }

    private fun attempt(car: Car) {
        if (car.request <= 0 || (0..20).random() == 20) {
            state = DAMAGE
            println("  Водитель спровоцировал аварию на терминале!")
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

    private fun refuelingHandler(): Boolean {
        println("Terminal.refuelingHandler(): Терминал $gasType на дозаправке")
        waitForTimer(WORKING)
        return false
    }

    private fun damageHandler(): Boolean {
        println("Terminal.refuelingHandler(): Терминал $gasType в состоянии ремонта")
        waitForTimer(REFUELING)
        return false
    }

    private fun waitForTimer(newState: TerminalState) {
        if (timer == 0) {
            fill = capacity
            state = newState
        }
        timer--
    }

    override fun toString(): String {
        return """
## Колонка              АИ${gasType.octane}
## Вместимость бака:    $capacity(мин. $minFill) л.
## Текущее заполнение:  $fill
## Состояние:           $state
#########################################
        """.trimIndent()
    }
}