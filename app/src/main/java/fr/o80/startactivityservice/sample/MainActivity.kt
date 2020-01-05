package fr.o80.startactivityservice.sample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.o80.startactivityservice.StartActivityService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private val startActivityService = StartActivityService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doIt()
    }

    override fun onResume() {
        super.onResume()
        helloTextView.setOnClickListener { doIt() }
    }

    private fun doIt() {
        startActivityService
            .startActivityForResult(
                intent = Intent(this, ResultActivity::class.java),
                requestCode = Random.nextInt(0, 5000)
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { result ->
                    val time = result.data?.getLongExtra("TIME", -1) ?: -2
                    val status = result.resultCode == Activity.RESULT_OK
                    val requestCode = result.requestCode
                    val message = "[$requestCode][$status] Time is: $time"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            )
            .ignoreDisposable()
    }

}
