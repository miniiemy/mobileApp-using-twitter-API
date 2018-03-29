package com.example.myminiie.mobileapp

import android.view.View
import kotlinx.android.synthetic.main.activity_showsearch.*
import java.net.URLEncoder

/**
 * Created by myminiie on 29/3/2561.
 */
class myAPPPresenter(){

    public fun getDatafromTwitter(text_search : String, text_type:String,type:String, activity:ShowsearchActivity){
        if(type.equals("search")){
            var connectUsecase : myAPPInteractor = myAPPInteractor()
            connectUsecase.connectAPISearchUseCase(text_search,text_type,activity)

        }else{
            activity.TextAlert.text = "Cannot work in your method."
            activity.progressBar.visibility = View.INVISIBLE
        }

    }//end getDatafromTwitter
    public fun handleListView(list:ArrayList<TweetObject>,activity: ShowsearchActivity){

        val adapter = ListAdapter(activity,list)
        activity.ListView.adapter =adapter
        activity.progressBar.visibility = View.INVISIBLE

    }//end handleListView

}//end class