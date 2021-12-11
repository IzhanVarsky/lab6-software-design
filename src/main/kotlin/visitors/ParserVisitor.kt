package visitors

import tokens.*

class ParserVisitor(tokens: List<Token>) : TokenVisitor(tokens) {
    private val result: MutableList<Token> = mutableListOf()
    private val operationStack: MutableList<Token> = mutableListOf()

    fun parseTokens(): List<Token> {
        tokens.forEach { it.accept(this) }
        while (operationStack.isNotEmpty()) {
            val last = operationStack.last()
            if (last is Brace) {
                throw IllegalStateException("Excessive brace ${last.type} found")
            }
            moveLastTokenToResult()
        }
        return result
    }

    override fun visit(token: NumberToken) {
        result.add(token)
    }

    override fun visit(token: Brace) {
        if (token.isOpenBrace()) {
            operationStack.add(token)
            return
        }
        while (true) {
            val last = operationStack.lastOrNull()
                ?: throw IllegalStateException("Excessive close brace found")

            if (last == Brace.OPEN_BRACE) {
                operationStack.removeLast()
                return
            }

            moveLastTokenToResult()
        }
    }

    override fun visit(token: Operation) {
        while (true) {
            val last = operationStack.lastOrNull() ?: break
            if (last is Operation && last hasNotLessPriorityThan token) {
                moveLastTokenToResult()
            } else {
                break
            }
        }
        operationStack.add(token)
    }

    private fun moveLastTokenToResult() {
        result.add(operationStack.removeLast())
    }
}