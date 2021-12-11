package tokens

enum class BraceType(val strName: String) {
    OPEN("("), CLOSE(")");

    companion object {
        fun <T> getBrace(c: T): BraceType? =
            values().find { it.strName == c.toString() }
    }
}