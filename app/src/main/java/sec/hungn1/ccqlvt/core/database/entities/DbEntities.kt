package sec.hungn1.ccqlvt.core.database.entities

import android.arch.persistence.room.*

/**
 * Created by hung.nq1 on 8/8/2017.
 */
@Entity(tableName = "ccrecord",
        foreignKeys = arrayOf(ForeignKey(entity = Human::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("hid"),
                onDelete = ForeignKey.CASCADE)))
data class CcRecord(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,
        @ColumnInfo(name = "hid")
        var hid: Int? = null,
        @ColumnInfo(name = "time")
        var time: Long? = 0,
        @ColumnInfo(name = "value")
        var value: Float? = 0F,
        @ColumnInfo(name = "isPayed")
        var isPayed: Boolean = false
) {
    var humanName: String? = null
}

@Entity(tableName = "human")
data class Human(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,
        @ColumnInfo(name = "name") var name: String? = "")

@Entity(tableName = "material")
data class Material(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,
        @ColumnInfo(name = "title")
        var title: String? = null,
        @ColumnInfo(name = "number")
        var number: String? = null,
        @ColumnInfo(name = "total")
        var total: Long = 0,
        @ColumnInfo(name = "time")
        var time: Long = 0,
        @ColumnInfo(name = "isPayed")
        var isPayed: Boolean = false
) : Comparable<Material> {
    override fun compareTo(other: Material): Int {
        return time.compareTo(other.time)
    }
}

@Entity(tableName = "other")
data class Other(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int? = null,
        @ColumnInfo(name = "title")
        var title: String? = null,
        @ColumnInfo(name = "time")
        var time: Long = 0,
        @ColumnInfo(name = "total")
        var total: Long = 0,
        @ColumnInfo(name = "isPayed")
        var isPayed: Boolean = false
) : Comparable<Other> {
    override fun compareTo(other: Other): Int {
        return time.compareTo(other.time)
    }
}