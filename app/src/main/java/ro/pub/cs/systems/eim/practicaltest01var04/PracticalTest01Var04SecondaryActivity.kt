package ro.pub.cs.systems.eim.practicaltest01var04

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class PracticalTest01Var04SecondaryActivity :  AppCompatActivity() {
    private lateinit var input1: TextView
    private lateinit var input2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practical_test01_var04_secondary)

        input1 = findViewById(R.id.nume)
        input2 = findViewById(R.id.grupa)

        val in1 = intent.getStringExtra("Nume")
        val in2 = intent.getStringExtra("Grupa")

        input1.text = in1.toString()
        input2.text = in2.toString()

        val okButton = findViewById<Button>(R.id.ok_button)
        okButton.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        val cancelButton = findViewById<Button>(R.id.cancel_button)
        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}