package fr.o80.startactivityservice

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Copyright 2020 Olivier Perez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class StartActivityFragment : Fragment() {

    private val result: MutableMap<Int, PublishSubject<Result>> = mutableMapOf()

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
        result[requestCode]?.onNext(
            Result(
                requestCode,
                resultCode,
                data
            )
        )
        result.remove(requestCode)
    }
}
