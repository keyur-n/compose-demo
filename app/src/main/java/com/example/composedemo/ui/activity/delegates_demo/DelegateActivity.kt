package com.example.composedemo.ui.activity.delegates_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.composedemo.ui.activity.animation.AnimationActivity

class DelegateActivity:ComponentActivity(),
    AnalyticsLogger by AnalyticsLoggerImpl(),
    DeepLinkHandler by DeepLinkHandlerImpl()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Handle Lifecycler event in AnalyticsLoggerImpl class
        registerLifecycleOwner(this)
        setContent{
            Surface() {
                Text(text = "Look into the code for better practice")
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //Handle all Deep linking in DeepLinkHandlerImpl class
        handleDeepLink(this,intent)
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, DelegateActivity::class.java))
        }
    }
}

//Handle deep linking at single place
interface DeepLinkHandler{
    fun handleDeepLink(activity: ComponentActivity,intent:Intent?)
}
class DeepLinkHandlerImpl:DeepLinkHandler{
    override fun handleDeepLink(activity: ComponentActivity,intent: Intent?) {
        //Custom logic for handling deep linking at once place only
    }

}

//Handle Lifecycler event at single place, and call method inside activity
interface AnalyticsLogger{
    fun registerLifecycleOwner(owner: LifecycleOwner)
}

class AnalyticsLoggerImpl():AnalyticsLogger,LifecycleEventObserver{
    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_RESUME -> {
             println("User Opened Screen")
            }
            Lifecycle.Event.ON_PAUSE -> {
                println("User Leaves Screen")
            }
            else -> Unit
        }
    }
}
