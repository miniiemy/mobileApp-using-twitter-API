package com.example.myminiie.mobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatSpinner
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_showsearch.*
import java.util.*
import kotlin.collections.ArrayList
import android.widget.AdapterView


class ShowsearchActivity : AppCompatActivity() {
    private var num_items_page:Int = 5
    private var pagecount:Int=0
    private var increment:Int=0
    private var datalist = ArrayList<TweetObject>()
    private var listsort = ArrayList<TweetObject>()
    private var mystrings = arrayOf("Create Date","Name","Retweet","Favorite")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showsearch)
        var text_search: String = intent.getStringExtra("text_input")
        var text_type : String =intent.getStringExtra("type")
        var title:String ="Search result: "+text_search
        this.setTitle(title)
        ButtonNext.visibility =View.INVISIBLE
        ButtonPrevious.visibility=View.INVISIBLE

        spinner.adapter =ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,mystrings )
        //connectAPI
        var getData : myAPPPresenter = myAPPPresenter()
        getData.getDatafromTwitter(text_search,text_type,"search",this)
        setEvent(this)

    }
    private fun setEvent(activity: ShowsearchActivity){
        ButtonNext.setOnClickListener(){
            increment++
            handleListView(increment,activity)
            checkButtonEnable(activity)
        }//end ButtonNext

        ButtonPrevious.setOnClickListener(){
            increment--
            handleListView(increment,activity)
            checkButtonEnable(activity)
        }//end ButtonPrevious

        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                handleSort(mystrings[p2])
                increment =0
                checkButtonEnable(activity)
                handleListView(0,activity)
            }
        }//end spinner
    }
    private fun checkButtonEnable(activity: ShowsearchActivity){
        if(increment+1 == pagecount){
            activity.ButtonNext.isEnabled =false
        }
        else if(increment == 0){
            activity.ButtonPrevious.isEnabled = false
        }
        else{
            activity.ButtonNext.isEnabled =true
            activity.ButtonPrevious.isEnabled =true
        }
    }//end checkButtonEnable
    public fun handleList(list:ArrayList<TweetObject>,activity: ShowsearchActivity){

        datalist = list
        listsort = list
        handleSort("Create Date")
        var value = datalist.size%num_items_page
        value = if(value == 0) 0 else 1
        pagecount = datalist.size/num_items_page+value
        handleListView(0,activity)
        activity.progressBar.visibility = View.INVISIBLE
        activity.ButtonNext.visibility =View.VISIBLE
        activity.ButtonPrevious.visibility=View.VISIBLE
        checkButtonEnable(activity)

    }//end handleList

    private fun handleListView( number :Int,activity: ShowsearchActivity){
        var number_page = number
        var listShow = ArrayList<TweetObject>()
        var start:Int = number_page*num_items_page
        var count = start
        while(count<(start+num_items_page)){
            if(count<listsort.size){
                listShow.add(listsort.get(count))
            }
            else{break}
            count++
        }//end while

        activity.textPage.text = "Page "+(number_page+1)+" of "+pagecount

        var adapter = ListAdapter(activity,listShow)
        activity.ListView.adapter = adapter
    }//end handleListView

    public fun handleSort(type:String){
        if(type.equals("Retweet")){
            listsort = datalist.sortedWith(compareBy({ it.retweet_count})).toCollection(ArrayList<TweetObject>())
        }
        else if(type.equals("Favorite")){
            listsort = datalist.sortedWith(compareBy({ it.favorite_count})).toCollection(ArrayList<TweetObject>())
        }
        else if(type.equals("Create Date")){
            listsort = datalist.sortedWith(compareBy({ it.createDate})).toCollection(ArrayList<TweetObject>())
        }
        else if(type.equals("Name")){
            listsort = datalist.sortedWith(compareBy({ it.name})).toCollection(ArrayList<TweetObject>())
        }
        else{listsort = datalist}
    }
}
