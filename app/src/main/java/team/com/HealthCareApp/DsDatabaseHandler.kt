package team.com.HealthCareApp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DsDatabaseHandler (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        val DATABASE_NAME = "DiseaseDetector.db"
        val TABLE_NAME = "ds_helper"
        val D_ID = "d_id"
        val S_ID = "s_id"
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME(D_ID INTEGER , S_ID INTEGER, FOREIGN KEY(D_ID) REFERENCES disease_table(D_ID), FOREIGN KEY(S_ID) REFERENCES symptom_table(S_ID)  )")
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(D_ID, 1)
        contentValues.put(S_ID, 1)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 1)
        contentValues.put(S_ID, 2)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 1)
        contentValues.put(S_ID, 3)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 1)
        contentValues.put(S_ID, 4)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 2)
        contentValues.put(S_ID, 5)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 2)
        contentValues.put(S_ID, 6)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 2)
        contentValues.put(S_ID, 7)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 2)
        contentValues.put(S_ID, 8)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 2)
        contentValues.put(S_ID, 9)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 3)
        contentValues.put(S_ID, 10)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 3)
        contentValues.put(S_ID, 11)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 3)
        contentValues.put(S_ID, 12)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 3)
        contentValues.put(S_ID, 13)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 3)
        contentValues.put(S_ID, 14)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 3)
        contentValues.put(S_ID, 15)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 4)
        contentValues.put(S_ID, 16)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 4)
        contentValues.put(S_ID, 17)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 4)
        contentValues.put(S_ID, 18)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 4)
        contentValues.put(S_ID, 19)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 4)
        contentValues.put(S_ID, 20)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 5)
        contentValues.put(S_ID, 21)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 5)
        contentValues.put(S_ID, 22)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 5)
        contentValues.put(S_ID, 23)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 5)
        contentValues.put(S_ID, 24)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
        contentValues.put(D_ID, 5)
        contentValues.put(S_ID, 25)
        db.insert(DiseaseDatabaseHelper.TABLE_NAME, null, contentValues)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun getDiseaseId(s_id: Int): Int {
        var d_id : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + S_ID + " = '" + s_id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        d_id = cursor.getInt(cursor.getColumnIndex(D_ID))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return d_id
    }
}