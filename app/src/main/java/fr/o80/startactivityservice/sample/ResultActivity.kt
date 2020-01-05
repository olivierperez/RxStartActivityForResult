package fr.o80.startactivityservice.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setResult(Activity.RESULT_OK, buildResult())
        finish()
    }

    private fun buildResult(): Intent {
        return Intent().apply {
            putExtra("TIME", System.currentTimeMillis())
        }
    }
}
