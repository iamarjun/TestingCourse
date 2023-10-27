package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ShoppingCartTest {

    private lateinit var cart: ShoppingCart

    @BeforeEach
    fun setUp() {
        cart = ShoppingCart()
    }

    @Test
    fun `add multiple products total price sum correct single test`() {
        //Given
        val product = Product(1, "ice", 100.0)
        cart.addProduct(product, 5)

        //Action
        val totalSum = cart.getTotalCost()

        //Assertion
        assertThat(totalSum).isEqualTo(500.0)
    }

    @RepeatedTest(10)
    fun `add multiple products total price sum correct repeated test`() {
        //Given
        val product = Product(1, "ice", 100.0)
        cart.addProduct(product, 5)

        //Action
        val totalSum = cart.getTotalCost()

        //Assertion
        assertThat(totalSum).isEqualTo(500.0)
    }

    @ParameterizedTest
    @ValueSource(
        ints = [9, 8, 7, 6, 5, 4, 3]
    )
    fun `add multiple products total price sum correct parameterized test`(quantity: Int) {
        //Given
        val product = Product(1, "ice", 100.0)
        cart.addProduct(product, quantity)

        //Action
        val totalSum = cart.getTotalCost()

        //Assertion
        assertThat(totalSum).isEqualTo(product.price * quantity)
    }

    @ParameterizedTest
    @CsvSource(
        "1, 100.0",
        "2, 200.0",
        "5, 500.0",
        "0, 0.0",
        "10, 1000.0",
    )
    fun `add multiple products total price sum correct parameterized test`(quantity: Int, totalSum: Double) {
        //Given
        val product = Product(1, "ice", 100.0)
        cart.addProduct(product, quantity)

        //Action
        val totalSum = cart.getTotalCost()

        //Assertion
        assertThat(totalSum).isEqualTo(product.price * quantity)
    }

    @Test
    fun `add product with negative quantity throws exception`() {
        //Given
        val product = Product(1, "ice", 100.0)

        //Assertion
        assertFailure {
            cart.addProduct(product, -5)
        }
    }

    @Test
    fun getTotalCost() {
    }
}