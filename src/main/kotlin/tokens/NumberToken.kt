package tokens

import visitors.TokenVisitor

data class NumberToken(val value: Double) : Token {
    constructor(x: Number) : this(x.toDouble())

    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }
}