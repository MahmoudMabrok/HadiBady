package com.mahmoudmabrok.hadibadi.features;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import com.mahmoudmabrok.hadibadi.R;
import com.mahmoudmabrok.hadibadi.features.hady.HadyActivity;
import com.mahmoudmabrok.hadibadi.features.LeagueActivity.LeagueActivity;

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
