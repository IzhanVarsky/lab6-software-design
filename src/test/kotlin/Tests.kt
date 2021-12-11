import org.junit.Assert.assertEquals
import org.junit.Test

class Tests {
    @Test
    fun `simple test`() {
        assertEquals(calc("(30 + 2) / 8"), 4.0, 0.0000001)
    }

    @Test
    fun `medium test 1`() {
        val expr = "(23 + 10) * 5 - 3 * (32 + 5) * (10 - 4 * 5) + 8 / 2"
        assertEquals(calc(expr), 1279.0, 0.0000001)
    }

    @Test
    fun `medium test 2`() {
        val expr = "1-2+3+4*5-6/7*8+9*0"
        assertEquals(calc(expr), 15.14285714285, 0.0000001)
    }

    @Test
    fun `with spaces and braces`() {
        val expr = "((23 + 10-3+4-1)) *(((5)))     - (3 * ((32+     5)) * (10 - 4 * 5)   ) + (((8) / (2)))"
        assertEquals(calc(expr), 1279.0, 0.0000001)
    }

    @Test
    fun `positive div by zero`() {
        assertEquals(calc("5 / 0"), Double.POSITIVE_INFINITY, 0.0)
    }

    @Test
    fun `negative div by zero`() {
        assertEquals(calc("(0 - 5) / 0"), Double.NEGATIVE_INFINITY, 0.0)
    }

    @Test(expected = IllegalStateException::class)
    fun `bad braces balance 1`() {
        calc("(0 - 5)) / 5")
    }

    @Test(expected = IllegalStateException::class)
    fun `bad braces balance 2`() {
        calc("(0 - 5) / (5")
    }

    @Test(expected = IllegalStateException::class)
    fun `bad braces balance 3`() {
        calc("(0 - 5 /) 5")
    }

    @Test(expected = IllegalStateException::class)
    fun `too much numbers`() {
        calc("0 1 2 - 5")
    }

    @Test(expected = IllegalStateException::class)
    fun `too much operations`() {
        calc("1 + - + * 4")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `too big number`() {
        calc("${Double.MAX_VALUE}0 / 2")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `unknown symbols`() {
        calc("a * 2")
    }

    companion object {
        private val calculator = Calculator()

        private fun calc(expr: String): Double = calculator.calculate(expr)
    }
}