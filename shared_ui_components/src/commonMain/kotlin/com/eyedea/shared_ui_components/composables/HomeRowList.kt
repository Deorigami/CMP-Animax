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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.eyedea.shared_ui_components.style.Colors
import com.eyedea.shared_ui_components.style.bodyMediumSemibold
import com.eyedea.shared_ui_components.style.bodyXSmallSemiBold
import com.eyedea.shared_ui_components.style.h2Bold
import com.eyedea.shared_ui_components.style.h5Bold
import com.eyedea.shared_ui_components.util.callback
import com.eyedea.shared_ui_components.util.scaledClickable
import com.seiko.imageloader.rememberAsyncImagePainter
import com.seiko.imageloader.rememberImagePainter
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun HomeRowList(
    headerTitle: String = "Top Hits Anime",
    modifier: Modifier = Modifier,
    list: List<HomeRowListData>,
    onRightHeaderPressed: callback = {},
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text(headerTitle, style = h5Bold())
            Text("See All", style = bodyMediumSemibold().copy(Colors.primary500()), modifier = Modifier.scaledClickable { onRightHeaderPressed.invoke() })
        }
        LazyRow(
            modifier = Modifier.padding(top = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 24.dp)
        ) {
            items(list){
                HomeRowListCardItem(data = it)
            }
        }
    }
}

data class HomeRowListData(
    val image: String,
    val rating: String,
    val index: String
)

@Composable
fun HomeRowListCardItem(
    modifier: Modifier = Modifier,
    data: HomeRowListData,
    onCardPressed: callback = {}
){
    Box(modifier = modifier.height(200.dp).width(150.dp).clip(RoundedCornerShape(12.dp)).scaledClickable { onCardPressed.invoke() }) {
        val image = rememberImagePainter(data.image)
        Image(image, "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        Text(data.rating, modifier = Modifier.padding(12.dp).clip(RoundedCornerShape(6.dp)).background(Colors.primary500()).align(Alignment.TopStart).padding(vertical = 6.dp, horizontal = 10.dp), style = bodyXSmallSemiBold())
        Text(data.index, modifier = Modifier.padding(12.dp).align(Alignment.BottomStart), style = h2Bold())
    }
}