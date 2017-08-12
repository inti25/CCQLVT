package sec.hungn1.ccqlvt.core.database.dao

import android.arch.persistence.room.*
import sec.hungn1.ccqlvt.core.database.entities.Human

/**
 * Created by hung.nq1 on 8/10/2017.
 */
@Dao
interface HumanDao {
    @Query("select * from human")
    fun getAllHuman(): List<Human>

    @Insert
    fun insertHuman(human: Human)

    @Update
    fun updateHuman(human: Human)

    @Delete
    fun deleteHuman(human: Human)
}