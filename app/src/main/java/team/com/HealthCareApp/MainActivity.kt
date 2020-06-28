package team.com.HealthCareApp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.MotionEvent


class MainActivity : AppCompatActivity() {

    var x1 = 0.0
    var x2 = 0.0
    var y1 = 0.0
    var y2 = 0.0

    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x.toDouble()
                y1 = touchEvent.y.toDouble()
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x.toDouble()
                y2 = touchEvent.y.toDouble()
                if (x1 > x2) {
                    val i = Intent(this, Activity2::class.java)
                    startActivity(i)
                } else if (x1 !== x2) finish()
            }
        }
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(team.com.HealthCareApp.R.layout.activity_main)
    }
}
