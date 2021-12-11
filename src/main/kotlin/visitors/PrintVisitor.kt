package visitors

import tokens.Token

abstract class PrintVisitor(tokens: List<Token>) : TokenVisitor(tokens) {
    fun printTokens() {
        tokens.forEach { it.accept(this) }
        printTokensImpl()
    }

    protected abstract fun printTokensImpl()
}