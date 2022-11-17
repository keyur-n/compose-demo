package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.composedemo.*
import com.example.composedemo.ui.activity.bottombar.BottombarDemActivity
import com.example.composedemo.ui.activity.bottombar_advance.BottombarDemAdvanceActivity
import com.example.composedemo.ui.activity.bottomsheet_demo.BottomsheetDemoActivity
import com.example.composedemo.ui.activity.checkbox_demo.CheckboxDemoActivity
import com.example.composedemo.ui.activity.dialog_demo.DialogDemoActivity
import com.example.composedemo.ui.activity.edittextdemo.LoginActivity2
import com.example.composedemo.ui.activity.image_capture.ImageCaptureActivity
import com.example.composedemo.ui.activity.image_picker.ImagePickerActivity
import com.example.composedemo.ui.activity.navigation.NavigationActivity
import com.example.composedemo.ui.activity.navigation_voyager.VoyagerNavigationActivity
import com.example.composedemo.ui.activity.radio.RadioButtonDemoActivity
import com.example.composedemo.ui.activity.retrofit_demo.HomeActivity
import com.example.composedemo.ui.activity.textviewdemo.TextViewDemoActivity
import com.example.composedemo.ui.theme.ComposeDemoTheme

class ComposeDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeDemoScreen(names = dataList)
                }
            }
        }
    }
}
private const val EDIT_TEXT_DEMO=1;
private const val EDIT_=2;
sealed class DemoData(val name:String){
    object Textview:DemoData("Textview")
    object Edittext:DemoData("Edittext")
    object Viewpager:DemoData("Viewpager")
    object Retrofit:DemoData("Retrofit API loading and show in List")
    object NavigationDrawer:DemoData("Navigation Drawer")
    object NavigationToOtherScreen:DemoData("Navigation between screens")
    object VoyagerNavigationScreen:DemoData("Navigation between screens by Voyager library")
    object Bottombar:DemoData("Bottombar")
    object BottombarAdvance:DemoData("Bottombar Advance")
    object Bottomsheet:DemoData("Bottomsheet")
    object Radiobutton:DemoData("Radiobutton")
    object Checkbox:DemoData("Checkbox")
    object Dialog:DemoData("Progress Dialog & Alert Dialog")
    object ImagePicker:DemoData("ImagePicker")
    object ImageCapture:DemoData("Image Capture")
}

val dataList = mutableListOf(
    DemoData.Textview,
    DemoData.Edittext,
    DemoData.Viewpager,
    DemoData.Retrofit,
    DemoData.NavigationDrawer,
    DemoData.NavigationToOtherScreen,
    DemoData.VoyagerNavigationScreen,
    DemoData.Bottombar,
    DemoData.BottombarAdvance,
    DemoData.Bottomsheet,
    DemoData.Radiobutton,
    DemoData.Checkbox,
    DemoData.Dialog,
    DemoData.ImagePicker,
    DemoData.ImageCapture,

)

@Composable
private fun ComposeDemoScreen(
    modifier: Modifier = Modifier,
    names: List<DemoData>
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            DemoRaw(name)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DemoRaw(names:DemoData){
    val context= LocalContext.current
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth(),
    onClick = {
        when(names){
            DemoData.Edittext -> {
                LoginActivity2.newIntent(context)
            }
            DemoData.Retrofit -> {
                HomeActivity.newIntent(context)
            }
            DemoData.Textview -> {
                TextViewDemoActivity.newIntent(context)
            }
            DemoData.Viewpager -> {
                WelcomeActivity.newIntent(context)
            }
            DemoData.NavigationDrawer -> {
                DrawerActivity.newIntent(context)
            }
            DemoData.NavigationToOtherScreen -> {
                NavigationActivity.newIntent(context)
            }
            DemoData.VoyagerNavigationScreen -> {
                VoyagerNavigationActivity.newIntent(context)
            }
            DemoData.Bottombar -> {
                BottombarDemActivity.newIntent(context)
            }
            DemoData.BottombarAdvance -> {
                BottombarDemAdvanceActivity.newIntent(context)
            }
            DemoData.Bottomsheet -> {
                BottomsheetDemoActivity.newIntent(context)
            }
            DemoData.Radiobutton -> {
                RadioButtonDemoActivity.newIntent(context)
            }
            DemoData.Checkbox -> {
                CheckboxDemoActivity.newIntent(context)
            }
            DemoData.Dialog -> {
                DialogDemoActivity.newIntent(context)
            }
            DemoData.ImagePicker -> {
                ImagePickerActivity.newIntent(context)
            }
            DemoData.ImageCapture -> {
                ImageCaptureActivity.newIntent(context)
            }
        }
    }) {
        Text(text = names.name, Modifier.padding(16.dp))
    }
}
