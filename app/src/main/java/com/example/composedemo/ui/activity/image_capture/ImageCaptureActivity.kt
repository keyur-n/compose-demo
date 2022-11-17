package com.example.composedemo.ui.activity.image_capture

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
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
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.activity.image_picker.ImagePickerActivity
import com.example.composedemo.ui.activity.image_picker.PickImageFromGallery
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.RegularText

class ImageCaptureActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme() {
                Surface {
                    CaptureImage()
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ImageCaptureActivity::class.java))
        }
    }
}




@Composable
fun CaptureImage(){
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) {
        bitmap=it
    }
    Column {
        Button(onClick = {
            launcher.launch()
        }) {
            RegularText(text = "Capture Image from camera")
        }
        Spacer(modifier = Modifier.height(20.dp))
        bitmap?.let {
            Image(bitmap = bitmap?.asImageBitmap()!!, contentDescription = null, modifier = Modifier.size(200.dp))
        }
    }
}
