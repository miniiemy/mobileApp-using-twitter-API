package com.example.myminiie.mobileapp
import android.content.Context
import android.os.AsyncTask
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
/**
 * Created by myminiie on 27/3/2561.
 */
class APITwitterEntity: AsyncTask<String, String, String>(){
    private lateinit var activity:ShowsearchActivity
    public fun setup(activity: ShowsearchActivity){
        this.activity = activity
    }
    override fun doInBackground(vararg params: String?): String {
        var access_token :String=""
        var urlParameters = "grant_type=client_credentials"
        var urlParametersArray = urlParameters.toByteArray()
        //setup connection
        val post = URL("https://api.twitter.com/oauth2/token").openConnection() as HttpURLConnection
        post.doInput = true
        post.doOutput =true
        post.requestMethod = "POST"
        post.setRequestProperty("Host","api.twitter.com")
        post.setRequestProperty("User-Agent","1")
        post.setRequestProperty("Authorization","Basic " +params[0])
        post.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8")

        post.setRequestProperty("Content-Length",urlParametersArray.size.toString())
        post.useCaches = false

        //connecting
        post.connect()
        val postdata: ByteArray = urlParameters.toByteArray(Charsets.UTF_8)
        val wr: DataOutputStream = DataOutputStream(post.outputStream)
        wr.write(postdata)
        wr.flush()
        //wr.close()
        if(post.responseCode!=200){return "ERROR"}
        else{

            val reader: BufferedReader = BufferedReader(InputStreamReader(post.inputStream))
            var line = reader.readLine()
            val response : StringBuilder = StringBuilder()
            while(line != null){
                response.append(line)
                line = reader.readLine()
            }
            var jsonObjectDocument = JSONObject(response.toString())
            access_token = jsonObjectDocument.getString("token_type") + " "+ jsonObjectDocument.getString("access_token")
            post.disconnect()

            var text: String
            val connection = URL(params[1]).openConnection() as HttpURLConnection
            connection.setRequestProperty("Authorization",access_token)
            connection.setRequestProperty("Content-Type", "application/json")
            try{
                connection.connect()
                text=connection.inputStream.use { it.reader().use{reader-> reader.readText()} }
            }finally {
                connection.disconnect()
            }
            return text


        }

    }//end doinbackground
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        var callback : APIInteractor = APIInteractor()
        callback.handleJSON(result.toString(),activity)



    }


}
