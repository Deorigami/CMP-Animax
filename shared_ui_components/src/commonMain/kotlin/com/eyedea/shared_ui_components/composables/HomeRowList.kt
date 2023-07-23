package com.eyedea.shared_ui_components.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.eyedea.shared_ui_components.style.Colors
import com.eyedea.shared_ui_components.style.bodyLargeBold
import com.eyedea.shared_ui_components.style.bodyLargeSemiBold
import com.eyedea.shared_ui_components.style.bodyMediumSemiBold
import com.eyedea.shared_ui_components.style.bodyXSmallSemiBold
import com.eyedea.shared_ui_components.style.h5Bold
import com.eyedea.shared_ui_components.util.callback
import com.eyedea.shared_ui_components.util.genericCallback
import com.eyedea.shared_ui_components.util.scaledClickable
import com.seiko.imageloader.rememberImagePainter

@Composable
fun HomeRowList(
    headerTitle: String = "Top Hits Anime",
    modifier: Modifier = Modifier,
    list: List<HomeRowListData>,
    onRightHeaderPressed: callback = {},
    onCardPressed: genericCallback<Int> = {}
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(headerTitle, style = h5Bold())
            Text("See All", style = bodyMediumSemiBold().copy(Colors.primary500()), modifier = Modifier.scaledClickable { onRightHeaderPressed.invoke() })
        }
        LazyRow(
            modifier = Modifier.padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            itemsIndexed(list){index, data ->
                HomeRowListCardItem(data = data, onCardPressed = { onCardPressed.invoke(index) })
            }
        }
    }
}

data class HomeRowListData(
    val image: String,
    val rating: String,
    val title: String,
    val index: String
)

@Composable
fun HomeRowListCardItem(
    modifier: Modifier = Modifier,
    data: HomeRowListData,
    onCardPressed: callback = {}
){
    Box(modifier = modifier.height(200.dp).width(150.dp).clip(RoundedCornerShape(12.dp)).scaledClickable {
        onCardPressed.invoke()
    }) {
        val image = rememberImagePainter(data.image)
        Image(image, "", modifier = Modifier.fillMaxSize().drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(Brush.verticalGradient(
                    0.5f to Color.Black.copy(alpha=0F),
                    1F to Color.Black.copy(alpha = 0.75f)
                ))
            }
        }, contentScale = ContentScale.Crop)
        Text(data.rating, modifier = Modifier.padding(12.dp).clip(RoundedCornerShape(6.dp)).background(Colors.primary500()).align(Alignment.TopStart).padding(vertical = 6.dp, horizontal = 10.dp), style = bodyXSmallSemiBold())
        Text(
            data.title,
            modifier = Modifier.padding(12.dp).align(Alignment.BottomStart),
            style = bodyLargeSemiBold(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}