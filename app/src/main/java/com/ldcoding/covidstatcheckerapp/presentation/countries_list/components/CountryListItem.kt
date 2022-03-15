package com.ldcoding.covidstatcheckerapp.presentation.countries_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.ldcoding.covidstatcheckerapp.domain.model.Country


@Composable
fun CountryListItem(
    country: Country,
    onItemClick: (Country) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable { onItemClick(country) }
            .padding(16.dp)) {
        Text(country.country, color = Color.White, style = MaterialTheme.typography.h5)
        Text("Cases : ${country.cases}", color = Color.White)
        Text(
            "Recovered : ${country.recovered}",
            color = Color.Green
        )
        Text(
            "Deaths : ${country.deaths}",
            color = Color.Red
        )
    }
}