package com.mahmoudmabrok.hadibadi.Datalayer;

import android.content.Context;

import com.mahmoudmabrok.hadibadi.Datalayer.Local.Sharedmanager;

import java.util.List;

/**
 * Created by Mahmoud on 9/11/2018.
 */
public class DataRepo {

    private Sharedmanager sharedmanager;

    public DataRepo(Context context) {
        sharedmanager = new Sharedmanager(context);
    }


    public void setTeams(List<String> teams, String name) {
        sharedmanager.setTeams(teams, name);
    }

    public List<String> getTeamList(String name) {
        return sharedmanager.getTeamList(name);
    }
}
