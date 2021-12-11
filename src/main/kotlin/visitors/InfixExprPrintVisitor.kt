package visitors

import tokens.Brace
import tokens.NumberToken
import tokens.Operation
import tokens.Token
import java.util.*

class InfixExprPrintVisitor(tokens: List<Token>) : PrintVisitor(tokens) {
    private val sb = StringBuilder()

    override fun visit(token: NumberToken) {
        sb.append(token.value)
    }

    override fun visit(token: Brace) {
        sb.append(token.toName())
    }

    override fun visit(token: Operation) {
        sb.append(" ${token.toName()} ")
    }

    override fun printTokensImpl() {
        println(sb)
    }
}
