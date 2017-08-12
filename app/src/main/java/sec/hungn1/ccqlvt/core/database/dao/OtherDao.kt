package sec.hungn1.ccqlvt.core.database.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import sec.hungn1.ccqlvt.core.database.entities.Other

/**
 * Created by hung.nq1 on 8/11/2017.
 */
@Dao
interface OtherDao {

    @Query("SELECT * FROM other")
    fun getAll(): Flowable<List<Other>>

    @Insert
    fun insert(other: Other)

    @Update
    fun update(other: Other)

    @Delete
    fun delete(other: Other)
}