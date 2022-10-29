package org.sabaini.thrones.core.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationManager {
    val navigationEvent: Flow<NavigationCommand>
    fun navigate(command: NavigationCommand)
}
