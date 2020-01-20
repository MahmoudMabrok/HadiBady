package com.mahmoudmabrok.hadibadi.Hady;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
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

    /**
     * select one player from list and draw it out
     * @param view
     */
    public void Select(View view) {
        int size = adapter.getItemCount();
        if (size > 1) {
            int rnd = Operation.getRandom(size);
            String playerName = adapter.drawOut(rnd);
            // display nam with dialog
            Alert.getDialog(HadyActivity.this, playerName).show();
        } else {
       //     showMessage("Add Player,(At least 2) ");
            edPlayer.setError("Add More Players");
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
            edPlayer.setText("");
        } else {
            edPlayer.setError("please add player name");
        }
    }
}
