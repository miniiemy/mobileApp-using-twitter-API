package com.example.myminiie.mobileapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_showsearch.*
import java.util.*


class ShowsearchActivity : AppCompatActivity() {
    private var num_items_page:Int = 5
    private var pagecount:Int=0
    private var increment:Int=0
    private var datalist = ArrayList<TweetObject>()
    private var listsort = ArrayList<TweetObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showsearch)
        var text_search: String = intent.getStringExtra("text_input")
        var text_type : String =intent.getStringExtra("type")
        var title:String ="Search result: "+text_search
        this.setTitle(title)
        ButtonNext.visibility =View.INVISIBLE
        ButtonPrevious.visibility=View.INVISIBLE

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
        }
        ButtonPrevious.setOnClickListener(){
            increment--
            handleListView(increment,activity)
            checkButtonEnable(activity)
        }
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
        var value = datalist.size%num_items_page
        value = if(value == 0) 0 else 1
        pagecount = datalist.size/num_items_page+value
        handleListView(0,activity)
        activity.progressBar.visibility = View.INVISIBLE
        activity.ButtonNext.visibility =View.VISIBLE
        activity.ButtonPrevious.visibility=View.VISIBLE
        checkButtonEnable(activity)


    }//end handleList
    private fun handleListView(number :Int,activity: ShowsearchActivity){


        var listShow = ArrayList<TweetObject>()

        var start:Int = number*num_items_page
        var count = start
        while(count<(start+num_items_page)){
            if(count<listsort.size){
                listShow.add(listsort.get(count))
            }
            else{break}
            count++
        }//end while

        activity.textPage.text = "Page "+(number+1)+" of "+pagecount

        var adapter = ListAdapter(activity,listShow)
        activity.ListView.adapter = adapter
    }//end handleListView

}
