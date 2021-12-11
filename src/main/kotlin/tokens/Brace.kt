package tokens

import visitors.TokenVisitor

data class Brace(val type: BraceType) : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    fun isOpenBrace(): Boolean = BraceType.OPEN == type

    fun toName(): String = type.strName

    companion object {
        val OPEN_BRACE = Brace(BraceType.OPEN)
        val CLOSE_BRACE = Brace(BraceType.CLOSE)
    }
}