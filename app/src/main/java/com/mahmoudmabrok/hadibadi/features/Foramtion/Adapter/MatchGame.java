package com.mahmoudmabrok.hadibadi.features.Foramtion.Adapter;

/**
 * Created by Mahmoud on 9/11/2018.
 */
public class MatchGame {
    private String firstTeam;
    private String secondTeam;
    private int matchNum;

    public MatchGame() {
        matchNum = 1;
    }

    public MatchGame(String firstTeam, String secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }
}
