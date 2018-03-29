package com.example.myminiie.mobileapp

import android.content.Context
import com.example.myminiie.mobileapp.R.id.async
import kotlinx.android.synthetic.main.activity_showsearch.*
import org.json.JSONObject
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.ArrayList

/**
 * Created by myminiie on 27/3/2561.
 */
class APIInteractor() {
    private lateinit var text_search:String
    private lateinit var text_type:String

    public fun connectAPISearch(text_search : String, text_type:String, context:ShowsearchActivity){

        var result :String = ""
        var credentials = "WEVIRU5mNjRxSjFLRHA1UkRrckJtbmZ5MDp4NUpUYmFsbXFQVERLa2hDUldRd3NPYTFnWW5xZlA0ZzFVTzVyWFdERkNYNEhoanNKaw=="
        var setURL = URLEncoder.encode(text_search, "utf-8")
        var URL =  "https://api.twitter.com/1.1/search/tweets.json?q="+setURL+"&result_type="+text_type
        var connectAPI : APITwitterEntity = APITwitterEntity()
       connectAPI.setup(context)
         connectAPI.execute(credentials,URL)



    }
    public fun handleJSON(jsonString:String,context: ShowsearchActivity){


        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("statuses")
        val list = ArrayList<TweetObject>()
        var count =0
        while(count<jsonArray.length()){
            val jsonObjectTemp = jsonArray.getJSONObject(count)
            val jsonObjectUser = jsonObjectTemp.getJSONObject("user")

            var twitter = "EEE MMM dd HH:mm:ss ZZZZZ yyyy"
            var sf = SimpleDateFormat(twitter)
            var time= sf.parse(jsonObjectTemp.getString("created_at")).toString()
            time.replace(" +0000","")
            var timeArray = time.split(" ")

            var timenew = timeArray[3]+" "+timeArray[2]+" "+timeArray[1]+" "+timeArray[5]

            list.add(TweetObject(
                    jsonObjectTemp.getString("id_str"),
                    jsonObjectUser.getString("name"),
                    jsonObjectTemp.getString("text"),
                    jsonObjectUser.getString("profile_image_url"),
                    jsonObjectTemp.getInt("retweet_count"),
                    jsonObjectTemp.getInt("favorite_count"),
                    timenew


            ))
            count++

        }
        var callback:ShowsearchActivity = ShowsearchActivity()
        callback.handleView(list,context)
    }

}