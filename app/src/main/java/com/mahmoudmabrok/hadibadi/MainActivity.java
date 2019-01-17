package com.mahmoudmabrok.hadibadi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mahmoudmabrok.hadibadi.Hady.HadyActivity;
import com.mahmoudmabrok.hadibadi.LeagueActivity.LeagueActivity;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openHady(View view) {
        intent = new Intent(this, HadyActivity.class);
        startActivity(intent);
    }

    public void OpENlIA(View view) {
        intent = new Intent(this, LeagueActivity.class);
        startActivity(intent);
    }
}
