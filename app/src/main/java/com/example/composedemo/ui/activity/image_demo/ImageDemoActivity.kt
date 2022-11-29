package com.example.composedemo.ui.activity.image_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.R
import com.example.composedemo.ui.activity.snapping_demo.imageList
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin

class ImageDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageDemoScreen(myUrl ="https://img.freepik.com/free-photo/landscape-morning-fog-mountains-with-hot-air-balloons-sunrise_335224-794.jpg?w=1380&t=st=1669374141~exp=1669374741~hmac=9780e3c1bed16dbe2abd0e37a346ee512367a9fd4884b70bb310d126dd31c790")
        }
    }
    companion object{
        fun newIntent(context: Context){
            context.startActivity(Intent(context,ImageDemoActivity::class.java))
        }
    }
}

@Composable
fun ImageDemoScreen(myUrl:String,modifier: Modifier=Modifier){
    var currentImage by remember {
        mutableStateOf(imageList[0])
    }
    Column {

        LazyRow{
            items(imageList){itemImage->
                Image(
                    painter = painterResource(id = itemImage),
                    contentDescription = null,
                    modifier=Modifier.clickable {
                        currentImage=itemImage
                    }.size(200.dp).padding(end = 8.dp),

//        modifier = Modifier.size(200.dp)
                )
            }
        }
        Text(text = "Image with Center Crop")
        com.skydoves.landscapist.glide.GlideImage(
            imageModel =  { currentImage },
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            imageOptions=ImageOptions(contentScale= ContentScale.FillWidth),
            // shows an image with a circular revealed animation.
            component = rememberImageComponent {
                +CircularRevealPlugin(
                    duration = 350
                )
                +PlaceholderPlugin.Loading(painterResource(id = R.drawable.ic_snap_9))
                +PlaceholderPlugin.Failure(painterResource(id = R.drawable.ic_welcome_1))

            },
            modifier = modifier
                .fillMaxSize()
                .padding(top = 16.dp)
                .weight(1f)

        )
    }

}



@Preview(showBackground = true)
@Composable
fun ImageDemoScreenPreview(){
    ImageDemoScreen(myUrl = "https://img.freepik.com/free-photo/landscape-morning-fog-mountains-with-hot-air-balloons-sunrise_335224-794.jpg?w=1380&t=st=1669374141~exp=1669374741~hmac=9780e3c1bed16dbe2abd0e37a346ee512367a9fd4884b70bb310d126dd31c790")
}