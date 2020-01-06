package fr.o80.startactivityservice

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import io.reactivex.Single

private const val TAG = "fr.o80.startactivityservice.startactivityfragment"

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
class StartActivityService(fragmentManager: FragmentManager) {

    constructor(activity: FragmentActivity) : this(activity.supportFragmentManager)
    constructor(fragment: Fragment) : this(fragment.childFragmentManager)

    private val startActivityFragment by lazy { getFragment(fragmentManager) }

    private fun getFragment(fragmentManager: FragmentManager): StartActivityFragment {
        return findFragment(fragmentManager) ?: newFragment(fragmentManager)
    }

    private fun findFragment(fragmentManager: FragmentManager): StartActivityFragment? {
        return fragmentManager.findFragmentByTag(TAG) as StartActivityFragment?
    }

    private fun newFragment(fragmentManager: FragmentManager): StartActivityFragment {
        return StartActivityFragment().also {
            fragmentManager
                .beginTransaction()
                .add(it, TAG)
                .commitNow()
        }
    }

    fun startActivityForResult(intent: Intent, requestCode: Int): Single<Result> {
        return startActivityFragment.start(intent, requestCode).firstOrError()
    }

}
