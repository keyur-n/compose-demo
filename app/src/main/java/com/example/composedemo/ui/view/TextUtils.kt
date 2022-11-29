package com.example.composedemo.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.R

val firaSansFamily = FontFamily(
    Font(R.font.firasans_light, FontWeight.Light),
    Font(R.font.firasans_regular, FontWeight.Normal),
    Font(R.font.firasans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.firasans_medium, FontWeight.Medium),
    Font(R.font.firasans_bold, FontWeight.Bold),
    Font(R.font.firasans_regular, FontWeight.Bold)
)



@Composable
fun RegularText(text:String){
    Text(text,fontFamily = firaSansFamily, fontWeight = FontWeight.Normal)
}
@Composable
fun BoldText(text:String){
    Text(text,fontFamily = firaSansFamily, fontWeight = FontWeight.Bold)
}
@Composable
fun ItalicText(text:String){
    Text(text,fontFamily = firaSansFamily, fontWeight = FontWeight.Normal,fontStyle = FontStyle.Italic)
}

@Preview(showBackground = true)
@Composable
private fun ButtonPreview(){
    Column() {
        RegularText(text = "Regular Text")
        BoldText(text = "Regular Text")
        ItalicText(text = "Regular Text")
    }

}