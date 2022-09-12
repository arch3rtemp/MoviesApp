package com.arch3rtemp.android.moviesapp.util

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters

@RunWith(Enclosed::class)
class PasswordValidatorTest {

    @RunWith(Parameterized::class)
    class ValidTest() {

        @Parameter(0)
        lateinit var validString: String

        @Test
        fun testValid() {
            assertTrue(PasswordValidator.isValid(validString))
        }

        companion object {
            @JvmStatic
            @Parameters
            fun data(): Collection<Any> {
                return listOf(
                    "AAAbbbccc@123",
                    "Hello world$123",
                    "A!@#&()–a1",               // test punctuation part 1
                    "A[{}]:;',?/*a1",           // test punctuation part 2
                    "A~$^+=<>a1",               // test symbols
                    "0123456789${'$'}abcdefgAB",     // test 20 chars
                    "123Aa${'$'}Aa",
                    "java REGEX 123",
                    "java REGEX 123 %"
                )
            }
        }
    }

    @RunWith(Parameterized::class)
    class InvalidTest {

        @Parameter(0)
        lateinit var invalidString: String

        @Test
        fun testInvalid() {
            assertFalse(PasswordValidator.isValid(invalidString))
        }

        companion object {
            // At least
            // one lowercase character,
            // one uppercase character,
            // one digit
            // and length between 8 to 20.
            @JvmStatic
            @Parameters
            fun data(): Collection<Any> {
                return listOf(
                    "12345678",                 // invalid, only digit
                    "abcdefgh",                 // invalid, only lowercase
                    "ABCDEFGH",                 // invalid, only uppercase
                    "abc123$$$",                // invalid, at least one uppercase
                    "ABC123$$$",                // invalid, at least one lowercase
                    "ABC$$$$$$",                // invalid, at least one digit
                    "________",                 // invalid
                    "--------",                 // invalid
                    " ",                        // empty
                    ""
                )
            }
        }
    }
}