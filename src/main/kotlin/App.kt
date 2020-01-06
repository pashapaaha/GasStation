import gasStation.Car
import gasStation.GasType
import gasStation.Terminal

fun main() {
    val terminals = GasType.values().map { Terminal(1000, it) }
    while (true) {
        val car = createCar()
        if (car == null) {
            println(".main(): Водитель, пораженный своим невежеством в типах топлива, уехал в слезах")
            continue
        }
        val isSuccess = terminals.find { car.gasType == it.gasType }!!.service(car)
        if (isSuccess) {
            println("Обслуживание завершилось успешно\n~~~~~~~~~~~~~~~~")
        } else {
            println("Морда клиента очень грустная\n~~~~~~~~~~~~~~~~")
        }
    }
}

fun createCar(): Car? {
    println("###")
    val type = inputInt("Тип топлива: ")
    val request = inputInt("Количество:  ")
    val gasType = GasType.values().find { it.octane == type } ?: return null

    return Car(gasType, request)
}

fun inputInt(title: String): Int {
    while (true) {
        print(title)
        val value = readLine()?.toIntOrNull()
        if (value != null) return value
    }
}