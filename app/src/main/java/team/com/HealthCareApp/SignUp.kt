package team.com.HealthCareApp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.signup.*

class SignUp : AppCompatActivity() {
    internal var helper = UserDatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        fun validate(): Boolean {
            if (userText.text.isEmpty()) {
                Toast.makeText(this@SignUp, "Enter Name", Toast.LENGTH_SHORT).show()
                return false
            } else if (passText.text.isEmpty()) {
                Toast.makeText(this@SignUp, "Enter Password", Toast.LENGTH_SHORT).show()
                return false
            } else if (ageText.text.isEmpty()) {
                Toast.makeText(this@SignUp, "Enter Age", Toast.LENGTH_SHORT).show()
                return false
            } else if (emailText.text.isEmpty()) {
                Toast.makeText(this@SignUp, "Enter Email", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }
        var gen: String = "male"
        genderGroup.setOnCheckedChangeListener{group, checkid->
            if(R.id.maleGender==checkid){
                gen = "male"
            }
            else if(R.id.femaleGender==checkid){
                gen = "female"
            }
            else {
                gen = "Prefer not to say"
            }
        }
        val click_register = findViewById<TextView>(R.id.registerText) as TextView
        click_register.setOnClickListener {
            var flag=true
            if(validate()){
                    var user = helper.getParticularUserData(emailText.text.toString())
                    if(user.email.equals(emailText.text.toString())) {
                        Toast.makeText(this@SignUp, "User Already Registered!!", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        helper.insertData(userText.text.toString(), passText.text.toString(), Integer.parseInt(ageText.text.toString()), emailText.text.toString(), gen.toString())
                        Toast.makeText(this@SignUp, "Successfully Registered!!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }

                }


        }

    }
}
