package tokens

enum class OperationType(
    val priority: Int,
    val operation: (Double, Double) -> Double,
    val strName: String,
//    isLeftAssoc: Boolean
) {
    ADD(0, Double::plus, "+"),
    SUB(0, Double::minus, "-"),
    MUL(1, Double::times, "*"),
    DIV(1, Double::div, "/");

    companion object {
        fun <T> getOperation(c: T): OperationType? =
            values().find { it.strName == c.toString() }
    }
}