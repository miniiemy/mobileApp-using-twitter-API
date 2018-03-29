package com.example.myminiie.mobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_showsearch.*


class ShowsearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showsearch)
        var text_search: String = intent.getStringExtra("text_input")
        var text_type : String =intent.getStringExtra("type")
        var title:String ="Search result: "+text_search
        this.setTitle(title)

        //connectAPI
        var getData : myAPPPresenter = myAPPPresenter()
        getData.getDatafromTwitter(text_search,text_type,"search",this)





    }

}
