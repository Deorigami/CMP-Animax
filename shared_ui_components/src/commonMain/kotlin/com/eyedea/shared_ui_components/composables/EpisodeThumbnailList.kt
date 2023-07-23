package com.eyedea.shared_ui_components.composables

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.eyedea.shared_ui_components.Res
import com.eyedea.shared_ui_components.style.Colors
import com.eyedea.shared_ui_components.style.bodyMediumSemiBold
import com.eyedea.shared_ui_components.style.bodySmallMedium
import com.eyedea.shared_ui_components.style.h6Bold
import com.eyedea.shared_ui_components.util.generateImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.model.ImageResult
import com.seiko.imageloader.rememberImageAction
import com.seiko.imageloader.rememberImagePainter
import com.seiko.imageloader.rememberImageResultPainter
import dev.icerock.moko.resources.compose.painterResource
import io.github.aakira.napier.Napier

@Composable
fun EpisodeThumbnail(
    modifier: Modifier = Modifier,
    data : EpisodeThumbnailData
) {
    Box(modifier = modifier.height(113.dp).width(150.dp).clip(RoundedCornerShape(10.dp))){
        val image = rememberImagePainter(data.imageUrl, errorPainter = { ColorPainter(Colors.greyScale500()) })
        Image(painter = image, modifier = Modifier.fillMaxSize().drawWithCache {
            onDrawWithContent {
                drawContent()
                drawRect(
                    Brush.verticalGradient(
                        0.5f to Color.Black.copy(alpha=0F),
                        1F to Color.Black.copy(alpha = 0.75f)
                    ))
            }
        }, contentDescription = "", contentScale = ContentScale.Crop)
        Image(painter = painterResource(Res.images.ic_play), "", modifier = Modifier.align(Alignment.Center))
        Text(data.title, style = bodySmallMedium(), modifier = Modifier.padding(12.dp).align(Alignment.BottomStart), maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

data class EpisodeThumbnailData(
    val imageUrl : String,
    val title : String
)

@Composable
fun EpisodeThumbnailList(
    modifier: Modifier = Modifier,
    list: List<EpisodeThumbnailData> = emptyList()
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), verticalAlignment = Alignment.CenterVertically){
            Text("Episodes", modifier = Modifier.weight(1f), style = h6Bold())
            Text("Season 2", style = bodyMediumSemiBold().copy(Colors.primary500()), modifier = Modifier.padding(end = 4.dp))
            Image(painter = painterResource(Res.images.ic_simple_arrow_right), modifier = Modifier.rotate(90f), contentDescription = "")
        }
        LazyRow(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(horizontal = 24.dp)) {
            items(list) {
                EpisodeThumbnail(data = it)
            }
        }
    }
}