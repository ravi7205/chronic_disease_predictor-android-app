package team.com.HealthCareApp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SymptomDatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        val DATABASE_NAME = "DiseaseDetector.db"
        val TABLE_NAME = "symptom_table"
        val S_ID = "s_id"
        val S_DESCRIPTION = "s_description"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (S_ID INTEGER PRIMARY KEY, S_DESCRIPTION TEXT )")
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertdata(){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        println(2)
        contentValues.put(S_ID, 1)
        contentValues.put(S_DESCRIPTION, "shortness of breath")
        db.insert(TABLE_NAME, null, contentValues)
        println(3)
        contentValues.put(S_ID, 2)
        contentValues.put(S_DESCRIPTION, "coughing or wheezing attacks")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 3)
        contentValues.put(S_DESCRIPTION, "chest tightness")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 4)
        contentValues.put(S_DESCRIPTION, "increased mucus production")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 5)
        contentValues.put(S_DESCRIPTION, "severe headache")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 6)
        contentValues.put(S_DESCRIPTION, "vision problems")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 7)
        contentValues.put(S_DESCRIPTION, "pounding in the chest,neck or ears")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 8)
        contentValues.put(S_DESCRIPTION, "obstructive sleep")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 9)
        contentValues.put(S_DESCRIPTION, "anxiety")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 10)
        contentValues.put(S_DESCRIPTION, "urinating often")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 11)
        contentValues.put(S_DESCRIPTION, "feeling very thirsty")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 12)
        contentValues.put(S_DESCRIPTION, "feeling very hungry even after eating")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 13)
        contentValues.put(S_DESCRIPTION, "extreme fatigue")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 14)
        contentValues.put(S_DESCRIPTION, "weight loss(even after eating more)")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 15)
        contentValues.put(S_DESCRIPTION, "tingling / pain or numbness in hands /feet")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 16)
        contentValues.put(S_DESCRIPTION, "coughing for three or more weeks")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 17)
        contentValues.put(S_DESCRIPTION, "coughing up blood")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 18)
        contentValues.put(S_DESCRIPTION, "fever")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 19)
        contentValues.put(S_DESCRIPTION, "night sweats")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 20)
        contentValues.put(S_DESCRIPTION, "loss of apetite")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 21)
        contentValues.put(S_DESCRIPTION, "joint redness")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 22)
        contentValues.put(S_DESCRIPTION, "joint swelling")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 23)
        contentValues.put(S_DESCRIPTION, "joint tenderness")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 24)
        contentValues.put(S_DESCRIPTION, "joint warmth")
        db.insert(TABLE_NAME, null, contentValues)
        contentValues.put(S_ID, 25)
        contentValues.put(S_DESCRIPTION, "limping")
        db.insert(TABLE_NAME, null, contentValues)
    }


    fun getParticularSymptomData(s_des: String): Symptom {
        var symp  = Symptom()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + S_DESCRIPTION + " = '" + s_des + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        symp.s_id = cursor.getInt(cursor.getColumnIndex(S_ID))
                        symp.s_description = cursor.getString(cursor.getColumnIndex(S_DESCRIPTION))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return symp
    }
}