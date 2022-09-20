package com.empty.zxcursed_soundboard_compose.pages

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.empty.zxcursed_soundboard_compose.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun Contact() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_person_24),
            contentDescription = "null",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = stringResource(id = R.string.check),
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.check3),
            fontSize = 20.sp,
            color = Color(0xFF2B62C2),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 5.dp)
                .clickable {
                    val mailto = "mailto:vangelnum@gmail.com" +
                            "?cc=" +
                            "&subject=" + Uri.encode("Zxcursed Sounboard")
                    val emailIntent = Intent(Intent.ACTION_SENDTO)
                    emailIntent.data = Uri.parse(mailto)
                    context.startActivity(emailIntent)
                }
        )
        Text(
            text = stringResource(id = R.string.check2),
            fontSize = 20.sp,
            color = Color(0xFF2B62C2),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 5.dp)
                .clickable {
                    val Uri: Uri = Uri.parse("https://vk.com/vangelnum")
                    val browser: Intent = Intent(Intent.ACTION_VIEW, Uri)
                    context.startActivity(browser)
                }
        )
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom) {
        Text(
            text = stringResource(id = R.string.check5),
            fontSize = 20.sp,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.atypdisplaynew)),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.check6),
            fontSize = 20.sp,
            color = Color(0xFF2B62C2),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 5.dp)
                .clickable {
                    val Uri: Uri = Uri.parse("https://www.youtube.com/zxcursed")
                    val browser: Intent = Intent(Intent.ACTION_VIEW, Uri)
                    context.startActivity(browser)
                }
        )
        Text(
            text = stringResource(id = R.string.check7),
            fontSize = 20.sp,
            color = Color(0xFF2B62C2),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 5.dp)
                .clickable {
                    val Uri: Uri = Uri.parse("https://t.me/zxcursed")
                    val browser: Intent = Intent(Intent.ACTION_VIEW, Uri)
                    context.startActivity(browser)
                }
        )
    }


}