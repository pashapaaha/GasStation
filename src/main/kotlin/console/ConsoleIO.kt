package console

import gasStation.Car
import gasStation.GasType
import gasStation.Station

class ConsoleIO {

    lateinit var station: Station
    fun startImitation() {
        println("### Начало работы заправочной станции ###")
        station = Station()
        do {
            val car = createCar()
            if (car == null) {
                println("Водитель, пораженный своим невежеством в типах топлива, уехал в слезах")
                continue
            }
            station.refueling(car)
            station.print()
        } while (!canIGetOffEarlier())

        println("### Завершение работы заправочной станции ###")
    }

    private fun createCar(): Car? {
        println("Добавление новой машины")
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

    private fun canIGetOffEarlier(): Boolean {
        print("Можно я сегодня уйду домой пораньше? (y/n) ")
        return readLine() == "y"
    }
}