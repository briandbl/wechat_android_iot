package com.ubtech.messageparser

import android.provider.ContactsContract
import com.beust.klaxon.Klaxon

/**
 * @作者：brian.li
 * @日期:  2019/1/9 11:28
 * @描述:
 */

object  MessageParser{

    fun parseMessage(json_message:String): DataContent {
        var content= DataContent();
        var result=Klaxon().parse<DataContent>(json_message)
        content.data= result !!.data;
        return content
    }




}
