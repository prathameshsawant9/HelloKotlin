package com.leo.hellokotlin

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.support.v7.app.AlertDialog
import android.widget.Toast

/**
 * Created by prathamesh on 10/5/17.
 */

fun getSPObj(context: Context, filename: String = "YourSPFileName",mode : Int = Context.MODE_PRIVATE )
        = context.getSharedPreferences(filename,mode)

fun Context.putStringSharedPreference(key : String, value : String){
    getSPObj(applicationContext).edit().putString(key,value).commit()
}

fun Context.getStringSharedPreference(key: String, defaultValue : String): String{
    return getSPObj(applicationContext).getString(key,defaultValue)
}

fun Context.putIntSharedPreference(key : String , value : Int){
    getSPObj(applicationContext).edit().putInt(key,value).commit()
}

fun Context.getIntSharedPreference(key: String, defaultValue : Int): Int{
    return getSPObj(applicationContext).getInt(key,defaultValue)
}

fun Context.showToast(message: String, duration : Int = Toast.LENGTH_SHORT){
    Toast.makeText(applicationContext,message,duration).show()
}

fun Context.isNetworkAvailable(): Boolean{
    var state = false
    try {
        var manager : ConnectivityManager? = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        state = manager?.activeNetworkInfo?.isAvailable ?: false
    }
    catch (e : Exception){
        e.printStackTrace()
    }
    return state
}

fun Context.showAlertDialog(title:String = "",
                            message:String = "",
                            neutralButton:String = "", neutralListerner : DialogInterface.OnClickListener? = null,
                            positiveButton:String = "", positiveListerner : DialogInterface.OnClickListener? = null,
                            negativeButton:String = "", negativeListerner : DialogInterface.OnClickListener? = null ): AlertDialog {

    var alertDialogBuilder = AlertDialog.Builder(this)

    with (alertDialogBuilder){
        if (title.isNotEmpty()) setTitle(title)
        if (message.isNotEmpty()) setMessage(message)
        if (neutralButton.isNotEmpty() && neutralListerner != null ) setNeutralButton( neutralButton, neutralListerner)
        if (positiveButton.isNotEmpty() && positiveListerner != null ) setPositiveButton( positiveButton, positiveListerner)
        if (negativeButton.isNotEmpty() && negativeListerner != null ) setNegativeButton( negativeButton, negativeListerner)
    }

    var alertDialog = alertDialogBuilder.create()
    alertDialog.show()

    return alertDialog
}

fun cancelAlertDialog(dialog : AlertDialog){
    try {
        dialog.cancel()
    }
    catch (e : Exception){
        // Catch or Print
        e.printStackTrace()
    }
}
