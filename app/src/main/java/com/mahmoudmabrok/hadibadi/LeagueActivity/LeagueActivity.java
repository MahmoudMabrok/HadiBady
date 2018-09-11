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

import com.mahmoudmabrok.hadibadi.Datalayer.DataRepo;
import com.mahmoudmabrok.hadibadi.Foramtion.Formation;
import com.mahmoudmabrok.hadibadi.Hady.Adapter.CustomAdapter;
import com.mahmoudmabrok.hadibadi.R;

import java.util.ArrayList;
import java.util.List;

public class LeagueActivity extends AppCompatActivity {


    EditText edPlayer ;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<String> listPlayer;


    private DataRepo dataRepo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_league);

        init();
        dataRepo = new DataRepo(getApplicationContext());
    }

    private void init() {

        edPlayer = (EditText) findViewById(R.id.edTeamName);

        recyclerView = (RecyclerView) findViewById(R.id.rvTeams);
        adapter = new CustomAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        listPlayer = new ArrayList<>() ;


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback( 0 , ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition() ;
                listPlayer.remove(pos);
                adapter.removeItem(pos);
            }
        }).attachToRecyclerView(recyclerView);

    }

    public void add(View view) {
        String s =edPlayer.getText().toString();
        if (!TextUtils.isEmpty(s)){
            listPlayer.add(s) ;
            adapter.addItem(s);
            edPlayer.setText("");
        }
    }

    public void Go(View view) {
        dataRepo.setTeams(listPlayer);
        Intent intent = new Intent( this, Formation.class) ;
        startActivity(intent);
    }


}
