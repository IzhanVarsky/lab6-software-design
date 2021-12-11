import tokens.*

class Tokenizer(private val expression: String) {
    private var curState: State = StartState()
    private val tokens: MutableList<Token> = mutableListOf()

    fun tokenize(): List<Token> {
        expression.forEach { curState.processChar(it) }
        curState.processEof()
        return tokens
    }

    private abstract inner class State {
        abstract fun processChar(c: Char)

        open fun processEof() {}

        protected fun createToken(token: Token) {
            tokens.add(token)
        }
    }

    private inner class StartState : State() {
        override fun processChar(c: Char) {
            OperationType.getOperation(c)?.let {
                createToken(Operation(it))
                return
            }

            BraceType.getBrace(c)?.let {
                createToken(Brace(it))
                return
            }

            if (c.isDigit()) {
                curState = NumberState()
                curState.processChar(c)
            } else if (!c.isWhitespace()) {
                throw IllegalArgumentException("Unknown char: `$c`.")
            }
        }
    }

    private inner class NumberState : State() {
        private val number = StringBuilder()

        override fun processChar(c: Char) {
            if (c.isDigit()) {
                number.append(c)
            } else {
                flushNumber()
                curState = StartState()
                curState.processChar(c)
            }
        }

        override fun processEof() {
            flushNumber()
        }

        private fun flushNumber() {
            val parsed = number.toString().toDoubleOrNull()
                ?: throw IllegalArgumentException("$number is not a double number.")
            createToken(NumberToken(parsed))
        }
    }
}