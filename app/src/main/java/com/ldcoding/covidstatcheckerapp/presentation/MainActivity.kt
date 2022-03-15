package com.ldcoding.covidstatcheckerapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ldcoding.covidstatcheckerapp.presentation.bottom_navigation.BottomNavItem
import com.ldcoding.covidstatcheckerapp.presentation.bottom_navigation.BottomNavigationBar
import com.ldcoding.covidstatcheckerapp.presentation.countries_list.CountryListScreen
import com.ldcoding.covidstatcheckerapp.presentation.country_details.CountryDetailScreen
import com.ldcoding.covidstatcheckerapp.presentation.ui.theme.CovidStatCheckerAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CovidStatCheckerAppTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    "Home",
                                    Screen.CountriesListScreen.route,
                                    Icons.Default.Home
                                ),
                                BottomNavItem(
                                    "About",
                                    Screen.AboutScreen.route,
                                    Icons.Default.Share
                                )
                            ), navController = navController, onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }) {
                        Navigation(navHostController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = "countries") {
        composable(Screen.CountriesListScreen.route) {
            CountryListScreen(navController = navHostController)
        }
        composable(route = Screen.CountryDetailScreen.route + "/{countryName}") {
            CountryDetailScreen()
        }
        composable(Screen.AboutScreen.route) {
            CountryDetailScreen()
        }
    }
}