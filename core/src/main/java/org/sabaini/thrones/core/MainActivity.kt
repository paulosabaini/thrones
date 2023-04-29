package org.sabaini.thrones.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sabaini.thrones.core.extensions.collectWithLifecycle
import org.sabaini.thrones.core.navigation.AppBarState
import org.sabaini.thrones.core.navigation.NavigationFactory
import org.sabaini.thrones.core.navigation.NavigationHost
import org.sabaini.thrones.core.navigation.NavigationManager
import org.sabaini.thrones.core.ui.ThronesTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThronesTheme {
                val navController = rememberNavController()
                var appBarState by remember { mutableStateOf(AppBarState()) }

                Scaffold(
                    topBar = { MainTopAppBar(appBarState) { navController.navigateUp() } },
                ) {
                    NavigationHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        factories = navigationFactories,
                    ) { newAppBarState ->
                        appBarState = newAppBarState
                    }
                }

                navigationManager
                    .navigationEvent
                    .collectWithLifecycle(
                        key = navController,
                    ) {
                        navController.navigate(it.destination, it.navOptions)
                    }
            }
        }
    }
}

@Composable
private fun MainTopAppBar(appBarState: AppBarState, navigateUp: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = appBarState.title,
                fontWeight = FontWeight.Medium,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            if (appBarState.showNavigationIcon) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "Back navigation",
                    )
                }
            }
        },
        actions = {
            appBarState.actions?.invoke(this)
        },
    )
}
