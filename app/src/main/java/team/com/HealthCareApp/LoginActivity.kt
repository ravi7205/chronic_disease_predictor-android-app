package team.com.HealthCareApp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.signup.passText


class LoginActivity : AppCompatActivity() {
    internal var helper = UserDatabaseHandler(this)
    var cnt: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

     val click_login = findViewById<TextView>(R.id.loginText) as TextView
        click_login.setOnClickListener{
            if(cnt==0) {
                helper.addData()
                cnt++
            }
            val usr_txt = findViewById<TextView>(R.id.userText) as TextView
                var userInfo = UserInfo();
               userInfo = helper.getParticularUserData(usr_txt.text.toString())
                val pass = userInfo.password;
            var user_email = userInfo.email;
            if(pass.equals(passText.text.toString())){
                Toast.makeText(this@LoginActivity, "Successful", Toast.LENGTH_SHORT).show()
                var cnt = helper.getUserDiseaseCount(usr_txt.text.toString())
                System.out.println("count "+cnt)
                var flag=0
                if(cnt!=0) {
                    val intent = Intent(this, UserWelcome::class.java)
                    intent.putExtra("userEmail", user_email)
                    startActivity(intent)
                }
                else{
                    val intent = Intent(this, UserWelcomeNoDisease::class.java)
                    intent.putExtra("userEmail", user_email)
                    startActivity(intent)
                }
            }
            else
                Toast.makeText(this@LoginActivity, "UnSuccessful", Toast.LENGTH_SHORT).show()
        }
        val click_register = findViewById<TextView>(R.id.registerText) as TextView
        click_register.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

    }

}
