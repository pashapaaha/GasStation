package gasStation

class Station(capacity: Int = 1000) {
    private val terminals = GasType.values().map { Terminal(capacity, it) }

    fun refueling(car: Car) : Boolean {
        val isSuccess = terminals.find { car.gasType == it.gasType }!!.service(car)
        val result = if(isSuccess) "Обслуживание завершилось успешно" else "Клиент остался недоволен"
        println(result)
        return isSuccess
    }

    fun print() {
        terminals.forEach(::println)
    }
}