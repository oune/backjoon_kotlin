package test.test

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class B9019KtTest {

    @Test
    fun d() {
        assertEquals(Pair(2468, "D") ,test.b3000to9999.d(1234,""))
        assertEquals(Pair(2341, "L") ,test.b3000to9999.l(1234,""))
    }

    @Test
    fun s() {
    }

    @Test
    fun l() {
    }

    @Test
    fun r() {
    }
}