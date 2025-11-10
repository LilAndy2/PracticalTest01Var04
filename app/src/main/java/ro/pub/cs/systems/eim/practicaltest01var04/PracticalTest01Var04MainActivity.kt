package ro.pub.cs.systems.eim.practicaltest01var04

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.CheckBox
import android.widget.Toast

class PracticalTest01Var04MainActivity : AppCompatActivity() {
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var uneditable: TextView

    private lateinit var bottomText: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var04_main)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        uneditable = findViewById(R.id.uneditable)
        bottomText = ""

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
}