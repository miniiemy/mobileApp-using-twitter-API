package com.example.myminiie.mobileapp

import android.view.View
import kotlinx.android.synthetic.main.activity_showsearch.*
import org.json.JSONObject
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.ArrayList

/**
 * Created by myminiie on 27/3/2561.
 */
class myAPPInteractor() {
    private lateinit var text_search:String
    private lateinit var text_type:String

    public fun connectAPISearchUseCase(text_search : String, text_type:String, activity:ShowsearchActivity){

        //prepare data to Twitter API
        var result :String = ""
        var credentials = "WEVIRU5mNjRxSjFLRHA1UkRrckJtbmZ5MDp4NUpUYmFsbXFQVERLa2hDUldRd3NPYTFnWW5xZlA0ZzFVTzVyWFdERkNYNEhoanNKaw=="
        var URL = prepareURL(text_search,text_type)
        var connectAPI : myAPPEntity = myAPPEntity()
        connectAPI.setup(activity)
        connectAPI.execute(credentials,URL)



    }//end connectAPI
    public fun prepareURL(text_search: String,text_type: String):String{
        var setURL = URLEncoder.encode(text_search, "utf-8")
        var URL =  "https://api.twitter.com/1.1/search/tweets.json?q="+setURL+"&result_type="+text_type
        return URL
    }
    public fun sendJSON( jsonString:String,activity: ShowsearchActivity){

        if(jsonString.equals("ERROR")){
            activity.TextAlert.text = "Cannot connect with Twitter API."
        }
        else{
         var callback:myAPPPresenter= myAPPPresenter()
            callback.handleJSON(jsonString,activity)

        }

    }//end handleListView

}//endclass