package team.com.HealthCareApp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.detected_disease.*
import kotlinx.android.synthetic.main.detected_disease.editText
import kotlinx.android.synthetic.main.remedies.*

class Remedies : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remedies)
        editText.setText(intent.getStringExtra("Passer"))
        editText.setEnabled(false)
        logout.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}