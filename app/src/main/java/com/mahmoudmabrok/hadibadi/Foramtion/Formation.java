package com.mahmoudmabrok.hadibadi.Foramtion;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.mahmoudmabrok.hadibadi.Datalayer.DataRepo;
import com.mahmoudmabrok.hadibadi.Foramtion.Adapter.Match;
import com.mahmoudmabrok.hadibadi.Foramtion.Adapter.matchAdapter;
import com.mahmoudmabrok.hadibadi.LeagueActivity.LeagueActivity;
import com.mahmoudmabrok.hadibadi.R;
import com.mahmoudmabrok.hadibadi.Util.Operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Formation extends AppCompatActivity {

    ProgressDialog progressDialog;

    private List<Match> list;
    private matchAdapter adapter;
    private RecyclerView recyclerView;
    private DataRepo dataRepo;
    private static final String TAG = "Formation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation);

        dataRepo = new DataRepo(getApplicationContext());
        initView();

    }

    int totalTeams = 0;
    private int stageNum = 0;

    private void initView() {

        initProgress();

        recyclerView = (RecyclerView) findViewById(R.id.rvMatch);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new matchAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        list = new ArrayList<>();

        fillAdapter();
    }

    private void fillAdapter() {

        List<String> teams;
        teams = new ArrayList<>(LeagueActivity.listPlayer);
        if (teams != null) {

            Collections.shuffle(teams);

            int loop = teams.size() / 2;
            Match match;
            String temp;
            int pos;
            for (int i = 0; i < loop; i++) {
                match = new Match();

                pos = Operation.getRandom(teams.size());
                temp = teams.remove(pos);
                match.setFirstTeam(temp);

                if (teams.size() == 1) {
                    pos = 0;
                } else {
                    pos = Operation.getRandom(teams.size());
                }
                temp = teams.remove(pos);
                match.setSecondTeam(temp);
                match.setMatchNum(++totalTeams);
                list.add(match);
            }

            if (teams.size() == 1) {
                match = new Match();
                match.setFirstTeam(teams.get(0));
                match.setMatchNum(++totalTeams);

                list.add(match);
            }

            //// TODO: 9/11/2018

            // Collections.shuffle(list);
            List<Match> vv = new ArrayList<>(list);

            //add first stage item header
            Match match1 = new Match();
            match1.setMatchNum(++stageNum);
            list.add(0, match1);


            //     show("ff %% " + vv.size());
            while (vv.size() > 1) {
                vv = completeLeageu(vv);
                //       show("%% " + vv.size());
            }

            //// TODO: 9/11/2018 last one
          //  show(list.get(list.size() - 1).getFirstTeam());
            adapter.setList(list);
            progressDialog.dismiss();
            //    show("$$ " + list.size());
        } else {
            progressDialog.dismiss();
            super.onBackPressed();
        }

    }

    private List<Match> completeLeageu(List<Match> ll) {
        List<Match> temp = new ArrayList<>(ll);
        Collections.shuffle(temp);
        List<Match> tempToAdd = new ArrayList<>();
        String xx = "الفائز من ";

        int loops = temp.size() / 2;
        Match match, oldMatch;
        int pos;
        String tmp;
        for (int i = 0; i < loops; i++) {
            match = new Match();
            pos = Operation.getRandom(temp.size());
            oldMatch = temp.remove(pos);
            if (oldMatch.getSecondTeam() != null) {
                tmp = xx + oldMatch.getMatchNum();
            } else {
                tmp = oldMatch.getFirstTeam();
            }

            match.setFirstTeam(tmp);
            pos = Operation.getRandom(temp.size());
            oldMatch = temp.remove(pos);
            if (oldMatch.getSecondTeam() != null) {
                tmp = xx + oldMatch.getMatchNum();
            } else {
                tmp = oldMatch.getFirstTeam();
            }
            match.setSecondTeam(tmp);
            match.setMatchNum(++totalTeams);

            tempToAdd.add(match);
        }

        if (temp.size() == 1) {
            match = new Match();

            oldMatch = temp.get(0);
            if (oldMatch.getSecondTeam() != null) {
                tmp = xx + oldMatch.getMatchNum();
            } else {
                tmp = oldMatch.getFirstTeam();
            }
            match.setFirstTeam(tmp);
            match.setMatchNum(++totalTeams);

            tempToAdd.add(match);
        }

        //// TODO: 9/12/2018
        //here a new stage to add
        if (tempToAdd.size() > 0) {
            Match stageMatch = new Match();
            stageMatch.setMatchNum(++stageNum);
            list.add(stageMatch);
        }
        for (Match match1 : tempToAdd) {
            if (tempToAdd.size() == 1) {
          //      show("^^ " + match1.getFirstTeam());
            }
            list.add(match1);
            Log.d(TAG, "before size " + ll.size() + "listToAdd: " + match1.getFirstTeam() + " vs " + match1.getMatchNum());
        }
        //list.addAll(tempToAdd) ; // cause unOrderd List

        return tempToAdd;
    }

    private void displayTeams(List<String> teams) {
        for (String s : teams) {
            show(s);
        }
    }

    private void show(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void initProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Load matched Wait");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);

        progressDialog.show();
    }
}
