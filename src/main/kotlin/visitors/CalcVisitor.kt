package visitors

import tokens.Brace
import tokens.NumberToken
import tokens.Operation
import tokens.Token

class CalcVisitor(tokens: List<Token>) : TokenVisitor(tokens) {
    private val stack: MutableList<Token> = mutableListOf()

    fun calcExpression(): NumberToken {
        tokens.forEach { it.accept(this) }

        if (stack.size != 1) throw IllegalStateException("Found too many operands")

        val res = stack.first()
        if (res !is NumberToken) throw IllegalStateException("Result is not a number")

        return res
    }

    override fun visit(token: NumberToken) {
        stack.add(token)
    }

    override fun visit(token: Brace) {
        throw IllegalArgumentException("Brace ${token.type} found while calculating")
    }

    override fun visit(token: Operation) {
        val v2 = stack.removeLastOrNull()
            ?: throw IllegalStateException("No enough operands for operation: $token")
        val v1 = stack.removeLastOrNull()
            ?: throw IllegalStateException("No enough operands for operation: $token")

        if (v1 !is NumberToken || v2 !is NumberToken) {
            throw IllegalStateException("Operand $v1 or $v2 are not numbers for operation: $token")
        }

        stack.add(token.calc(v1, v2))
    }
}