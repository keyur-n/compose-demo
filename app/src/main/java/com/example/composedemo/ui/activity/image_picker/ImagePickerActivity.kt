package com.example.composedemo.ui.activity.image_picker

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.RegularText

class ImagePickerActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme() {
                Surface {
                    PickImageFromGallery()
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ImagePickerActivity::class.java))
        }
    }
}

@Composable
fun PickImageFromGallery(){
    val context= LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = {
        imageUri=it
    })
    Column {
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            RegularText(text = "Pick Image from gallery")
        }
        Spacer(modifier = Modifier.height(20.dp))
        imageUri?.let {
            bitmap=if (Build.VERSION.SDK_INT<28){
                MediaStore.Images.Media.getBitmap(context.contentResolver,it)
            }else{
                val soruce=ImageDecoder.createSource(context.contentResolver,it)
                ImageDecoder.decodeBitmap(soruce)
            }
            Image(bitmap = bitmap?.asImageBitmap()!!, contentDescription = null, modifier = Modifier.size(200.dp))
        }

    }
}

@Preview
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        PickImageFromGallery()
    }
}