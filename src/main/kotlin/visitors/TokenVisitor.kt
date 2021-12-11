package visitors

import tokens.Brace
import tokens.NumberToken
import tokens.Operation
import tokens.Token

abstract class TokenVisitor(protected val tokens: List<Token>) {
    abstract fun visit(token: NumberToken)
    abstract fun visit(token: Brace)
    abstract fun visit(token: Operation)
}