package tokens

import visitors.TokenVisitor

data class Operation(private val op: OperationType) : Token {
    infix fun hasNotLessPriorityThan(other: Operation): Boolean =
        op.priority >= other.op.priority

    fun calc(v1: NumberToken, v2: NumberToken): NumberToken =
        NumberToken(op.operation(v1.value, v2.value))

    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    fun toName(): String = op.strName
}
