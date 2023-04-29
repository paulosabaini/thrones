package org.sabaini.thrones.core.navigation

import androidx.navigation.NamedNavArgument
import app.cash.turbine.test
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NavigationManagerTest {
    private val testScope = TestScope()

    private lateinit var objectUnderTest: NavigationManager

    @BeforeEach
    fun setUp() {
        setUpNavigationManager()
    }

    @Test
    fun `should preserve navigation command properties`() = testScope.runTest {
        // Given
        val testNavigationCommands = generateTestNavigationCommands(1)
        val testNavigationCommand = testNavigationCommands.first()

        // When
        objectUnderTest.navigate(testNavigationCommand)

        // Then
        objectUnderTest.navigationEvent.test {
            val actualItem = awaitItem()

            assertEquals(
                expected = testNavigationCommand.destination,
                actual = actualItem.destination,
            )

            assertEquals(
                expected = testNavigationCommand.navOptions,
                actual = actualItem.navOptions,
            )
        }
    }

    @Test
    fun `should collect all navigation commands in order`() = testScope.runTest {
        // Given
        val testNavigationCommands = generateTestNavigationCommands(5)

        // When
        for (testCommand in testNavigationCommands) {
            objectUnderTest.navigate(testCommand)
        }

        // Then
        objectUnderTest.navigationEvent.test {
            assertEquals(
                expected = "testDestination0",
                actual = awaitItem().destination,
            )

            assertEquals(
                expected = "testDestination1",
                actual = awaitItem().destination,
            )

            assertEquals(
                expected = "testDestination2",
                actual = awaitItem().destination,
            )

            assertEquals(
                expected = "testDestination3",
                actual = awaitItem().destination,
            )

            assertEquals(
                expected = "testDestination4",
                actual = awaitItem().destination,
            )
        }
    }

    private fun generateTestNavigationCommands(number: Int): List<NavigationCommand> {
        return List(number) {
            object : NavigationCommand {
                override val destination = "testDestination$it"

                override val arguments = emptyList<NamedNavArgument>()
            }
        }
    }

    private fun setUpNavigationManager() {
        objectUnderTest = NavigationManager(testScope)
    }
}
