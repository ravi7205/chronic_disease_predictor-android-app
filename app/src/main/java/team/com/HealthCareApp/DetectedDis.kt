package team.com.HealthCareApp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.detected_disease.*


class DetectedDis : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detected_disease)
                editText.setText(intent.getStringExtra("Pass"))
        editText.setEnabled(false)
        remedies.setOnClickListener {
            var remedies_str = intent.getStringExtra("Passer")
            val intent = Intent(this, Remedies::class.java)
            intent.putExtra("Passer", remedies_str)
            startActivity(intent)
        }

    }
}