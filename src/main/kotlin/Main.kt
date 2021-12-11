import tokens.Brace
import tokens.BraceType.*
import tokens.NumberToken
import tokens.Operation
import tokens.OperationType.*

fun main() {
    val c = Calculator()
    val expr = listOf(
        NumberToken(3),
        Operation(ADD),
        NumberToken(4),
        Operation(MUL),
        NumberToken(2),
        Operation(DIV),
        Brace(OPEN),
        NumberToken(1),
        Operation(SUB),
        NumberToken(5),
        Brace(CLOSE),
    )
    c.calculate(expr)
    c.calculate("(23 + 10) * 5 - 3 * (32 + 5) * (10 - 4 * 5) + 8 / 2")
}
