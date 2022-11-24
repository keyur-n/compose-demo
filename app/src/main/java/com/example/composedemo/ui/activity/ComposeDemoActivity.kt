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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.*
import com.example.composedemo.ui.activity.animation.AnimationActivity
import com.example.composedemo.ui.activity.bottombar.BottombarDemActivity
import com.example.composedemo.ui.activity.bottombar_advance.BottombarDemAdvanceActivity
import com.example.composedemo.ui.activity.bottomsheet_demo.BottomsheetDemoActivity
import com.example.composedemo.ui.activity.box_demo.BoxDemoActivity
import com.example.composedemo.ui.activity.button_demo.ButtonDemoActivity
import com.example.composedemo.ui.activity.checkbox_demo.CheckboxDemoActivity
import com.example.composedemo.ui.activity.chip_demo.ChipDemoActivity
import com.example.composedemo.ui.activity.constraint_set_demo.ConstraintSetDemoActivity
import com.example.composedemo.ui.activity.constraintlayout_advance_demo.ConstraintLayoutAdvanceDemoActivity
import com.example.composedemo.ui.activity.constraintlayout_demo.ConstraintLayoutDemoActivity
import com.example.composedemo.ui.activity.deeplink_demo.DeepLinkDemoActivity
import com.example.composedemo.ui.activity.delegates_demo.DelegateActivity
import com.example.composedemo.ui.activity.derived_state_demo.BestPracticesActivity
import com.example.composedemo.ui.activity.dialog_demo.DialogDemoActivity
import com.example.composedemo.ui.activity.edittextdemo.EditTextDemoActivity
import com.example.composedemo.ui.activity.event_handler.EventHandlerActivity
import com.example.composedemo.ui.activity.hoisting_demo.HoistingDemoActivity
import com.example.composedemo.ui.activity.image_capture.ImageCaptureActivity
import com.example.composedemo.ui.activity.image_picker.ImagePickerActivity
import com.example.composedemo.ui.activity.navigation.NavigationActivity
import com.example.composedemo.ui.activity.navigation_drawer_demo.NavigationDrawerDemoActivity
import com.example.composedemo.ui.activity.navigation_voyager.VoyagerNavigationActivity
import com.example.composedemo.ui.activity.pagination_demo.PaginationActivity
import com.example.composedemo.ui.activity.pull_refresh_demo.PullRefreshDemoActivity
import com.example.composedemo.ui.activity.radio.RadioButtonDemoActivity
import com.example.composedemo.ui.activity.retrofit_demo.HomeActivity
import com.example.composedemo.ui.activity.shadow_demo.ShadowActivity
import com.example.composedemo.ui.activity.snackbar_demo.SnackbarDemoActivity
import com.example.composedemo.ui.activity.snapping_demo.SnappingActivity
import com.example.composedemo.ui.activity.tab_demo.TabDemoActivity
import com.example.composedemo.ui.activity.textviewdemo.TextViewDemoActivity
import com.example.composedemo.ui.activity.toggle_demo.ToggleDemoActivity
import com.example.composedemo.ui.activity.viewmode_string_demo.ViewModelStringDemoActivity
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

sealed class DemoData(val name: String) {
    object Textview : DemoData("Textview")
    object Button : DemoData("Button")
    object Edittext : DemoData("Edittext")
    object Viewpager : DemoData("Viewpager")
    object Retrofit : DemoData("Retrofit API loading and show in List")
    object NavigationToolBar : DemoData("Toolbar")
    object NavigationDrawer : DemoData("Navigation Drawer")
    object NavigationToOtherScreen : DemoData("Navigation between screens")
    object VoyagerNavigationScreen : DemoData("Navigation between screens by Voyager library")
    object Bottombar : DemoData("Bottombar")
    object BottombarAdvance : DemoData("Bottombar Advance")
    object Bottomsheet : DemoData("Bottomsheet")
    object Radiobutton : DemoData("Radiobutton")
    object Checkbox : DemoData("Checkbox")
    object Toggle : DemoData("Toggle")
    object Dialog : DemoData("Progress Dialog & Alert Dialog")
    object ImagePicker : DemoData("ImagePicker")
    object ImageCapture : DemoData("Image Capture")
    object Snackbar : DemoData("Snackbar")
    object Box : DemoData("Box")
    object Tab : DemoData("Tab")
    object ConstraintLayout : DemoData("Constraint Layout")
    object ConstraintLayoutAdvance :
        DemoData("Constraint Layout Advance: Guideline, Barrier, Chains")

    object ConstraintSet : DemoData("Constraint Set")
    object Hoisting : DemoData("Hoisting")
    object Chip : DemoData("Chip")
    object DeepLink : DemoData("DeepLink")
    object Animation : DemoData("Animation")
    object Delegation : DemoData("Delegation - Look into the code for best practices")
    object Pagination : DemoData("Pagination")
    object PullRefresh : DemoData("Pull Refresh (Swipe to Refresh)")
    object ViewModelStringResource : DemoData("Use string resource inside Viewmodel")
    object BestPractices : DemoData("BestPractices - Check code to learn")
    object Shadow : DemoData("Shadow Customization")
    object Snapping : DemoData("Snapping Row")
    object EventHandler : DemoData("Event Handler")
}

val dataList = mutableListOf(
    DemoData.Textview,
    DemoData.Button,
    DemoData.Edittext,
    DemoData.Radiobutton,
    DemoData.Checkbox,
    DemoData.Snackbar,
    DemoData.Toggle,
    DemoData.Chip,
    DemoData.NavigationToolBar,
    DemoData.Viewpager,
    DemoData.Snapping,
    DemoData.Tab,
    DemoData.NavigationDrawer,
    DemoData.NavigationToOtherScreen,
    DemoData.VoyagerNavigationScreen,
    DemoData.Bottombar,
    DemoData.BottombarAdvance,
    DemoData.Bottomsheet,

    DemoData.Dialog,
    DemoData.ImagePicker,
    DemoData.ImageCapture,
    DemoData.Box,
    DemoData.ConstraintLayout,
    DemoData.ConstraintLayoutAdvance,
    DemoData.ConstraintSet,
    DemoData.Hoisting,

    DemoData.DeepLink,
    DemoData.Animation,
    DemoData.Delegation,
    DemoData.Retrofit,
    DemoData.Pagination,
    DemoData.PullRefresh,
    DemoData.ViewModelStringResource,
    DemoData.BestPractices,
    DemoData.Shadow,
    DemoData.EventHandler,

    )

@Composable
private fun ComposeDemoScreen(
    modifier: Modifier = Modifier,
    names: List<DemoData>,
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            DemoRaw(name)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DemoRaw(names: DemoData) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(),
        onClick = {
            when (names) {
                DemoData.Edittext -> {
                    EditTextDemoActivity.newIntent(context)
                }
                DemoData.Retrofit -> {
                    HomeActivity.newIntent(context)
                }
                DemoData.Textview -> {
                    TextViewDemoActivity.newIntent(context)
                }
                DemoData.Button -> {
                    ButtonDemoActivity.newIntent(context)
                }
                DemoData.Viewpager -> {
                    WelcomeActivity.newIntent(context)
                }
                DemoData.NavigationToolBar -> {
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
                DemoData.Snackbar -> {
                    SnackbarDemoActivity.newIntent(context)
                }
                DemoData.NavigationDrawer -> {
                    NavigationDrawerDemoActivity.newIntent(context)
                }
                DemoData.Box -> {
                    BoxDemoActivity.newIntent(context)
                }
                DemoData.Tab -> {
                    TabDemoActivity.newIntent(context)
                }
                DemoData.ConstraintLayout -> {
                    ConstraintLayoutDemoActivity.newIntent(context)
                }
                DemoData.ConstraintLayoutAdvance -> {
                    ConstraintLayoutAdvanceDemoActivity.newIntent(context)
                }
                DemoData.ConstraintSet -> {
                    ConstraintSetDemoActivity.newIntent(context)
                }
                DemoData.Hoisting -> {
                    HoistingDemoActivity.newIntent(context)
                }
                DemoData.Toggle -> {
                    ToggleDemoActivity.newIntent(context)
                }
                DemoData.Chip -> {
                    ChipDemoActivity.newIntent(context)
                }
                DemoData.DeepLink -> {
                    DeepLinkDemoActivity.newIntent(context)
                }
                DemoData.Animation -> {
                    AnimationActivity.newIntent(context)
                }
                DemoData.Delegation -> {
                    DelegateActivity.newIntent(context)
                }
                DemoData.Pagination -> {
                    PaginationActivity.newIntent(context)
                }
                DemoData.PullRefresh -> {
                    PullRefreshDemoActivity.newIntent(context)
                }
                DemoData.ViewModelStringResource -> {
                    ViewModelStringDemoActivity.newIntent(context)
                }
                DemoData.BestPractices -> {
                    BestPracticesActivity.newIntent(context)
                }
                DemoData.Shadow -> {
                    ShadowActivity.newIntent(context)
                }
                DemoData.Snapping -> {
                    SnappingActivity.newIntent(context)
                }
                DemoData.EventHandler -> {
                    EventHandlerActivity.newIntent(context)
                }

            }
        }) {
        Text(text = names.name, Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeDemoPreview() {
    ComposeDemoTheme {
        ComposeDemoScreen(Modifier.fillMaxSize(), dataList)
    }
}