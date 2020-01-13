package gasStation

class Station(capacity: Int = 1000) {
    private val terminals = GasType.values().map { Terminal(capacity, it) }

    fun refueling(car: Car) : Boolean {
        val isSuccess = terminals.find { car.gasType == it.gasType }!!.service(car)
        if (isSuccess) {
            println("Обслуживание завершилось успешно\n~~~~~~~~~~~~~~~~")
        } else {
            println("Морда клиента очень грустная\n~~~~~~~~~~~~~~~~")
        }
        return isSuccess
    }
}