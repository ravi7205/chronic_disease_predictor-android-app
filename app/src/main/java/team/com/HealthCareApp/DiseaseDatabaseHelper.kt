package team.com.HealthCareApp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DiseaseDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        val DATABASE_NAME = "DiseaseDetector.db"
        val TABLE_NAME = "disease_table"
        val D_ID = "d_id"
        val D_NAME = "d_name"
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME(D_ID INTEGER PRIMARY KEY, D_NAME TEXT )")
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(D_ID, 1)
        contentValues.put(D_NAME, "Hypertension")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 2)
        contentValues.put(D_NAME, "Diabetes")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 3)
        contentValues.put(D_NAME, "Asthma")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 4)
        contentValues.put(D_NAME, "Tuberculosis")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 5)
        contentValues.put(D_NAME, "Arthritis")
        db.insert(TABLE_NAME, null, contentValues)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun getParticularDiseaseData(d_id: Int): Disease {
        var dis  = Disease()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + D_ID + " = '" + d_id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        dis.d_id = cursor.getInt(cursor.getColumnIndex(D_ID))
                        dis.d_name = cursor.getString(cursor.getColumnIndex(D_NAME))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return dis
    }

}