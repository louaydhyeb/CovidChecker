package com.ldcoding.covidstatcheckerapp.presentation.country_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.ldcoding.covidstatcheckerapp.data.remote.dto.CountriesDto

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CountryDetailScreen(
    viewModel: CountryViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.country?.let { country ->
            LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(20.dp)) {
                item {
                    FlagCountryCard(country = country)
                    CountryStats("Total Cases", country.cases.toString(), Color.White)
                    CountryStats("Today Cases", country.todayCases.toString(), Color.White)
                    CountryStats("Total Deaths", country.todayDeaths.toString(), Color.Red)
                    CountryStats("Today Deaths", country.todayDeaths.toString(), Color.Red)
                    CountryStats("Total Recovered", country.recovered.toString(), Color.Green)
                    CountryStats("Total Active", country.active.toString(), Color.Red)
                    CountryStats("Total Critical", country.critical.toString(), Color.Red)
                }
            }
        }
    }

    if (state.error.isNotBlank()) {
        Text(
            text = state.error,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FlagCountryCard(country: CountriesDto) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(country.countryInfo.flag),
            contentDescription = "Flag Tunisia",
            modifier = Modifier.size(128.dp)
        )
        Column {
            Text(
                country.country,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(country.continent, modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp))
        }
    }
}

@Composable
fun CountryStats(statName: String, countryStat: String, color: Color = Color.White) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(0.dp, 16.dp, 0.dp, 0.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                statName,
                style = MaterialTheme.typography.h2,
                //modifier = Modifier.padding(16.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(countryStat, color = color)
        }
    }
}