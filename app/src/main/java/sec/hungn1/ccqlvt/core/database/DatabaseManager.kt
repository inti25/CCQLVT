@file:Suppress("UNCHECKED_CAST")

package sec.hungn1.ccqlvt.core.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import sec.hungn1.ccqlvt.core.database.dao.CcRecordDao
import sec.hungn1.ccqlvt.core.database.dao.HumanDao
import sec.hungn1.ccqlvt.core.database.dao.MaterialDao
import sec.hungn1.ccqlvt.core.database.dao.OtherDao
import sec.hungn1.ccqlvt.core.database.entities.CcRecord
import sec.hungn1.ccqlvt.core.database.entities.Human
import sec.hungn1.ccqlvt.core.database.entities.Material
import sec.hungn1.ccqlvt.core.database.entities.Other

/**
 * Created by hung.nq1 on 8/8/2017.
 */
@Database(entities = arrayOf(Human::class, CcRecord::class, Material::class, Other::class), version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun humanDao(): HumanDao
    abstract fun recordDao(): CcRecordDao
    abstract fun materialDao(): MaterialDao
    abstract fun otherDao(): OtherDao
}