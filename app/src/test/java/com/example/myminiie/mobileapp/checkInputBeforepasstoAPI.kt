package com.example.myminiie.mobileapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class checkInputBeforepasstoAPI {
    @Test
    fun checkTextInput1() {
        var text_input:String = "nasa"
        var text_type:String = "mixed"
        var expected : String  ="https://api.twitter.com/1.1/search/tweets.json?q=nasa&result_type=mixed"
        var obj : myAPPInteractor = myAPPInteractor()
        var result : String = obj.prepareURL(text_input,text_type)
        assertEquals(expected,result)

    }
    @Test
    fun checkTextInput2() {
        var text_input:String = "#nasa"
        var text_type:String = "mixed"
        var expected : String  ="https://api.twitter.com/1.1/search/tweets.json?q=%23nasa&result_type=mixed"
        var obj : myAPPInteractor = myAPPInteractor()
        var result : String = obj.prepareURL(text_input,text_type)
        assertEquals(expected,result)
    }
    @Test
    fun checkTextInput3() {
        var text_input:String = "\"happy hour\" :)"
        var text_type:String = "mixed"
        var expected : String  ="https://api.twitter.com/1.1/search/tweets.json?q=%22happy+hour%22+%3A%29&result_type=mixed"
        var obj : myAPPInteractor = myAPPInteractor()
        var result : String = obj.prepareURL(text_input,text_type)
        assertEquals(expected,result)
    }
    @Test
    fun checkTextType1() {
        var text_input:String = "nasa"
        var text_type:String = "popular"
        var expected : String  ="https://api.twitter.com/1.1/search/tweets.json?q=nasa&result_type=popular"
        var obj : myAPPInteractor = myAPPInteractor()
        var result : String = obj.prepareURL(text_input,text_type)
        assertEquals(expected,result)
    }
    @Test
    fun checkTextType2() {
        var text_input:String = "nasa"
        var text_type:String = "recent"
        var expected : String  ="https://api.twitter.com/1.1/search/tweets.json?q=nasa&result_type=recent"
        var obj : myAPPInteractor = myAPPInteractor()
        var result : String = obj.prepareURL(text_input,text_type)
        assertEquals(expected,result)
    }

}
