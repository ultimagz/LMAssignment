package gz.tar.ultimagz.lmassignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import gz.tar.ultimagz.lmassignment.presentation.coinlist.CoinListScreen
import gz.tar.ultimagz.lmassignment.theme.AppTheme

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "coin_list_screen"
                ) {
                    composable("coin_list_screen") {
                        CoinListScreen(navController = navController)
                    }
                }
            }
        }
    }
}