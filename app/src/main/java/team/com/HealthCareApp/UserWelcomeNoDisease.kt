package team.com.HealthCareApp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import kotlinx.android.synthetic.main.user_welcome_no_dis.*

class UserWelcomeNoDisease : AppCompatActivity() {
    internal var helper = UserDatabaseHandler(this)

    var x1=0.0
    var y1=0.0
    var x2=0.0
    var y2=0.0


    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        var user_email = intent.getStringExtra("userEmail")
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x.toDouble()
                y1 = touchEvent.y.toDouble()
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x.toDouble()
                y2 = touchEvent.y.toDouble()
                if (x1 > x2) {
                    val intent = Intent(this, Symptoms::class.java)
                    intent.putExtra("user_email", user_email)
                    startActivity(intent)
                } else if (x1 !== x2) {
                    finish()
                }
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_welcome_no_dis)
        var user_email = intent.getStringExtra("userEmail")
        var user = helper.getParticularUserData(user_email)
        var username = user.user_name
        editText.setText("Hi "+username+" !!")
        editText2.setText("Feeling Uneasy!! Swipe Right")

    }
}