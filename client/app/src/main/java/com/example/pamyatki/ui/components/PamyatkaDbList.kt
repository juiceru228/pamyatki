package com.example.pamyatki.ui.components

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PamyatkaDbDao {
    @Insert
    suspend fun insert(pamyatkaDbList: PamyatkaDbEntity)

    @Delete
    suspend fun delete(pamyatkaDbList: PamyatkaDbEntity)

    @Update
    suspend fun update(pamyatkaDbEntity: PamyatkaDbEntity)

    @Query("SELECT * FROM PamyatkiList")
    fun getAllPamyatki(): Flow<List<PamyatkaDbEntity>>
}

@Database(entities = [PamyatkaDbEntity::class], version = 1)
abstract class PamyatkiDatabase :  RoomDatabase(){
    abstract fun getDao(): PamyatkaDbDao
}

@Entity(tableName = "PamyatkiList")
data class PamyatkaDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val theme: String,
    val text: String,
    val date: String
)
