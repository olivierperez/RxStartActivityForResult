package fr.o80.startactivityservice.lib

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class StartActivityFragment : Fragment() {

    private val result : MutableMap<Int, PublishSubject<Result>> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun start(intent: Intent, requestCode: Int): Observable<Result> {
        return PublishSubject.create<Result>().also {
            result[requestCode] = it
            startActivityForResult(intent, requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        result[requestCode]?.onNext(Result(
            requestCode,
            resultCode,
            data
        ))
        result.remove(requestCode)
    }
}
