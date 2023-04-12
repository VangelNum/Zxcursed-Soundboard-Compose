package com.zxcursedsoundboard.apk.feature_contacts.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zxcursedsoundboard.apk.R

@Composable
fun ContactScreen(contactViewModel: ContactViewModel = viewModel()) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_person_24),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = stringResource(id = R.string.errorContract),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.gmail_vangelnum),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .clickable {
                            contactViewModel.emailSend(context)
                        }
                )
                Text(
                    text = stringResource(id = R.string.vk_com_vangelnum),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .clickable {
                            contactViewModel.goToMyVk(context)
                        }
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.best_dota_rep),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(id = R.string.youtube_zxcursed),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .clickable {
                            contactViewModel.goToYoutubeZxcursed(context)
                        }
                )
                Text(
                    text = stringResource(id = R.string.telegram_zxcursed),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .clickable {
                            contactViewModel.goToTelegramZxcursed(context)
                        }
                )
                Text(
                    text = stringResource(id = R.string.vk_zxcursed),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .clickable {
                            contactViewModel.goToVkZxcursed(context)
                        }
                )
            }
        }
    }

}