package visitors

import tokens.Brace
import tokens.NumberToken
import tokens.Operation
import tokens.Token
import java.util.*

class PrefixExprPrintVisitor(tokens: List<Token>) : PrintVisitor(tokens) {
    private val sj = StringJoiner(" ")

    override fun visit(token: NumberToken) {
        sj.add(token.value.toString())
    }

    override fun visit(token: Brace) {
        throw IllegalArgumentException("Brace ${token.type} found in prefix notation")
    }

    override fun visit(token: Operation) {
        sj.add(token.toName())
    }

    override fun printTokensImpl() {
        println(sj)
    }
}
