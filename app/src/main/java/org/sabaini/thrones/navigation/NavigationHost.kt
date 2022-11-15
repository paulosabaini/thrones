package org.sabaini.thrones.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.sabaini.thrones.core.navigation.NavigationDirections
import org.sabaini.thrones.core.navigation.NavigationFactory

@Composable
fun NavigationHost(
    navController: NavHostController,
    factories: Set<NavigationFactory>,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationDirections.CharacterList.destination,
        modifier = modifier
    ) {
        factories.forEach {
            it.create(this)
        }
    }
}
