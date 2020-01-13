package console

import gasStation.Car
import gasStation.GasType
import gasStation.Station

class ConsoleIO {
    fun startImitation() {
        val station = Station()
        while (true) {
            val car = createCar()
            if (car == null) {
                println("ConsoleIO.createCar(): Водитель, пораженный своим невежеством в типах топлива, уехал в слезах")
                continue
            }
            station.refueling(car)
        }
    }

    private fun createCar(): Car? {
        println("ConsoleIO.createCar(): Добавление новой машины")
        val type = inputInt("Тип топлива: ")
        val request = inputInt("Количество:  ")
        val gasType = GasType.values().find { it.octane == type } ?: return null

        return Car(gasType, request)
    }

    private fun inputInt(title: String): Int {
        while (true) {
            print(title)
            val value = readLine()?.toIntOrNull()
            if (value != null) return value
        }
    }
}