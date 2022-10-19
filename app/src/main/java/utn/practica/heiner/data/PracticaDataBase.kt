package utn.practica.heiner.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import utn.practica.heiner.model.Estado

@Database(entities = [Estado::class], version = 2, exportSchema = false)
abstract class PracticaDataBase  : RoomDatabase()
{
    abstract fun estadoDao() : EstadoDao

    companion object{//variables globales y estaticas
    @Volatile
    private var INSTANCE: PracticaDataBase? = null


        fun getDataBase(context: android.content.Context): PracticaDataBase {
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PracticaDataBase::class.java,
                    "practica_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}