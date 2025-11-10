package ro.pub.cs.systems.eim.practicaltest01var04

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi

class PracticalTest01Var04MainActivity : AppCompatActivity() {
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var uneditable: TextView
    private lateinit var bottomText: String

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val message = intent?.getStringExtra("MESAJ")
            Log.d("MainActivityReceiver", "Mesaj de broadcast primit: $message")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var04_main)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        uneditable = findViewById(R.id.uneditable)
        bottomText = ""

        val activityResultsLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "The activity returned with result OK", Toast.LENGTH_LONG).show()
            }
            else if (result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "The activity returned with result CANCELED", Toast.LENGTH_LONG).show()
            }
        }

        val navigateButton = findViewById<Button>(R.id.navigate_to_second_activity)
        navigateButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var04SecondaryActivity::class.java)
            intent.putExtra("Nume", input1.text.toString())
            intent.putExtra("Grupa", input2.text.toString())
            activityResultsLauncher.launch(intent)
        }

        val displayButton = findViewById<Button>(R.id.set_button)
        displayButton.setOnClickListener {
            val input1Text = input1.text.toString()
            val input2Text = input2.text.toString()

            val checkbox1 = findViewById<CheckBox>(R.id.checkbox1)
            val checkbox2 = findViewById<CheckBox>(R.id.checkbox2)

            if (checkbox1.isChecked && input1Text.isEmpty()) {
                Toast.makeText(this, "Camp necompletat", Toast.LENGTH_LONG).show()
            } else if (checkbox1.isChecked) {
                bottomText += "$input1Text\n"
            }

            if (checkbox2.isChecked && input2Text.isEmpty()) {
                Toast.makeText(this, "Camp necompletat", Toast.LENGTH_LONG).show()
            } else if (checkbox2.isChecked) {
                bottomText += "$input2Text\n"
            }

            uneditable.text = bottomText

            if (input1Text.isNotEmpty() && input2Text.isNotEmpty()) {
                val serviceIntent = Intent(this, PracticalTest01Var04Service::class.java)
                serviceIntent.putExtra("Nume", input1.text.toString())
                serviceIntent.putExtra("Grupa", input2.text.toString())
                startService(serviceIntent)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("input1", input1.text.toString())
        outState.putString("input2", input2.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        input1.setText(savedInstanceState.getString("input1"))
        input2.setText(savedInstanceState.getString("input2"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        registerReceiver(broadcastReceiver, IntentFilter("ProcessingThread"), Context.RECEIVER_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onDestroy() {
        val intent = Intent(applicationContext, PracticalTest01Var04Service::class.java)
        applicationContext.stopService(intent)
        super.onDestroy()
    }
}