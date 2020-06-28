package team.com.HealthCareApp
import android.annotation.TargetApi
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import android.R.attr.name
import android.widget.Toast


class UserDatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {

        var DATABASE_NAME = "Disease_Detector"
        val DATABASE_VERSION = 1
        val TABLE_USER = "user_table"
        val TABLE_DISEASE = "disease_table"
        val TABLE_SYMPTOM = "symptom_table"
        val TABLE_DSHELPER = "dshelper_table"
        val TABLE_REMEDIES = "remedies_table"
        val TABLE_RDHELPER = "rdhelper_table"
        val USER_NAME = "USER_NAME"
        val AGE = "AGE"
        val PASSWORD ="PASSWORD"
        val EMAIL_ID = "EMAIL_ID"
        val GENDER = "GENDER"
        val S_ID = "s_id"
        val S_DESCRIPTION = "s_description"
        val R_ID = "s_id"
        val R_DESCRIPTION = "r_description"
        val DIAGNOSED_DATE = "diagnosed_date"
        val STATUS = "status"
        val D_ID = "d_id"
        val D_NAME = "d_name"
        val DS_DID = "d_id"
        val DS_SID = "s_id"
        val RS_DID = "d_id"
        val RS_RID = "r_id"
        val KEY_ID="did"
        val TABLE_USERHISTORY = "userhistory_table"
        /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

        val CREATE_TABLE_USER = ("CREATE TABLE IF NOT EXISTS "
                + TABLE_USER + "(" + EMAIL_ID
                + " TEXT PRIMARY KEY," + USER_NAME + " TEXT," + PASSWORD + "TEXT," + AGE + "INTEGER," + GENDER +"TEXT );")

        val CREATE_TABLE_DISEASE = ("CREATE TABLE IF NOT EXISTS "
                + TABLE_DISEASE + "(" + D_ID + " INTEGER PRIMARY KEY," + D_NAME + " TEXT );")

        val CREATE_TABLE_SYMPTOM = ("CREATE TABLE "
                + TABLE_SYMPTOM + "(" + S_ID + " INTEGER PRIMARY KEY," + S_DESCRIPTION + " TEXT );")

        val CREATE_TABLE_DSHELPER = ("CREATE TABLE IF NOT EXISTS "
                + TABLE_DSHELPER + "(" + DS_DID + " INTEGER," + DS_SID + " INTEGER PRIMARY KEY );")
        val CREATE_TABLE_REMEDIES = ("CREATE TABLE IF NOT EXISTS "
                + TABLE_REMEDIES + "(" + R_ID + " INTEGER PRIMARY KEY," + R_DESCRIPTION + " TEXT );")

        val CREATE_TABLE_RDHELPER = ("CREATE TABLE IF NOT EXISTS "
                + TABLE_RDHELPER + "(" + RS_DID + " INTEGER," + RS_RID + " INTEGER PRIMARY KEY );")
        val CREATE_TABLE_USER_HISTORY = ("CREATE TABLE IF NOT EXISTS "
                + TABLE_USERHISTORY + "("  + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+ EMAIL_ID + " TEXT ," + D_ID + " INTEGER,"+ DIAGNOSED_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP," + STATUS +" TEXT );")
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_USER (EMAIL_ID TEXT PRIMARY KEY, USER_NAME TEXT,PASSWORD TEXT, AGE INTEGER, GENDER TEXT)")
        db.execSQL(CREATE_TABLE_DISEASE)
        db.execSQL(CREATE_TABLE_SYMPTOM)
        db.execSQL(CREATE_TABLE_DSHELPER)
        db.execSQL(CREATE_TABLE_REMEDIES)
        db.execSQL(CREATE_TABLE_RDHELPER)
        db.execSQL(CREATE_TABLE_USER_HISTORY)
    }



    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_USER'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_DISEASE'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_SYMPTOM'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_DSHELPER'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_REMEDIES'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_RDHELPER'")
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_USERHISTORY'")
        onCreate(db)
    }


    /**
     * insert data
     */
    fun insertData(name: String, password: String, age: Int, email: String, gender: String) {
        val db = this.writableDatabase
        val ucontentValues = ContentValues()
        var flag = true
        ucontentValues.put(EMAIL_ID,email)
        ucontentValues.put(USER_NAME, name)
        ucontentValues.put(PASSWORD, password)
        ucontentValues.put(AGE, age)
        ucontentValues.put(GENDER, gender)
        db.insert(TABLE_USER, null, ucontentValues)
    }
    @TargetApi(Build.VERSION_CODES.O)
    fun insertUserHistory(email: String, d_id: Int){
        val db = this.writableDatabase
        val current = LocalDateTime.now()
        val userContentValues = ContentValues()
        userContentValues.put(EMAIL_ID, email)
        userContentValues.put(D_ID, d_id)
        userContentValues.put(STATUS, "unresolved")
        db.insert(TABLE_USERHISTORY, null, userContentValues)
    }

    fun addData(){
        val db = this.writableDatabase
        var dValues = ContentValues()
        dValues.put(D_ID, 1)
        dValues.put(D_NAME, "Hypertension")
        db.insert(TABLE_DISEASE, null, dValues)
        dValues.put(D_ID, 2)
        dValues.put(D_NAME, "Diabetes")
        db.insert(TABLE_DISEASE, null, dValues)
        dValues.put(D_ID, 3)
        dValues.put(D_NAME, "Asthma")
        db.insert(TABLE_DISEASE, null, dValues)
        dValues.put(D_ID, 4)
        dValues.put(D_NAME, "Tuberculosis")
        db.insert(TABLE_DISEASE, null, dValues)
        dValues.put(D_ID, 5)
        dValues.put(D_NAME, "Arthritis")
        db.insert(TABLE_DISEASE, null, dValues)
        var sValues = ContentValues()
        sValues.put(S_ID, 1)
        sValues.put(S_DESCRIPTION, "shortness of breath")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 2)
        sValues.put(S_DESCRIPTION, "coughing or wheezing attacks")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 3)
        sValues.put(S_DESCRIPTION, "chest tightness")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 4)
        sValues.put(S_DESCRIPTION, "increased mucus production")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 5)
        sValues.put(S_DESCRIPTION, "severe headache")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 6)
        sValues.put(S_DESCRIPTION, "vision problems")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 7)
        sValues.put(S_DESCRIPTION, "pounding in the chest,neck or ears")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 8)
        sValues.put(S_DESCRIPTION, "obstructive sleep")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 9)
        sValues.put(S_DESCRIPTION, "anxiety")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 10)
        sValues.put(S_DESCRIPTION, "urinating often")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 11)
        sValues.put(S_DESCRIPTION, "feeling very thirsty")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 12)
        sValues.put(S_DESCRIPTION, "feeling very hungry even after eating")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 13)
        sValues.put(S_DESCRIPTION, "extreme fatigue")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 14)
        sValues.put(S_DESCRIPTION, "weight loss(even after eating more)")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 15)
        sValues.put(S_DESCRIPTION, "tingling / pain or numbness in hands /feet")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 16)
        sValues.put(S_DESCRIPTION, "coughing for three or more weeks")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 17)
        sValues.put(S_DESCRIPTION, "coughing up blood")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 18)
        sValues.put(S_DESCRIPTION, "fever")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 19)
        sValues.put(S_DESCRIPTION, "night sweats")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 20)
        sValues.put(S_DESCRIPTION, "loss of apetite")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 21)
        sValues.put(S_DESCRIPTION, "joint redness")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 22)
        sValues.put(S_DESCRIPTION, "joint swelling")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 23)
        sValues.put(S_DESCRIPTION, "joint tenderness")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 24)
        sValues.put(S_DESCRIPTION, "joint warmth")
        db.insert(TABLE_SYMPTOM, null, sValues)
        sValues.put(S_ID, 25)
        sValues.put(S_DESCRIPTION, "limping")
        db.insert(TABLE_SYMPTOM, null, sValues)
        var dsValues = ContentValues()
        dsValues.put(DS_DID, 1)
        dsValues.put(DS_SID, 1)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 1)
        dsValues.put(DS_SID, 2)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 1)
        dsValues.put(DS_SID, 3)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 1)
        dsValues.put(DS_SID, 4)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 2)
        dsValues.put(DS_SID, 5)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 2)
        dsValues.put(DS_SID, 6)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 2)
        dsValues.put(DS_SID, 7)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 2)
        dsValues.put(DS_SID, 8)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 2)
        dsValues.put(DS_SID, 9)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 3)
        dsValues.put(DS_SID, 10)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 3)
        dsValues.put(DS_SID, 11)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 3)
        dsValues.put(DS_SID, 12)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 3)
        dsValues.put(DS_SID, 13)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 3)
        dsValues.put(DS_SID, 14)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 3)
        dsValues.put(DS_SID, 15)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 4)
        dsValues.put(DS_SID, 16)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 4)
        dsValues.put(DS_SID, 17)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 4)
        dsValues.put(DS_SID, 18)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 4)
        dsValues.put(DS_SID, 19)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 4)
        dsValues.put(DS_SID, 20)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 5)
        dsValues.put(DS_SID, 21)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 5)
        dsValues.put(DS_SID, 22)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 5)
        dsValues.put(DS_SID, 23)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 5)
        dsValues.put(DS_SID, 24)
        db.insert(TABLE_DSHELPER, null, dsValues)
        dsValues.put(DS_DID, 5)
        dsValues.put(DS_SID, 25)
        db.insert(TABLE_DSHELPER, null, dsValues)
        var rValues=ContentValues()
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 1)
        rValues.put(R_DESCRIPTION,"Massage mustard oil mixed with camphor all over the chest")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 2)
        rValues.put(R_DESCRIPTION,"drip a few drops of eucalyptus oil in a bowl of boiling water and take its steam")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 3)
        rValues.put(R_DESCRIPTION,"Theophyline is a daily pill that helps to keep the airways open")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 4)
        rValues.put(R_DESCRIPTION,"Long acting beta agonist like salmeterol and formeterol opens airways")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 5)
        rValues.put(R_DESCRIPTION,"Medications: montelucast, zafirlukast help to relieve the symptoms upto 1 day")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 6)
        rValues.put(R_DESCRIPTION,"Walk and exercise regularly")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 7)
        rValues.put(R_DESCRIPTION,"Reduce your sodium intake(salt)")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 8)
        rValues.put(R_DESCRIPTION,"Drink less Alcohol")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 9)
        rValues.put(R_DESCRIPTION,"Eat potassium rich foods like melons, bananas, apricots")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 10)
        rValues.put(R_DESCRIPTION,"Diuretics are water pills which helps to remove salt from you body")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 11)
        rValues.put(R_DESCRIPTION,"Beta-Blockers makes the heart beat at lower rate and with less force")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 12)
        rValues.put(R_DESCRIPTION,"Alpha-blockers relax your blood vessels which lowers the blood pressure")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 13)
        rValues.put(R_DESCRIPTION,"Exercise Regularly")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 14)
        rValues.put(R_DESCRIPTION,"Reduce your carb intake and increase fiber intake")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 15)
        rValues.put(R_DESCRIPTION,"Drink water and stay hiderated")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 16)
        rValues.put(R_DESCRIPTION,"Diabetes can be managed with insulin")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 17)
        rValues.put(R_DESCRIPTION,"Methods to take insulin : syringe, pre-filled pens and insulin pump")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 18)
        rValues.put(R_DESCRIPTION,"Pancrease transplant")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 19)
        rValues.put(R_DESCRIPTION,"Isoniazid: is an antibiotic and works by stopping the growth of bacteria")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 20)
        rValues.put(R_DESCRIPTION,"Rifamycin is an antibiotic used to prevent and treat TB and other infections")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 21)
        rValues.put(R_DESCRIPTION,"Ethambutol: It is used to treat Tb and works by stopping bacteria growth")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 22)
        rValues.put(R_DESCRIPTION,"Pyrazinamide: It is used with other medications to treat TB")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 23)
        rValues.put(R_DESCRIPTION,"Loose weight")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 24)
        rValues.put(R_DESCRIPTION,"Do more exercise")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 25)
        rValues.put(R_DESCRIPTION,"Long warm shower in morning helps to ease stiffnes in joints")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 26)
        rValues.put(R_DESCRIPTION,"Wrap a gel ice pack or a bag of frozen vegetables and apply to painfull joints ")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 27)
        rValues.put(R_DESCRIPTION,"Try acupuncture")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 28)
        rValues.put(R_DESCRIPTION,"Acitamenophen: It helps reduce pain but has no effect on inflammation")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 29)
        rValues.put(R_DESCRIPTION,"Corticosteroids: This class of drugs reduces inflammation")
        db.insert(TABLE_REMEDIES, null,rValues)
        rValues.put(R_ID, 30)
        rValues.put(R_DESCRIPTION,"Corticosteroids can be taken orally or can be injected directly in joints")
        var rsValues = ContentValues()
        rsValues.put(RS_DID, 1)
        rsValues.put(RS_RID, 1)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 1)
        rsValues.put(RS_RID, 2)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 1)
        rsValues.put(RS_RID, 3)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 1)
        rsValues.put(RS_RID, 4)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 1)
        rsValues.put(RS_RID, 5)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 2)
        rsValues.put(RS_RID, 6)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 2)
        rsValues.put(RS_RID, 7)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 2)
        rsValues.put(RS_RID, 8)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 2)
        rsValues.put(RS_RID, 9)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 2)
        rsValues.put(RS_RID, 10)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 2)
        rsValues.put(RS_RID, 11)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 2)
        rsValues.put(RS_RID, 12)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 3)
        rsValues.put(RS_RID, 13)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 3)
        rsValues.put(RS_RID, 14)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 3)
        rsValues.put(RS_RID, 15)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 3)
        rsValues.put(RS_RID, 16)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 3)
        rsValues.put(RS_RID, 17)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 3)
        rsValues.put(RS_RID, 18)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 4)
        rsValues.put(RS_RID, 19)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 4)
        rsValues.put(RS_RID, 20)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 4)
        rsValues.put(RS_RID, 21)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 4)
        rsValues.put(RS_RID, 22)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 5)
        rsValues.put(RS_RID, 23)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 5)
        rsValues.put(RS_RID, 24)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 5)
        rsValues.put(RS_RID, 25)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 5)
        rsValues.put(RS_RID, 26)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 5)
        rsValues.put(RS_RID, 27)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 5)
        rsValues.put(RS_RID, 28)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 5)
        rsValues.put(RS_RID, 29)
        db.insert(TABLE_RDHELPER, null, rsValues)
        rsValues.put(RS_DID, 5)
        rsValues.put(RS_RID, 30)
        db.insert(TABLE_RDHELPER, null, rsValues)







    }

    fun del(){
        val db = this.writableDatabase
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER)
        onCreate(db)
    }
    fun getParticularUserData(email: String): UserInfo {
        var userInfo  = UserInfo()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_USER + " WHERE " + EMAIL_ID + " = '" + email + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        userInfo.user_name = cursor.getString(cursor.getColumnIndex(USER_NAME))
                        userInfo.age = cursor.getInt(cursor.getColumnIndex(AGE))
                        userInfo.password = cursor.getString(cursor.getColumnIndex(PASSWORD))
                        userInfo.email = cursor.getString(cursor.getColumnIndex(EMAIL_ID))
                        userInfo.gender = cursor.getString(cursor.getColumnIndex(GENDER))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return userInfo
    }
    fun getParticularDiseaseData(d_id: Int): Disease {
        var dis  = Disease()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_DISEASE + " WHERE " + D_ID + " = '" + d_id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        println("!!!")
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

    fun getParticularSymptomData(s_des: String): Symptom {
        var symp  = Symptom()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_SYMPTOM + " WHERE " + S_DESCRIPTION + " = '" + s_des + "'"
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

    fun getUserDiseaseCount(email: String): Int{
        var cnt=0
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_USERHISTORY + " WHERE " + EMAIL_ID + " = '" + email + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try{
            cnt=cursor.count
        }
        finally {
            cursor.close()
        }
        return cnt
    }

    fun getUserHistory(email: String): ArrayList<UserHistory>{
        var userHistory = UserHistory()
        var userHistoryArray: ArrayList<UserHistory> = ArrayList<UserHistory>()
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_USERHISTORY + " WHERE " + EMAIL_ID + " = '" + email + "'"
        val cursor = db.rawQuery(selectQuery, null)
        print(cursor.count)
        print("Size")
        try {
            if (cursor.count != 0) {
                cursor.moveToFirst()
                if (cursor.count > 0) {
                    do {
                        userHistory.email = cursor.getString(cursor.getColumnIndex(EMAIL_ID))
                        userHistory.did = cursor.getInt(cursor.getColumnIndex(D_ID))
//                        var d = cursor.getString(cursor.getColumnIndex(DIAGNOSED_DATE))
//                        val sdf = SimpleDateFormat("MM/dd/YYYY", Locale.getDefault())
//                        userHistory.diagdate = sdf.parse(d)
//                        userHistory.status = cursor.getString(cursor.getColumnIndex(STATUS))
                        userHistoryArray.add(userHistory)
                    } while ((cursor.moveToNext()))
                }
            }
        }
        finally {
            cursor.close()
        }
        return userHistoryArray


    }
    fun UpdateStatus(email: String) {
        val db = this.writableDatabase
        val query = "update "+ TABLE_USERHISTORY+" set "+ STATUS +" = resolved where "+ EMAIL_ID +" = '" + email + "'"
        val cursor = db.rawQuery(query, null)

    }
    fun getRIDS(d_id:Int):ArrayList<Int>{
        var r_ids=ArrayList<Int>()
        val db = this.readableDatabase
        val selectQuery = "SELECT * FROM " + TABLE_RDHELPER + " WHERE " + RS_DID + " = '" + d_id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        var value=0;
                        value=cursor.getInt(cursor.getColumnIndex(RS_RID))
                        r_ids.add(value)

                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return r_ids
    }
    fun getRemedies(r_ids: ArrayList<Int>): ArrayList<Remedy> {
        var remd  = ArrayList<Remedy>()
        val db = this.readableDatabase
        for(r_id in r_ids){
            val selectQuery = "SELECT *  FROM " + TABLE_REMEDIES + " WHERE " + R_ID + " = '" + r_id + "'"
            val cursor = db.rawQuery(selectQuery, null)
            try {
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst()
                    if (cursor.getCount() > 0) {
                        do {
                            var obj=Remedy()
                            obj.r_id = cursor.getInt(cursor.getColumnIndex(R_ID))
                            obj.r_description = cursor.getString(cursor.getColumnIndex(R_DESCRIPTION))
                            remd.add(obj)
                        } while ((cursor.moveToNext()))
                    }
                }
            } finally {
                cursor.close()
            }
        }

        return remd
    }

    fun getDiseaseId(s_id: Int): Int {
        var d_id : Int = 0
        val db = this.readableDatabase
        val selectQuery = "SELECT  * FROM " + TABLE_DSHELPER + " WHERE " + DS_SID + " = '" + s_id + "'"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            if (cursor.getCount() != 0) {
                cursor.moveToFirst()
                if (cursor.getCount() > 0) {
                    do {
                        d_id = cursor.getInt(cursor.getColumnIndex(DS_DID))
                    } while ((cursor.moveToNext()))
                }
            }
        } finally {
            cursor.close()
        }
        return d_id
    }
    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_USER,"USER_Id = ?", arrayOf(id))
    }


}