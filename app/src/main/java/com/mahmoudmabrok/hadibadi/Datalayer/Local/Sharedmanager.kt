package com.mahmoudmabrok.hadibadi.Datalayer.Local

import android.content.Context
import android.content.SharedPreferences
import java.util.*

/**
 * Created by Mahmoud on 9/11/2018.
 */
class Sharedmanager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("teams", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor

    fun setTeams(teams: List<String>, name: String) {
        val set: MutableSet<String> = TreeSet()
        set.addAll(teams)
        editor.putStringSet(name, set)
    }

    fun getTeams(name: String): List<String> {
        val list = mutableListOf<String>()
        val temasSet = sharedPreferences.getStringSet(name, null)
        temasSet?.let { list.addAll(it.toList()) }
        return list
    }

    init {
        editor = sharedPreferences.edit()
    }
}