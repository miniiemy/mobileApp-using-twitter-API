package com.example.myminiie.mobileapp

import android.view.View
import kotlinx.android.synthetic.main.activity_showsearch.*
import org.json.JSONObject
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.ArrayList

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
    public fun handleJSON(jsonString:String,activity: ShowsearchActivity){


            //handle JSON to arraylist of TweetOBject
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
                        "@"+jsonObjectUser.getString("screen_name"),
                        jsonObjectTemp.getString("text"),
                        jsonObjectUser.getString("profile_image_url"),
                        jsonObjectTemp.getInt("retweet_count"),
                        jsonObjectTemp.getInt("favorite_count"),
                        timenew


                ))
                count++

            }//end while
            if(list.size<=0){

                activity.TextAlert.text = "Cannot find your input."
                activity.progressBar.visibility = View.INVISIBLE
            }
            else{

                activity.handleList(list,activity)
            }



    }//end handleJSON

}//end class