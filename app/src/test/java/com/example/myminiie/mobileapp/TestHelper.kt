package com.example.myminiie.mobileapp
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * Created by myminiie on 29/3/2561.
 */
class TestHelper {
    private fun convertStreamToString(input: InputStream): String {
        val reader = BufferedReader(InputStreamReader(input))
        val sb = StringBuilder()

        var line = reader.readLine()
        while (line != null) {
            sb.append(line)

            line = reader.readLine()
        }

        reader.close()
        return sb.toString()
    }

    public fun getStringFromFile(filePath: String): String {
        //val fileName = System.getProperty("user.dir") + "/app/src/test/data/"
        val stream = FileInputStream(filePath )
        val ret = convertStreamToString(stream)
        stream.close()
        return ret.toString()
    }
}