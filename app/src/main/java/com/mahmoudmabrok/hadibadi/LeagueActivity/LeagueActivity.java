package com.mahmoudmabrok.hadibadi.LeagueActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mahmoudmabrok.hadibadi.Datalayer.DataRepo;
import com.mahmoudmabrok.hadibadi.Foramtion.Formation;
import com.mahmoudmabrok.hadibadi.Hady.Adapter.CustomAdapter;
import com.mahmoudmabrok.hadibadi.R;
import com.mahmoudmabrok.hadibadi.Util.Operation;

import java.util.ArrayList;
import java.util.List;

public class LeagueActivity extends AppCompatActivity {

    public static List<String> listPlayer;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    EditText edPlayer;
    private DataRepo dataRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);
        init();
        dataRepo = new DataRepo(getApplicationContext());
     //   initList();

    }

    private void initList() {
        listPlayer.add("برشلونة ");
        listPlayer.add("الريال");
        listPlayer.add("روما");
        listPlayer.add("ايفرتون");
        listPlayer.add("بايرن");
        listPlayer.add("ليفربول");
        listPlayer.add("يوفينتوس");
        listPlayer.add("اتليتكو مدريد ");
        listPlayer.add("تشيلسي");
        listPlayer.add("باريس");
        listPlayer.add("مان سيتى");
        //listPlayer.add("ليون") ;
        adapter.setList(listPlayer);
    }

    private void init() {

        edPlayer = (EditText) findViewById(R.id.edTeamName);

        recyclerView = (RecyclerView) findViewById(R.id.rvTeams);
        adapter = new CustomAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        listPlayer = new ArrayList<>();


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                listPlayer.remove(pos);
                adapter.removeItem(pos);
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void add(View view) {
        Operation.hideInputKeyboard(this);
        String s = edPlayer.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            listPlayer.add(s);
            adapter.addItem(s);
            edPlayer.setText("");
        } else {
            showMessage("Error , Enter Team Name ");
        }
    }

    public void Go(View view) {
        // dataRepo.setTeams(listPlayer);
        if (listPlayer.size() > 2) {
            Intent intent = new Intent(LeagueActivity.this, Formation.class);
            startActivity(intent);
        } else {
            showMessage("Please Insert Teams more than 2 teams ");
        }
    }


    private void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
