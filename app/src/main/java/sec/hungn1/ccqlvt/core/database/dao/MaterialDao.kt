package sec.hungn1.ccqlvt.core.database.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import sec.hungn1.ccqlvt.core.database.entities.Material

/**
 * Created by hung.nq1 on 8/10/2017.
 */
@Dao
interface MaterialDao {
    @Query("SELECT * FROM material")
    fun getAllMaterial(): Flowable<List<Material>>

    @Insert
    fun insert(material: Material)

    @Update
    fun update(material: Material)

    @Delete
    fun delete(material: Material)
}