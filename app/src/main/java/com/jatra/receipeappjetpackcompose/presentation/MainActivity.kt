package com.jatra.receipeappjetpackcompose.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.jatra.receipeappjetpackcompose.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
//        setContent {
//            ColumnButton()
//        }


    }

    @Preview
    @Composable
    fun ColumnButton() {
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = Color(0xFFF2F2F2))
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_river),
                contentDescription = "Happy Meal",
                modifier = Modifier.height(200.dp),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5f),
                    contentAlignment = Alignment.CenterStart

                ) {
                    Text(
                        text = "Happy Meal",
                        style = TextStyle(fontSize = 26.sp)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(0.5f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = "$5.99",
                        style = TextStyle(fontSize = 17.sp)
                    )
                }

            }

            Text(
                text = "8000 Calories",
                style = TextStyle(fontSize = 17.sp),
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Apply")
            }
        }
    }
}