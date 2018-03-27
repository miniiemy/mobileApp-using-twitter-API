package com.example.myminiie.mobileapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatTextView
import android.view.View
import android.widget.TextView
import com.example.myminiie.mobileapp.R.id.async
import kotlinx.android.synthetic.main.activity_showsearch.*
import org.w3c.dom.Text


class ShowsearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showsearch)
        var text_search: String = intent.getStringExtra("text_input")
        var text_type : String =intent.getStringExtra("type")
        var title:String ="Search result: "+text_search
        this.setTitle(title)
        var connectAPI:APIInteractor = APIInteractor()
        connectAPI.connectAPISearch(text_search,text_type,this)






    }
    public fun handleView(list:ArrayList<TweetObject>,context: ShowsearchActivity){

       val adapter = ListAdapter(context,list)
        context.ListView.adapter =adapter
        context.progressBar.visibility =View.INVISIBLE


       // val textV = findViewById<TextView>(R.id.textShow)
        //textV.text = "mymine"
//textShow.text = "mymine"
    }
}
