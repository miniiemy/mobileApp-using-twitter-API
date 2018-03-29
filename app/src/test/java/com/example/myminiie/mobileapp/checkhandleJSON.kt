package com.example.myminiie.mobileapp
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.json.JSONObject


/**
 * Created by myminiie on 29/3/2561.
 */

class checkhandleJSON {
    private lateinit var json:String
    @Before
    @Throws(Exception::class)
    fun setUp() {
        var t = TestHelper()
        json = t.getStringFromFile("C:\\Users\\myminiie\\Desktop\\Git\\mobileApp-using-twitter-API\\app\\src\\test\\java\\com\\example\\myminiie\\mobileapp\\test.json")
    }

    @Test
    fun checkTextInput1() {
      var test = myAPPPresenter()
      var activity = ShowsearchActivity()
       //var result = test.handleJSON(json,activity)
        assertEquals("NASA","NASA")

    }
}