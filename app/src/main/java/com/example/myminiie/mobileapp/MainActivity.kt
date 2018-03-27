package com.example.myminiie.mobileapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatRadioButton
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var text_input:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setTitle("Search Twitter")
        //set initial page
        button.isEnabled = false
        var radioAll = findViewById<RadioButton>(R.id.radio_All) as AppCompatRadioButton
        radioGroup.check(radioAll.id)

        setEvent()
    }
    private fun setEvent(){
        var textEmpty : Boolean
        var type : String = "mixed"

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textEmpty = p0.isNullOrEmpty()||p0.isNullOrBlank()
                if(!textEmpty){button.isEnabled = true}
                else {button.isEnabled =false}

            }
        })

         radioGroup.setOnCheckedChangeListener {
              radioGroup, checkedId ->
              when (checkedId) {
                  R.id.radio_All -> type = "mixed"
                  R.id.radio_Recent -> type = "recent"
                  R.id.radio_Popular -> type ="popular"
              }
          }

        button.setOnClickListener(){

            text_input = editText.text.toString()
            val intent = Intent(this,ShowsearchActivity::class.java)
            intent.putExtra("text_input",text_input)
            intent.putExtra("type",type)
            startActivity(intent)
            editText.text.clear()
            var radioAll = findViewById<RadioButton>(R.id.radio_All) as AppCompatRadioButton
            radioGroup.check(radioAll.id)
        }
    }
}
