package sec.hungn1.ccqlvt.core.database.dao

import android.arch.persistence.room.*
import sec.hungn1.ccqlvt.core.database.entities.CcRecord
import sec.hungn1.ccqlvt.core.database.entities.RecordItem

/**
 * Created by hung.nq1 on 8/10/2017.
 */
@Dao
interface CcRecordDao {
    @Query("SELECT ccrecord.*, human.name AS humanName FROM ccrecord INNER JOIN human ON human.id = ccrecord.hid")
    fun getAllRecord(): List<CcRecord>

    @Query("SELECT ccrecord.*, human.name AS humanName FROM ccrecord INNER JOIN human ON human.id = ccrecord.hid")
    fun getAllRecordItem(): List<RecordItem>

    @Insert
    fun insertRecord(record: CcRecord)

    @Update
    fun updateRecord(record: CcRecord)

    @Delete
    fun deleteRecord(record: CcRecord)

    @Query("SELECT ccrecord.*, human.name AS humanName FROM ccrecord INNER JOIN human ON human.id = ccrecord.hid WHERE ccrecord.time = :arg0")
    fun getRecordByTime(time: Long): List<CcRecord>
}