package com.example.composedemo.ui.activity.dialog_demo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.DatePicker
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composedemo.ui.activity.checkbox_demo.CheckboxDemoActivity
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.BoldText
import com.example.composedemo.ui.view.RegularButton
import com.example.composedemo.ui.view.RegularText
import com.google.android.material.dialog.MaterialDialogs
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class DialogDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme() {
                Surface {
                    DialogScreen()
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, DialogDemoActivity::class.java))
        }
    }
}


@Composable
fun DialogScreen() {
    var isDatePickerDialog by remember { mutableStateOf(false) }
    var isTimePickerDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProgressDialogScreen()
        AlertDialogScreen()
        DatePickerDailogScreeen()
        Button(onClick = { isDatePickerDialog = true }) {
            RegularText(text = "Open Date Picker Dialog")
        }
        Button(onClick = { isTimePickerDialog = true }) {
            RegularText(text = "Open Time Picker Dialog")
        }
    }



    if (isDatePickerDialog) {
        DatePickerDialogScreen()
    }
    if (isTimePickerDialog) {
        // Fetching local context
        val mContext = LocalContext.current

        // Declaring and initializing a calendar
        val mCalendar = Calendar.getInstance()
        val mHour = mCalendar[Calendar.HOUR_OF_DAY]
        val mMinute = mCalendar[Calendar.MINUTE]

        // Value for storing time as a string
        val mTime = remember { mutableStateOf("") }

        // Creating a TimePicker dialod
        val mTimePickerDialog = TimePickerDialog(
            mContext,
            { _, _: Int, _: Int ->
                mTime.value = "$mHour:$mMinute"
            }, mHour, mMinute, false
        )
        mTimePickerDialog.show()
    }
}

@Composable
fun DatePickerDailogScreeen() {
    val context = LocalContext.current
    var pickDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickTime by remember {
        mutableStateOf(LocalTime.NOON)
    }
    val formattedDate by remember {
        derivedStateOf {
//            SimpleDateFormat("MMM dd yyyy").format(pickDate)
            DateTimeFormatter.ofPattern("MMM dd yyyy")
                .format(pickDate)
        }
    }
    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("hh:mm")
                .format(pickTime)
        }
    }

    val rememberDialogState = rememberMaterialDialogState()
    val rememberTimDialogState = rememberMaterialDialogState()
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { rememberDialogState.show() }) {
            RegularText(text = "Open Date Picker Dialog")
        }
        Text(text = "Selected date = $formattedDate")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { rememberTimDialogState.show() }) {
            RegularText(text = "Open Time Picker Dialog")
        }
        Text(text = "Selected time = $formattedTime")
        Spacer(modifier = Modifier.height(16.dp))
    }
    MaterialDialog(dialogState = rememberDialogState,
        buttons = {
            positiveButton("OK") {
                Toast.makeText(context, "Clicked OK", Toast.LENGTH_LONG).show()
            }
            negativeButton("Cancel")
        }) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = {
                it.dayOfYear > LocalDate.now().dayOfYear
            }
        ) {
            pickDate = it
        }
    }
    MaterialDialog(dialogState = rememberTimDialogState,
        buttons = {
            positiveButton("OK") {
                Toast.makeText(context, "Clicked OK", Toast.LENGTH_LONG).show()
            }
            negativeButton("Cancel")
        }) {
        timepicker(
            initialTime = LocalTime.MIDNIGHT,
            title = "Pick a time",
            timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
        ) {
            pickTime = it
        }
    }

}

@Composable
private fun ProgressDialogScreen() {
    var isDialog by remember { mutableStateOf(false) }

    Button(onClick = { isDialog = true }) {
        RegularText(text = "Open Loading Dialog")
    }
    if (isDialog) {
        Dialog(
            onDismissRequest = { isDialog = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Card(modifier = Modifier.padding(8.dp)) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

        }
    }
}

@Composable
private fun AlertDialogScreen() {
    var isAlertDialog by remember { mutableStateOf(false) }
    Button(onClick = { isAlertDialog = true }) {
        RegularText(text = "Open Alert Dialog")
    }

    if (isAlertDialog) {
        Column() {
            AlertDialog(onDismissRequest = { /*TODO*/ },
                title = {
                    BoldText(text = "Success")
                },
                text = {
                    RegularText(text = "You successfully completed dialog course.")
                },
                modifier = Modifier.fillMaxWidth(),
                buttons = {
                    Button(
                        onClick = { isAlertDialog = false },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        RegularText(text = "OK")
                    }
                })
        }
    }
}

@Composable
fun DatePickerDialogScreen() {
// Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
    mDatePickerDialog.show()

}

@Composable
fun TimerPickerScreen() {

    // Fetching local context
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }

    // Creating a TimePicker dialod
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, _: Int, _: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )
    mTimePickerDialog.show()

}
