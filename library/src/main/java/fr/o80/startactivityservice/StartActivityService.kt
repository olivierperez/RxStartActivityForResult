package fr.o80.startactivityservice

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import io.reactivex.Single

private const val TAG = "fr.o80.startactivityservice.startactivityfragment"

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
