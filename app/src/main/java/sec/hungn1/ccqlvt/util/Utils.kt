package sec.hungn1.ccqlvt.util

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by hung.nq1 on 8/10/2017.
 */
object Utils {
    fun converTimeStampToDateString(long: Long): String {
        val myFormat = "dd/MM/yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        return sdf.format(long)
    }

    fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }

    fun View.updateVisibility(visibility: Int) {
        val current = this.visibility
        if (visibility != current) {
            this.visibility = visibility
        }
    }
}