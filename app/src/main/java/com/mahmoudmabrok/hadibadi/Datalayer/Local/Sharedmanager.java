package com.mahmoudmabrok.hadibadi.Datalayer.Local;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Mahmoud on 9/11/2018.
 */
public class Sharedmanager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Sharedmanager(Context context) {
        sharedPreferences = context.getSharedPreferences("teams", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setTeams(List<String> teams) {
        Set<String> set = new TreeSet<>();
        set.addAll(teams);
        editor.putStringSet("teams", set);
    }

    public List<String> getTeamList() {
        List<String> list = new ArrayList<>();
        try {
            list.addAll(sharedPreferences.getStringSet("teams", null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
