package gasStation

enum class TerminalState(val title: String) {
    WORKING("Работа"), REFUELING("Дозаправка"), DAMAGE("Авария");

    override fun toString() = title
}
