package sec.hungn1.ccqlvt.util

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
}