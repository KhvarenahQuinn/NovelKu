package com.reanlayungsukmo.novelku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.reanlayungsukmo.novelku.data.DataSource
import com.reanlayungsukmo.novelku.model.Topic
import com.reanlayungsukmo.novelku.ui.theme.NovelKuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NovelKuTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(), // menambahkan padding untuk menghindari tumpang tindih dengan status bar
                    color = MaterialTheme.colorScheme.background
                ) {
                    NovelGrid( // menambahkan padding secara konsisten di sekitar grid
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small),
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun NovelGrid(modifier: Modifier = Modifier) {
    // Variabel state untuk melacak jumlah kolom
    var columns by remember { mutableStateOf(2) } // Mulai dengan 2 kolom

    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns), // Menggunakan variabel state untuk kolom
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
            modifier = Modifier
                .weight(1f) // Memungkinkan grid untuk mengembang
        ) {
            items(DataSource.topics) { topic ->
                NovelCard(topic)
            }
        }

        // Menempatkan tombol di bawah grid
        FilledTonalButtonExample(
            onClick = {
                // Mengubah jumlah kolom saat tombol ditekan
                columns = if (columns == 2) 1 else 2 // Beralih antara 1 dan 2 kolom
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // Memusatkan tombol secara horizontal
                .padding(dimensionResource(R.dimen.padding_small))
                .size(width = 150.dp, height = 50.dp) // Mengatur lebar dan tinggi
        )
    }
}

@Composable //button untuk mengubah UI dari 2 kolom menjadi 1 kolom pada Grid
fun FilledTonalButtonExample(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FilledTonalButton(onClick = onClick, modifier = modifier) {
        Text("Change UI")
    }
}

@Composable
fun NovelCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row {
            Box {
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .padding(10.dp)
                        .size(width = 100.dp, height = 150.dp)
//                        .aspectRatio(1f)
                    ,
                    contentScale = ContentScale.Crop
                )
            }

            Column {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2, // Membatasi teks maksimal 2 baris
                    overflow = TextOverflow.Ellipsis, // Menambahkan titik-titik jika teks melebihi 2 baris
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    ) // Menambahkan padding di sekitar teks
                )
                Text(
                    text = stringResource(id = topic.author),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis, // Menambahkan titik-titik jika teks melebihi 2 baris
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_test),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    ) // Menambahkan padding di sekitar teks
                )
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(
//                        painter = painterResource(R.drawable.ic_grain), // Menampilkan ikon untuk kursus di samping jumlah kursus
//                        contentDescription = null,
//                        modifier = Modifier
//                            .padding(start = dimensionResource(R.dimen.padding_medium))
//                    )
//                    Text(
//                        text = topic.availableCourses.toString(), // Menampilkan jumlah kursus yang tersedia
//                        style = MaterialTheme.typography.labelMedium,
//                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
//                    )
//                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NovelPreview() {
    NovelKuTheme {
//        val topic = Topic(R.string.title1, R.string.author1, R.drawable.a)
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            NovelCard(topic = topic)
//        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(), // Menambahkan padding untuk menghindari tumpang tindih dengan status bar
            color = MaterialTheme.colorScheme.background
        ) {
            NovelGrid( // Menambahkan padding secara konsisten di sekitar grid
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_small),
                    top = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_small),
                )
            )
        }
    }
}
