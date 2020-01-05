package gasStation

class Terminal(
    private val capacity: Int,
    val gasType: GasType,
    var state: TerminalState = TerminalState.WORKING,
    var fill: Int = capacity
) {
    val minFill: Int = capacity / 10

    fun service(car: Car): Boolean {
        println("\nTerminal: service(). start service ~~~~~~~~~~~~~~~~~~~~~~~")
        if(car.request > fill) {
            println("Terminal: service(). Gas is not enough")
            return false
        }

        println("Terminal: service(). Old fill is $fill")
        fill -= car.request
        println("Terminal: service(). New fill is $fill")

        println("Terminal: service(). Finish service ~~~~~~~~~~~~~~~~~~~~~~~\n")
        return true
    }
}