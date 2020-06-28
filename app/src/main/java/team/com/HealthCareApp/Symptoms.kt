package team.com.HealthCareApp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.symptoms.*

class Symptoms : AppCompatActivity(){
    internal var helper = UserDatabaseHandler(this)
    var predicted_disease=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.symptoms)
        var userEmail = intent.getStringExtra("user_email")
        var symList = ArrayList<Int>()
        val submit = findViewById<TextView>(R.id.submitText) as TextView
        submit.setOnClickListener{
            if(s1.isChecked)
            {println(1)
             val symp=helper.getParticularSymptomData(s1.text.toString())
                println(symp.s_id)
                symList.add(symp.s_id)
            }
            if(s2.isChecked)
            {
                val symp=helper.getParticularSymptomData(s2.text.toString())
                symList.add(symp.s_id)
            }
            if(s3.isChecked)
            {
                val symp=helper.getParticularSymptomData(s3.text.toString())
                symList.add(symp.s_id)
            }
            if(s4.isChecked)
            {
                val symp=helper.getParticularSymptomData(s4.text.toString())
                symList.add(symp.s_id)
            }
            if(s5.isChecked)
            {
                val symp=helper.getParticularSymptomData(s5.text.toString())
                symList.add(symp.s_id)
            }
            if(s6.isChecked)
            {
                val symp=helper.getParticularSymptomData(s6.text.toString())
                symList.add(symp.s_id)
            }
            if(s7.isChecked)
            {
                val symp=helper.getParticularSymptomData(s7.text.toString())
                symList.add(symp.s_id)
            }
            if(s8.isChecked)
            {
                val symp=helper.getParticularSymptomData(s8.text.toString())
                symList.add(symp.s_id)
            }
            if(s9.isChecked)
            {
                val symp=helper.getParticularSymptomData(s9.text.toString())
                symList.add(symp.s_id)
            }
            if(s10.isChecked)
            {
                val symp=helper.getParticularSymptomData(s10.text.toString())
                symList.add(symp.s_id)
            }
            if(s11.isChecked)
            {
                val symp=helper.getParticularSymptomData(s11.text.toString())
                symList.add(symp.s_id)
            }
            if(s12.isChecked)
            {
                val symp=helper.getParticularSymptomData(s12.text.toString())
                symList.add(symp.s_id)
            }
            if(s13.isChecked)
            {
                val symp=helper.getParticularSymptomData(s13.text.toString())
                symList.add(symp.s_id)
            }
            if(s14.isChecked)
            {
                val symp=helper.getParticularSymptomData(s14.text.toString())
                symList.add(symp.s_id)
            }
            if(s15.isChecked)
            {
                val symp=helper.getParticularSymptomData(s15.text.toString())
                symList.add(symp.s_id)
            }
            if(s16.isChecked)
            {
                val symp=helper.getParticularSymptomData(s16.text.toString())
                symList.add(symp.s_id)
            }
            if(s17.isChecked)
            {
                val symp=helper.getParticularSymptomData(s17.text.toString())
                symList.add(symp.s_id)
            }
            if(s18.isChecked)
            {
                val symp=helper.getParticularSymptomData(s18.text.toString())
                symList.add(symp.s_id)
            }
            if(s19.isChecked)
            {
                val symp=helper.getParticularSymptomData(s19.text.toString())
                symList.add(symp.s_id)
            }
            if(s20.isChecked)
            {
                val symp=helper.getParticularSymptomData(s20.text.toString())
                symList.add(symp.s_id)
            }
            if(s21.isChecked)
            {
                val symp=helper.getParticularSymptomData(s21.text.toString())
                symList.add(symp.s_id)
            }
            if(s22.isChecked)
            {
                val symp=helper.getParticularSymptomData(s22.text.toString())
                symList.add(symp.s_id)
            }
            if(s23.isChecked)
            {
                val symp=helper.getParticularSymptomData(s23.text.toString())
                symList.add(symp.s_id)
            }
            if(s24.isChecked)
            {
                val symp=helper.getParticularSymptomData(s24.text.toString())
                symList.add(symp.s_id)
            }
            if(s25.isChecked)
            {
                val symp=helper.getParticularSymptomData(s25.text.toString())
                symList.add(symp.s_id)
            }

            var disMap:HashMap<Int,Int> = HashMap<Int,Int>()
            for(i in symList) {
                val disease= helper.getDiseaseId(i)
                var j = disMap.get(disease)
                disMap.put(disease,if (j==null) 1 else j+1)
            }


            var max=Int.MIN_VALUE
            var res = ArrayList<String>()
            var symp= arrayOf<Int>(0,4,5,6,5,5)
            for(i in disMap){
                var diseaseCnt=0
                diseaseCnt = i.value
                var percent=1
                percent = (diseaseCnt * 100)/symp[i.key]
                if(percent > max){
                    max= percent
                    predicted_disease = i.key
                }
                var disease = helper.getParticularDiseaseData(i.key)
                res.add(disease.d_name+" with "+percent.toString()+"% chance")
            }
            if(predicted_disease!=null){
                helper.insertUserHistory(userEmail,predicted_disease)
            }
            var resultString: String = "You have been predicted with: \n"
            for(i in res){
                resultString = resultString + i+" \n"
            }
            println(resultString)
            fun getRemedies():String {
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

            //resultString+="\n"+remedies_string;
            val intent = Intent(this,DetectedDis::class.java)
            intent.putExtra("Pass", resultString)
            intent.putExtra("Passer",getRemedies())
            startActivity(intent)
        }
    }





}
