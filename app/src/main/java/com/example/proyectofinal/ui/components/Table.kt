package com.example.proyectofinal.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyectofinal.data.model.NewsModel
import com.example.proyectofinal.app.TableColumn
import com.example.proyectofinal.ui.theme.Purple200
import com.example.proyectofinal.ui.theme.Purple500
import com.example.proyectofinal.ui.theme.Purple700

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun RowScope.Cell(
    text: String,
    weight: Float,
    textColor: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Normal,
    action: () -> Unit = {}
) {
    Text(
        text = if(text.length >= 40)  text.substring(0, 40) + "..." else text,
        Modifier
            .fillMaxHeight(1f)
            .border(0.5.dp, Color.hsl(0f, 0f, 0.8f))
            .weight(weight)
            .padding(8.dp)
            .clickable(enabled = true) { action() },
        color = textColor,
        fontWeight = fontWeight,
    )
}

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun Table(columns: List<TableColumn>, data: List<NewsModel>, onRowClick: (NewsModel) -> Unit = {}) {
    LazyColumn(modifier = Modifier
        .padding(14.dp)
        .fillMaxSize()) {
        item() {
            Row(modifier = Modifier.background(Purple500)) {
                columns.forEach { column ->
                    Cell(text = column.header, weight = column.columnSizeFraction, textColor = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
        items(data) { data ->
            Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                    Cell(text = data.title,
                        weight = columns[0].columnSizeFraction,
                        textColor = Color.Black,
                        fontWeight = FontWeight.Normal,
                        action = {
                            Log.d("Table", "Click on ${data.title}")
                            onRowClick(data)
                        }
                    )
                    Cell(text = data.newsSite, weight = columns[1].columnSizeFraction)
            }
        }
    }
}

@Preview
@Composable
fun PreviewTable() {
    val newsModelDemos = listOf<NewsModel>(
        NewsModel(
            title = "Title 1", summary = "Description 1",
            newsSite = "News Site 1", publishedAt = "Published At 1",
            featured = true,
            url = "https://www.google.com", imageUrl = "https://www.google.com",
            id = "2"
        ),
    )
    val columns = listOf(
        TableColumn("Canal", 0.3f),
        TableColumn("Título", 0.7f)
    )
    Scaffold {
        Table(columns = columns, newsModelDemos)
    }
}