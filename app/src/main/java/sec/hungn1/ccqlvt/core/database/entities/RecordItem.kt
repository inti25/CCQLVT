package sec.hungn1.ccqlvt.core.database.entities

/**
 * Created by hung.nq1 on 8/10/2017.
 */
class RecordItem : Comparable<RecordItem> {
    var id: Int = 0
    var hid: Int = 0
    var time: Long = 0
    var value: Float = 0F
    var humanName: String? = null
    var isSection: Boolean = false

    override fun compareTo(other: RecordItem): Int = time.compareTo(other.time)
}