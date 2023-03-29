package org.sabaini.thrones.core.navigation

import androidx.navigation.NavGraphBuilder

interface NavigationFactory {
    fun create(builder: NavGraphBuilder, onAppBarState: (AppBarState) -> Unit)
}
