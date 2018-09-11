package com.mahmoudmabrok.hadibadi.Foramtion;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mahmoudmabrok.hadibadi.Datalayer.DataRepo;
import com.mahmoudmabrok.hadibadi.Foramtion.Adapter.Match;
import com.mahmoudmabrok.hadibadi.Foramtion.Adapter.matchAdapter;
import com.mahmoudmabrok.hadibadi.R;
import com.mahmoudmabrok.hadibadi.Util.Operation;

import java.util.List;

public class Formation extends AppCompatActivity {

    ProgressDialog progressDialog;

    private List<Match> list;
    private matchAdapter adapter;
    private RecyclerView recyclerView;
    private DataRepo dataRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation);

        dataRepo = new DataRepo(getApplicationContext());
        initView();
        initProgress();


    }

    private void initView() {

        initProgress();

        recyclerView = (RecyclerView) findViewById(R.id.rvMatch);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new matchAdapter();
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        fillAdapter();
    }

    private void fillAdapter() {
        List<String> teams = dataRepo.getTeamList();
        if (teams != null) {
            int loop = teams.size() / 2;
            Match match;
            String temp;
            int pos;
            for (int i = 0; i < loop; i += 2) {
                match = new Match();

                pos = Operation.getRandom(teams.size() - 1);
                temp = teams.remove(pos);
                match.setFirstTeam(temp);

                pos = Operation.getRandom(teams.size() - 1);
                temp = teams.remove(pos);
                match.setSecondTeam(temp);

                match.setMatchNum(i + 1);
                list.add(match);
            }

            if (teams.size() == 1) {
                match = new Match();
                match.setFirstTeam(teams.get(0));
                match.setMatchNum(loop + 1);
            }

            adapter.setList(list);
            progressDialog.dismiss();
        } else {
            progressDialog.dismiss();
            super.onBackPressed();
        }

    }

    private void initProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Load matched Wait");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);

        progressDialog.show();
    }
}
