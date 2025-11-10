package ro.pub.cs.systems.eim.practicaltest01var04

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Random

class ProcessingThread(private val context: Context, private val nume: String?, private val grupa: String?) : Thread() {
    private var isRunning = true

    private val random = Random()

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun run() {
        while (true) {
            sendMessage()
            sleep(5000)
            sendMessage()
            sleep(5000)
        }
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    private fun sendMessage() {
        val intent = Intent("ProcessingThread")
        val random = random.nextInt(0, 100)
        if (random == 0) {
            intent.putExtra("MESAJ", "Nume: $nume")
        } else {
            intent.putExtra("MESAJ", "Grupa: $grupa")
        }
        context.sendBroadcast(intent)
    }

    fun stopThread() {
        isRunning = false
    }
}