package com.mahmoudmabrok.hadibadi.Hady;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mahmoudmabrok.hadibadi.Hady.Adapter.CustomAdapter;
import com.mahmoudmabrok.hadibadi.R;
import com.mahmoudmabrok.hadibadi.Util.Alert;
import com.mahmoudmabrok.hadibadi.Util.Operation;

import java.util.ArrayList;
import java.util.List;

public class HadyActivity extends AppCompatActivity {


    EditText edPlayer;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<String> listPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hady);

        initView();

    }

    private void initView() {
        edPlayer = (EditText) findViewById(R.id.edHadyName);

        recyclerView = (RecyclerView) findViewById(R.id.rvHadyList);
        adapter = new CustomAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        listPlayer = new ArrayList<>();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
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

    public void Select(View view) {
        int size = listPlayer.size();
        if (size > 0) {
            //  adapter.setList(listPlayer);
            int randmo = Operation.getRandom(size);
            String s = listPlayer.get(randmo);
            String cc = getString(R.string.drawn, s);
            // showMessage(s);
            Alert.getDialog(HadyActivity.this, cc).show();
        } else {
            showMessage("Add Player ");
        }
    }

    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void addPlayer(View view) {
        Operation.hideInputKeyboard(this);
        String s = edPlayer.getText().toString();
        if (!TextUtils.isEmpty(s)) {
            listPlayer.add(s);
            adapter.addItem(s);
            //adapter.setList(listPlayer);
            edPlayer.setText("");
        } else {
            showMessage("please add Player ");
        }
    }
}
