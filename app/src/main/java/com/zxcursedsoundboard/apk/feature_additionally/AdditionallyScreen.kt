package com.zxcursedsoundboard.apk.feature_additionally

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.zxcursedsoundboard.apk.R

@Composable
fun AdditionallyScreen(
    onNavigateToCat: () -> Unit,
    onNavigateToZxcursedBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xCB0B283F)),
    ) {
        ListItem(colors = ListItemDefaults.colors(Color.Transparent), modifier = Modifier
            .clickable {
                onNavigateToCat()
            }, headlineContent = {
            Text(text = stringResource(id = R.string.cat))
        }, leadingContent = {
            Card(shape = MaterialTheme.shapes.large) {
                Image(
                    painter = painterResource(id = R.drawable.nenado),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            }
        })
        ListItem(colors = ListItemDefaults.colors(Color.Transparent), modifier = Modifier
            .clickable {
                onNavigateToZxcursedBack()
            }, headlineContent = {
            Text(text = stringResource(id = R.string.zxcursed_in_back))
        }, leadingContent = {
            Card(shape = MaterialTheme.shapes.large) {
                Image(
                    painter = painterResource(id = R.drawable.photo7),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            }
        })
    }
}