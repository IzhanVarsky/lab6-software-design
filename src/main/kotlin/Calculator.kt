import tokens.Token
import visitors.CalcVisitor
import visitors.InfixExprPrintVisitor
import visitors.ParserVisitor
import visitors.PrefixExprPrintVisitor

class Calculator(private val noPrint: Boolean = false) {
    fun calculate(str: String): Double =
        calculate(Tokenizer(str).tokenize())

    fun calculate(tokens: List<Token>): Double {
        if (!noPrint) {
            println("================")
            println("You have entered:")
            InfixExprPrintVisitor(tokens).printTokens()
        }
        val parsedTokens = ParserVisitor(tokens).parseTokens()
        if (!noPrint) {
            println("Reverse Polish notation:")
            PrefixExprPrintVisitor(parsedTokens).printTokens()
        }
        val res = CalcVisitor(parsedTokens).calcExpression().value
        if (!noPrint) {
            println("Result is: $res")
        }
        return res
    }
}