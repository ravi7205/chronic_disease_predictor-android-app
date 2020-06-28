package team.com.HealthCareApp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.user_welcome.*
import kotlinx.android.synthetic.main.user_welcome_no_dis.*
import kotlinx.android.synthetic.main.user_welcome_no_dis.editText
import kotlinx.android.synthetic.main.user_welcome_no_dis.editText2
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList
import java.time.LocalDateTime
class UserWelcome : AppCompatActivity() {
    internal var helper = UserDatabaseHandler(this)
    fun getRemedies(predicted_disease:Int):String {
        var rem_ids =helper.getRIDS(predicted_disease)
        var remedies = helper.getRemedies(rem_ids)

        var remedies_string = "Remedies for the predicted disease are : \n"
        var i=1
        for(rem in remedies){
            remedies_string = remedies_string +"Rem"+i+" : "+ rem.r_description +" \n"
            i++
        }
        println(remedies_string)
        return remedies_string
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_welcome)
        var user_email = intent.getStringExtra("userEmail")
        var user = helper.getParticularUserData(user_email)
        var username = user.user_name
        editText.setText("Hi, "+username)
        var userHistoryArray = helper.getUserHistory(user_email)
        var recentdate=userHistoryArray.get(0).diagdate
        for(i in userHistoryArray){
            var d = i.did
            var x= i.email
            if (x.equals(user_email)) {
                recentdate=i.diagdate
                print(i.diagdate)
            }
        }
        var r_did : Int = 0
        var recentDate = recentdate
        for(i in userHistoryArray){
            var s = i.diagdate
            if (s==recentdate) {
                r_did = i.did
            }
        }
        var dis = helper.getParticularDiseaseData(r_did)

        editText2.setText("You had been most recently diagonised with "+ dis.d_name)
        editText3.setText("Is this disease Resolved? ")
        yesText.setOnClickListener {
            val intent = Intent(this, Symptoms::class.java)
            intent.putExtra("user_email", user_email)
            startActivity(intent)
        }
        noText.setOnClickListener {
            val intent = Intent(this, Remedies::class.java)
            var k = dis.d_id
            if(k!=null) {
                intent.putExtra("Passer", getRemedies(k))
                startActivity(intent)
            }
        }
    }
}