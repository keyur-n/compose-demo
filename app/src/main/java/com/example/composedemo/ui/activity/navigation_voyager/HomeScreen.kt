package com.example.composedemo.ui.activity.navigation_voyager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.composedemo.ui.activity.navigation.NavigationScreen
import com.example.composedemo.ui.view.RegularButton

object HomeScreen : Screen {

    @Composable
    override fun Content() {
        // ...
        val navigator = LocalNavigator.currentOrThrow
        Surface {

            Column {
                Text(text = "Welcome to Navigation Demo")
                RegularButton(text = "Next") {
                    navigator.push(PostListScreen(data = "hey data passed with voyager library"))
                }
            }
        }
    }
}

class PostListScreen(val data: String) : Screen {

    @Composable
    override fun Content() {
        // ...
        val navigator = LocalNavigator.currentOrThrow
        Surface {
            Card(
                modifier = Modifier.clickable {
                    navigator.push(PostDetailsScreen(101))
                    // Also works:
                    // navigator push PostDetailsScreen(post.id)
                    // navigator += PostDetailsScreen(post.id)
                }
            ) {
                Column() {
                    Text(text = "Congratulations, You travel to the moon via navigation")
                    Text(text = data)
                    Text(text = "Click Back to goto previous screen.", modifier = Modifier.padding(top = 16.dp))
                    RegularButton(text = "Next") {
                        navigator.push(PostDetailsScreen(101))
                    }
                }
            }
        }

    }

}

data class PostDetailsScreen(val postId: Long) : Screen {

    @Composable
    override fun Content() {
        // ...
        val navigator = LocalNavigator.currentOrThrow

        Surface {
            Column() {
                Text(text = "Hold your travel", modifier = Modifier.clickable {

                })
                Text(text = "Go back and you will be redirect to 1st screen. Because popUpTo used in second screen.", modifier = Modifier.padding(top = 16.dp))
                RegularButton(text = "Next") {
                    navigator.push(PostInfoScreen(102))
//                    navigator.popUntilRoot()
//                    navController.navigate(NavigationScreen.NavigationFour.route){
//                        popUpTo(NavigationScreen.NavigationHome.route){
//                            //inclusive=true -> This will remove First screen also, and exit activity
//                            inclusive=true
//                        }
//                    }
                }
            }
        }
    }
}

data class PostInfoScreen(val postId: Long) : Screen {

    @Composable
    override fun Content() {
        // ...
        val navigator = LocalNavigator.currentOrThrow

        Surface {
            Column() {
                Text(text = "Hold your travel", modifier = Modifier.clickable {

                })
                Text(text = "Go back and you will be redirect to 1st screen. Because popUpTo used in second screen.", modifier = Modifier.padding(top = 16.dp))
                RegularButton(text = "Done") {
                    navigator.popUntil {

                        false
                    }
                }
            }
        }
    }
}