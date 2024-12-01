package com.lloydsbanking.lloydstest

import com.lloydsbanking.lloydstest.utilities.toTitleCase
import org.junit.Assert
import org.junit.Test

class StringExtensionTest {
    @Test
    fun `produces string with title case`() {
        val input = "test"
        val expectedResult = "Test"
        val actualResult = input.toTitleCase()
        Assert.assertEquals(expectedResult, actualResult)
    }
}