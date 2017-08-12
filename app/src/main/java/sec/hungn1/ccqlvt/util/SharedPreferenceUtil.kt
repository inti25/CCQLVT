package sec.hungn1.ccqlvt.util

import android.content.Context
import android.content.SharedPreferences
import sec.hungn1.ccqlvt.core.application.App
import sec.hungn1.ccqlvt.core.application.Constants

/**
 * Created by hung.nq1 on 8/8/2017.
 */

object SharedPreferenceUtil {
    const val SHAREDPREFERENCES_NAME = "my_sp"
    const val CURRENT_PAGE_KEY = "current_page"

    fun getAppSp(): SharedPreferences {
        return App.mInstance.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun getCurrentPage(): Int {
        return getAppSp().getInt(CURRENT_PAGE_KEY, Constants.CC_PAGE)
    }

    fun setCurrentPage(page: Int) {
        return getAppSp().edit().putInt(CURRENT_PAGE_KEY, page).apply()
    }
}