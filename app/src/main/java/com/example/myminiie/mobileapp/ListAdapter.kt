package com.example.myminiie.mobileapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by myminiie on 27/3/2561.
 */
class ListAdapter(val context: ShowsearchActivity, val list: ArrayList<TweetObject>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false)
        val tweetName = view.findViewById<TextView>(R.id.tweet_name) as AppCompatTextView
        val tweetScreenName = view.findViewById<TextView>(R.id.tweet_screenname) as AppCompatTextView
        val tweetText = view.findViewById<TextView>(R.id.tweet_text) as AppCompatTextView
        val tweetRetweet = view.findViewById<TextView>(R.id.tweet_retweet) as AppCompatTextView
        val tweetFavorite = view.findViewById<TextView>(R.id.tweet_favorite) as AppCompatTextView
        val tweetTime = view.findViewById<TextView>(R.id.tweet_date) as AppCompatTextView
        val tweetpic = view.findViewById<ImageView>(R.id.tweet_image) as AppCompatImageView

        tweetName.text =list[position].name
        tweetScreenName.text =list[position].screen_name
        tweetText.text = list[position].text
        tweetRetweet.text = list[position].retweet_count.toString()
        tweetFavorite.text = list[position].favorite_count.toString()
        tweetTime.text = list[position].createDate.toString()

        //for retrieve image from url to show in profile image
       DownloadImageTask(tweetpic).execute(list[position].profile_image_url)



        return view
    }
    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }


}
class DownloadImageTask(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {
    private var image: Bitmap? = null

    override fun doInBackground(vararg urls: String): Bitmap? {
        val urldisplay = urls[0]
        try {
            val img = java.net.URL(urldisplay).openStream()
            image = BitmapFactory.decodeStream(img)
        } catch (e: Exception) {
            image = null
        }

        return image
    }//end doInBackground

    @SuppressLint("NewApi")
    override fun onPostExecute(result: Bitmap?) {
        if (result != null) {
            imageView.setImageBitmap(result)
        }
    }//end onPostExecute
}