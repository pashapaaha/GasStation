import gasStation.Car
import gasStation.GasType
import gasStation.Terminal

fun main() {
    val terminals = GasType.values().map { Terminal(1000, it) }
    while(true) {
        val car = addCar() ?: return
        val isSuccess = terminals.find { car.gasType == it.gasType }!!.service(car)
        if (isSuccess) {
            println("Обслуживание завершилось успешно\n~~~~~~~~~~~~~~~~")
        } else {
            println("Морда клиента очень грустная\n~~~~~~~~~~~~~~~~")
        }
    }
}

fun addCar(): Car? {
    println("###")
    val type = inputInt("Тип топлива: ")
    val request = inputInt("Количество:  ")
    val gasType = GasType.values().find { it.octane == type } ?: return null

    return Car(gasType, request)
}

fun inputInt(title: String): Int {
    while(true) {
        print(title)
        val value = readLine()?.toIntOrNull()
        if (value != null) return value
    }
}